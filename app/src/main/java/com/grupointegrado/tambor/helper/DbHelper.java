package com.grupointegrado.tambor.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAMBOR";
    public static String TABELA_COMPETIDORES = "COMPETIDORES";
    public static String TABELA_PASSADAS = "PASSADAS";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCompetidores = "CREATE TABLE IF NOT EXISTS " + TABELA_COMPETIDORES +
                " (id_com INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL);";

        String sqlPassadas = "CREATE TABLE IF NOT EXISTS " + TABELA_PASSADAS +
                " (id_pass INTEGER PRIMARY KEY AUTOINCREMENT, tempo DOUBLE," +
                "com_id INTEGER, FOREIGN KEY(com_id) REFERENCES " + TABELA_COMPETIDORES +
                " (id_com));";

        try {
            db.execSQL( sqlCompetidores );
            db.execSQL( sqlPassadas );
            Log.i("LOGBANCO", "Sucesso ao criar tabela");
        } catch (Exception e) {
            Log.i("LOGBANCO", "Erro ao criar tabela " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlCompetidores = "DROP TABLE IF EXISTS " + TABELA_COMPETIDORES + ";";
        String sqlPassadas = "DROP TABLE IF EXISTS " + TABELA_PASSADAS + ";";

        try {
            db.execSQL( sqlCompetidores );
            db.execSQL( sqlPassadas );
            onCreate(db);
            Log.i("LOGBANCO", "Sucesso ao atualizar APP");
        } catch (Exception e) {
            Log.i("LOGBANCO", "Erro ao atualizar APP " + e.getMessage());
        }
    }
}
