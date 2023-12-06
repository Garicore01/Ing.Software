package es.unizar.eina.M42_comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Date;
import androidx.room.TypeConverters;

@Database(entities = {Plato.class,Pedido.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class PedidoPlatoRoomDatabase extends RoomDatabase {

    public abstract PlatoDao PlatoDao();

    private static volatile PedidoPlatoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract PedidoDao PedidoDao();

    static PedidoPlatoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PedidoPlatoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PedidoPlatoRoomDatabase.class, "PedidoPlato_database")
                            .addCallback(sDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more Platos, just add them.

                PedidoDao daoPedido = INSTANCE.PedidoDao();
                daoPedido.deleteAll();
                
                PlatoDao daoPlato = INSTANCE.PlatoDao();
                daoPlato.deleteAll();


                Pedido pedido = new Pedido("Gariko","678903987",new Date(),"Espera");
                daoPedido.insert(pedido);
                pedido = new Pedido("Gariko2","678903987",new Date(),"Recogido");
                daoPedido.insert(pedido);

                Plato plato = new Plato("Arroz con pollo", "Mierda",5,"Comida de mierda");
                daoPlato.insert(plato);

            });
        }
    };



}