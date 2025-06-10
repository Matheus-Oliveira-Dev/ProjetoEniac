package com.example.projetoeniac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoControllerPerfil {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoControllerPerfil(Context context) {
        banco = new CriaBanco(context);
    }

    public Cursor carregaDadosPeloCodigo(int id) {
        Cursor cursor;
        String[] campos = {"codigo", "nome", "email"};
        String where = "codigo=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("resgagte", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}