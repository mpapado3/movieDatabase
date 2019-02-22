/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.service;

import moviedatabase.entities.Movie;
import moviedatabase.entities.Genre;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author michael_papado
 */
public class MovieDatabase {
    

    //Το prefix της συνδεσης με το API
    public static String mainUrl = "https://api.themoviedb.org/3/";
    public static String genreUrl = "genre/movie/list?";
    public static String movieUrl = "discover/movie?primary_release_date.gte=2000&with_genres=28%7C10749%7C878&page=";
    //to API key
    public final static String api = "api_key=6c76adc6e7505b2ba74808e91301eb26";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
            // TODO code application logic here

        String webpage = mainUrl + genreUrl + api;        
        JSONObject obj = new JSONObject(getText(webpage));
        JSONArray arr = obj.getJSONArray("genres");
        
        deleteFromDB();
        
        for (int i=0;i<arr.length();i++) {
                if (arr.getJSONObject(i).getInt("id") == 28 || arr.getJSONObject(i).getInt("id") == 10749 || arr.getJSONObject(i).getInt("id") == 878) {
                    System.out.print("Genre Name: "+arr.getJSONObject(i).getString("name")+"\n");
                    System.out.print("Genre ID: "+arr.getJSONObject(i).getInt("id")+"\n");
                    Genre gen = new Genre();
                    gen.setId(arr.getJSONObject(i).getInt("id"));
                    gen.setName(arr.getJSONObject(i).getString("name"));
                    addToDB(gen);
                }
        }
        
        getMovies();
        
    }
    
    private static void getMovies() {
        int x = 1;
        while (x<40) {
                    
        String webpage = mainUrl + movieUrl + x + "&" + api;

            try {
                JSONObject movObj;
                movObj = new JSONObject(getText(webpage));
                JSONArray movArr = movObj.getJSONArray("results");
                
                for (int i=0; i<movArr.length(); i++) {
                    System.out.print("Movie: " + movArr.getJSONObject(i).getString("title")+"\n");
                    
                    Movie mov = new Movie();
                    mov.setId(movArr.getJSONObject(i).getInt("id"));
                    mov.setTitle(movArr.getJSONObject(i).getString("title"));
                    String dt = movArr.getJSONObject(i).getString("release_date");
                    SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-M-d");
                    Date date=localDateFormat.parse(dt);
                    mov.setReleaseDate(date);
                    mov.setRating(movArr.getJSONObject(i).getFloat("vote_average"));
                    String overview = movArr.getJSONObject(i).getString("overview");
                    mov.setOverview(overview.substring(0, Math.min(overview.length(), 500))); 
                    JSONArray gen = movArr.getJSONObject(i).getJSONArray("genre_ids");
                    for (int z=0;z<gen.length();z++) {
                        int current = gen.getInt(z);
                        if (current == 28 || current == 10749 || current == 878) {
                            Genre newGen = new Genre(current);
                            mov.setGenreId(newGen);
                            break;
                        }
                    }
                    
                    addToDB(mov);

                    }

                
            } catch (Exception ex) {
                Logger.getLogger(MovieDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        x++;
        
        }
    }
    
    private static void addToDB(Object gen) {
        EntityManager em; // Ο EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU"); // Το EntityManagerFactory

        em = emf.createEntityManager(); //αρχικοποιώ τη μεταβλητή em
        
        em.getTransaction().begin(); //ξεκινάω μια καινούργια συναλλαγή για να αποθηκεύσω στη βάση δεδομένων τα αντικείμενα Genre που θα δημιουργήσουμε
        em.persist(gen); // δημιουργώ τo query εισαγωγής
        em.getTransaction().commit();// κάνω commit το query
    }
    
    private static void deleteFromDB() {
        EntityManager em; // Ο EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU"); // Το EntityManagerFactory

        em = emf.createEntityManager(); //αρχικοποιώ τη μεταβλητή em
        em.getTransaction().begin(); //ξεκινάω μια καινούργια συναλλαγή για να αποθηκεύσω στη βάση δεδομένων τα αντικείμενα Genre που θα δημιουργήσουμε
        em.createNamedQuery("Movie.deleteAll").executeUpdate();
        em.createNamedQuery("FavoriteList.deleteAll").executeUpdate();
        em.createNamedQuery("Genre.deleteAll").executeUpdate();
        em.getTransaction().commit();
    }

    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) 
                response.append(inputLine);

            in.close();

            return response.toString();
    }

  
    
}
