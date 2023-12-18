package es.unizar.eina.M42_comidas.database;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/** Definicion de clase repositorio para los pedidos */
public class PedidoPlatoRepository {
    private final long TIMEOUT = 15000;
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
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);
        //final CountDownLatch latch = new CountDownLatch(1); // Para esperar a que se complete la inserción
        //final long[] result = {0};
    
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value = mPedidoDao.insert(pedido);
            result.set(value);
            resource.release();
            //latch.countDown(); // Liberar el contador
        });
    
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }
    
        return result.get(); // Devolver el id del pedido insertado
    }

    /** Modifica una nota
     * @param pedido
     * @return un valor entero con el numero de filas modificadas.
     */
    public int update(Pedido pedido) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mPedidoDao.update(pedido));
            resource.release();
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e ){
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }
        return result.get();
    }

    /** Elimina un pedido
     * @param pedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(Pedido pedido) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
           result.set(mPedidoDao.delete(pedido));
           resource.release();
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e ){
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }
        return result.get();
    }

    /**
     * @return Devuelve la lista de Pedidos ordenados por fecha
     */
    public LiveData<List<Pedido>> obtenerPedidosOrdenados(String criterio) {
        switch (criterio) {
            case "FECHA":
                return mPedidoDao.getAllPedidosOrderedByDate();
            case "NUMERO":
                return mPedidoDao.getAllPedidosOrderedByTelefono();
            case "NOMBRE":
                return mPedidoDao.getAllPedidosOrderedByNombre();
            default:
                return mPedidoDao.getAllPedidosOrderedByDate();
        }

    }

    /**
     *
     * @param filtro
     * @return lista de pedidos filtrados.
     */
    public LiveData<List<Pedido>> obtenerPedidosFiltrado(String filtro) {
        return mPedidoDao.getAllPedidosFiltered(filtro);
    }

    /**
     *
     * @param filtro
     * @param criterio
     * @return lista de pedidos filtrados y ordenados.
     */
    public LiveData<List<Pedido>> obtenerPedidosFiltradoYOrdenado(String filtro, String criterio) {
        return mPedidoDao.getAllPedidosFilteredAndOrdered(filtro, criterio);
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
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value = mPlatoDao.insert(plato);
            result.set(value);
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del plato insertado
    }


    /** Modifica una nota
     * @param plato
     * @return un valor entero con el numero de filas modificadas.
     */
    public int update(Plato plato) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mPlatoDao.update(plato));
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del plato actualizado
    }

    /** Elimina una nota
     * @param plato
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(Plato plato) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mPlatoDao.delete(plato));
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del plato eliminado
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

    /**
     * @return Devuelve la lista de Platos ordenados por categoria y nombre
     */
    public LiveData<List<Plato>> obtenerPlatosPorCategoriayNombre() {
        return mPlatoDao.getOrderedPlatosByCategoyAndName();
    }

    public LiveData<List<Plato>> obtenerPlatosFiltradoYOrdenado(String filtro, String criterio) {
        return mPlatoDao.getAllPlatosFilteredAndOrdered(filtro,criterio);
    }
    //-----------------------------------ESPEDIDO-----------------------------------------------------------

    /** Inserta un plato en un pedido
     * @param plato
     * @return un valor entero largo con el identificador del plato que se ha creado.
     */
    public long insert(EsPedido esPedido) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value = mEsPedidoDao.insert(esPedido);
            result.set(value);
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del esPedido insertado
    }

    /** Modifica una nota
     * @param esPedido
     * @return un valor entero con el numero de filas modificadas.
     */
    public long update(EsPedido esPedido) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mEsPedidoDao.update(esPedido));
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del esPedido actualizado
    }

    /** Elimina la relación entre Pedido y Plato
     * @param esPedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(EsPedido esPedido) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mEsPedidoDao.delete(esPedido));
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del esPedido eliminado
    }

    public LiveData<List<EsPedido>> obtenerEsPedidoPorIdPedido(int idPedido) {
        return mEsPedidoDao.getPlatoPedido(idPedido);
    }


    


}
