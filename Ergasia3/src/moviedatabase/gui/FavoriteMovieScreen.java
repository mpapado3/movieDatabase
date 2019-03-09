/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import moviedatabase.MovieTableModel;
import moviedatabase.entities.FavoriteList;
import moviedatabase.entities.Movie;
import moviedatabase.service.FavoriteListJPA;
import static moviedatabase.service.FavoriteListJPA.deleteFavouriteList;


/**
 *
 * @author Administrator
 */
public class FavoriteMovieScreen extends javax.swing.JFrame {

    /**
     * Creates new form FavoriteMovieScreen
     */
    public FavoriteMovieScreen() {
        initComponents();
        editButton.setEnabled(false);                                           //αρχικοποίηση editButton ώστε να μην είναι visible
        deleteButton.setEnabled(false);                                         //αρχικοποίηση deleteButton ώστε να μην είναι visible
        init();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0)));

        deleteButton.setText("Διαγραφή");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        createButton.setText("Δημιουργία Λίστας");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        editButton.setText("Επεξεργασία Λίστας");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editButton)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(createButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private void init() {
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);           //αρχικοποίηση του τρόπυ επιλογής μιας εγγραφής στη jlist
        getAllFavoriteLists();
        
    }
    
    //μέθοδος για αντιστοίχιση των id της jlist με τα id της βάσης
    private void getAllFavoriteLists() {
        model = new DefaultListModel();                                          
        keys = new HashMap<>();                                                 //δημιουργία hashmap ώστε να γίνει αντιστοίχιση μεταξύ των id σε βάση και jlist
        //βρίσκει όλες τις λίστες από τη βάση favoritelist
        List<FavoriteList> fl = FavoriteListJPA.findAll();
        for (int i = 0; i < fl.size(); i++) {
            model.addElement(fl.get(i).getName());                               
            keys.put(i, fl.get(i).getId());
        }

        jList1.setModel(model);                                                 //σύνδεση του defaultlistmodel με την jlist
    }
    DefaultListModel<String> model = new DefaultListModel();                    //δήλωση και δημιουργία defaultList και HashMap
    Map<Integer, Integer> keys = new HashMap<Integer, Integer>();               //

    //createButton listener
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        JFrame f = new JFrame("Όνομα Λίστας");                                  //δημιουργία του frame με title                   
        JButton saveButton = new JButton("Αποθήκευση");                         //δημιουργία saveButton
        saveButton.setBounds(30, 100, 120, 40);                                 //όρια για το κουμπί
        JButton cancelButton = new JButton("Ακύρωση");                          //δημιουργία cancelButton
        cancelButton.setBounds(150, 100, 120, 40);                              //όρια για το κουμπί

        JLabel label = new JLabel();                                            //δημιουργία label
        label.setText("Δώσε όνομα λίστας");                                     //ονομασία για label
        label.setBounds(30, 30, 150, 20);

        JTextField textfield = new JTextField(10);                              //δημιουργία textfield πεδίου
        textfield.setBounds(30, 60, 150, 30);                                   //όρια για το πεδίο
        f.add(textfield);                                                       
        f.add(label);                                                             
        f.add(saveButton);                                                      //πρόσθεση των αντικειμένω πάνω στο frame           
        f.setSize(400, 200);                                                    
        f.add(cancelButton);                                                    
        f.setLayout(null);     
        f.setLocationRelativeTo(null);
        f.setVisible(true);                                                     
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                      //κλείσιμο του τρέχοντος παραθύρου και διατήρηση του αρχικού παραθύρου

        //savebutton listener
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String name = textfield.getText();
                if (textfield.getText().equals("")) {                           //έλεγχος για κενό πεδίο μετά το πάτημα του κουμπιού αποθήκευση
                    JOptionPane.showMessageDialog(null, "Μη έγκυρο όνομα", "Failure", JOptionPane.ERROR_MESSAGE);   //μήνυμα μη έγκυρης εισαγωγής πεδίου από χρήστη
                } else {
                    FavoriteListJPA fl = new FavoriteListJPA();                 //δημιουργία του αντικειμένου
                    fl.createFavouriteList(name);                               //κλήση της μεθόδου createFavoriteList για εισαγωγή ονόματος στη βάση
                    getAllFavoriteLists();
                }
                f.dispose();                                                    //κλείσιμο παραθύρου και αποδέσμευση από μνήμη
                System.out.println(model);                                      //εκτύπωση για επιβεβαίωση
            }
        });
        //cancelButton listener
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                f.dispose();
            }
        });

    }//GEN-LAST:event_createButtonActionPerformed
    //editButton listener
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        JFrame f = new JFrame("Επεξεργασία ονόματος λίστας");                   //δημιουργία του frame με title                                                                
        JButton saveButton = new JButton("Αποθήκευση");                         //δημιουργία saveButton
        saveButton.setBounds(30, 100, 120, 40);                                 //όρια για το κουμπί
        JButton cancelButton = new JButton("Ακύρωση");                          //δημιουργία cancelButton
        cancelButton.setBounds(150, 100, 120, 40);                              //όρια για το κουμπί    
        //enter name label
        JLabel label = new JLabel();
        label.setText("Δώσε όνομα λίστας");
        label.setBounds(30, 30, 150, 20);
        //textfield to enter name
        JTextField textfield1 = new JTextField(jList1.getSelectedValue());      //δημιουργία textfield1 πεδίου με τιμή που επιλέξαμε από jlist
        textfield1.setBounds(30, 60, 150, 30);
        int index = jList1.getSelectedIndex();
        f.add(textfield1);
        f.add(label);
        f.add(saveButton);
        f.setSize(400, 200);
        f.add(cancelButton);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                      //κλείσιμο του τρέχοντος παραθύρου και διατήρηση του αρχικού menu

        //savebutton listener
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String name = textfield1.getText();
                int selectedIndex = jList1.getSelectedIndex();
                String selectedString = jList1.getSelectedValue();

                if (textfield1.getText().equals("")) {                          //έλεγχος και μήνυμα λάθους στην περίπτωση που ο χρήστης δώσει κενό textfield
                    JOptionPane.showMessageDialog(null, "Μη έγκυρο όνομα", "Failure", JOptionPane.ERROR_MESSAGE);
                } else {
                    FavoriteListJPA.editFavouriteList((FavoriteList)new FavoriteList(keys.get(selectedIndex), name));
                    getAllFavoriteLists();
                }
                f.dispose();
                System.out.println(model);
            }
        });
        //cancelButton listener
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                f.dispose();
            }
        });
    }//GEN-LAST:event_editButtonActionPerformed
    //deleteButton listener
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedIndex = jList1.getSelectedIndex();                         
        String selectedString = jList1.getSelectedValue();
        int[] selectedIndices = jList1.getSelectedIndices();
        List<String> selectedStrings = jList1.getSelectedValuesList();
        Object[] options = {"Ναι", "Ακύρωση"};
        int j = JOptionPane.showOptionDialog(null,
                "Είστε σίγουροι ότι θέλετε να γίνει διαγραφή;",
                "Διαγραφή",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);
            if(j == JOptionPane.YES_OPTION){
                FavoriteList temp = new FavoriteList();
                List<FavoriteList> tempList = new ArrayList<FavoriteList>();
                   
                for (int i = 0; i < selectedIndices.length; i++) {
                   temp.setId(keys.get(selectedIndex));
                   temp.setName(selectedString);
                   tempList.add(temp);
                   deleteFavouriteList(tempList);
                   getAllFavoriteLists();  
        }        
            }else{
                JOptionPane.showMessageDialog(null, "Η διαγραφή της λίστας ακυρώθηκε");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed
    
    //jList listener
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
       if (evt.getValueIsAdjusting() == false) {                                     //ελέγχουμε αν έχουμε ολοκληρώσει την επιλογή
            jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); //μόλις πατηθεί από το ποντίκι η jlist αλλάζει ο τρόπος επίλογής λιστών
            editButton.setEnabled(true);                                             //το κουμπί επεξεργασία γίνεται διαθέσιμο
            deleteButton.setEnabled(true);                                           //το κουμπί διαγραφή γίνεται διαθέσιμο
            enableJtable();                                                          //μέθοδος για δημιουργία jtable
            int[] selectedIndices = jList1.getSelectedIndices();                               
            List<String> selectedValues = jList1.getSelectedValuesList();

            //εαν έχουν επιλεχθεί πάνω από 2 αγαπημένες λίστες ταινιών το κουμπί επεξεργασίας γίνεται ανενεργό ενώ το κουμπί διαγραφής παραμένει ενεργό
            if(selectedIndices.length > 1){                                         
                editButton.setEnabled(false);
                deleteButton.setEnabled(true);
            }
       }
    }//GEN-LAST:event_jList1ValueChanged
    //μέθοδος για τη δημιουργία του jtable
    private void enableJtable(){
       TableModel movieTableModel = createTable();
       jTable1.setModel(movieTableModel);
    }

    //δημιουργία του jtable
    private TableModel createTable() {
        List<Movie> movies = new ArrayList<>();
        int selectedIndex = jList1.getSelectedIndex();
        movies = FavoriteListJPA.getFavoriteMovies(keys.get(selectedIndex));                     
        String[] columnNames = {"Τίτλος ταινίας", "Βαθμολογία", "Περιγραφή"};   //τα ονόματα των στηλών 
        TableModel movieTableModel = new MovieTableModel(movies, columnNames);  //Δημιουργία tableModel με την λίστα των ταινιών και τους τίτλους των στηλών
        return movieTableModel;                                                 //επιστρέφει τον πίνακα συμπληρωμένο με τις ταινίες και τις στήλες
    }
   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FavoriteMovieScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FavoriteMovieScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FavoriteMovieScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FavoriteMovieScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FavoriteMovieScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
