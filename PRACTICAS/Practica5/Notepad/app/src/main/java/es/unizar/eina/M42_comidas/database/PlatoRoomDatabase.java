package es.unizar.eina.M42_comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Plato.class}, version = 1, exportSchema = false)
public abstract class PlatoRoomDatabase extends RoomDatabase {

    public abstract PlatoDao PlatoDao();

    private static volatile PlatoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PlatoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlatoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PlatoRoomDatabase.class, "Plato_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more Platos, just add them.
                PlatoDao dao = INSTANCE.PlatoDao();
                dao.deleteAll();

                Pedido pedido = new Pedido("Gariko","678903987",new Date(),"Espera"); //Se pone X para posteriormente añadir los atributos
                dao.insert(pedido);
                pedido = new Pedido("Gariko2","678903987",new Date(),"Recogido");
                dao.insert(pedido);

            });
        }
    };

}
