
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

    //δημιουργία λίστας αγαπημένων
    public static void createFavouriteList(String listName) {
        
        FavoriteList fl = new FavoriteList();
        fl.setName(listName);
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();                                         //αρχικοποίηση της μεταβλητή em
        em.getTransaction().begin();                                            //καινούργια συναλλαγή για να γίνει αποθήκευση στη βάση Favorite_List
        try {
            em.persist(fl);                                                     // δημιουργία τoυ query 
        } catch (Exception e) {
            System.out.println("Δεν ολοκληρώθηκε η εγγραφή στη βάση FavoriteList");
        }
        em.getTransaction().commit();
        em.close();                                             //κλείσιμο το EntityManager
    }

    //επεξεργασία αγαπημένης λίστας
    public static String editFavouriteList(FavoriteList object) {
        
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();                                         //αρχικοποίηση της μεταβλητής em
        try {
            FavoriteList a = em.find(FavoriteList.class, object.getId());
            if (a == null) {
                return ("Δε βρέθηκε το όνομα της λίστας στη βάση");
            } else {
                em.getTransaction().begin();                                        //ξεκινάω μια καινούργια συναλλαγή για να αποθηκεύσω στη βάση  
                a.setName(object.getName());                                        // δημιουργία  query εισαγωγής/μεταβολής  
                em.getTransaction().commit();                                       //τέλος συναλλαγής
                em.close();                                                         //κλείσιμο το EntityManager
            }
        } catch (Exception e) {
            System.err.println("Error: Δεν έγινε σύνδεση στη βάση");        //μήνυμα σε περίπτωση που δε γίνει σύνδεση στη βάση
        }
        return ("Finish");
    }
    
    //διαγραφή αγαπημένων λιστών
    public static void deleteFavouriteList(List<FavoriteList> objects) {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");   //αρχικοποίηση της μεταβλητής emf
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        try {
            for (FavoriteList temp : objects) {
                FavoriteList d = em.find(FavoriteList.class, temp.getId());
                
                if (d == null) {
                    System.out.println("Δε βρέθηκε το όνομα της λίστας στη βάση");  //μήμυμα σε περίπτωση που δε βρεθεί το id που ζητήθηκε
                } else {

                    List<Movie> movies = d.getMovieList();
                    d.setMovieList(null);
                    AddToFavorites rem = new AddToFavorites();
                    for (Movie m : movies) {
                        rem.removeFromFavorite(m);
                    }
                    FavoriteList t = em.merge(d);
                    em.remove(t);
                    em.getTransaction().commit();
                    System.out.println("Έγινε διαγραφή της λίστας με id: " + d.getId() + " και όνομα" + " " + d.getName());
                }
            }
        } catch (Exception e) {
            System.err.println("Error: Δεν έγινε σύνδεση στη βάση" + e.getMessage());  //μήνυμα σε περίπτωση που δε γίνει σύνδεση στη βάση
        }
        em.close();                                                             //κλείσιμο το EntityManager
    }

    public static List<Movie> getFavoriteMovies(Integer id) {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        try {
            FavoriteList d = em.find(FavoriteList.class, id);                       //αναζήτηση στη βάση για τον εντοπισμό του id που ζητείται
            if (d == null) {
                System.out.println("Δε βρέθηκε το όνομα της λίστας στη βάση");
            } else {
                em.refresh(d);
                System.out.println("Βρέθηκε λίστα με id: " + d.getId() + " και όνομα" + " " + d.getName());
                return d.getMovieList();
            }
        } catch (Exception e) {
            System.err.println("Error: Δεν έγινε σύνδεση στη βάση" + e.getMessage());  //μήνυμα σε περίπτωση που δε γίνει σύνδεση με τη βάση
        }
        em.close();                                                             //κλείσιμο του EntityManager
        return new ArrayList<Movie>();                                          //επιστροφή της λίστας με τις ταινίες     
    }

    //εύρεση αγαπημένων λιστών      
    public static List<FavoriteList> findAll() {
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("FavoriteList.findAll");              //δημιουργία ερωτήματος από τον entity για να βρει όλες τις λίστες αγαπημένων
        List<FavoriteList> resultList = query.getResultList();
        em.close();
        return resultList;                                                      //επιστροφή αγαπημένων ταινιών
    }

}
