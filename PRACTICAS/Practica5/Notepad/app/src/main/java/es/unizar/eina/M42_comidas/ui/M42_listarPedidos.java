package es.unizar.eina.M42_comidas.ui;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.PedidoDao;


/** Pantalla principal de la aplicacion M42_comidas */
public class M42_listarPedidos extends AppCompatActivity {
    private PedidoViewModel mPedidoViewModel;

    private PedidoListAdapter mPedidoAdapter;

    public static final int ACTIVITY_CREATE = 1;

    public static final int EDIT_ID = 2;

    public static final int DELETE_ID = 3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_pedidos);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPedidos);
        mPedidoAdapter = new PedidoListAdapter(new PedidoListAdapter.PedidoDiff());
        recyclerView.setAdapter(mPedidoAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        mPedidoViewModel.getAllPedidos().observe(this,Pedidos -> {
            mPedidoAdapter.submitList(Pedidos);
        });

    }

    public boolean onContextItemSelected(MenuItem item) {
        Pedido current = mPedidoAdapter.getCurrent();
        switch (item.getItemId()) {
            case DELETE_ID:
                Toast.makeText(
                        getApplicationContext(),
                        "Deleting " + current.getIdPedido(),
                        Toast.LENGTH_LONG).show();
                mPedidoViewModel.delete(current);
                return true;
            case EDIT_ID:
                //M42_editarPedido(current);
                return true;
        }
        return super.onContextItemSelected(item);
    }


}