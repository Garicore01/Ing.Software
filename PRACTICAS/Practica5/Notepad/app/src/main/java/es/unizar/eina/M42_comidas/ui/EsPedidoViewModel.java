package es.unizar.eina.M42_comidas.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.EsPedido;
import es.unizar.eina.M42_comidas.database.PedidoPlatoRepository;

/** Clase utilizada como modelo de vista del pedido */
public class EsPedidoViewModel extends AndroidViewModel {

    private PedidoPlatoRepository mRepository;




    public EsPedidoViewModel(Application application) {
        super(application);
        mRepository = new PedidoPlatoRepository(application);
        

    }


    public void insert(EsPedido esPedido) { mRepository.insert(esPedido); }

    public void update(EsPedido esPedido) { mRepository.update(esPedido); }
    public void delete(EsPedido esPedido) { mRepository.delete(esPedido); }

    public LiveData<List<EsPedido>> getAllPlatosFromPedido(int pedidoId) { return mRepository.obtenerEsPedidoPorIdPedido(pedidoId); }
}
