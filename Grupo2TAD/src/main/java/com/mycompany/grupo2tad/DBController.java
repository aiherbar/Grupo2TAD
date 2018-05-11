/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
       
       
    public Entrevistador getEntrevistador(){
    
        Transaction tx = hibernateSession.beginTransaction();
        Query query = hibernateSession.createQuery("FROM Entrevistador E WHERE E.dni = '11111111A'");
        List<Entrevistador> result = query.list();
        tx.commit();
        
        return  result.get(0);
    }
    
}
