package com.example.projetoeniac;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Redefinir_senha extends AppCompatActivity {
    EditText etNovaSenha, etConfirmarSenha;
    Button btnSalvar;
    BancoControllerUsuarios dbController;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        etNovaSenha = findViewById(R.id.et_nova_senha);
        etConfirmarSenha = findViewById(R.id.et_confirmar_senha);
        btnSalvar = findViewById(R.id.btn_salvar);
        dbController = new BancoControllerUsuarios(this);

        email = getIntent().getStringExtra("email");

        btnSalvar.setOnClickListener(view -> {
            String novaSenha = etNovaSenha.getText().toString().trim();
            String confirmarSenha = etConfirmarSenha.getText().toString().trim();

            if (novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else if (!novaSenha.equals(confirmarSenha)) {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            } else {
                boolean sucesso = dbController.atualizarSenha(email, novaSenha);
                if (sucesso) {
                    Toast.makeText(this, "Senha atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Erro ao atualizar a senha", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}