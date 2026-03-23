package com.example.BackendApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class LineaConversacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private int tiempoEspera;

    private int orden;

    @ManyToOne
    @JoinColumn(name = "configuracion_id")
    @JsonIgnore
    private ConfiguracionLlamada configuracion;

    public LineaConversacion() {}

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public int getOrden() {
        return orden;
    }

    public ConfiguracionLlamada getConfiguracion() {
        return configuracion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setConfiguracion(ConfiguracionLlamada configuracion) {
        this.configuracion = configuracion;
    }
}