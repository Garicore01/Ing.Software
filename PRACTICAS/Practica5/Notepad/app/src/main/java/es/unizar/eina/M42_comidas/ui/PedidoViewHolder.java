package es.unizar.eina.M42_comidas.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M42_comidas.R;

class PedidoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    private final TextView mNoteItemView;
    private final TextView mTelClient;
    private final TextView mDatePedido;



    private PedidoViewHolder(View itemView) {
        super(itemView);
        mNoteItemView = itemView.findViewById(R.id.nombre_cliente_lista);
        mTelClient = itemView.findViewById(R.id.telefono_lista);
        mDatePedido = itemView.findViewById(R.id.fecha_recogida_lista);

        itemView.setOnCreateContextMenuListener(this);
    }

    public void bind(String text, String text2, String text3) {
        mNoteItemView.setText(text);
        mTelClient.setText(text2);
        mDatePedido.setText(text3);
    }

    static PedidoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pedido_plantilla, parent, false);
        return new PedidoViewHolder(view);
    }

    // En este método esta la logica para mantener pulsado un plato y que aparezca la opción
    // de eliminar o editar.
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        menu.add(Menu.NONE, M42_listarPedidos.DELETE_ID, Menu.NONE, R.string.menu_deletePedido);
        menu.add(Menu.NONE, M42_listarPedidos.EDIT_ID, Menu.NONE, R.string.menu_editPedido);
        menu.add(Menu.NONE, M42_listarPedidos.MANDARMENSAJE, Menu.NONE, R.string.menu_mandarMensaje);
    }


}
