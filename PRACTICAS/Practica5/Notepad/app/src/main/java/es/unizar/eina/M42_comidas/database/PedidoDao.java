package es.unizar.eina.M42_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definición de un Data Access Object para las notas */
@Dao
public interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Pedido pedido);

    @Update
    int update(Pedido pedido);

    @Delete
    int delete(Pedido pedido);

    @Query("DELETE FROM pedidos")
    void deleteAll();

    @Query("SELECT * FROM pedido")
    LiveData<List<Pedido>> getAllPedidos();

    @Query("SELECT * FROM pedido ORDER BY nombreCliente ASC")
    LiveData<List<Pedido>> getAllPedidosOrderedByNombre();

    @Query("SELECT * FROM pedido ORDER BY telefonoCliente ASC")
    LiveData<List<Pedido>> getAllPedidosOrderedByTelefono();

    @Query("SELECT * FROM pedido ORDER BY fechaRecogida ASC")
    LiveData<List<Pedido>> getAllPedidosOrderedByDate();


}



