package com.grupointegrado.tambor.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupointegrado.tambor.model.Competidor;
import com.grupointegrado.tambor.model.Passada;
import com.grupointegrado.tambor.model.PassadaCompetidor;

import java.util.ArrayList;
import java.util.List;

public class PassadaDAO implements IPassadaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public PassadaDAO( Context context ) {
        DbHelper db = new DbHelper( context );
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Passada passada) {
        ContentValues cv = new ContentValues();
        cv.put("tempo", passada.getTempo());
        cv.put("com_id", passada.getId_com());

        try{
            escreve.insert(DbHelper.TABELA_PASSADAS, null, cv);
            Log.i("RESULTADO", "Sucesso ao salvar passada");
        } catch (Exception e){
            Log.i("RESULTADO", "Erro ao salvar passada");
            return false;
        }
        return true;
    }

    @Override
    public boolean buscar(Long id_com) {

        List<Passada> passadas = new ArrayList<>();

        String[] args = { id_com.toString() };

        Cursor consulta = le.rawQuery("SELECT * FROM " + DbHelper.TABELA_PASSADAS +
                " WHERE com_id = ?;", args);

        while (consulta.moveToNext() ){

            Passada passada = new Passada();

            Long id = consulta.getLong(consulta.getColumnIndex("id_pass") );
            Double tempo = consulta.getDouble(consulta.getColumnIndex("tempo") );
            Long com_id = consulta.getLong(consulta.getColumnIndex("com_id") );

            passada.setId( id );
            passada.setTempo( tempo );
            passada.setId_com( com_id );

            passadas.add( passada );

        }

        if (passadas.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<PassadaCompetidor> listar() {

        List<PassadaCompetidor> passadas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_PASSADAS +
                " INNER JOIN " + DbHelper.TABELA_COMPETIDORES +
                " ON " + DbHelper.TABELA_PASSADAS + ".com_id = " +
                DbHelper.TABELA_COMPETIDORES + ".id_com ORDER BY tempo ASC;";

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext() ){

            PassadaCompetidor passada = new PassadaCompetidor();

            Double tempo = c.getDouble(c.getColumnIndex("tempo"));
            String nome = c.getString(c.getColumnIndex("nome") );

            passada.setTempoCompetidor( nome + " - " + tempo + "s" );

            passadas.add( passada );

        }
        return passadas;

    }
}
