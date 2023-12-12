package es.unizar.eina.M42_comidas.ui;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.Plato;
import es.unizar.eina.M42_comidas.database.PlatoDao;


public class M42_listarPlatosAdd extends AppCompatActivity {
    private PlatoViewModel mPlatoViewModel;

    private PlatoListAdapterPedidos mPlatoAdapter;

    public static final int ACTIVITY_CREATE = 1;
    public static final int ACTIVITY_EDIT = 2;

    public static final int EDIT_ID = 2;
    
    public static final int DELETE_ID = 3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Especifico la pantalla que tiene que mostrar.
        setContentView(R.layout.anyadir_platos);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewPlatosAdd);
        mPlatoAdapter = new PlatoListAdapterPedidos(new PlatoListAdapterPedidos.PlatoDiff());
        recyclerView.setAdapter(mPlatoAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        mPlatoViewModel.getAllPlatos().observe(this,platos -> {
            mPlatoAdapter.submitList(platos);
        });

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
            switch (requestCode) {
                case ACTIVITY_CREATE:
                    Plato newPlato = new Plato(extras.getString(M42_editarPlato.PLATO_NOMBRE)
                            , extras.getString(M42_editarPlato.PLATO_DESCRIPCION)
                            ,  extras.getString(M42_editarPlato.PLATO_PRECIO)
                            , extras.getString(M42_editarPlato.PLATO_CATEGORIA));
                    mPlatoViewModel.insert(newPlato);
                    break;
                case ACTIVITY_EDIT:
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


    public boolean onContextItemSelected(MenuItem item) {
        Plato current = mPlatoAdapter.getCurrent();
        switch (item.getItemId()) {
            case DELETE_ID:
                Toast.makeText(
                        getApplicationContext(),
                        "Deleting " + current.getNombre(),
                        Toast.LENGTH_LONG).show();
                mPlatoViewModel.delete(current);
                return true;
            case EDIT_ID:
                editarPlato(current);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void editarPlato(Plato current) {
        Intent intent = new Intent(this, M42_editarPlato.class);
        intent.putExtra(M42_editarPlato.PLATO_NOMBRE, current.getNombre());
        intent.putExtra(M42_editarPlato.PLATO_DESCRIPCION, current.getDescripcion());
        intent.putExtra(M42_editarPlato.PLATO_CATEGORIA, current.getCategoria());
        intent.putExtra(M42_editarPlato.PLATO_PRECIO, current.getPrecio());
        intent.putExtra(M42_editarPlato.PLATO_ID, current.getIdPlato());
        startActivityForResult(intent, ACTIVITY_EDIT);
    }

}
