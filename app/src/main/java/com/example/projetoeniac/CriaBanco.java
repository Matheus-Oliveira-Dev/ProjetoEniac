package com.example.projetoeniac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco_exemplo.db";
    private static final int VERSAO = 2; // Se mudar algo na estrutura, aumente a versão!

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabela de contatos (se ainda for usada)
        db.execSQL("CREATE TABLE IF NOT EXISTS contatos (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "email TEXT)");

        // Tabela de usuários
        db.execSQL("CREATE TABLE IF NOT EXISTS usuarios (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "cpf TEXT," +
                "email TEXT," +
                "senha TEXT)");

        // ✅ Nova tabela de animais
        db.execSQL("CREATE TABLE IF NOT EXISTS animais (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "idade TEXT," +
                "especie TEXT," +
                "porte TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Cuidado: isso APAGA todos os dados antigos ao atualizar!
        db.execSQL("DROP TABLE IF EXISTS contatos");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS animais");
        onCreate(db);
    }
}