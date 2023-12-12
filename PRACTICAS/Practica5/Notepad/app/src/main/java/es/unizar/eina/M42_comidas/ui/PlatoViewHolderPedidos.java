package es.unizar.eina.M42_comidas.ui;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M42_comidas.R;

public class PlatoViewHolderPedidos extends RecyclerView.ViewHolder {
    private final TextView mNombreItemView;
    private final TextView mCantidadItemView;
    private Button incrementButton;
    private Button decrementButton;
    private int count = 0;


    
    private PlatoViewHolderPedidos(View itemView) {
        super(itemView);
        mNombreItemView = itemView.findViewById(R.id.nombre_plato);
        mCantidadItemView = itemView.findViewById(R.id.num_cantidad_plato);
        incrementButton = itemView.findViewById(R.id.boton_anyadir);
        decrementButton = itemView.findViewById(R.id.boton_quitar);
        
        
        incrementButton.setOnClickListener(v -> {
            count++;
            mCantidadItemView.setText(String.valueOf(count));
            Toast.makeText(itemView.getContext(), "Count: " + count, Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Count: " + count);
        });

        decrementButton.setOnClickListener(v -> {
            if(count > 0){
                count--;
            }
            mCantidadItemView.setText(String.valueOf(count));
            Toast.makeText(itemView.getContext(), "Count: " + count, Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Count: " + count);
        });

    }

    public void bind(String text) {
        mNombreItemView.setText(text);
        mCantidadItemView.setText(String.valueOf(count));
    }

    static PlatoViewHolderPedidos create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seleccionar_plato_plantilla, parent, false);
        return new PlatoViewHolderPedidos(view);
    }
    

}
