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

    // Inserir usuário PF ou PJ
    public String insereDados(
            String tipo,
            String nome,
            String cpf_cnpj,
            String email,
            String senha,
            String data_nascimento,
            String telefone,
            String razao_social,
            String nome_fantasia,
            String inscricao_estadual) {

        ContentValues valores = new ContentValues();
        valores.put("tipo", tipo);
        valores.put("nome", nome);
        valores.put("cpf_cnpj", cpf_cnpj);
        valores.put("email", email);
        valores.put("senha", senha);
        valores.put("data_nascimento", data_nascimento);
        valores.put("telefone", telefone);
        valores.put("razao_social", razao_social);
        valores.put("nome_fantasia", nome_fantasia);
        valores.put("inscricao_estadual", inscricao_estadual);

        db = banco.getWritableDatabase();
        long resultado = db.insert("usuarios", null, valores);
        db.close();

        return (resultado == -1) ? "Erro ao efetuar o cadastro" : "Cadastro efetuado com sucesso";
    }

    // Consulta login com email e senha e tipo (PF/PJ)
    public Cursor ConsultaDadosLogin(String email, String senha, String tipo) {
        String[] campos = { "codigo", "tipo", "nome", "cpf_cnpj", "email", "senha"};
        String where = "email = ? AND senha = ? AND tipo = ?";
        db = banco.getReadableDatabase();
        Cursor cursor = db.query("usuarios", campos, where, new String[]{email, senha, tipo}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        // Não fechar db aqui, senão cursor fica inválido
        return cursor;
    }

    // Verifica se o email já existe (independente do tipo)
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