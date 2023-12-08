package es.unizar.eina.M42_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.Plato;


/** Pantalla principal de la aplicacion M42_comidas */
public class M42_comidas extends AppCompatActivity {
    private PlatoViewModel mPlatoViewModel;

    public static final int ACTIVITY_CREATE = 1;

    public static final int ACTIVITY_EDIT = 2;

    static final int INSERT_ID = Menu.FIRST;
    static final int DELETE_ID = Menu.FIRST + 1;
    static final int EDIT_ID = Menu.FIRST + 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);

        Button button_addPlato = findViewById(R.id.button_addPlato);
        button_addPlato.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(M42_comidas.this, M42_editarPlato.class);
            Log.d("Prueba", "EStoy en plato");
            startActivityForResult(intent, ACTIVITY_CREATE);

        }
        });

        Button button_listarPlato = findViewById(R.id.button_listarPlatos);
        button_listarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M42_comidas.this, M42_listarPlatos.class);
                startActivity(intent);
            }
        });

        Button button_addPedido = findViewById(R.id.button_addPedido);
        button_addPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M42_comidas.this, M42_editarPedido.class);
                Log.d("Prueba", "EStoy en pedido");
                startActivity(intent);
            }
        });

        Button button_listarPedido = findViewById(R.id.button_listarPedidos);
        button_listarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M42_comidas.this, M42_listarPedidos.class);
                startActivity(intent);
            }
        });
 
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Platos onActivityResult", "en onActivityResult");

        Bundle extras = data.getExtras();

        if (resultCode != RESULT_OK) {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        } else {
            switch (requestCode) {
                case ACTIVITY_CREATE:
                    Log.d("Platos onActivityResult", "ACTIVITY_CREATE");
                    Log.d("OBJETO PLATO CEADO 1", "SE HA CREADO EL OBJETO PLATO");
                    Plato newPlato = new Plato(extras.getString(M42_editarPlato.PLATO_NOMBRE)
                            , extras.getString(M42_editarPlato.PLATO_DESCRIPCION)
                            ,  extras.getString(M42_editarPlato.PLATO_PRECIO)
                            , extras.getString(M42_editarPlato.PLATO_CATEGORIA));
                    Log.d("OBJETO PLATO CEADO 2", "SE HA CREADO EL OBJETO PLATO");
                    mPlatoViewModel.insert(newPlato);
                    Log.d("OBJETO PLATO CEADO 3", "SE HA CREADO EL OBJETO PLATO");
                    break;

                case ACTIVITY_EDIT:
                    Log.d("Platos onActivityResult", "ACTIVITY_EDIT");
                    Log.d("ACTIVITY_EDIT", "Name: " + extras.getString(M42_editarPlato.PLATO_NOMBRE));
                    Log.d("ACTIVITY_EDIT", "Description: " + extras.getString(M42_editarPlato.PLATO_DESCRIPCION));
                    Log.d("ACTIVITY_EDIT", "Price: " + extras.getString(M42_editarPlato.PLATO_PRECIO));
                    Log.d("ACTIVITY_EDIT", "Category: " + extras.getString(M42_editarPlato.PLATO_CATEGORIA));

                    int id = extras.getInt(M42_editarPlato.PLATO_ID);
                    Plato updatedPlato = new Plato(extras.getString(M42_editarPlato.PLATO_NOMBRE)
                            , extras.getString(M42_editarPlato.PLATO_DESCRIPCION)
                            , extras.getString(M42_editarPlato.PLATO_PRECIO)
                            , extras.getString(M42_editarPlato.PLATO_CATEGORIA));
                    updatedPlato.setIdPlato(id);
                    mPlatoViewModel.update(updatedPlato);
                    break;
            }
        }
    }


}