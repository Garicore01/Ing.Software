package es.unizar.eina.M42_comidas.ui;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.Plato;
import es.unizar.eina.M42_comidas.database.PedidoPlatoRepository;

public class PlatoViewModel extends AndroidViewModel {

    private PedidoPlatoRepository mRepository;

    private final LiveData<List<Plato>> mAllPlatos;
    private final LiveData<List<Plato>> platosPorNombre;
    private final LiveData<List<Plato>> platosPorCategoria;
    private final LiveData<List<Plato>> platosPorCategoriayNombre;

    public PlatoViewModel(Application application) {
        super(application);
        mRepository = new PedidoPlatoRepository(application);
        mAllPlatos = mRepository.getAllPlatos();
        platosPorNombre = mRepository.obtenerPlatosPorNombre();
        platosPorCategoria = mRepository.obtenerPlatosPorCategoria();
        platosPorCategoriayNombre = mRepository.obtenerPlatosPorCategoriayNombre();
    }

    public LiveData<List<Plato>> obtenerPlatosFiltradosyOrdenados(String filtro, String criterio) {
        if (filtro.equals("PRIMEROS")) {
            if (criterio.equals("Ambos")) {
                Log.d("ESTOY EN ", "PREPARADO + FECHA");
                //return pedidosPreparadosYFecha;
                return mRepository.obtenerPlatosFiltradoYOrdenado("PRIMERO","AMBOS");
            } else if (criterio.equals("Nombre de Cliente")) {
                return mRepository.obtenerPlatosFiltradoYOrdenado("PRIMERO","NOMBRE");
            } else if (criterio.equals("Categoria del plato")) {
                return mRepository.obtenerPlatosFiltradoYOrdenado("PRIMERO","CATEGORIA");
            }
        } else if (filtro.equals("SEGUNDOS")) {
            if (criterio.equals("Ambos")) {
                Log.d("ESTOY EN ", "PREPARADO + FECHA");
                //return pedidosPreparadosYFecha;
                return mRepository.obtenerPlatosFiltradoYOrdenado("SEGUNDO","AMBOS");
            } else if (criterio.equals("Nombre de Cliente")) {
                return mRepository.obtenerPlatosFiltradoYOrdenado("SEGUNDO","NOMBRE");
            } else if (criterio.equals("Categoria del plato")) {
                return mRepository.obtenerPlatosFiltradoYOrdenado("SEGUNDO","CATEGORIA");
            }
        } else if (filtro.equals("POSTRES")) {
            if (criterio.equals("Ambos")) {
                Log.d("ESTOY EN ", "PREPARADO + FECHA");
                //return pedidosPreparadosYFecha;
                return mRepository.obtenerPlatosFiltradoYOrdenado("POSTRE","AMBOS");
            } else if (criterio.equals("Nombre de Cliente")) {
                return mRepository.obtenerPlatosFiltradoYOrdenado("POSTRE","NOMBRE");
            } else if (criterio.equals("Categoria del plato")) {
                return mRepository.obtenerPlatosFiltradoYOrdenado("POSTRE","CATEGORIA");
            }
        }
        return mRepository.obtenerPlatosFiltradoYOrdenado("PRIMERO","NOMBRE");
    }
    public LiveData<List<Plato>> obtenerPlatosOrdenados(String criterio) {
        switch (criterio) {
            case "Ambos":
                return platosPorCategoriayNombre;
            case "Nombre del plato":
                return platosPorNombre;
            case "Categoría del plato":
                return platosPorCategoria;
            default:
                return platosPorNombre;
        }
    }


    LiveData<List<Plato>> getAllPlatos() { return mAllPlatos; }

    public void insert(Plato plato) { mRepository.insert(plato); }

    public void update(Plato plato) { mRepository.update(plato); }
    public void delete(Plato plato) { mRepository.delete(plato); }
}
