package com.example.projetoeniac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // vinculando os objetos com a interface gráfica
        btLogin = findViewById(R.id.btLogin);


        //quando houver o click no botão, chama (executa) o método onClick
        btLogin.setOnClickListener(this);
    }


    @Override
    public void onClick (View v){
        // se clicou no botão tela1, carrega a interface da tela1
        if (v.getId() == R.id.btLogin) {
            Intent login = new Intent(this, Login.class);
            startActivity(login);
        }

    }
}
