package es.unizar.eina.M42_comidas.ui;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.Plato;


public class GlobalState {
    private static GlobalState instance;
    private Map<Integer, Integer> cantidadPlatosMap;

    private GlobalState() {
        cantidadPlatosMap = new HashMap<>();
    }

    public static GlobalState getInstance() {
        if (instance == null) {
            synchronized (GlobalState.class) {
                if (instance == null) {
                    instance = new GlobalState();
                }
            }
        }
        return instance;
    }

    public Map<Integer, Integer> getCantidadPlatosMap() {
        return cantidadPlatosMap;
    }

    public void agregarAlMapa(Integer clave, Integer valor) {
        cantidadPlatosMap.put(clave, valor);

    }

    public Integer obtenerDelMapa(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return cantidadPlatosMap.get(clave);
        }else{

            return 0;
        }
    }

    public void printAll() {
        for (Map.Entry<Integer, Integer> entry : cantidadPlatosMap.entrySet()) {
            Log.d("TAG", "Plato: " + entry.getKey()+ " Cantidad: " + entry.getValue());
        }
    }
}

