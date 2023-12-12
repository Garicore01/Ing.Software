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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.Plato;
import es.unizar.eina.M42_comidas.database.PlatoDao;


public class M42_listarPlatosAdd extends AppCompatActivity {
    private PlatoViewModel mPlatoViewModel;

    private PlatoListAdapterPedidos mPlatoAdapter;
    Button mSaveButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Especifico la pantalla que tiene que mostrar.
        setContentView(R.layout.anyadir_platos);
        Intent intent = getIntent();

        RecyclerView recyclerView = findViewById(R.id.recyclerviewPlatosAdd);
        mPlatoAdapter = new PlatoListAdapterPedidos(new PlatoListAdapterPedidos.PlatoDiff());
        recyclerView.setAdapter(mPlatoAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        mPlatoViewModel.getAllPlatos().observe(this,platos -> {
            mPlatoAdapter.submitList(platos);
        });


        mSaveButton = findViewById(R.id.button4);
        mSaveButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            setResult(RESULT_OK, replyIntent);
            
            finish();
        });

    }


}
