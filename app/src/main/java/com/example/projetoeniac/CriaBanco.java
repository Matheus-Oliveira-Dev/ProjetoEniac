package com.example.projetoeniac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CriaBanco extends SQLiteOpenHelper {


    private static final String NOME_BANCO = "banco_exemplo.db";
    private static final int VERSAO = 3;
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE usuarios_pessoa_fisica("
                + "Nome text,"
                + "Cpf  text primary key,"
                + "Email text,"
                + "Telefone text,"
                + "Senha text,"
                + "ConfimaSenha text)";
        db.execSQL(sql);

        sql = "CREATE TABLE usuarios_pessoa_juridica("
                + "RazaoSocial text,"
                + "NomeFantasia text,"
                + "cnpj  text primary key,"
                + "InscricaoEstadual,"
                + "Email text,"
                + "Telefone text,"
                + "Senha text,"
                + "ConfimaSenha text)";
        db.execSQL(sql);

        sql = "CREATE TABLE Cadastro_animal("
                + "codigo integer primary key autoincrement ,"
                + "nomeAnimal text,"
                + "idadeAnimal text,"
                + "especie text,"
                + "porte text)";
        db.execSQL(sql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios_pessoa_fisica");
        db.execSQL("DROP TABLE IF EXISTS usuarios_pessoa_juridica");
        db.execSQL("DROP TABLE IF EXISTS Cadastro_animal");
        onCreate(db);
    }
}