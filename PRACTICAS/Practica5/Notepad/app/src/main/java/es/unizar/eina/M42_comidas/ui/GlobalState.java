package es.unizar.eina.M42_comidas.ui;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unizar.eina.M42_comidas.database.EsPedido;

public class GlobalState {
    private static GlobalState instance;
    private Map<Integer, ElemEsPedido> cantidadPlatosMap;

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

    public Map<Integer, ElemEsPedido> getCantidadPlatosMap() {
        return cantidadPlatosMap;
    }


    public void agregarAlMapa(Integer clave, ElemEsPedido valor) {
        cantidadPlatosMap.put(clave, valor);

    }
    public void vaciarMapa() {
        cantidadPlatosMap.clear();
    }

    public int obtenerDelMapaId(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return cantidadPlatosMap.get(clave).platoId;
        }else{

            return 0;
        }
    }
    public int obtenerDelMapaCantidad(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return cantidadPlatosMap.get(clave).cantidad;
        }else{

            return 0;
        }
    }
    public double obtenerDelMapaPrecio(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return cantidadPlatosMap.get(clave).precio;
        }else{

            return 0;
        }
    }

    public int existeEnMapa(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return 1;
        }else{
            return 0;
        }
    }




}

