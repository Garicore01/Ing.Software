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
public interface esPedidoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(esPedido esPedido);

    @Update
    int update(esPedido esPedido);

    @Delete
    int delete(esPedido esPedido);

    @Query("DELETE FROM esPedido")
    void deleteAll();

}



