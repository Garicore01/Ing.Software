package es.unizar.eina.M42_comidas.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "esPedido",
        primaryKeys = { "idPedido", "idPlato" },
        foreignKeys = {
                @ForeignKey(entity = Pedido.class,
                            parentColumns = "pedidoId",
                            childColumns = "pedidoId"),
                @ForeignKey(entity = Plato.class,
                            parentColumns = "idPlato",
                            childColumns = "idPlato")
        },
        indices = {
                @Index("idPedido"),
                @Index("idPlato")
        }
)
public class EsPedido {
    public final int pedidoId;
    public final int platoId;
    public final int numero;
    public final int precioTotal;
    /**
     * Constructor de la clase EsPedido.
     * inicializa los atributos de la clase pedidoId y platoId.
     */
    public EsPedido(final int pedidoId, final int platoId) {
        this.pedidoId = pedidoId;
        this.platoId = platoId;
    }

    /**
     * Setter del atributo numero.
     */
    public void setNumero(final int numero) {
        this.numero = numero;
    }

    /**
     * Setter del atributo precioTotal.
     */
    public void setPrecioTotal(final int precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Getter del atributo pedidoId.
     */
    public int getPedidoId() {
        return pedidoId;
    }

    /**
     * Getter del atributo platoId.
     */
    public int getPlatoId() {
        return platoId;
    }

    /**
     * Getter del atributo numero.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Getter del atributo precioTotal.
     */
    public int getPrecioTotal() {
        return precioTotal;
    }

}