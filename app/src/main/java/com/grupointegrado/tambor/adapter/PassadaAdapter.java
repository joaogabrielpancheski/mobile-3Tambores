package com.grupointegrado.tambor.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grupointegrado.tambor.R;
import com.grupointegrado.tambor.model.Passada;
import com.grupointegrado.tambor.model.PassadaCompetidor;

import java.util.List;

public class PassadaAdapter extends RecyclerView.Adapter<PassadaAdapter.MyViewHolder> {

    private List<PassadaCompetidor> listaPassadas;

    public PassadaAdapter(List<PassadaCompetidor> lista) {
        this.listaPassadas = lista;
    }

    @NonNull
    @Override
    public PassadaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_passada_adapter, parent, false);

        return new PassadaAdapter.MyViewHolder(itemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull PassadaAdapter.MyViewHolder myViewHolder, int i) {

        PassadaCompetidor passada = listaPassadas.get(i);
        myViewHolder.passada.setText(passada.getTempoCompetidor());

    }

    @Override
    public int getItemCount() {
        return this.listaPassadas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView passada;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            passada = itemView.findViewById(R.id.textPassada);
        }
    }
}