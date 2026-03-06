package com.example.BackendApp.controller;

import com.example.BackendApp.model.ContactoEmergencia;
import com.example.BackendApp.repository.ContactoEmergenciaRepositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactos")
@CrossOrigin
public class ContactoEmergenciaController {

    private final ContactoEmergenciaRepositorio repositorio;

    public ContactoEmergenciaController(ContactoEmergenciaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    public ContactoEmergencia guardar(@RequestBody ContactoEmergencia contacto) {
        return repositorio.save(contacto);
    }

    @GetMapping("/{usuarioId}")
    public List<ContactoEmergencia> obtenerContactos(@PathVariable Long usuarioId) {
        return repositorio.findByUsuarioId(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}