package com.ecwo.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 22.04.13
 * Time: 19:49
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * To change this template use File | Settings | File Templates.
 */
public class CodeGenerator {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void startConnection() {
        emf = Persistence.createEntityManagerFactory("QueryTester");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public static void closeConnection() {
        em.getTransaction().commit();
        emf.close();
    }

    public static EntityManager getEntityManager() {
        return em;
    }

    public static void generateData() {
        //здесь вставлять данные в базу
        // em.persist(teacherLogin);
        //    em.flush();
    }
}
