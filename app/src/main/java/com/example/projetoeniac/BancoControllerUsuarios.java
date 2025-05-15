package com.example.projetoeniac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class BancoControllerUsuarios {
    private SQLiteDatabase db;
    private CriaBanco banco;


    public BancoControllerUsuarios(Context context) {
        banco = new CriaBanco(context);
    }


    public String insereDados(String _nome, String _cpf, String _email, String _senha ) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();


        valores = new ContentValues();
        valores.put("nome", _nome);
        valores.put("cpf", _cpf);
        valores.put("email", _email);
        valores.put("senha", _senha);


        resultado = db.insert("usuarios", null, valores);
        db.close();


        if (resultado == -1)
            return "Erro ao efetuar o Cadastre-se";
        else
            return "Cadastro efetuado com sucesso";
    }


    public Cursor ConsultaDadosLogin(String _email, String _senha){
        Cursor cursor;
        String[] campos = { "codigo","nome","cpf","email","senha"};
        String where = "email = '" + _email + "' and senha = '" + _senha + "'";
        db = banco.getReadableDatabase();


        // select * from usuarios where email = 'digitado' and senha = 'senha'
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
