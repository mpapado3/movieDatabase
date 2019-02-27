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
        /*
        createFavouriteList("Comedy");
        findAll();
        createFavouriteList("thriller");
        findAll();
        deleteFavouriteList(new FavoriteList(50, "thriller"));
        findAll();
        editFavouriteList(new FavoriteList(60, "X"));
        */
    }

    public static void createFavouriteList(String listName) {
        FavoriteList fl = new FavoriteList();
        fl.setName(listName);

        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();                         //αρχικοποίηση της μεταβλητή em
        em.getTransaction().begin();                            //καινούργια συναλλαγή για να γίνει αποθήκευση στη βάση Favorite_List
        try {
            em.persist(fl);
        } catch (Exception e) {
        }
                                         // δημιουργία τoυ query 
        em.getTransaction().commit();
        em.close();                                             //κλείσιμο το EntityManager
    }

    public static String editFavouriteList(FavoriteList object) {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        FavoriteList a = em.find(FavoriteList.class, object.getId());
        if (a == null) {
            return ("Δε βρέθηκε το όνομα της λίστας στη βάση");
        } else {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
            em.close();
            
        }
        return ("Ok, Έγινε αλλαγή ονόματος της λίστας στο id: " + a.getId() + " σε: " + a.getName());
    }

    public static String deleteFavouriteList(FavoriteList object) {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        FavoriteList d = em.find(FavoriteList.class, object.getId());           //αναζήτηση του ονόματος στη βάση βάση του id
        if (d == null) {                                                        //έλεγχος για το όνομα
            return ("Δε βρέθηκε το όνομα της λίστας στη βάση");                 //επιστροφή ανάλογου μηνύματος
        } else {
            em.getTransaction().begin();
            FavoriteList t = em.merge(object);      
            em.remove(t);                           
            em.getTransaction().commit();           
            em.close();
            
        }
      return "Ok, έγινε η διαγραφή";
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
