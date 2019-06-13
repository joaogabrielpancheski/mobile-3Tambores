package com.grupointegrado.tambor.model;

import java.io.Serializable;

public class PassadaCompetidor implements Serializable {
    private String tempoCompetidor;

    public String getTempoCompetidor() {
        return tempoCompetidor;
    }

    public void setTempoCompetidor(String tempoCompetidor) {
        this.tempoCompetidor = tempoCompetidor;
    }
}
