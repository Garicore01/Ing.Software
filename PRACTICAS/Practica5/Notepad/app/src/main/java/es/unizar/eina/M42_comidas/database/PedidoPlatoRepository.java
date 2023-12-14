package es.unizar.eina.M42_comidas.database;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CountDownLatch;


/** Definicion de clase repositorio para los pedidos */
public class PedidoPlatoRepository {

    private PedidoDao mPedidoDao;
    private LiveData<List<Pedido>> mAllPedidos;

    private PlatoDao mPlatoDao;
    private LiveData<List<Plato>> mAllDishes;

    private EsPedidoDao mEsPedidoDao;

    
    public PedidoPlatoRepository(Application application) {
        PedidoPlatoRoomDatabase db = PedidoPlatoRoomDatabase.getDatabase(application);
        mPedidoDao = db.PedidoDao();
        //No se si hace falta coger todos por defecto
        mAllPedidos = mPedidoDao.getAllPedidosOrderedByNombre();

        mPlatoDao = db.PlatoDao();
        mAllDishes = mPlatoDao.getAllPlatos();

        mEsPedidoDao = db.EsPedidoDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.


    //-----------------------------------PEDIDOS-----------------------------------------------------------
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
        final CountDownLatch latch = new CountDownLatch(1); // Para esperar a que se complete la inserción
        final long[] result = {0};
    
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.insert(pedido);
            latch.countDown(); // Liberar el contador
        });
    
        try {
            latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        return result[0]; // Devolver el id del pedido insertado
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

    /**
     * @return Devuelve la lista de Pedidos ordenados por fecha
     */
    public LiveData<List<Pedido>> obtenerPedidosPorFecha() {
        return mPedidoDao.getAllPedidosOrderedByDate();
    }
    /**
     * @return Devuelve la lista de Pedidos ordenados por Nombre de cliente
     */
    public LiveData<List<Pedido>> obtenerPedidosPorNombre() {
        return mPedidoDao.getAllPedidosOrderedByNombre();
    }
    /**
     * @return Devuelve la lista de Pedidos ordenados por Telefono
     */
    public LiveData<List<Pedido>> obtenerPedidosPorTelefono() {
        return mPedidoDao.getAllPedidosOrderedByTelefono();
    }




    //-----------------------------------PLATOS-----------------------------------------------------------

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Plato>> getAllPlatos() {
        return mAllDishes;
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

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




    /**
     * @return Devuelve la lista de Platos ordenados por nombre
     */
    public LiveData<List<Plato>> obtenerPlatosPorNombre() {
        return mPlatoDao.getOrderedPlatosByName();
    }

    /**
     * @return Devuelve la lista de Platos ordenados por categoria
     */
    public LiveData<List<Plato>> obtenerPlatosPorCategoria() {
        return mPlatoDao.getOrderedPlatosByCategoy();
    }
    //-----------------------------------ESPEDIDO-----------------------------------------------------------

    /** Inserta un plato en un pedido
     * @param plato
     * @return un valor entero largo con el identificador del plato que se ha creado.
     */
    public long insert(EsPedido esPedido) {
        final long[] result = {0};
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mEsPedidoDao.insert(esPedido);
        });
        return result[0];
    }

    /** Modifica una nota
     * @param esPedido
     * @return un valor entero con el numero de filas modificadas.
     */
    public long update(EsPedido esPedido) {
        final long[] result = {0};
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mEsPedidoDao.update(esPedido);
        });
        return result[0];
    }

    /** Elimina la relación entre Pedido y Plato
     * @param esPedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(EsPedido esPedido) {
        final int[] result = {0};
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mEsPedidoDao.delete(esPedido);
        });
        return result[0];
    }

    public LiveData<List<EsPedido>> obtenerEsPedidoPorIdPedido(int idPedido) {
        return mEsPedidoDao.getPlatoPedido(idPedido);
    }

    


}
