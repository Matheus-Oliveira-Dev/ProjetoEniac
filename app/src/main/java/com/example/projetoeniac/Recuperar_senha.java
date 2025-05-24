package com.example.projetoeniac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Recuperar_senha extends AppCompatActivity {
    EditText etEmail;
    Button btnRecuperar;
    BancoControllerUsuarios dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        etEmail = findViewById(R.id.et_email);
        btnRecuperar = findViewById(R.id.btn_recuperar);
        dbController = new BancoControllerUsuarios(this);

        btnRecuperar.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Digite seu e-mail", Toast.LENGTH_SHORT).show();
            } else if (dbController.verificarEmail(email)) {
                Intent intent = new Intent(this, Redefinir_senha.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "E-mail não encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}