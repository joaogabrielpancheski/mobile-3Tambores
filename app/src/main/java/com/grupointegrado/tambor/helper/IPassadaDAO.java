package com.grupointegrado.tambor.helper;

import com.grupointegrado.tambor.model.Passada;
import com.grupointegrado.tambor.model.PassadaCompetidor;

import java.util.List;

public interface IPassadaDAO {
    public boolean salvar(Passada passada);
    public boolean buscar(Long id_com);
    public List<PassadaCompetidor> listar();
}
