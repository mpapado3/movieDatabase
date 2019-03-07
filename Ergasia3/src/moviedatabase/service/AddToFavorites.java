/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.service;

import moviedatabase.entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import moviedatabase.MoviePOJO;
import moviedatabase.entities.FavoriteList;

/**
 *
 * @author michael_papado
 */
public class AddToFavorites {
    
    public static void checkMovies(List<MoviePOJO> selectedMovies) {

    }
    
    public void addToFavorite(FavoriteList fav, Movie mov) {
        EntityManager em; // Ο EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU"); // Το EntityManagerFactory
        em = emf.createEntityManager(); //αρχικοποιώ τη μεταβλητή em
        try {
            Movie mov2 = em.find(Movie.class, mov.getId());
            em.getTransaction().begin(); //ξεκινάω μια καινούργια συναλλαγή για να αποθηκεύσω στη βάση δεδομένων τα αντικείμενα Genre που θα δημιουργήσουμε
            mov2.setFavoriteListId(fav); // αποθηκεύουμε στην ταινία το favoriteList ID
            mov.setFavoriteListId(fav); 
            em.getTransaction().commit();// κάνω commit το query
        } catch (Exception e) {
            System.out.println("");
        } 
    }
    
    public void removeFromFavorite(Movie mov) {
        EntityManager em; // Ο EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU"); // Το EntityManagerFactory
        em = emf.createEntityManager(); //αρχικοποιώ τη μεταβλητή em
        try {
            Movie mov2 = em.find(Movie.class, mov.getId());
            em.getTransaction().begin(); //ξεκινάω μια καινούργια συναλλαγή για να αποθηκεύσω στη βάση δεδομένων τα αντικείμενα Genre που θα δημιουργήσουμε
            mov2.setFavoriteListId(null); //θέτουμε το favorite list id της ταινίας σε null
            mov.setFavoriteListId(null);
            em.getTransaction().commit();// κάνω commit το query
        } catch (Exception e) {
            System.out.println("");
        }
    }
    
    
}
