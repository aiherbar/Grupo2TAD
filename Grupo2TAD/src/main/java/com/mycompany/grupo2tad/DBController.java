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
    private static DBController instance = null;

    private DBController() {
        this.hibernateSession = HibernateUtil.getSessionFactory().openSession();
    }

    public static DBController getInstance() {
        if (instance == null) {
            instance = new DBController();
        }
        return instance;
    }

    public void closeInstance() {
        this.hibernateSession.close();
    }
    //Entrevistas

    public List<Entrevista> getEntrevistas() {
        List<Entrevista> lista = new ArrayList();
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("From Entrevista");
        lista = q.list();
        tx.commit();
        for (Entrevista entrevista : lista) {
            hibernateSession.refresh(entrevista);
        }
        return lista;
    }

    public void setEntrevista(int id_entrevistado, int id_entrevistador, String apto, String lugar) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = hibernateSession.createSQLQuery("INSERT INTO entrevista (id,id_entrevistado,id_entrevistador,apto,fecha,lugar) VALUES (NULL, '" + id_entrevistado + "', '" + id_entrevistador + "', '', '0000-00-00 00:00:00.000000', '" + lugar + "');");
        q.executeUpdate();
        tx.commit();
    }

    public void deleteEntrevista(int id) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("delete Entrevista where id= :ident");
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public void updateEntrevista(int id, String apto, Date fecha) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("update Entrevista set apto=apt,lugar=place where id= :ident ");
        q.setParameter("apt", apto);
        q.setParameter("date", fecha);
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public void updateEntrevistaApto(int id, String apto) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("update Entrevista set apto=:apt where id= :ident ");
        q.setParameter("apt", apto);
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public void updateEntrevistaFecha(int id, Date fecha) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("update Entrevista set fecha=:date where id= :ident ");
        q.setParameter("date", fecha);
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
        this.getEntrevista(id);
    }

    public List<Entrevista> getEntrevistasConFecha() {
        List<Entrevista> lista = new ArrayList();
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("From Entrevista where fecha!='0000-00-00 00:00:00'");
        lista = q.list();
        tx.commit();
        for (Entrevista entrevista : lista) {
            hibernateSession.refresh(entrevista);
        }
        return lista;
    }

    public List<Entrevista> getEntrevistasSinFecha() {
        List<Entrevista> lista = new ArrayList();
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("From Entrevista where fecha='0000-00-00 00:00:00'");
        lista = q.list();
        tx.commit();
        for (Entrevista entrevista : lista) {
            hibernateSession.refresh(entrevista);
        }
        return lista;
    }

    public Entrevista getEntrevista(int id) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("From Entrevista where id='" + id + "'");
        Entrevista entrevista = (Entrevista) q.list().get(0);
        tx.commit();
        hibernateSession.refresh(entrevista);
        return entrevista;
    }

    //Entrevistadores
    public List<Entrevistador> getEntrevistadores() {
        List<Entrevistador> lista = new ArrayList();
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("From Entrevistador");
        lista = q.list();
        tx.commit();
        for (Entrevistador entrevistador : lista) {
            hibernateSession.refresh(entrevistador);
        }
        return lista;
    }

    public void setEntrevistador(String dni, String nombre, String departamento) {
        Entrevistador p = new Entrevistador(dni, nombre, departamento);
        Transaction tx = this.hibernateSession.beginTransaction();
        this.hibernateSession.save(p);
        tx.commit();
    }

    public void deleteEntrevistador(int id) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("delete Entrevistador where id= :ident");
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public void updateEntrevistador(int id, String dni2, String nombre2, String departamento2) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("update Entrevistador set dni=:dn,nombre=:name,departamento=:dpto where id=:ident ");
        q.setParameter("name", nombre2);
        q.setParameter("dpto", departamento2);
        q.setParameter("dn", dni2);
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public Entrevistador getEntrevistador(int id) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("From Entrevistador where id='" + id + "'");
        Entrevistador entrevistador = (Entrevistador) q.list().get(0);
        tx.commit();
        hibernateSession.refresh(entrevistador);
        return entrevistador;
    }

    //Entrevistados
    public List<Entrevistado> getEntrevistados() {
        List<Entrevistado> lista = new ArrayList();
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("From Entrevistado");
        lista = q.list();
        tx.commit();
        for (Entrevistado entrevistado : lista) {
            hibernateSession.refresh(entrevistado);
        }
        return lista;
    }

    public void setEntrevistado(String dni, String nombre) {
        Entrevistado p = new Entrevistado(dni, nombre);
        Transaction tx = this.hibernateSession.beginTransaction();
        this.hibernateSession.save(p);
        tx.commit();
    }

    public void deleteEntrevistado(int id) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("delete Entrevistado where id= :ident");
        q.setParameter("ident", id);
        q.executeUpdate();
        tx.commit();
    }

    public void updateEntrevistado(int id, String dni2, String nombre2) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("update Entrevistado set dni=:dn,nombre=:name where id=:ident ");
        q.setParameter("name", nombre2);
        q.setParameter("ident", id);
        q.setParameter("dn", dni2);
        q.executeUpdate();
        tx.commit();

    }

    public Entrevistado getEntrevistado(int id) {
        Transaction tx = this.hibernateSession.beginTransaction();
        Query q = this.hibernateSession.createQuery("From Entrevistado where id='" + id + "'");
        Entrevistado entrevistado = (Entrevistado) q.list().get(0);
        tx.commit();
        hibernateSession.refresh(entrevistado);
        return entrevistado;
    }
}
