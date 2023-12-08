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
import es.unizar.eina.M42_comidas.database.Plato;
import es.unizar.eina.M42_comidas.database.PlatoDao;

/** Pantalla principal de la aplicacion M42_comidas */
public class M42_listarPlatos extends AppCompatActivity {
    private PlatoViewModel mPlatoViewModel;

    private PlatoListAdapter mPlatoAdapter;

    public static final int ACTIVITY_CREATE = 1;

    public static final int EDIT_ID = 2;
    
    public static final int DELETE_ID = 3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_platos);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPlatos);
        mPlatoAdapter = new PlatoListAdapter(new PlatoListAdapter.PlatoDiff());
        recyclerView.setAdapter(mPlatoAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        mPlatoViewModel.getAllPlatos().observe(this,platos -> {
            mPlatoAdapter.submitList(platos);
        });

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
                //editPlato(current);
                return true;
        }
        return super.onContextItemSelected(item);
    }


}