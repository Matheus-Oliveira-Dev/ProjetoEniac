package com.example.projetoeniac;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastrarAnimal extends AppCompatActivity implements View.OnClickListener {

    EditText txtNomeAnimal, txtIdadeAnimal;
    Spinner spinnerEspecie, spinnerPorte;
    Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_animal);

        txtNomeAnimal = findViewById(R.id.txtNomeAnimal);
        txtIdadeAnimal = findViewById(R.id.txtIdadeAnimal);
        spinnerEspecie = findViewById(R.id.spinnerEspecie);
        spinnerPorte = findViewById(R.id.spinnerPorte);
        btCadastrar = findViewById(R.id.btCadastrar); // Faltava isso!

        btCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String _nomeAnimal = txtNomeAnimal.getText().toString();
        String _idadeAnimal = txtIdadeAnimal.getText().toString();
        String _especie = spinnerEspecie.getSelectedItem().toString();
        String _porte = spinnerPorte.getSelectedItem().toString();

        if (_nomeAnimal.isEmpty()) {
            Toast.makeText(this, "O campo NOME DO ANIMAL deve ser preenchido!", Toast.LENGTH_LONG).show();
            return;
        }

        if (_idadeAnimal.isEmpty()) {
            Toast.makeText(this, "O campo IDADE DO ANIMAL deve ser preenchido!", Toast.LENGTH_LONG).show();
            return;
        }

        if (_especie.equals("Selecione a espécie")) {
            Toast.makeText(this, "Por favor, selecione uma ESPÉCIE válida!", Toast.LENGTH_LONG).show();
            return;
        }

        if (_porte.equals("Selecione o porte")) {
            Toast.makeText(this, "Por favor, selecione um PORTE válido!", Toast.LENGTH_LONG).show();
            return;
        }

        // Tudo validado — salvar no banco
        BancoControllerAnimais bd = new BancoControllerAnimais(getBaseContext());
        String resultado = bd.insereAnimal(_nomeAnimal, _idadeAnimal, _especie, _porte);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        limpar();
    }

    private void limpar() {
        txtNomeAnimal.setText("");
        txtIdadeAnimal.setText("");
        spinnerEspecie.setSelection(0);
        spinnerPorte.setSelection(0);
    }
}
