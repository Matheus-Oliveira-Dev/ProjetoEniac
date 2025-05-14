package com.example.projetoeniac;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Cadastre_se extends AppCompatActivity implements View.OnClickListener {
    EditText txtCadNome, txtCadCPF, txtCadEmail, txtCadSenha, txtCadConfSenha;
    Button btCadSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre_se);


        txtCadNome      = findViewById(R.id.txtCadNome);
        txtCadCPF       = findViewById(R.id.txtCadCPF);
        txtCadEmail     = findViewById(R.id.txtCadEmail);
        txtCadSenha     = findViewById(R.id.txtCadSenha);
        txtCadConfSenha = findViewById(R.id.txtCadConfSenha);
        btCadSalvar     = findViewById(R.id.btCadSalvar);


        btCadSalvar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String _nome  = txtCadNome.getText().toString();
        String _cpf   = txtCadCPF.getText().toString();
        String _email = txtCadEmail.getText().toString();
        String _senha = txtCadSenha.getText().toString();
        String _confsenha = txtCadConfSenha.getText().toString();
        String msg = "";
        if (_nome.isEmpty()){
            msg = "O campo NOME deve ser preenchido!";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }else{
            if (_cpf.isEmpty()) {
                msg = "O campo CPF deve ser preenchido!";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            }else{
                if (_email.isEmpty()) {
                    msg = "O campo E-MAIL deve ser preenchido!";
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                }else{
                    if (_senha.isEmpty()) {
                        msg = "O campo SENHA deve ser preenchido!";
                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    }else{
                        if (_confsenha.isEmpty()){
                            msg = "O campo CONFIRMA SENHA deve ser preenchido!";
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                        }else{
                            if(!_senha.equals(_confsenha)) {
                                msg = "Os campos SENHA e CONFIRMA SENHA devem ser iguais!";
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }else{
                                BancoControllerUsuarios bd = new BancoControllerUsuarios(getBaseContext());
                                String resultado;


                                resultado = bd.insereDados(_nome,_cpf,_email, _senha);


                                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                                limpar();
                            }
                        }
                    }
                }
            }
        }
    }

    public void limpar(){
        txtCadNome.setText("");
        txtCadCPF.setText("");
        txtCadEmail.setText("");
        txtCadSenha.setText("");
        txtCadConfSenha.setText("");
    }
}