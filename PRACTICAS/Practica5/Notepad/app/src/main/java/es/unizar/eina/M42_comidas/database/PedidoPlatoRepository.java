package es.unizar.eina.M42_comidas.database;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.PedidoDao;



/** Definicion de clase repositorio para los pedidos */
public class PedidoPlatoRepository {

    private PedidoDao mPedidoDao;
    private LiveData<List<Pedido>> mAllPedidos;

    private PlatoDao mPlatoDao;
    private LiveData<List<Plato>> mAllDishes;

    
    public PedidoPlatoRepository(Application application) {
        PedidoPlatoRoomDatabase db = PedidoPlatoRoomDatabase.getDatabase(application);
        mPedidoDao = db.PedidoDao();
        //No se si hace falta coger todos por defecto
        mAllPedidos = mPedidoDao.getAllPedidosOrderedByNombre();

        mPlatoDao = db.PlatoDao();
        mAllDishes = mPlatoDao.getAllPlatos();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    /** Obtiene todos los pedidos
     * @return un valor entero largo con el identificador de los pedidos que se han creado.
     */
    public LiveData<List<Pedido>> getAllPedidos() {
        return mAllPedidos;
    }

    /** Inserta un pedido
     * @param pedido
     * @return un valor entero largo con el identificador del pedido que se ha creado.
     */
    public long insert(Pedido pedido) {
        final long[] result = {0};
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.insert(pedido);
        });
        return result[0];
    }

    /** Modifica una nota
     * @param pedido
     * @return un valor entero con el numero de filas modificadas.
     */
    public int update(Pedido pedido) {
        final int[] result = {0};
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.update(pedido);
        });
        return result[0];
    }

    /** Elimina un pedido
     * @param pedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(Pedido pedido) {
        final int[] result = {0};
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.delete(pedido);
        });
        return result[0];
    }






    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Plato>> getAllPlatos() {
        return mAllDishes;
    }

    /** Inserta un plato
     * @param plato
     * @return un valor entero largo con el identificador del plato que se ha creado.
     */
    public long insert(Plato plato) {
        final long[] result = {0};
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.insert(plato);
        });
        return result[0];
    }


    /** Modifica una nota
     * @param plato
     * @return un valor entero con el numero de filas modificadas.
     */
    public int update(Plato plato) {
        final int[] result = {0};
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.update(plato);
        });
        return result[0];
    }

    /** Elimina una nota
     * @param plato
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(Plato plato) {
        final int[] result = {0};
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.delete(plato);
        });
        return result[0];
    }
}
