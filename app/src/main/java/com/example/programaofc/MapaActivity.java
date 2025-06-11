package com.example.programaofc; // ajuste esse pacote conforme o seu

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MapaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa); // conecta ao XML do mapa
    }
}
