package es.unizar.eina.M42_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import es.unizar.eina.M42_comidas.R;


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
        Button button_addPlato = findViewById(R.id.button_addPlato);
        button_addPlato.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(M42_comidas.this, M42_editarPlato.class);
            startActivity(intent);
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
 
    }


}