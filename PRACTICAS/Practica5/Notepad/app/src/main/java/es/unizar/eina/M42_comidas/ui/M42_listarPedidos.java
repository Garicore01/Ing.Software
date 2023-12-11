package es.unizar.eina.M42_comidas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.PedidoDao;


/** Pantalla principal de la aplicacion M42_comidas */
public class M42_listarPedidos extends AppCompatActivity {
    private PedidoViewModel mPedidoViewModel;


    private PedidoListAdapter mPedidoAdapter;

    public static final int ACTIVITY_CREATE = 1;

    public static final int ACTIVITY_EDIT = 2;

    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");


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
                editarPedido(current);
                return true;
        }
        return super.onContextItemSelected(item);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Toast.makeText(
                getApplicationContext(),
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show();
        } else {
            Bundle extras = data.getExtras();
            Log.d("Prueba","he vuelto de actualizar");
           
            switch (requestCode) {
                case ACTIVITY_EDIT:
                    Pedido newPedido;
                    int id = extras.getInt(M42_editarPedido.PEDIDO_ID);
                    try {
                        newPedido = new Pedido(extras.getString(M42_editarPedido.PEDIDO_NOMBRE_CLIENTE)
                        , extras.getString(M42_editarPedido.PEDIDO_TELEFONO)
                        ,  formato.parse(extras.getString(M42_editarPedido.PEDIDO_FECHA_RECOGIDA)),
                        "En preparacion");
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    Log.d("Prueba","voy a meter el  pedido nuevo ");
                    newPedido.setIdPedido(id);
                    mPedidoViewModel.update(newPedido);
                    Log.d("Prueba","ya esta metido  pedido nuevo ");
                    break;
            }
            Log.d("Prueba","he salido de actualizar");
        }
    }



    private void editarPedido(Pedido current) {
        Intent intent = new Intent(this, M42_editarPedido.class);
        intent.putExtra(M42_editarPedido.PEDIDO_NOMBRE_CLIENTE, current.getNombreCliente());
        intent.putExtra(M42_editarPedido.PEDIDO_TELEFONO, current.getTelefonoCliente());
        intent.putExtra(M42_editarPedido.PEDIDO_FECHA_RECOGIDA, formato.format(current.getFechaRecogida()));
        intent.putExtra(M42_editarPedido.PEDIDO_ID, current.getIdPedido());
        startActivityForResult(intent, ACTIVITY_EDIT);
    }


}