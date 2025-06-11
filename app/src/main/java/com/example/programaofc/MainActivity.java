package com.example.programaofc; // ajuste para o nome do seu pacote

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // conecta com o activity_main.xml

        // Botões
        Button btnAdotar = findViewById(R.id.btnAdotar);
        Button btnDoar = findViewById(R.id.btnDoar);
        Button btnMapa = findViewById(R.id.btnMapa);
        Button btnLogin = findViewById(R.id.btnLogin);

        // Ações dos botões
        btnMapa.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapaActivity.class);

            startActivity(intent);
        });

        btnAdotar.setOnClickListener(v -> {
            // Substituir quando criar a tela de adoção
            // Intent intent = new Intent(MainActivity.this, AdotarActivity.class);
            // startActivity(intent);
        });

        btnDoar.setOnClickListener(v -> {
            // Substituir quando criar a tela de doação
        });

        btnLogin.setOnClickListener(v -> {
            // Substituir quando criar a tela de login/cadastro
        });
    }
}
