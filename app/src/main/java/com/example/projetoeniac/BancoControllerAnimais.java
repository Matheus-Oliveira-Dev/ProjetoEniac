package com.example.projetoeniac;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoControllerAnimais {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoControllerAnimais(Context context) {
        banco = new CriaBanco(context);
    }

    public String insereAnimal(String nome, String idade, String especie, String porte) {
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("idade", idade);
        valores.put("especie", especie);
        valores.put("porte", porte);

        db = banco.getWritableDatabase();
        long resultado = db.insert("animais", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao cadastrar o animal";
        else
            return "Animal cadastrado com sucesso";
    }
}