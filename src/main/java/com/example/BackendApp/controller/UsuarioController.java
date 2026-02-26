package com.example.BackendApp.controller;

import com.example.BackendApp.model.Usuario;
import com.example.BackendApp.repository.UsuarioRepositorio;
import com.example.BackendApp.service.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        user.setEnabled(true);
        user.setVerificationToken(null);
        userRepository.save(user);

        return "Cuenta verificada correctamente";
    }
}