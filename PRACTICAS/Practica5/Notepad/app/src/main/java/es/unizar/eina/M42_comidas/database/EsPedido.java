package es.unizar.eina.M42_comidas.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

/** Definicion de clase relacion entre pedido y plato*/
@Entity(tableName = "esPedido",
        primaryKeys = { "idPedido", "idPlato" },
        foreignKeys = {
                @ForeignKey(entity = Pedido.class,
                            parentColumns = "pedidoId",
                            childColumns = "pedidoId"),
                @ForeignKey(entity = Plato.class,
                            parentColumns = "idPlato",
                            childColumns = "platoId")
        },
        indices = {
                @Index("idPedido"),
                @Index("idPlato")
        }
)

public class EsPedido {
    public int pedidoId;
    public int platoId;
    public int numero;
    public int precio;
    /**
     * Constructor de la clase EsPedido.
     * inicializa los atributos de la clase pedidoId y platoId.
     * @param pedidoId
     * @param platoId
     */
    public EsPedido(final int pedidoId, final int platoId,final int cantidad) {
        this.pedidoId = pedidoId;
        this.platoId = platoId;
        this.numero = cantidad;
    }

    /**
     * Setter del atributo numero.
     * @param numero
     */
    public void setNumero(final int numero) {
        this.numero = numero;
    }

    /**
     * Setter del atributo precio.
     * @param precio
     */
    public void setPrecio(final int precio) {
        this.precio = precio;
    }

    /**
     * Getter del atributo pedidoId.
     * @return pedidoId
     */
    public int getPedidoId() {
        return pedidoId;
    }

    /**
     * Getter del atributo platoId.
     * @return platoId
     */
    public int getPlatoId() {
        return platoId;
    }

    /**
     * Getter del atributo numero.
     * @return numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Getter del atributo precio.
     * @return precio
     */

    public int getPrecio() {
        return precio;
    }

}