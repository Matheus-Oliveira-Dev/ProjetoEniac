package com.example.projetoeniac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco_exemplo.db";
    private static final int VERSAO = 2;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS usuarios (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipo TEXT NOT NULL," +               // PF ou PJ
                "nome TEXT NOT NULL," +
                "cpf_cnpj TEXT NOT NULL UNIQUE," +   // CPF ou CNPJ
                "email TEXT NOT NULL UNIQUE," +
                "senha TEXT NOT NULL," +
                "data_nascimento TEXT," +             // para PF
                "telefone TEXT," +
                "razao_social TEXT," +                // para PJ
                "nome_fantasia TEXT," +               // para PJ
                "inscricao_estadual TEXT)");          // para PJ
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}