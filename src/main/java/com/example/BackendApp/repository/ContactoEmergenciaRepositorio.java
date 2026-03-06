package com.example.BackendApp.repository;

import com.example.BackendApp.model.ContactoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactoEmergenciaRepositorio extends JpaRepository<ContactoEmergencia, Long> {

    List<ContactoEmergencia> findByUsuarioId(Long usuarioId);

}