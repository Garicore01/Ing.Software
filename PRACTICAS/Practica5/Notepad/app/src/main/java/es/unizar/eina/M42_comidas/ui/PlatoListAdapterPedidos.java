
package es.unizar.eina.M42_comidas.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.Plato;
import es.unizar.eina.M42_comidas.R;


public class PlatoListAdapterPedidos extends ListAdapter<Plato, PlatoViewHolderPedidos> {
    private int position;
    

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PlatoListAdapterPedidos(@NonNull DiffUtil.ItemCallback<Plato> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PlatoViewHolderPedidos onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlatoViewHolderPedidos.create(parent);
    }

    public Plato getCurrent() {
        return getItem(getPosition());
    }

    @Override
    public void onBindViewHolder(PlatoViewHolderPedidos holder, int position) {
        Plato current = getItem(position);
        holder.bind(current.getNombre(),current.getIdPlato());

    }

    static class PlatoDiff extends DiffUtil.ItemCallback<Plato> {

        @Override
        public boolean areItemsTheSame(@NonNull Plato oldItem, @NonNull Plato newItem) {
            return oldItem.getIdPlato() == newItem.getIdPlato();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Plato oldItem, @NonNull Plato newItem) {
            return oldItem.getNombre().equals(newItem.getNombre()) &&
                    oldItem.getDescripcion().equals(newItem.getDescripcion()) &&
                    oldItem.getCategoria().equals(newItem.getCategoria()) &&
                    oldItem.getPrecio().equals(newItem.getPrecio());

        }


    }
}
