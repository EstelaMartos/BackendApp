package com.example.BackendApp.controller;

import com.example.BackendApp.model.Usuario;
import com.example.BackendApp.repository.UsuarioRepositorio;
import com.example.BackendApp.service.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/autenticacion")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio userRepository;

    @Autowired
    private Servicio emailService;

    @PostMapping("/registro")
    public String registrar(@RequestBody Usuario user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email ya registrado";
        }

        user.setEnabled(false);
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);

        userRepository.save(user);

        emailService.sendVerificationEmail(user.getEmail(), token);

        return "Usuario creado. Revisa tu email.";
    }

    @GetMapping("/verificacion")
    public String verificr(@RequestParam String token) {

        Usuario user = userRepository.findByVerificationToken(token).orElse(null);

        if (user == null) {
            return "Token inválido";
        }

        user.setEnabled(true);                // activo la cuenta
        user.setVerificationToken(null);      // borro el token
        userRepository.save(user);            // guardo cambios

        return "Cuenta verificada correctamente";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario user) {

        Optional<Usuario> usuarioOpt = userRepository.findByEmail(user.getEmail());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.isEnabled()) {
            return ResponseEntity.status(403).body("Cuenta no verificada");
        }

        if (!usuario.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        return ResponseEntity.ok("Login correcto");
    }

    @GetMapping("/estado")
    public ResponseEntity<Boolean> estado(@RequestParam String email) {

        boolean enabled = userRepository.findByEmail(email)
                .map(Usuario::isEnabled)
                .orElse(false);

        return ResponseEntity.ok(enabled);
    }
}