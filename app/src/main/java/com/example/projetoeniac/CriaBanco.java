package com.example.projetoeniac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco_usuarios.db";
    private static final int VERSAO = 2;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipo TEXT NOT NULL," + // PF ou PJ
                "nome TEXT NOT NULL," +
                "documento TEXT NOT NULL," + // CPF ou CNPJ
                "email TEXT NOT NULL UNIQUE," +
                "senha TEXT NOT NULL," +
                "data_nasc TEXT," + // Apenas PF
                "telefone TEXT," +
                "razao_social TEXT," + // Apenas PJ
                "nome_fantasia TEXT," + // Apenas PJ
                "inscricao_estadual TEXT" + // Apenas PJ
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}