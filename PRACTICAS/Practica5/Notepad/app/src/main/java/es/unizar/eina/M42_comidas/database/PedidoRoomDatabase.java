package es.unizar.eina.M42_comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.TypeConverters;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {Pedido.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})

public abstract class PedidoRoomDatabase extends RoomDatabase {

    public abstract PedidoDao PedidoDao();

    private static volatile PedidoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PedidoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PedidoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PedidoRoomDatabase.class, "Pedido_database")
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
                // If you want to start with more Pedidos, just add them.
                PedidoDao dao = INSTANCE.PedidoDao();
                dao.deleteAll();

            });
        }
    };

}
