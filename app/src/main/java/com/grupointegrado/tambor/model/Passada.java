package com.grupointegrado.tambor.model;

import java.io.Serializable;

public class Passada implements Serializable {
    private Long id;
    private Double tempo;
    private Long id_com;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    public Long getId_com() {
        return id_com;
    }

    public void setId_com(Long id_com) {
        this.id_com = id_com;
    }
}
