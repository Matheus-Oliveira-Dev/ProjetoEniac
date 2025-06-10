package com.example.projetoeniac;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import java.util.List;

public class PerfilViewModel extends AndroidViewModel {
    private PedidoDao pedidoDao;

    public PerfilViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        pedidoDao = db.pedidoDao();
    }

    public List<Pedido> getPedidosAdocao() {
        return pedidoDao.getPedidosAdocao();
    }

    public List<Pedido> getPedidosResgate() {
        return pedidoDao.getPedidosResgate();
    }
}
