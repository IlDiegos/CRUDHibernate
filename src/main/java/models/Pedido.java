package models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "nombre_pedido")
    private String nombre_pedido;

    @JoinColumn(name = "fecha")
    private Date fecha;

    @JoinColumn(name = "recogido")
    private boolean recogido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Carta carta;

    public Pedido() {
    }

    public Pedido(int id, int producto_id, String nombre_pedido, Date fecha, boolean recogido) {
        this.id = id;
        this.nombre_pedido = nombre_pedido;
        this.fecha = fecha;
        this.recogido = recogido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_pedido() {
        return nombre_pedido;
    }

    public void setNombre_pedido(String nombre_pedido) {
        this.nombre_pedido = nombre_pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isRecogido() {
        return recogido;
    }

    public void setRecogido(boolean recogido) {
        this.recogido = recogido;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ",  nombre_pedido=" + nombre_pedido + ", fecha=" + fecha + ", recogido=" + recogido + '}';
    }

}
