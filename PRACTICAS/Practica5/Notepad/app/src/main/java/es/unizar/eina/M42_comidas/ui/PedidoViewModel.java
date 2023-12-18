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

    private LiveData<List<Pedido>> pedidosPreparados;
    private LiveData<List<Pedido>> pedidosSolicitados;
    private LiveData<List<Pedido>> pedidosRecogidos;


    public PedidoViewModel(Application application) {
        super(application);
        mRepository = new PedidoPlatoRepository(application);
        mAllPedidos = mRepository.getAllPedidos();
        pedidosPorFecha = mRepository.obtenerPedidosOrdenados("FECHA");
        pedidosPorNombre = mRepository.obtenerPedidosOrdenados("NOMBRE");
        pedidosPorTelefono = mRepository.obtenerPedidosOrdenados("NUMERO");
        pedidosPreparados = mRepository.obtenerPedidosFiltrado("PREPARADO");
        pedidosSolicitados = mRepository.obtenerPedidosFiltrado("SOLICITADO");
        pedidosRecogidos = mRepository.obtenerPedidosFiltrado("RECOGIDO");

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

    public LiveData<List<Pedido>> obtenerPedidosFiltrados(String filtro) {
        switch (filtro) {
            case "PREPARADO":
                return pedidosPreparados;
            case "SOLICITADO":
                return pedidosSolicitados;
            case "RECOGIDO":
                return pedidosRecogidos;
            default:
                return pedidosPorFecha;
        }
    }

    public LiveData<List<Pedido>> obtenerPedidosFiltradosyOrdenados(String filtroSeleccionado, String criterioSeleccionado) {
        switch (criterioSeleccionado) {
            case "Fecha":
                criterioSeleccionado = "fechaRecogida";
            case "Nombre de Cliente":
                criterioSeleccionado = "nombreCliente";
            case "Número de Teléfono":
                criterioSeleccionado = "telefonoCliente";
        }
        return mRepository.obtenerPedidosFiltradoYOrdenado(filtroSeleccionado, criterioSeleccionado);
    }

    public long insert(Pedido pedido) { return mRepository.insert(pedido); }

    public void update(Pedido pedido) { mRepository.update(pedido); }
    public void delete(Pedido pedido) { mRepository.delete(pedido); }
}
