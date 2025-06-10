package com.example.projetoeniac;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PedidoDao {
    @Insert
    void inserirPedido(Pedido pedido);

    @Query("SELECT * FROM pedidos WHERE tipo = 'adoção'")
    List<Pedido> getPedidosAdocao();

    @Query("SELECT * FROM pedidos WHERE tipo = 'resgate'")
    List<Pedido> getPedidosResgate();
}

