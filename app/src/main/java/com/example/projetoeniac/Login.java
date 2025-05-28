package com.example.projetoeniac;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.activity.EdgeToEdge;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup radioGroupUserType;
    private RadioButton rbPessoaF, rbPessoaJ;
    private EditText txtLogEmail, txtLogSenha;
    private Button btLogAcessar, btLogCadastre_se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radioGroupUserType = findViewById(R.id.radioGroupUserType);
        rbPessoaF = findViewById(R.id.rbPessoaF);
        rbPessoaJ = findViewById(R.id.rbPessoaJ);
        txtLogEmail = findViewById(R.id.txtLogEmail);
        txtLogSenha = findViewById(R.id.txtLogSenha);
        btLogAcessar = findViewById(R.id.btLogAcessar);
        btLogCadastre_se = findViewById(R.id.btLogCadastre_se);

        btLogAcessar.setOnClickListener(this);
        btLogCadastre_se.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btLogAcessar) {
            if (validaLogin()) {
                Intent tela = new Intent(this, MainActivity.class);
                startActivity(tela);
                finish();
            }
        }
        if (v.getId() == R.id.btLogCadastre_se) {
            Intent tela = new Intent(this, Cadastrar.class);
            startActivity(tela);
        }
    }

    public boolean validaLogin() {
        String _email = txtLogEmail.getText().toString().trim();
        String _senha = txtLogSenha.getText().toString().trim();

        if (_email.isEmpty()) {
            Toast.makeText(this, "O campo de E-mail deve ser preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (_senha.isEmpty()) {
            Toast.makeText(this, "O campo SENHA não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }

        String tipoUsuario = rbPessoaF.isChecked() ? "PF" : "PJ";

        BancoControllerUsuarios bd = new BancoControllerUsuarios(getBaseContext());

        Cursor dados = bd.ConsultaDadosLogin(_email, _senha, tipoUsuario);

        boolean temDados = dados != null && dados.moveToFirst();

        if (!temDados) {
            Toast.makeText(this, "E-mail / Senha não cadastrados para o tipo " + tipoUsuario + ". Cadastre-se!", Toast.LENGTH_LONG).show();
            return false;
        }
        dados.close();
        return true;
    }
}