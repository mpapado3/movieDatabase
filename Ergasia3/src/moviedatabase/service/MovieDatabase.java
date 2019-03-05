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
import javax.swing.JOptionPane;

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

        String webpage = mainUrl + genreUrl + api;  //Συνεννόνουμε την διεύθυνση για να πάρουμε τα genre        
        JSONObject obj = new JSONObject(getText(webpage)); //Διάβαζουμε το json σε ένα νέο json object
        JSONArray arr = obj.getJSONArray("genres"); //Από το json που έχει επιστραφεί κρατάμε τον πίνακα με τα genres
        
        deleteFromDB(); //Καλούμε την μέθοδο που διαγράφει τους πίνακες της βάσης
        
        for (int i=0;i<arr.length();i++) { //Για κάθε εγγραφή του πίνακα ελέγχουμε
                //Αν το πεδίο id είναι κάποιο εκ των 28, 10749 ή 878
                if (arr.getJSONObject(i).getInt("id") == 28 || arr.getJSONObject(i).getInt("id") == 10749 || arr.getJSONObject(i).getInt("id") == 878) {
                    //εκτυπώνουμε βοηθητικά στην κονσόλα τις επιλεγμένες κατηγορίες
                    System.out.print("Genre Name: "+arr.getJSONObject(i).getString("name")+"\n");
                    System.out.print("Genre ID: "+arr.getJSONObject(i).getInt("id")+"\n");
                    Genre gen = new Genre(); //Δημιουργούμε ένα νέο entity Genre
                    gen.setId(arr.getJSONObject(i).getInt("id")); //Ορίζουμε το id του σύμφωνα με το τρέχον
                    gen.setName(arr.getJSONObject(i).getString("name")); //Ορίζουμε το όνομα σύμφωνα με το τρέχον
                    addToDB(gen); //Καλούμε την μέθοδο για να κάνει την εισαγωγή στην βάση
                }
        }
        
        getMovies(); //Καλούμε την μέθοδο που διαβάζει τις ταινίες από την διεύθυνση του movieDatabase
        
        JOptionPane.showMessageDialog(null, "Η ανάκτηση των δεδομένων ολοκληρώθηκε!"); //Εμφανίζουμε παράθυρο ότι έγινε η ανάγνωση από το site
    }
    
    private static void getMovies() {
        int x = 1; //Ορίζουμε βοηθητική μεταβλητή x και θέτουμε την τιμή της ίσον με 1
        //Κάθε σελίδα που επιστρέφει μια κλήση του api έχει 40 ταινίες
        while (x<40) {
                    
        String webpage = mainUrl + movieUrl + x + "&" + api; //Συνεννόνουμε την διεύθυνση με τον αριθμό της σελίδας

            try {
                JSONObject movObj; //Δημιουργούμε ένα νέο json object
                movObj = new JSONObject(getText(webpage)); //Και διαβάζουμε το αποτέλεσμα του api
                JSONArray movArr = movObj.getJSONArray("results"); //Δημιουργούμε ένα νέο json πίνακα με τα αποτελέσματα των ταινιών
                
                //Για όλα τα αποτελέσματα την json array
                for (int i=0; i<movArr.length(); i++) {
                    //Ετυπώνουμε βοηθητικά στην κονσόλα το όνομα της ταινίας
                    System.out.print("Movie: " + movArr.getJSONObject(i).getString("title")+"\n");
                    
                    Movie mov = new Movie(); //Δημιουργούμε νέο entity για την ταινία
                    mov.setId(movArr.getJSONObject(i).getInt("id")); //Προσθέτουμε το id
                    mov.setTitle(movArr.getJSONObject(i).getString("title")); //Προσθέτουμε τον τίτλο
                    String dt = movArr.getJSONObject(i).getString("release_date"); //Προσθέτουμε την ημερομηνία κυκλοφορίας σε text
                    SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-M-d"); //Μέσω μιας μεταβλητής simple date της μορφής yyyy-M-d 
                    Date date=localDateFormat.parse(dt); //Τροποποιούμε την ημερομηνία σε σωστό φορμάτ
                    mov.setReleaseDate(date); //Προσθέτουμε τη ημερομηνία στο entity
                    mov.setRating(movArr.getJSONObject(i).getFloat("vote_average")); //Προσθέτουμε την αξιολόγηση
                    String overview = movArr.getJSONObject(i).getString("overview"); //Προσθέτουμε την περίληψη σε text μεταβλητή
                    mov.setOverview(overview.substring(0, Math.min(overview.length(), 500))); //Κόβουμε το μήκος του text στους 500 χαρακτήρες
                    JSONArray gen = movArr.getJSONObject(i).getJSONArray("genre_ids"); //Σε μια νέα μεταβλητή αποθηκεύουμε τις κατηγορίες που υπάγεται η ταινία
                    //Για κάθε κατηγορία που υπάρχει στην ταινία ελέγχουμε
                    for (int z=0;z<gen.length();z++) {
                        int current = gen.getInt(z); //Αποθηκεύουμε το τρέχον Genre ID
                        //και ελέγχουμε αν είναι κάποιο από τα 3 που μας ενδιαφέρει
                        if (current == 28 || current == 10749 || current == 878) {
                            Genre newGen = new Genre(current); //Τότε φτιάχνουμε ένα νέο genre με id από το τρέχον
                            mov.setGenreId(newGen); //Και το προσθέτουμε στην τρέχουσα ταινία
                            break; //Και διακόπτουμε το for μιας που δεν μας ενδιαφέρει αν υπάγεται και σε άλλο
                        }
                    }
                    
                    addToDB(mov); //Καλούμε την μέθοδο addToDB για να προσθέσουμε την ταινία στην βάση

                    }   
            } catch (Exception ex) {
                Logger.getLogger(MovieDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        x++; //Αυξάνουμε την μεταβλητή x κατά 1
        
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
        em.createNamedQuery("Movie.deleteAll").executeUpdate(); //Καλούμε την namedQuery
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
