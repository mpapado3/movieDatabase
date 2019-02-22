/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.service;

/**
 *
 * @author dim_zachos
 */

import moviedatabase.entities.FavoriteList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FavoriteListJPA {

    public static void main(String[] args) {
        createFavouriteList("Comedy");
        findAll();
        createFavouriteList("thriller");
        findAll();
      //deleteFavouriteList(9,"adventure");
    }

    public static void createFavouriteList(String listName) {
        FavoriteList fl = new FavoriteList();
        fl.setName(listName);

        EntityManager em; 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager(); 
        em.getTransaction().begin(); 
        em.persist(fl); 
        em.getTransaction().commit();
        em.close();
    }

    public static void editFavouriteList(FavoriteList object) {
        EntityManager em; 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager(); 
        em.getTransaction().begin(); 
        em.merge(object); 
        em.getTransaction().commit();
        em.close();
    }

    public static void deleteFavouriteList(FavoriteList object) {
        EntityManager em; 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager(); 
        em.getTransaction().begin(); 
        FavoriteList t = em.merge(object);      //Find an attached object with the same id and update it.
        em.remove(t);                           //If exists update and return the already attached object.
        em.getTransaction().commit();           //If doesn't exist insert the new register to the database
        em.close();
        
    }

    public static List<FavoriteList> findAll() {
        EntityManager em; 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager(); 
        Query query = em.createNamedQuery("FavoriteList.findAll");
        List<FavoriteList> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        em.close();
        return resultList;
    }

}

