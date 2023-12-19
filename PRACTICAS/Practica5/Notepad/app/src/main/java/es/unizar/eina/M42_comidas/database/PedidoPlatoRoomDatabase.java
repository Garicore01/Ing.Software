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

@Database(entities = {Plato.class,Pedido.class,EsPedido.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class PedidoPlatoRoomDatabase extends RoomDatabase {

    public abstract PlatoDao PlatoDao();

    private static volatile PedidoPlatoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract PedidoDao PedidoDao();
    public abstract EsPedidoDao EsPedidoDao();

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

                EsPedidoDao daoEsPedido = INSTANCE.EsPedidoDao();
                daoEsPedido.deleteAll();


                Pedido pedido = new Pedido("Gariko","678903987",new Date(),"SOLICITADO");
                daoPedido.insert(pedido);
                pedido = new Pedido("Gariko2","678903982",new Date(),"SOLICITADO");
                daoPedido.insert(pedido);

                Plato plato = new Plato("Arroz con pollo", "Mierda",9.99,"Comida de mierda");
                Plato plato2 = new Plato("Pollo frito", "Mierda",9.0,"Comida de mierda");
                Plato plato3 = new Plato("Lentejas", "Mierda",5.85,"Comida de mierda");
                Plato plato4 = new Plato("Paella de marisco", "Mierda",19.99,"Comida de mierda");
                Plato plato5 = new Plato("Tacos de carne mixta", "Mierda",4.75,"Comida de mierda");
                Plato plato6 = new Plato("Algo random", "Mierda",6.5,"Comida de mierda");
                daoPlato.insert(plato);


                daoPlato.insert(plato2);
                
                daoPlato.insert(plato3);
                daoPlato.insert(plato4);
                daoPlato.insert(plato5);
                daoPlato.insert(plato6);
            });
        }
    };



}