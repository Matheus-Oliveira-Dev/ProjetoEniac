package com.example.projetoeniac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;

public class PedidoAdapter extends ArrayAdapter<Pedido> {
    public PedidoAdapter(Context context, List<Pedido> pedidos) {
        super(context, 0, pedidos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pedido pedido = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pedido, parent, false);
        }

        TextView nomeAnimal = convertView.findViewById(R.id.textNomeAnimal);
        TextView descricao = convertView.findViewById(R.id.textDescricao);
        TextView data = convertView.findViewById(R.id.textData);

        nomeAnimal.setText(pedido.getNomeAnimal());
        descricao.setText(pedido.getDescricao());
        data.setText(pedido.getData());

        return convertView;
    }
}
