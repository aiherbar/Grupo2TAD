/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto_tad.entity.Entrevista;
import proyecto_tad.entity.Entrevistado;
import proyecto_tad.entity.Entrevistador;
import proyecto_tad.util.HibernateUtil;

/**
 *
 * @author Anais
 */
public class DBController {
       Session hibernateSession;

    public DBController() {
        this.hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
        
    }
    //Entrevistas
    
    public static List<Entrevista> consultarEntrevistas() {
        List<Entrevista>lista=new ArrayList();
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("From Entrevista");
        lista=q.list();
        tx.commit();
        return lista;
    }
    
    public static void altaEntrevista(int id_entrevistado, int id_entrevistador, byte apto,Date fecha,String lugar) {
        Entrevista p=new Entrevista(id_entrevistado, id_entrevistador,apto,fecha,lugar);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        session.save(p);
        tx.commit();
    }
    
     public static void eliminarEntrevista(int id) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("delete Entrevista where id= :ident");
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public static void actualizarEntrevista(int id,int id_entrevistado, int id_entrevistador, byte apto,Date fecha,String lugar) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("update Entrevista set id_entrevistado=:idendo,id_entrevistador=:identa,apto=apt,fecha=date,lugar=place where id= :ident ");
        q.setParameter("idendo", id_entrevistado);
        q.setParameter("identa", id_entrevistador);
        q.setParameter("apt", apto);
        q.setParameter("date", fecha);
        q.setParameter("place", lugar);
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }
    
    //Entrevistadores
    public static List<Entrevistador> consultarEntrevistadores() {
        List<Entrevistador>lista=new ArrayList();
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("From Entrevistador");
        lista=q.list();
        tx.commit();
        return lista;
    }
    
    public static void altaEntrevistador(String dni, String nombre,String departamento) {
        Entrevistador p=new Entrevistador(dni, nombre,departamento);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        session.save(p);
        tx.commit();
    }
    
     public static void eliminarEntrevistador(int id) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("delete Entrevistador where id= :ident");
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public static void actualizarEntrevistador(int id,String nombreAntiguo,String dni2, String nombre2,String departamento2) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("update Entrevistador set dni=:dn,nombre=:name,departamento=dpto where id=:ident ");
        q.setParameter("name", nombre2);
        q.setParameter("dpto", departamento2);
        q.setParameter("name2", nombreAntiguo);
        q.setParameter("dn", dni2);
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    //Entrevistados
    
    public static List<Entrevistado> consultarEntrevistados() {
        List<Entrevistado>lista=new ArrayList();
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("From Entrevistado");
        lista=q.list();
        tx.commit();
        return lista;
    }
    
    public static void altaEntrevistado(String dni, String nombre) {
        Entrevistado p=new Entrevistado(dni, nombre);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        session.save(p);
        tx.commit();
    }
    
     public static void eliminarEntrevistado(int id) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("delete Entrevistado where id= :ident");
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public static void actualizarEntrevistado(int id,String nombreAntiguo,String dni2, String nombre2) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=session.beginTransaction();
        Query q=session.createQuery("update Entrevistado set dni=:dn,nombre=:name where id=:ident ");
        q.setParameter("name", nombre2);
        q.setParameter("ident", id);
        q.setParameter("dn", dni2);
        
        q.executeUpdate();
        tx.commit();
    }

    
}
