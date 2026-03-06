package com.example.BackendApp.model;

import jakarta.persistence.*;

@Entity
public class ContactoEmergencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private String nombre;

    private String telefono;

    public ContactoEmergencia() {}

    public ContactoEmergencia(Long usuarioId, String nombre, String telefono) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}