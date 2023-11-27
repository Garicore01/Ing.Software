package es.unizar.eina.M42_comidas.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.PlatoRepository;

public class PlatoViewModel extends AndroidViewModel {

    private PlatoRepository mRepository;

    private final LiveData<List<Pedido>> mAllNotes;

    public PlatoViewModel(Application application) {
        super(application);
        mRepository = new PlatoRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    LiveData<List<Pedido>> getAllNotes() { return mAllNotes; }

    public void insert(Pedido pedido) { mRepository.insert(pedido); }

    public void update(Pedido pedido) { mRepository.update(pedido); }
    public void delete(Pedido pedido) { mRepository.delete(pedido); }
}
