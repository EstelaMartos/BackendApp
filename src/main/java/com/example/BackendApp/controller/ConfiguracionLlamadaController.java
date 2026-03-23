package com.example.BackendApp.controller;

import com.example.BackendApp.model.ConfiguracionLlamada;
import com.example.BackendApp.model.LineaConversacion;
import com.example.BackendApp.repository.ConfiguracionLlamadaRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuracion-llamada")
@CrossOrigin
public class ConfiguracionLlamadaController {

    private final ConfiguracionLlamadaRepositorio repositorio;

    public ConfiguracionLlamadaController(ConfiguracionLlamadaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ConfiguracionLlamada> guardar(@RequestBody ConfiguracionLlamada config) {

        repositorio.deleteByUsuarioId(config.getUsuarioId());
        repositorio.flush();

        // limpiar ids y asignar relación
        if (config.getLineas() != null) {
            for (LineaConversacion linea : config.getLineas()) {
                linea.setId(null);
                linea.setConfiguracion(config);
            }
        }

        ConfiguracionLlamada guardado = repositorio.save(config);

        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<ConfiguracionLlamada> obtener(@PathVariable Long usuarioId) {

        return repositorio.findByUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}