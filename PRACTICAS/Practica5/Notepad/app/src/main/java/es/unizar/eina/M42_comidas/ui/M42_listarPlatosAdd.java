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
import java.util.Map;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.Plato;
import es.unizar.eina.M42_comidas.database.PlatoDao;


public class M42_listarPlatosAdd extends AppCompatActivity {
    private PlatoViewModel mPlatoViewModel;

    private PlatoListAdapterPedidos mPlatoAdapter;
    




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


        // mSaveButton = findViewById(R.id.button6);
        // mSaveButton.setOnClickListener(view -> {
        //     Intent replyIntent = new Intent();
        //     if (TextUtils.isEmpty(mNombreText.getText())) {
        //         setResult(RESULT_CANCELED, replyIntent);
        //     } else {
        //         replyIntent.putExtra(M42_editarPedido.PEDIDO_NOMBRE_CLIENTE, mNombreText.getText().toString());
        //         replyIntent.putExtra(M42_editarPedido.PEDIDO_TELEFONO, mTelefonoText.getText().toString());
        //         replyIntent.putExtra(M42_editarPedido.PEDIDO_FECHA_RECOGIDA, mFechaText.getText().toString()); //comprobar error
        //         if (mId!=null) {
        //             replyIntent.putExtra(M42_editarPedido.PEDIDO_ID, mId.intValue());
        //         }
        //         setResult(RESULT_OK, replyIntent);
        //     }
        //     finish();
        // });

    }


}
