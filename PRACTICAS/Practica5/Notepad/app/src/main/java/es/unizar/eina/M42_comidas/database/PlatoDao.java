package es.unizar.eina.M42_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definicion de un Data Access Object para los Platos */
@Dao
public interface PlatoDao {


    /**
     * Inserta un plato en la base de datos
     * @param plato
     * @return un valor entero con el id del plato insertado.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Plato plato);


    /**
     * Actualiza un plato en la base de datos
     * @param plato
     * @return un valor entero con el numero de filas modificadas.
     */
    @Update
    int update(Plato note);


    /**
     * Elimina un plato de la base de datos
     * @param plato
     * @return un valor entero con el numero de filas eliminadas.
     */
    @Delete
    int delete(Plato plato);


    /**
     * Devuelve todos los platos de la base de datos
     * @return una lista de platos
     */
    @Query("SELECT * FROM Plato")
    LiveData<List<Plato>> getAllPlatos();

    
    /**
     * Devuelve todos los platos de la base de datos ordenados por nombre
     * @return una lista de platos ordenados por nombre
     */
    @Query("SELECT * FROM Plato ORDER BY nombre ASC")
    LiveData<List<Plato>> getOrderedPlatos();
}