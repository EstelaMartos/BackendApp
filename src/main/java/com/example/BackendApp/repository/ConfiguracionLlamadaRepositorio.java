package com.example.BackendApp.repository;

import com.example.BackendApp.model.ConfiguracionLlamada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracionLlamadaRepositorio
        extends JpaRepository<ConfiguracionLlamada, Long> {

    Optional<ConfiguracionLlamada> findByUsuarioId(Long usuarioId);

    void deleteByUsuarioId(Long usuarioId);
}