package es.unizar.eina.M42_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definicion de DAO para los platos */
@Dao
public interface PlatoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Plato plato);

    @Update
    int update(Plato note);

    @Delete
    int delete(Plato plato);

    @Query("SELECT * FROM Plato")
    LiveData<List<Plato>> getAllPlatos();


    @Query("SELECT * FROM Plato ORDER BY nombre ASC")
    LiveData<List<Plato>> getOrderedPlatos();
}