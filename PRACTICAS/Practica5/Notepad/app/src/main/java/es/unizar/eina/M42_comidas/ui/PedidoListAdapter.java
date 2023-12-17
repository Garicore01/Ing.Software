
package es.unizar.eina.M42_comidas.ui;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.lifecycle.Observer;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import es.unizar.eina.M42_comidas.database.EsPedido;
import es.unizar.eina.M42_comidas.database.Pedido;


public class PedidoListAdapter extends ListAdapter<Pedido, PedidoViewHolder> {
    private int position;
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");


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
    public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PedidoViewHolder.create(parent);
    }

    public Pedido getCurrent() {
        return getItem(getPosition());
    }

    @Override
    public void onBindViewHolder(PedidoViewHolder holder, int position) {
        Pedido current = getItem(position);


        holder.bind(current.getNombreCliente(),current.getTelefonoCliente(),formato.format(current.getFechaRecogida()));
        
        holder.itemView.setOnLongClickListener(v -> {
            Log.d("PedidoListAdapter", "Evento de click largo");
            setPosition(holder.getAdapterPosition());
            return false;
        });
    }

    static class PedidoDiff extends DiffUtil.ItemCallback<Pedido> {

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
