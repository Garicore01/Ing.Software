package es.unizar.eina.M42_comidas.ui;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.PedidoPlatoRepository;

public class PedidoViewModel extends AndroidViewModel {

    private PedidoPlatoRepository mRepository;

    private final LiveData<List<Pedido>> mAllPedidos;
    private LiveData<List<Pedido>> pedidosPorFecha;
    private LiveData<List<Pedido>> pedidosPorNombre;
    private LiveData<List<Pedido>> pedidosPorTelefono;

    public PedidoViewModel(Application application) {
        super(application);
        mRepository = new PedidoPlatoRepository(application);
        mAllPedidos = mRepository.getAllPedidos();
        pedidosPorFecha = mRepository.obtenerPedidosPorFecha();
        pedidosPorNombre = mRepository.obtenerPedidosPorTelefono();
        pedidosPorTelefono = mRepository.obtenerPedidosPorTelefono();
    }

    LiveData<List<Pedido>> getAllPedidos() { return mAllPedidos; }

    public LiveData<List<Pedido>> obtenerPedidosOrdenados(String criterio) {
        switch (criterio) {
            case "Fecha":
                return pedidosPorFecha;
            case "Nombre de Cliente":
                return pedidosPorNombre;
            case "Número de Teléfono":
                return pedidosPorTelefono;
            default:
                return pedidosPorFecha;
        }
    }

    public void insert(Pedido pedido) { mRepository.insert(pedido); }

    public void update(Pedido pedido) { mRepository.update(pedido); }
    public void delete(Pedido pedido) { mRepository.delete(pedido); }
}
