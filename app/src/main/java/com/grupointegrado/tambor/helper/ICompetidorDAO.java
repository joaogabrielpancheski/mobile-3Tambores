package com.grupointegrado.tambor.helper;

import com.grupointegrado.tambor.model.Competidor;

import java.util.List;

public interface ICompetidorDAO {
    public boolean salvar(Competidor competidor);
    public boolean atualizar(Competidor competidor);
    public boolean deletar(Competidor competidor);
    public List<Competidor> listar();
}
