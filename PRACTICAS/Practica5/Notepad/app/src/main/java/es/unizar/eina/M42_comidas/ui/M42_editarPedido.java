package es.unizar.eina.M42_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import es.unizar.eina.M42_comidas.R;

/** Pantalla principal de la aplicacion M42_comidas */
public class M42_editarPedido extends AppCompatActivity {
    public static final String PEDIDO_NOMBRE_CLIENTE = "title";
    public static final String PEDIDO_TELEFONO = "Primero";
    public static final String PEDIDO_FECHA_RECOGIDA = "10.0";
    public static final String PEDIDO_ID = "id";

    private EditText mNombreText;
    private EditText mTelefonoText;
    private EditText mFechaText;
    private Integer mId;

    Button mSaveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pedido);

        mNombreText = findViewById(R.id.nombre_cliente_crear_pedido);
        mTelefonoText = findViewById(R.id.telefono_crear_pedido);
        mFechaText = findViewById(R.id.fecha_recogida_crear_pedido);


        mSaveButton = findViewById(R.id.button6);
        mSaveButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mNombreText.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(M42_editarPedido.PEDIDO_NOMBRE_CLIENTE, mNombreText.getText().toString());
                replyIntent.putExtra(M42_editarPedido.PEDIDO_TELEFONO, mTelefonoText.getText().toString());
                replyIntent.putExtra(M42_editarPedido.PEDIDO_FECHA_RECOGIDA, mFechaText.getText().toString()); //comprobar error
                if (mId!=null) {
                    replyIntent.putExtra(M42_editarPedido.PEDIDO_ID, mId.intValue());
                }
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });


    }
}