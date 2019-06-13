package com.grupointegrado.tambor.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.grupointegrado.tambor.R;
import com.grupointegrado.tambor.adapter.PassadaAdapter;
import com.grupointegrado.tambor.helper.PassadaDAO;
import com.grupointegrado.tambor.model.Passada;
import com.grupointegrado.tambor.model.PassadaCompetidor;

import java.util.ArrayList;
import java.util.List;

public class PassadaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PassadaAdapter passadaAdapter;
    private List<PassadaCompetidor> listaPassadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passada);

        recyclerView = findViewById( R.id.passadaRecyclerView);
    }

    public void carregarListaPassadas(){
        PassadaDAO passadaDAO = new PassadaDAO( getApplicationContext() );
        listaPassadas = passadaDAO.listar();

        passadaAdapter = new PassadaAdapter( listaPassadas );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), LinearLayout.VERTICAL ));
        recyclerView.setAdapter( passadaAdapter );
    }

    @Override
    protected void onStart() {
        carregarListaPassadas();
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
