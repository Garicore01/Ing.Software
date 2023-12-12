package es.unizar.eina.M42_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import es.unizar.eina.M42_comidas.R;

/** Pantalla principal de la aplicacion M42_comidas */
public class M42_editarPedido extends AppCompatActivity {
    public static final String PEDIDO_NOMBRE_CLIENTE = "title";
    public static final String PEDIDO_TELEFONO = "666666666";
    public static final String PEDIDO_FECHA_RECOGIDA = "";
    public static final String PEDIDO_ID = "id";


    public static final int ACTIVITY_CREATE = 1;

    public static final int ACTIVITY_EDIT = 2;

    public static final int ACTIVITY_ADD_PLATOS_PEDIDO = 3;

    private EditText mNombreText;
    private EditText mTelefonoText;
    private EditText mFechaText;
    private Integer mId;
    private String fechaHora;

    Button mBotonAnyadirPlatos;
    Button mSaveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pedido);

        mNombreText = findViewById(R.id.nombre_cliente_crear_pedido);
        mTelefonoText = findViewById(R.id.telefono_crear_pedido);
        mFechaText = findViewById(R.id.fecha_recogida_crear_pedido);
        //mFechaText = findViewById(R.id.fecha_recogida_crear_pedido);
        mBotonAnyadirPlatos = findViewById(R.id.boton_anyadir_platos);

         // Configura el click en el EditText para mostrar el DatePickerDialog
        mFechaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePickerDialog();
            }
        });

        // Configura el click en el EditText para mostrar el DatePickerDialog
        mBotonAnyadirPlatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(M42_editarPedido.this, M42_listarPlatosAdd.class);
            startActivityForResult(intent, ACTIVITY_ADD_PLATOS_PEDIDO);
            }
    
        });

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
        

        populateFields();
    }

    private void populateFields () {
        mId = null;
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            mNombreText.setText(extras.getString(M42_editarPedido.PEDIDO_NOMBRE_CLIENTE));
            mTelefonoText.setText(extras.getString(M42_editarPedido.PEDIDO_TELEFONO));
            mFechaText.setText(extras.getString(M42_editarPedido.PEDIDO_FECHA_RECOGIDA));
            mId = extras.getInt(M42_editarPedido.PEDIDO_ID);
        }
    }
    private void mostrarDatePickerDialog() {
        // Obtiene la fecha actual
        Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int día = calendario.get(Calendar.DAY_OF_MONTH);
    
        // Crea un DatePickerDialog y configura la acción al seleccionar una fecha
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Obtiene el día de la semana
                        Calendar selectedDate = new GregorianCalendar(year, month, dayOfMonth);
                        int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK);
                        // Verifica que el día seleccionado esté en el rango permitido
                        if (dayOfWeek != Calendar.MONDAY) {
                            // Actualiza el texto del EditText con la fecha seleccionada
                            fechaHora = dayOfMonth + "/" + (month + 1) + "/" + year;

    
                            // Llama a la función para mostrar el TimePickerDialog
                            mostrarTimePickerDialog();
                        } else {
                            // Muestra un mensaje indicando que el día seleccionado no es válido
                            Toast.makeText(getApplicationContext(), R.string.fecha_incorrecta,Toast.LENGTH_LONG).show();                    }
                    }
                }, año, mes, día);
    
        // Muestra el DatePickerDialog
        datePickerDialog.show();
    }
    
    
        private void mostrarTimePickerDialog() {
            // Obtiene la hora actual
            Calendar calendario = Calendar.getInstance();
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minuto = calendario.get(Calendar.MINUTE);
    
            // Crea un TimePickerDialog y configura la acción al seleccionar una hora
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            if (hourOfDay >= 19 && hourOfDay < 23) {
                                // Actualiza el texto del EditText con la hora seleccionada
                                fechaHora = fechaHora + " " + hourOfDay + ":" + minute;
                                mFechaText.setText(fechaHora);
                            } else {
                                // Muestra un mensaje indicando que la hora seleccionada no es válida
                                Toast.makeText(getApplicationContext(), R.string.hora_incorrecta,Toast.LENGTH_LONG).show();

                            }
                        }
                    }, hora, minuto, true);
    
            // Muestra el TimePickerDialog
            timePickerDialog.show();
        }
}