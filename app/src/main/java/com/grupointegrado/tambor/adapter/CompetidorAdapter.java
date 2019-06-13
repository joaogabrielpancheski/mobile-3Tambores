package com.grupointegrado.tambor.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grupointegrado.tambor.R;
import com.grupointegrado.tambor.model.Competidor;

import java.util.List;

public class CompetidorAdapter extends RecyclerView.Adapter<CompetidorAdapter.MyViewHolder> {

    private List<Competidor> listaCompetidores;

    public CompetidorAdapter(List<Competidor> lista) {
        this.listaCompetidores = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_competidor_adapter, parent, false);

        return new MyViewHolder(itemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Competidor competidor = listaCompetidores.get(i);
        myViewHolder.competidor.setText(competidor.getNome());

    }

    @Override
    public int getItemCount() {
        return this.listaCompetidores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView competidor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            competidor = itemView.findViewById(R.id.textCompetidor);
        }
    }
}