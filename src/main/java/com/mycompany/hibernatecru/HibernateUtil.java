/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hibernatecru;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author diegu
 */
public class HibernateUtil {

    private static final SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.out.println("Error al crear SessionFactory");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

}

