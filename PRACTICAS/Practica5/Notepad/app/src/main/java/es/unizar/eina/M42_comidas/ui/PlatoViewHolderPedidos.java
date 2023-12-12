package es.unizar.eina.M42_comidas.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M42_comidas.R;

public class PlatoViewHolderPedidos extends RecyclerView.ViewHolder {
    private final TextView mNoteItemView;



    private PlatoViewHolderPedidos(View itemView) {
        super(itemView);
        mNoteItemView = itemView.findViewById(R.id.nombre_plato);

    }

    public void bind(String text) {
        mNoteItemView.setText(text);
    }

    static PlatoViewHolderPedidos create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seleccionar_plato_plantilla, parent, false);
        return new PlatoViewHolderPedidos(view);
    }

}
