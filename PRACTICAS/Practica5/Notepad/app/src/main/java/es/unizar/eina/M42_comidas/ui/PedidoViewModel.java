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
    private LiveData<List<Pedido>> pedidosPreparadosYFecha;
    private LiveData<List<Pedido>> pedidosPreparadosYNombre;
    private LiveData<List<Pedido>> pedidosPreparadosYNumero;

    private LiveData<List<Pedido>> pedidosSolicitados;
    private LiveData<List<Pedido>> pedidosSolicitadosYFecha;
    private LiveData<List<Pedido>> pedidosSolicitadosYNombre;
    private LiveData<List<Pedido>> pedidosSolicitadosYNumero;

    private LiveData<List<Pedido>> pedidosRecogidos;
    private LiveData<List<Pedido>> pedidosRecogidosYFecha;
    private LiveData<List<Pedido>> pedidosRecogidosYNombre;
    private LiveData<List<Pedido>> pedidosRecogidosYNumero;


    public PedidoViewModel(Application application) {
        super(application);
        mRepository = new PedidoPlatoRepository(application);
        mAllPedidos = mRepository.getAllPedidos();
        pedidosPorFecha = mRepository.obtenerPedidosPorFecha();
        pedidosPorNombre = mRepository.obtenerPedidosPorTelefono();
        pedidosPorTelefono = mRepository.obtenerPedidosPorTelefono();
        pedidosPreparados = mRepository.obtenerPedidosFiltrado("PREPARADO");
        pedidosPreparadosYFecha = mRepository.obtenerPedidosFiltradoYOrdenado("PREPARADO","FECHA");
        pedidosPreparadosYNombre = mRepository.obtenerPedidosFiltradoYOrdenado("PREPARADO","NOMBRE");
        pedidosPreparadosYNumero = mRepository.obtenerPedidosFiltradoYOrdenado("PREPARADO","NUMERO");
        pedidosSolicitados = mRepository.obtenerPedidosFiltrado("SOLICITADO");
        pedidosSolicitadosYFecha = mRepository.obtenerPedidosFiltradoYOrdenado("SOLICITADO","FECHA");
        pedidosSolicitadosYNombre = mRepository.obtenerPedidosFiltradoYOrdenado("SOLICITADO","NOMBRE");
        pedidosSolicitadosYNumero = mRepository.obtenerPedidosFiltradoYOrdenado("SOLICITADO","NUMERO");
        pedidosRecogidos = mRepository.obtenerPedidosFiltrado("RECOGIDO");
        pedidosRecogidosYFecha = mRepository.obtenerPedidosFiltradoYOrdenado("RECOGIDO","FECHA");
        pedidosRecogidosYNombre = mRepository.obtenerPedidosFiltradoYOrdenado("RECOGIDO","NOMBRE");
        pedidosRecogidosYNumero = mRepository.obtenerPedidosFiltradoYOrdenado("RECOGIDO","NUMERO");

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
        if (filtroSeleccionado == "PREPARADO") {
            if (criterioSeleccionado == "Fecha") {
                return pedidosPreparadosYFecha;
            } else if (criterioSeleccionado == "Nombre de Cliente") {
                return pedidosPreparadosYNombre;
            } else if (criterioSeleccionado == "Número de Teléfono") {
                return pedidosPreparadosYNumero;
            }
        } else if (filtroSeleccionado == "SOLICITADO") {
            if (criterioSeleccionado == "Fecha") {
                return pedidosSolicitadosYFecha;
            } else if (criterioSeleccionado == "Nombre de Cliente") {
                return pedidosSolicitadosYNombre;
            } else if (criterioSeleccionado == "Número de Teléfono") {
                return pedidosSolicitadosYNumero;
            }
        } else if (filtroSeleccionado == "RECOGIDO") {
            if (criterioSeleccionado == "Fecha") {
                return pedidosRecogidosYFecha;
            } else if (criterioSeleccionado == "Nombre de Cliente") {
                return pedidosRecogidosYNombre;
            } else if (criterioSeleccionado == "Número de Teléfono") {
                return pedidosRecogidosYNumero;
            }
        }
        return pedidosPorFecha;
    }

    public long insert(Pedido pedido) { return mRepository.insert(pedido); }

    public void update(Pedido pedido) { mRepository.update(pedido); }
    public void delete(Pedido pedido) { mRepository.delete(pedido); }
}
