/*package es.unizar.eina.M42_comidas.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.M42_comidas.database.Plato;

public class PlatoListAdapter extends ListAdapter<Plato, PlatoViewHolder> {
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PlatoListAdapter(@NonNull DiffUtil.ItemCallback<Plato> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlatoViewHolder.create(parent);
    }

    public Note getCurrent() {
        return getItem(getPosition());
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, int position) {

        Note current = getItem(position);
        holder.bind(current.getTitle());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });
    }


    static class NoteDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            //android.util.Log.d ( "NoteDiff" , "areItemsTheSame " + oldItem.getId() + " vs " + newItem.getId() + " " +  (oldItem.getId() == newItem.getId()));
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            //android.util.Log.d ( "NoteDiff" , "areContentsTheSame " + oldItem.getTitle() + " vs " + newItem.getTitle() + " " + oldItem.getTitle().equals(newItem.getTitle()));
            // We are just worried about differences in visual representation, i.e. changes in the title
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
*/
package es.unizar.eina.M42_comidas.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.M42_comidas.database.Pedido;


public class PedidoListAdapter extends ListAdapter<Pedido, PlatoViewHolder> {
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PedidoListAdapter(@NonNull DiffUtil.ItemCallback<Pedido> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlatoViewHolder.create(parent);
    }

    public Pedido getCurrent() {
        return getItem(getPosition());
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, int position) {
        Pedido current = getItem(position);
        holder.bind(current.getNombreCliente()); // Asegúrate de tener un método getNombre en la clase Plato

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });
    }

    static class PlatoDiff extends DiffUtil.ItemCallback<Pedido> {

        @Override
        public boolean areItemsTheSame(@NonNull Pedido oldItem, @NonNull Pedido newItem) {
            return oldItem.getIdPedido() == newItem.getIdPedido();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pedido oldItem, @NonNull Pedido newItem) {
            return oldItem.getNombreCliente().equals(newItem.getNombreCliente());
        }
    }
}
