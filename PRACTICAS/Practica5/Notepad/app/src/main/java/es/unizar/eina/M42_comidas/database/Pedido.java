package es.unizar.eina.M42_comidas.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import java.util.Date;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/** Clase anotada como entidad que representa una nota y que consta de título y cuerpo */
@Entity(tableName = "pedido")
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idPedido")
    private int idPedido;

    @NonNull
    @ColumnInfo(name = "nombreCliente")
    private String nombreCliente;

    @NonNull
    @ColumnInfo(name = "telefonoCliente")
    private String telefonoCliente;

    @NonNull
    @ColumnInfo(name = "fechaRecogida")
    private Date fechaRecogida;

    @ColumnInfo(name = "estado")
    private String estado;

    public Pedido(@NonNull String nombre, String telefono, String estado) {
        this.nombreCliente = nombre;
        this.telefonoCliente = telefono;
        this.estado = estado;
    }

    /** Devuelve el identificador de la nota */
    public int getId(){
        return this.idPedido;
    }

    /** Permite actualizar el identificador de un pedido */
    public void setId(int idPedido) {
        this.idPedido = idPedido;
    }

    /** Devuelve el nombre de cliente que ha realizado el pedido */
    public String getNombre(){
        return this.nombreCliente;
    }

    /** Devuelve el estado del pedido */
    public String getEstado(){
        return this.estado;
    }

    /** Devuelve el telefono asociado al pedido */
    public String getTelefono() {
        return telefonoCliente;
    }
}
