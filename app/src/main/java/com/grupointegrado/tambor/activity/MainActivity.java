package com.grupointegrado.tambor.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.grupointegrado.tambor.R;
import com.grupointegrado.tambor.helper.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void telaCompetidores (View view) {
        Intent intent = new Intent( getApplicationContext(), CompetidorActivity.class );
        startActivity(intent);
    }

    public void telaPassadas (View view) {
        Intent intent = new Intent( getApplicationContext(), PassadaActivity.class );
        startActivity(intent);
    }
}
