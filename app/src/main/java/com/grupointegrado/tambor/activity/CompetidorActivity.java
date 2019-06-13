package com.grupointegrado.tambor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.grupointegrado.tambor.R;
import com.grupointegrado.tambor.adapter.CompetidorAdapter;
import com.grupointegrado.tambor.helper.CompetidorDAO;
import com.grupointegrado.tambor.helper.PassadaDAO;
import com.grupointegrado.tambor.helper.RecyclerItemClickListener;
import com.grupointegrado.tambor.model.Competidor;

import java.util.ArrayList;
import java.util.List;

public class CompetidorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CompetidorAdapter competidorAdapter;
    private List<Competidor> listaCompetidores = new ArrayList<>();
    private Competidor competidorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competidor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById( R.id.competidorRecyclerView);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Competidor competidorSelecionado = listaCompetidores.get( position );
                                PassadaDAO passadaDAO = new PassadaDAO( getApplicationContext() );

                                if (!passadaDAO.buscar(competidorSelecionado.getId())) {
                                    Intent intent = new Intent( CompetidorActivity.this, AdicionarPassadaActivity.class );
                                    intent.putExtra("competidorSelecionado", competidorSelecionado );

                                    startActivity( intent );
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Competidor já possui tempo registrado!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                competidorSelecionado = listaCompetidores.get( position );

                                Intent intent = new Intent( CompetidorActivity.this, AdicionarCompetidorActivity.class );
                                intent.putExtra("competidorSelecionado", competidorSelecionado );

                                startActivity( intent );
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarCompetidorActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarListaCompetidores(){
        CompetidorDAO competidorDAO = new CompetidorDAO( getApplicationContext() );
        listaCompetidores = competidorDAO.listar();

        competidorAdapter = new CompetidorAdapter( listaCompetidores );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), LinearLayout.VERTICAL ));
        recyclerView.setAdapter( competidorAdapter );
    }

    @Override
    protected void onStart() {
        carregarListaCompetidores();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
