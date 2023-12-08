package es.unizar.eina.M42_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import es.unizar.eina.M42_comidas.R;

/** Pantalla principal de la aplicacion M42_comidas */
public class M42_editarPlato extends AppCompatActivity {
    public static final String PLATO_NOMBRE = "title";
    public static final String PLATO_CATEGORIA = "Primero";
    public static final String PLATO_PRECIO = "10.0";
    public static final String PLATO_DESCRIPCION = "body";
    public static final String PLATO_ID = "id";

    private EditText mNombreText;
    private EditText mCategoriaText;
    private EditText mDescripcionText;
    private EditText mPrecio;
    private EditText mId;

    Button mSaveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_platos);

        mNombreText = findViewById(R.id.nombre_plato_crear_plato);
        mCategoriaText = findViewById(R.id.categoria_plato_crear_plato);
        mPrecio = findViewById(R.id.precio_plato_crear_plato);
        mDescripcionText = findViewById(R.id.descripcion_plato_crear_plato);
       

        mSaveButton = findViewById(R.id.button2);
        mSaveButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mNombreText.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(M42_editarPlato.PLATO_NOMBRE, mNombreText.getText().toString());
                replyIntent.putExtra(M42_editarPlato.PLATO_DESCRIPCION, mDescripcionText.getText().toString());
                replyIntent.putExtra(M42_editarPlato.PLATO_PRECIO, mPrecio.getText()); //comprobar error
                replyIntent.putExtra(M42_editarPlato.PLATO_CATEGORIA, mCategoriaText.getText().toString());
                if (mId!=null) {
                    replyIntent.putExtra(M42_editarPlato.PLATO_ID, mId.intValue());
                }
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });


    }




}