/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        //        List<FavoriteList> fl = FavoriteListJPA.findAll();                      //κλήση της μεθόδου FavoriteListJPA.findAll() για να φέρει τα ονόματα από τη βάση
        //ArrayList<String> list  = new ArrayList<String>();
        editButton.setEnabled(false);                   //αρχικοποίηση editButton
        deleteButton.setEnabled(false);                  //αρχικοποίηση deleteButton
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createButton.setText("Δημιουργία Λίστας");

        editButton.setText("Επεξεργασία Λίστας");

        deleteButton.setText("Διαγραφή");

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(createButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private void init() {
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getAllFavoriteLists();
    }

    private void getAllFavoriteLists() {
        model = new DefaultListModel();
        keys = new HashMap<>();

        List<FavoriteList> fl = FavoriteListJPA.findAll();
        for (int i = 0; i < fl.size(); i++) {
            model.addElement(fl.get(i).getName());
            keys.put(i, fl.get(i).getId());
        }

        jList1.setModel(model);
    }
    DefaultListModel<String> model = new DefaultListModel();
    Map<Integer, Integer> keys = new HashMap<Integer, Integer>();

    //createButton listener
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        JFrame f = new JFrame("Όνομα Λίστας");               //δημιουργία του frame με title                   
        JButton saveButton = new JButton("Αποθήκευση");      //δημιουργία saveButton
        saveButton.setBounds(30, 100, 120, 40);              //όρια για το κουμπί
        JButton cancelButton = new JButton("Ακύρωση");       //δημιουργία cancelButton
        cancelButton.setBounds(150, 100, 120, 40);           //όρια για το κουμπί

        JLabel label = new JLabel();                         //δημιουργία label
        label.setText("Δώσε όνομα λίστας");                  //ονομασία για label
        label.setBounds(30, 30, 100, 20);

        JTextField textfield = new JTextField(10);           //δημιουργία textfield πεδίου
        textfield.setBounds(30, 60, 150, 30);                //όρια για το πεδίο
        f.add(textfield);                                    //
        f.add(label);                                        //   
        f.add(saveButton);                                   //πρόσθεση των αντικειμένω πάνω στο frame           
        f.setSize(400, 400);                                 //
        f.add(cancelButton);                                 //
        f.setLayout(null);                                   //
        f.setVisible(true);                                  //
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //κλείσιμο του τρέχοντος παραθύρου και διατήρηση του αρχικού παραθύρου

        //savebutton listener
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String name = textfield.getText();
                if (textfield.getText().equals("")) {       //έλεγχος για κενό πεδίο μετά το πάτημα του κουμπιού αποθήκευση
                    JOptionPane.showMessageDialog(null, "Μη έγκυρο όνομα", "Failure", JOptionPane.ERROR_MESSAGE);   //μήνυμα μη έγκυρης εισαγωγής πεδίου από χρήστη
                } else {
                    FavoriteListJPA fl = new FavoriteListJPA();     //δημιουργία του αντικειμένου
                    fl.createFavouriteList(name);                   //κλήση της μεθόδου createFavoriteList για εισαγωγή ονόματος στη βάση
                    //model.addElement(name);                         //προσθήκη σε modelList
                    //jList1.setModel(model);                         //εμφάνιση στη jlist
                    getAllFavoriteLists();
                }
                f.dispose();                                        //κλείσιμο παραθύρου και αποδέσμευση από μνήμη
                System.out.println(model);                          //εκτύπωση για επιβεβαίωση
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
        label.setBounds(30, 30, 100, 20);
        //textfield to enter name
        JTextField textfield1 = new JTextField(jList1.getSelectedValue());      //δημιουργία textfield1 πεδίου με τιμή που επιλέξαμε από jlist
        textfield1.setBounds(30, 60, 150, 30);
        int index = jList1.getSelectedIndex();
        f.add(textfield1);
        f.add(label);
        f.add(saveButton);
        f.setSize(400, 400);
        f.add(cancelButton);
        f.setLayout(null);
        f.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                      //κλείσιμο του τρέχοντος παραθύρου και διατήρηση του αρχικού παραθύρου

        //savebutton listener
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String name = textfield1.getText();

                int selectedIndex = jList1.getSelectedIndex();                          //deleteButton listener
                String selectedString = jList1.getSelectedValue();

                if (textfield1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Μη έγκυρο όνομα", "Failure", JOptionPane.ERROR_MESSAGE);
                } else {
                    // model.setElementAt(name, index);
                    // model.add(index, name);
                    //editFavouriteList(new FavoriteList();

                    FavoriteListJPA.editFavouriteList(new FavoriteList(keys.get(selectedIndex), name));
                    getAllFavoriteLists();

                    // jList1.setModel(model);
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
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedIndex = jList1.getSelectedIndex();                          //deleteButton listener
        String selectedString = jList1.getSelectedValue();

        System.out.println("selectedIndex: " + selectedIndex + " " + selectedString);

        Object[] options = {"Ναι", "Ακύρωση"};
        JOptionPane.showOptionDialog(null,
                "Είστε σίγουροι ότι θέλετε να γίνει διαγραφή;",
                "Διαγραφή",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);
        // if (selectedIndex != -1) {
        //model.remove(selectedIndex);

        deleteFavouriteList(new FavoriteList(keys.get(selectedIndex), selectedString));
        getAllFavoriteLists();
        //        } else {
        //
        //        }
    }
        /*
    private TableModel createTable() {
        list = new ArrayList();
        Object obj = jList1.getSelectedValue();
        Genre gen = (Genre) obj;
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        list = em.createNamedQuery("Movie.findById").getResultList();
        String[] columnNames = {"Τίτλος ταινίας", "Βαθμολογία", "Περιγραφή"};
        TableModel movieTableModel = new MovieTableModel(list,columnNames);
        return movieTableModel;
    }
         */

     private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting() == false) {
            if (jList1.getSelectedIndex() > 0) {
                //Selection, enable the edit button.
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
                //     }else if  {
            }

        }//GEN-LAST:event_jList1ValueChanged
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
