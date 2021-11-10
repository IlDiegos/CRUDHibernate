/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.hibernatecru;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import models.Carta;
import models.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author diegu
 */
public class Main {

    static Session s = HibernateUtil.getSessionFactory().openSession();

    public static void main(String[] args) {
        java.util.Date utilDate = new java.util.Date();
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int num;
        Pedido p = new Pedido();

        while (!salir) {
            System.out.println("\n \n");
            System.out.println("1. Crear pedido.");
            System.out.println("2. Eliminar pedido.");
            System.out.println("3. Marcar pedido.");
            System.out.println("4. Listar pendientes.");
            System.out.println("5. Listar carta.");
            System.out.println("6. Salir.");

            System.out.println("\n");
            num = sc.nextInt();

            try {
                switch (num) {
                    case 1:

                        System.out.println("Carta de productos: ");
                        Query listarCarta = s.createQuery("FROM Carta");

                        ArrayList<Carta> resultadoCarta = (ArrayList<Carta>) listarCarta.list();
                        System.out.println("\n \n");
                        resultadoCarta.forEach((aa) -> System.out.println(aa));

                        System.out.println("Introduce un id de producto: ");
                        int id_prod = sc.nextInt();

                        System.out.println("Introduce un nombre para el pedido: ");
                        String nombrePedido = sc.next();

                        p.setNombre_pedido(nombrePedido);

                        p.setFecha(sqlDate);

                        p.setRecogido(false);

                        p.setCarta((Carta) listarCarta.list().get(0));

                        Transaction tr = s.beginTransaction();
                        s.save(p);
                        tr.commit();

                        break;

                    case 2:

                        Query listarAnteriores = s.createQuery("FROM Pedido");

                        ArrayList<Pedido> listarAntes = (ArrayList<Pedido>) listarAnteriores.list();
                        listarAntes.forEach((aa) -> System.out.println(aa));
                        
                        System.out.println("Selecciona el pedido a borrar: ");
                        int param = sc.nextInt();
                        
                        
                        Transaction transaction = s.beginTransaction();
                        Query borrar = s.createQuery("DELETE FROM Pedido where id=:id");

                        borrar.setParameter("id", param);
                        borrar.executeUpdate();
                        transaction.commit();

                        Query listarActuales = s.createQuery("FROM Pedido");

                        ArrayList<Pedido> listarTodo = (ArrayList<Pedido>) listarActuales.list();
                        System.out.println("\n \n");

                        System.out.println("Los pedidos actuales son: ");
                        listarTodo.forEach((aa) -> System.out.println(aa));

                        break;
                    case 3:
                        Query listarPedidos = s.createQuery("FROM Pedido");

                        ArrayList<Pedido> sacaPedidos = (ArrayList<Pedido>) listarPedidos.list();

                        sacaPedidos.forEach((aa) -> System.out.println(aa));

                        System.out.println("Selecciona el pedido que quieres marcar: ");

                        int recoger = sc.nextInt();

                        Transaction t = s.beginTransaction();

                        Pedido recogido = s.load(Pedido.class, recoger);

                        recogido.setRecogido(true);

                        s.update(recogido);

                        t.commit();

                        System.out.println("Pedido actualizado.");
                        break;
                    case 4:
                        System.out.println("Las comandas pendientes son: " + "\n");
                        //static final String COMANDA_HOY = "SELECT * FROM pedidos where fecha = CURDATE() and recogido = 0";

                        Query pendienteHoy = s.createQuery("FROM Pedido where fecha =current_date() and recogido=false");

                        ArrayList<Pedido> pendientes = (ArrayList<Pedido>) pendienteHoy.list();

                        pendientes.forEach((aa) -> System.out.println(aa));

                        break;
                    case 5:

                        Query listar = s.createQuery("FROM Carta", Carta.class);

                        ArrayList<Carta> resultado = (ArrayList<Carta>) listar.list();
                        System.out.println("\n \n");
                        System.out.println("Carta:");
                        System.out.println("\n");
                        resultado.forEach((aa) -> System.out.println(aa));

                        break;
                    case 6:
                        System.out.println("Saliendo.");
                        System.exit(0);

                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce una opción correcta.");
                sc.next();
            }

        }
    }

}
