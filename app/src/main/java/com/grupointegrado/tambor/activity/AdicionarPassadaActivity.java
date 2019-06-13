package com.grupointegrado.tambor.activity;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.grupointegrado.tambor.R;
import com.grupointegrado.tambor.helper.PassadaDAO;
import com.grupointegrado.tambor.model.Competidor;
import com.grupointegrado.tambor.model.Passada;

public class AdicionarPassadaActivity extends AppCompatActivity {

    private TextInputEditText editPassada;
    private Competidor competidorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_passada);

        editPassada = findViewById(R.id.textPassada);

        competidorSelecionado = (Competidor) getIntent().getSerializableExtra("competidorSelecionado");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_passada, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        PassadaDAO passadaDAO = new PassadaDAO( getApplicationContext() );

        switch ( item.getItemId() ){
            case R.id.itemSalvar:

                String textPassada = editPassada.getText().toString();

                if ( !textPassada.isEmpty() ) {

                    Double tempoPassada = Double.parseDouble(textPassada);

                    Passada passada = new Passada();
                    passada.setTempo( tempoPassada );
                    passada.setId_com(competidorSelecionado.getId());

                    if ( passadaDAO.salvar( passada ) ){
                        finish();
                        Toast.makeText(getApplicationContext(),
                                "Sucesso ao salvar tempo da passada!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Erro ao salvar tempo da passada!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
