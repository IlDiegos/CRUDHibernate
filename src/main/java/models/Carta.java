/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author diegu
 */

@Entity
@Table(name="carta")
public class Carta implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    
    @JoinColumn (name="nombre")
    private String nombre;
    
    @JoinColumn (name="precio")
    private double precio;
    
    @OneToMany(mappedBy="carta", fetch=FetchType.EAGER)
    private List<Pedido> pedido;

    public Carta(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

   
    
    public Carta() {
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Carta{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + '}';
    }
    
    
    
    
    
    

}
