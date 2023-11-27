package es.unizar.eina.M42_comidas.database;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.PedidoDao;



/** Definicion de clase repositorio para los pedidos */
public class PedidoRepository {

    private PedidoDao mPedidoDao;
    private LiveData<List<Pedido>> mAllPedidos;

    
    public PedidoRepository(Application application) {
        PedidoRoomDatabase db = PedidoRoomDatabase.getDatabase(application);
        mPedidoDao = db.PedidoDao();
        //No se si hace falta coger todos por defecto
        mAllPedidos = mPedidoDao.getAllPedidosOrderedByNombre();
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
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
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
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.update(pedido);
        });
        return result[0];
    }

    /** Elimina una nota
     * @param pedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(Pedido pedido) {
        final int[] result = {0};
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.delete(pedido);
        });
        return result[0];
    }
}
