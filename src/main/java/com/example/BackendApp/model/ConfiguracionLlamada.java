package com.example.BackendApp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "usuarioId")
)
public class ConfiguracionLlamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private String palabraAlerta;

    private String palabraCancelar;

    @OneToMany(
            mappedBy = "configuracion",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<LineaConversacion> lineas;

    public ConfiguracionLlamada() {}

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getPalabraAlerta() {
        return palabraAlerta;
    }

    public String getPalabraCancelar() {
        return palabraCancelar;
    }

    public List<LineaConversacion> getLineas() {
        return lineas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setPalabraAlerta(String palabraAlerta) {
        this.palabraAlerta = palabraAlerta;
    }

    public void setPalabraCancelar(String palabraCancelar) {
        this.palabraCancelar = palabraCancelar;
    }

    public void setLineas(List<LineaConversacion> lineas) {
        this.lineas = lineas;
    }
}