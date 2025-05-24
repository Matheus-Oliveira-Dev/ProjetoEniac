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
        ContentValues valores = new ContentValues();
        valores.put("nome", _nome);
        valores.put("cpf", _cpf);
        valores.put("email", _email);
        valores.put("senha", _senha);

        db = banco.getWritableDatabase();
        long resultado = db.insert("usuarios", null, valores);
        db.close();

        return (resultado == -1) ? "Erro ao efetuar o Cadastre-se" : "Cadastro efetuado com sucesso";
    }

    public Cursor ConsultaDadosLogin(String _email, String _senha) {
        String[] campos = { "codigo","nome","cpf","email","senha"};
        String where = "email = ? AND senha = ?";
        db = banco.getReadableDatabase();
        Cursor cursor = db.query("usuarios", campos, where, new String[]{_email, _senha}, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        db.close();
        return cursor;
    }

    public boolean verificarEmail(String email) {
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ?", new String[]{email});
        boolean existe = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return existe;
    }

    public boolean atualizarSenha(String email, String novaSenha) {
        db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("senha", novaSenha);
        int linhasAfetadas = db.update("usuarios", valores, "email = ?", new String[]{email});
        db.close();
        return linhasAfetadas > 0;
    }
}