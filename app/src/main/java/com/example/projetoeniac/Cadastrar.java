package com.example.projetoeniac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.activity.EdgeToEdge;

public class Cadastrar extends AppCompatActivity {

    private RadioGroup radioGroupUserType;
    private RadioButton rbPessoaF, rbPessoaJ;

    private LinearLayout layoutPessoaFisica, layoutPessoaJuridica;

    // Campos PF
    private EditText txtNomePf, txtCpfPf, txtDataPf, txtEmailPf, txtTelefonePf;
    // Campos PJ
    private EditText editTextRazaoSocialPj, txtNomePj, txtCnpjPj, txtInscricaoEstadualPj, txtEmailPj, txtTelefonePj;

    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Cadastrar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Init views
        radioGroupUserType = findViewById(R.id.radioGroupUserType);
        rbPessoaF = findViewById(R.id.rbPessoaF);
        rbPessoaJ = findViewById(R.id.rbPessoaJ);

        layoutPessoaFisica = findViewById(R.id.layoutPessoaFisica);
        layoutPessoaJuridica = findViewById(R.id.layoutPessoaJuridica);

        txtNomePf = findViewById(R.id.txtNomePf);
        txtCpfPf = findViewById(R.id.txtCpfPf);
        txtDataPf = findViewById(R.id.txtDataPf);
        txtEmailPf = findViewById(R.id.txtEmailPf);
        txtTelefonePf = findViewById(R.id.txtTelefonePf);

        editTextRazaoSocialPj = findViewById(R.id.editTextRazaoSocialPj);
        txtNomePj = findViewById(R.id.txtNomePj);
        txtCnpjPj = findViewById(R.id.txtCnpjPj);
        txtInscricaoEstadualPj = findViewById(R.id.txtInscricaoEstadualPj);
        txtEmailPj = findViewById(R.id.txtEmailPj);
        txtTelefonePj = findViewById(R.id.txtTelefonePj);

        btCadastrar = findViewById(R.id.btCadastrar);

        // Listener para alternar layouts
        radioGroupUserType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbPessoaF) {
                layoutPessoaFisica.setVisibility(View.VISIBLE);
                layoutPessoaJuridica.setVisibility(View.GONE);
            } else {
                layoutPessoaFisica.setVisibility(View.GONE);
                layoutPessoaJuridica.setVisibility(View.VISIBLE);
            }
        });

        btCadastrar.setOnClickListener(v -> cadastrarUsuario());
    }

    private void cadastrarUsuario() {
        BancoControllerUsuarios bd = new BancoControllerUsuarios(getBaseContext());

        if (rbPessoaF.isChecked()) {
            // Validação mínima PF
            String nome = txtNomePf.getText().toString().trim();
            String cpf = txtCpfPf.getText().toString().trim();
            String dataNasc = txtDataPf.getText().toString().trim();
            String email = txtEmailPf.getText().toString().trim();
            String telefone = txtTelefonePf.getText().toString().trim();

            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || dataNasc.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios (PF).", Toast.LENGTH_SHORT).show();
                return;
            }

            if (bd.verificarEmail(email)) {
                Toast.makeText(this, "E-mail já cadastrado!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Senha fixa para exemplo, você pode criar um campo de senha aqui
            String senha = "123456";

            String msg = bd.insereDados("PF", nome, cpf, email, senha, dataNasc, telefone, null, null, null);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            if (msg.equals("Cadastro efetuado com sucesso")) {
                finish();
            }
        } else {
            // Pessoa Jurídica
            String razaoSocial = editTextRazaoSocialPj.getText().toString().trim();
            String nomeFantasia = txtNomePj.getText().toString().trim();
            String cnpj = txtCnpjPj.getText().toString().trim();
            String inscricaoEstadual = txtInscricaoEstadualPj.getText().toString().trim();
            String email = txtEmailPj.getText().toString().trim();
            String telefone = txtTelefonePj.getText().toString().trim();

            if (razaoSocial.isEmpty() || nomeFantasia.isEmpty() || cnpj.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios (PJ).", Toast.LENGTH_SHORT).show();
                return;
            }

            if (bd.verificarEmail(email)) {
                Toast.makeText(this, "E-mail já cadastrado!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Senha fixa para exemplo, você pode criar um campo de senha aqui
            String senha = "123456";

            String msg = bd.insereDados("PJ", nomeFantasia, cnpj, email, senha, null, telefone, razaoSocial, nomeFantasia, inscricaoEstadual);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            if (msg.equals("Cadastro efetuado com sucesso")) {
                finish();
            }
        }
    }
}