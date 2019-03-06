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
import java.util.ArrayList;
import moviedatabase.entities.FavoriteList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import moviedatabase.entities.Movie;

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
            em.persist(fl);                                     // δημιουργία τoυ query 
        } catch (Exception e) {
            System.out.println("Δεν ολοκληρώθηκε η εγγραφή στη βάση FavoriteList");
        }
        em.getTransaction().commit();
        em.close();                                             //κλείσιμο το EntityManager
    }

    public static String editFavouriteList(FavoriteList object) {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        try{
        FavoriteList a = em.find(FavoriteList.class, object.getId());
        if (a == null) {
            return ("Δε βρέθηκε το όνομα της λίστας στη βάση");
        } else {
            em.getTransaction().begin();                                        //ξεκινάω μια καινούργια συναλλαγή για να αποθηκεύσω στη βάση  
            em.merge(object);                                                   // δημιουργία  query εισαγωγής/μεταβολής  
                }
        }catch(Exception e){
                System.err.println("Error: Δεν έγινε σύνδεση στη βάση");
                }
            em.getTransaction().commit();                                       //τέλος συναλλαγής
            em.close();                                                         //κλείσιμο το EntityManager
        return ("Finish");                                                      
    }

        public static void deleteFavouriteList(List<FavoriteList> objects) {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            for(FavoriteList temp :objects){
            FavoriteList d = em.find(FavoriteList.class, temp.getId());         
            if (d == null) {
                System.out.println("Δε βρέθηκε το όνομα της λίστας στη βάση");  //μήμυμα σε περίπτωση που δε βρεθεί το id που ζητήθηκε
            } else {
                FavoriteList t = em.merge(d);                                   //Find an attached object with the same id and update it,
                em.remove(t);                                                   //If exists update and return the already attached object, If doesn't exist insert the new register to the database
                em.getTransaction().commit();                                   //τέλος συναλλαγής
                System.out.println("Έγινε διαγραφή της λίστας με id: " + d.getId() + " και όνομα" + " " + d.getName());
            } 
            }
        }catch(Exception e){
                System.err.println("Error: Δεν έγινε σύνδεση στη βάση");
                }
        em.close();                                                             //κλείσιμο το EntityManager
    }

        public static List<Movie> getFavoriteMovies(Integer id) {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        try{
        FavoriteList d = em.find(FavoriteList.class, id);                       //αναζήτηση στη βάση για τον εντοπισμό του id που ζητείται
        if (d == null) {
            System.out.println("Δε βρέθηκε το όνομα της λίστας στη βάση");
        } else {
            System.out.println("Βρέθηκε λίστα με id: " + d.getId() + " και όνομα" + " " + d.getName());
            return d.getMovieList();
        }
        }catch(Exception e){
                System.err.println("Error: Δεν έγινε σύνδεση στη βάση");        //μήνυμα σε περίπτωση που δε γίνει σύνδεση με τη βάση
                }
        em.close();                                                             //κλείσιμο το EntityManager
        return new ArrayList<Movie>();                                               
    }

    public static List<FavoriteList> findAll() {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("FavoriteList.findAll");              //δημιουργία ερωτήματος από τον entity για να βρει όλες τις λίστες αγαπημένων
        List<FavoriteList> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        em.close();
        return resultList;
    }
}
