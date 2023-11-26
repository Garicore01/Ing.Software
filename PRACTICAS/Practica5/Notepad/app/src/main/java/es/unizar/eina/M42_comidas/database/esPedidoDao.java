package es.unizar.eina.M42_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import es.unizar.eina.M42_comidas.database.EsPedido;

import java.util.List;

/** Definicion de un Data Access Object para la tabla N:M entre Plato y Pedido */
@Dao
public interface esPedidoDao {

    /**
     * Inserta una relacion Plato-Pedido en la base de datos
     * @param esPedido
     * @return un valor entero con el id de la relacion insertada.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(EsPedido esPedido);


    /**
     * Actualiza una relacion Plato-Pedido en la base de datos
     * @param esPedido
     * @return un valor entero con el numero de filas modificadas.
     */
    @Update
    int update(EsPedido esPedido);


    /**
     * Elimina una relacion Plato-Pedido de la base de datos
     * @param esPedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    @Delete
    int delete(EsPedido esPedido);


    /**
     * Elimina todas las relaciones Plato-Pedido de la base de datos
     */
    @Query("DELETE FROM esPedido")
    void deleteAll();

}



