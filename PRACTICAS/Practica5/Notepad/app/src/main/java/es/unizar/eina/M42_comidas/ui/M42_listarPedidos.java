package es.unizar.eina.M42_comidas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.EsPedido;
import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.PedidoDao;
import es.unizar.eina.send.SendAbstraction;
import es.unizar.eina.send.SendAbstractionImpl;


/** Pantalla principal de la aplicacion M42_comidas */
public class M42_listarPedidos extends AppCompatActivity {
    private PedidoViewModel mPedidoViewModel;

    private PlatoViewModel mPlatoViewModel;

    private EsPedidoViewModel mEsPedidoViewModel;


    private PedidoListAdapter mPedidoAdapter;

    private SendAbstraction mSendAbstraction;

    public static final int ACTIVITY_CREATE = 1;

    public static final int ACTIVITY_EDIT = 2;

    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private Spinner spinnerOrdenarPor;
    private String criterioSeleccionado;
    private GlobalState globalState;


    public static final int EDIT_ID = 2;

    public static final int DELETE_ID = 3;

    public static final int MANDARMENSAJE = 4;

    public static final String metodoSend = "sms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_pedidos);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPedidos);
        mPedidoAdapter = new PedidoListAdapter(new PedidoListAdapter.PedidoDiff());
        recyclerView.setAdapter(mPedidoAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);

        mEsPedidoViewModel = new ViewModelProvider(this).get(EsPedidoViewModel.class);

        mPedidoViewModel.getAllPedidos().observe(this,Pedidos -> {
            mPedidoAdapter.submitList(Pedidos);

        });

        mSendAbstraction = new SendAbstractionImpl(this, metodoSend);


        spinnerOrdenarPor = findViewById(R.id.PedidosspinnerOrdenarPor);

        // Crear un ArrayAdapter utilizando el array de recursos opciones_ordenamiento
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.criterios_ordenamiento_pedidos, android.R.layout.simple_spinner_item);

        // Especificar el diseño a utilizar cuando la lista de opciones aparezca
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinnerOrdenarPor.setAdapter(adapter);

        // Configurar el listener para manejar las selecciones del Spinner
        spinnerOrdenarPor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Aquí puedes manejar la selección del usuario
                criterioSeleccionado = parentView.getItemAtPosition(position).toString();
                // Puedes hacer lo que necesites con el criterio seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Método llamado cuando no se ha seleccionado nada
                criterioSeleccionado = "Fecha";
            }
        });

        // Añade el listener al botón "Ordenar por"
        Button botonOrdenar = findViewById(R.id.boton_ordenar);
        botonOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FILTRO DE ORDENAR", criterioSeleccionado);

                // Ordena los pedidos según el criterio seleccionado
                mPedidoViewModel.obtenerPedidosOrdenados(criterioSeleccionado).observe(M42_listarPedidos.this, new Observer<List<Pedido>>() {
                    @Override
                    public void onChanged(List<Pedido> pedidos) {
                        // Actualiza tu adaptador con la nueva lista de pedidos
                        Log.d("ACTUALIZO", "PANTALLA");
                        mPedidoAdapter.submitList(pedidos);
                    }
                });
            }
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
            case MANDARMENSAJE:
            if(current.getEstado().equals("SOLICITADO")){
                mSendAbstraction.send(current.getTelefonoCliente().toString(),"Su pedido se ha registrado correctamente.");
            }else if (current.getEstado().equals("PREPARADO")) {
                mSendAbstraction.send(current.getTelefonoCliente().toString(),"Su ha sido preparado y ya puede pasarse a recogerlo a la hora seleccionada.");
            }else if (current.getEstado().equals("RECOGIDO")) {
                mSendAbstraction.send(current.getTelefonoCliente().toString(),"Su pedido ha sido recogido ¡Gracias por confiar en nosotros!");
            }
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
                        extras.getString(M42_editarPedido.PEDIDO_ESTADO));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    Log.d("Prueba","voy a meter el  pedido nuevo ");
                    newPedido.setIdPedido(id);
                    
                    mPedidoViewModel.update(newPedido);
                    globalState = GlobalState.getInstance();
                    Map<Integer, ElemEsPedido> map = globalState.getCantidadPlatosMap();
                    Log.d("Prueba","voy a meter los platos del pedido nuevo ");
                    for (Map.Entry<Integer, ElemEsPedido> entry : map.entrySet()) {
                        Integer key = entry.getKey();
                        ElemEsPedido value = entry.getValue();
                        Log.d("Prueba","voy a meter el plato "+key+" del pedido "+id+" con cantidad "+value.cantidad+" y precio "+value.precio);
                        EsPedido esPedido = new EsPedido(id,key, value.cantidad, value.precio);
                        mEsPedidoViewModel.update(esPedido);
                    }
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
        globalState = GlobalState.getInstance();
        globalState.vaciarMapa();
        EsPedidoViewModel mEsPedidoViewModel = new ViewModelProvider(this).get(EsPedidoViewModel.class);
        LiveData<List<EsPedido>> listaPlatos = mEsPedidoViewModel.getAllPlatosFromPedido(current.getIdPedido());


        listaPlatos.observe(this, new Observer<List<EsPedido>>() {
            @Override
            public void onChanged(List<EsPedido> esPedidos) {
                for (EsPedido esPedido : esPedidos) {
                    ElemEsPedido elem = new ElemEsPedido();
                    elem.platoId = esPedido.getPlatoId();
                    elem.cantidad = esPedido.getNumero();
                    elem.precio = esPedido.getPrecio();
                    globalState.agregarAlMapa(esPedido.getPlatoId(),elem);
                }
            }
        });
        startActivityForResult(intent, ACTIVITY_EDIT);
    }


}