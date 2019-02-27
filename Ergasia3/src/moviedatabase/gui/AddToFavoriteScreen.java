package moviedatabase.gui;

import moviedatabase.entities.Genre;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import moviedatabase.MoviePOJO;
import moviedatabase.MovieTableModel;
import moviedatabase.entities.Movie;


/**
 *
 * @author michael_papado
 */
public class AddToFavoriteScreen extends javax.swing.JFrame {
    
    private MoviePOJO oneMovie;
    List<Movie> myList;
    private Boolean textBool = false;
    private Boolean comboBool = false;
    /**
     * Creates new form MainScreen
     */
    public AddToFavoriteScreen() {
        initComponents();
        //yearTextInput = null;
        genreCombo.setSelectedIndex(-1);
        /*if (yearTextInput != null && genreCombo.getSelectedItem() != null) {
            searchBtn.setEnabled(true);
        }*/
        
        genreCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBool = true;
                enableSearch();
              }
            });
        
    }
    
    private void enableSearch() {
        if (textBool == true && comboBool == true) {
            searchBtn.setEnabled(true);
        }
    }
    
    private TableModel createTable() {
        myList = new ArrayList();
        Object obj = genreCombo.getSelectedItem();
        Genre gen = (Genre) obj;
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        int year = Integer.parseInt(yearTextInput.getText());
        em = emf.createEntityManager();
        em.getTransaction().begin();
        myList = em.createNamedQuery("Movie.findByReleaseDateAndGenre").setParameter("releaseDate", year).setParameter("genreId", gen).getResultList();
        String[] columnNames = {"Τίτλος ταινίας", "Βαθμολογία", "Περιγραφή"};
        TableModel movieTableModel = new MovieTableModel(myList,columnNames);
        return movieTableModel;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        MovieDatabasePUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("MovieDatabasePU").createEntityManager();
        genreQuery = java.beans.Beans.isDesignTime() ? null : MovieDatabasePUEntityManager.createQuery("SELECT g FROM Genre g");
        genreList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : genreQuery.getResultList();
        addToFavorites1 = new moviedatabase.AddToFavorites();
        jLabel1 = new javax.swing.JLabel();
        genreCombo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        yearTextInput = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        movieTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        favoriteListCombo = new javax.swing.JComboBox<>();
        removeFromListBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Είδος Ταινίας");

        genreCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Genre) {
                    Genre mec = (Genre)value;
                    setText(mec.getName());
                }
                return this;
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, genreList, genreCombo);
        bindingGroup.addBinding(jComboBoxBinding);

        genreCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreComboActionPerformed(evt);
            }
        });

        jLabel2.setText("Έτος Κυκλοφορίας");

        yearTextInput.setColumns(8);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, addToFavorites1, org.jdesktop.beansbinding.ELProperty.create("${yearText}"), yearTextInput, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        yearTextInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearTextInputActionPerformed(evt);
            }
        });
        yearTextInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                yearTextInputKeyTyped(evt);
            }
        });

        searchBtn.setText("Αναζήτηση");
        searchBtn.setEnabled(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Καθαρισμός Κριτηρίων");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        movieTable.setAutoCreateRowSorter(true);
        movieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        movieTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(movieTable);

        jLabel3.setText("Προσθήκη σε Λίστα");

        favoriteListCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        removeFromListBtn.setText("Αφαίρεση από Λίστα");
        removeFromListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromListBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(favoriteListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(removeFromListBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(genreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(yearTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchBtn)
                                .addGap(18, 18, 18)
                                .addComponent(clearBtn)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(genreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(yearTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBtn)
                    .addComponent(clearBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(favoriteListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeFromListBtn))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        TableModel movieTableModel = createTable();
        movieTable.setModel(movieTableModel);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        genreCombo.setSelectedIndex(-1);
        yearTextInput.setText(null);
        textBool = false;
        comboBool = false;
        searchBtn.setEnabled(false);
        myList = new ArrayList();
        String[] columnNames = {"Τίτλος ταινίας", "Βαθμολογία", "Περιγραφή"};
        TableModel movieTableModel = new MovieTableModel(myList,columnNames);
        movieTable.setModel(movieTableModel);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void yearTextInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearTextInputKeyTyped
        // TODO add your handling code here:
        textBool = true;
        enableSearch();  
    }//GEN-LAST:event_yearTextInputKeyTyped

    private void removeFromListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromListBtnActionPerformed
        // TODO add your handling code here:
        int[] selected;
        selected = movieTable.getSelectedRows();
        
        for (int item: selected) {
            System.out.println(myList.get(item).getTitle());
        }
    }//GEN-LAST:event_removeFromListBtnActionPerformed

    private void yearTextInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTextInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearTextInputActionPerformed

    private void genreComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreComboActionPerformed

    private Date getDateFromText(JTextField yearSearchInput) {
        String dateText = yearSearchInput.getText();
        Date yearDate = null;
        try {
            SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy");
            yearDate = localDateFormat.parse(dateText);
        } catch(ParseException nfe) {
            System.err.println("Wrong Date Format");
        }
        return yearDate;
    } 
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AddToFavoriteScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddToFavoriteScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddToFavoriteScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddToFavoriteScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddToFavoriteScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager MovieDatabasePUEntityManager;
    private moviedatabase.AddToFavorites addToFavorites1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> favoriteListCombo;
    private javax.swing.JComboBox<String> genreCombo;
    private java.util.List<moviedatabase.entities.Genre> genreList;
    private javax.persistence.Query genreQuery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable movieTable;
    private javax.swing.JButton removeFromListBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField yearTextInput;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
