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
import com.grupointegrado.tambor.helper.CompetidorDAO;
import com.grupointegrado.tambor.model.Competidor;

public class AdicionarCompetidorActivity extends AppCompatActivity {

    private TextInputEditText editCompetidor;
    private Competidor competidorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_competidor);

        editCompetidor = findViewById(R.id.textCompetidor);

        competidorSelecionado = (Competidor) getIntent().getSerializableExtra("competidorSelecionado");

        if ( competidorSelecionado != null ){
            editCompetidor.setText( competidorSelecionado.getNome() );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_competidor, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        CompetidorDAO competidorDAO = new CompetidorDAO( getApplicationContext() );

        switch ( item.getItemId() ){
            case R.id.itemSalvar:

                String nomeCompetidor = editCompetidor.getText().toString();

                if ( !nomeCompetidor.isEmpty() ) {

                    Competidor competidor = new Competidor();
                    competidor.setNome( nomeCompetidor );

                    if ( competidorSelecionado != null ) {

                        competidor.setId( competidorSelecionado.getId() );

                        if ( competidorDAO.atualizar( competidor )){
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao atualizar competidor!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Erro ao atualizar competidor!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        if ( competidorDAO.salvar( competidor ) ){
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao salvar competidor!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Erro ao salvar competidor!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            break;
            case R.id.itemExcluir:

                if ( competidorSelecionado != null ) {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(AdicionarCompetidorActivity.this);

                    dialog.setTitle("Confirmar exclusão");
                    dialog.setMessage("Desejar excluir o competidor: " + competidorSelecionado.getNome() + "?");

                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            CompetidorDAO competidorDAO = new CompetidorDAO( getApplicationContext() );
                            if ( competidorDAO.deletar(competidorSelecionado) ){
                                finish();
                                Toast.makeText(getApplicationContext(),
                                        "Sucesso ao deletar competidor!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Erro ao deletar competidor!",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    dialog.setNegativeButton("Não", null);
                    dialog.create();
                    dialog.show();

                }

            break;
        }

        return super.onOptionsItemSelected(item);
    }
}
