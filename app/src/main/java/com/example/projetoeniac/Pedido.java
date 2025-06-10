package com.example.projetoeniac;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pedidos")
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nomeAnimal;
    private String descricao;
    private String data;
    private String tipo; // "adoção" ou "resgate"

    public Pedido(String nomeAnimal, String descricao, String data, String tipo) {
        this.nomeAnimal = nomeAnimal;
        this.descricao = descricao;
        this.data = data;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomeAnimal() { return nomeAnimal; }
    public String getDescricao() { return descricao; }
    public String getData() { return data; }
    public String getTipo() { return tipo; }
}
