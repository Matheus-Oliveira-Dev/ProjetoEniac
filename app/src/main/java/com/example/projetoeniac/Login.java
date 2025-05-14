package com.example.projetoeniac;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btLogAcessar = findViewById(R.id.btLogAcessar);
        btLogCadastre_se = findViewById(R.id.btLogCadastre_se);
        txtLogEmail = findViewById(R.id.txtLogEmail);
        txtLogSenha = findViewById(R.id.txtLogSenha);


        btLogAcessar.setOnClickListener(this);
        btLogCadastre_se.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btLogAcessar){
            if (validaLogin()) {
                Intent tela = new Intent(this, MainActivity.class);
                startActivity(tela);
            }
        }
        if (v.getId()==R.id.btLogCadastre_se) {
            Intent tela = new Intent(this, Cadastre_se.class);
            startActivity(tela);
        }
    }
    public boolean validaLogin(){
        boolean retorno = true;
        String _email = txtLogEmail.getText().toString();
        String _senha = txtLogSenha.getText().toString();
        String msg ="";
        if (_email.isEmpty()){
            msg = "O campo de E-mail deve ser preenchido!";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            retorno = false;
        }
        if (_senha.isEmpty()){
            msg = "O campo SENHA não foi preenchido!";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            retorno = false;
        }
        BancoControllerUsuarios bd = new BancoControllerUsuarios(getBaseContext());


        Cursor dados = bd.ConsultaDadosLogin(_email, _senha) ;


        if(dados.moveToFirst()){
            retorno = true;
        }else{
            msg = "O E-mail / Senha não estão cadastrados no sistema, CADASTRE-SE";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            retorno = false;
        }
        return retorno;
    }
}
