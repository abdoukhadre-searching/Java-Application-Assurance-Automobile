/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projet_java;
import java.sql.Connection;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author LENOVO
 */
    public final class principale extends javax.swing.JFrame {
        public principale() {
            initComponents();
            setExtendedState(principale.MAXIMIZED_BOTH);
            t.start();
            affiche_listeAssure();
            affiche_HistoriqueAssure();
            afficheBorderaux();
        }

    private Connection connection = null;
    public static int id;
    public static String groupeBord;
    public static int ATT ;
    
    
    Timer t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            java.util.Date dt  =new java.util.Date();
            SimpleDateFormat st = new SimpleDateFormat("h:mm:ss a");        
            String tt = st.format(dt);
            labelHeure.setText(tt);
            //-------------------------------
            
            
            //--------------------------------
            double RCM;
            ;// RC= prime nette(51452) *80% pour Catégorie 1
            int c = cboCategorie.getSelectedIndex();
            if (c == 0)  {//&& (nombreMois.getValue() != 0)
                double RC = 41160;
                txtRC.setText(Double.toString(RC));
                RCM = (RC * 8.75 * nombreMois.getValue() )/100;
                //int rcm_int = (int)RCM;
                txtPrimeNette.setText(Double.toString(RCM));
                int ctPolice = 5000;
                txtCoutPolice.setText(Integer.toString(ctPolice));

                double fg = (RCM * 2.5)/100;
                //int fg_int = (int)fg;
                txtFG.setText(Double.toString(fg));

                double taxe =  RCM + (ctPolice * 10)/100;                
                txtTaxe.setText(Double.toString(taxe));

                int TTC = (int) (RCM + taxe + fg + 3000);
                txtPT.setText(Integer.toString(TTC));
                txtSommeFacture.setText(Integer.toString(TTC));
                         
            } else if (c==3) {
                double RC = 48800;
                txtRC.setText(Double.toString(RC));
                RCM = (RC * 8.75 * nombreMois.getValue() )/100;
                //int rcm_int = (int)RCM;
                txtPrimeNette.setText(Double.toString(RCM));
                int ctPolice = 5000;
                txtCoutPolice.setText(Integer.toString(ctPolice));

                double fg = (RCM * 2.5)/100;
                //int fg_int = (int)fg;
                txtFG.setText(Double.toString(fg));

                double taxe =  RCM + (ctPolice * 10)/100;                
                txtTaxe.setText(Double.toString(taxe));

                int TTC = (int) (RCM + taxe + fg + 3000);
                txtPT.setText(Integer.toString(TTC));
                txtSommeFacture.setText(Integer.toString(TTC));
            }
            /*
            double RCM;
            int c = cboCategorie.getSelectedIndex();
            if (c == 0) {
                double RC = 41160;// RC= prime nette(51452) *80% pour Catégorie 1

                txtRC.setText(Double.toString(RC));
                RCM =(double) (RC * 8.75 * nombreMois.getValue() )/100;
                txtPrimeNette.setText(Double.toString(RCM));
                int ctPolice = 5000;
                txtCoutPolice.setText(Integer.toString(ctPolice));

                double fg = (RCM * 2.5)/100;
                //int fg_int = (int)fg;
                txtFG.setText(Double.toString(fg));

                double taxe = RCM + (ctPolice * 10)/100;                
                txtTaxe.setText(Double.toString(taxe));

                double TTC = RCM + taxe + fg + 3000;
                txtPT.setText(Double.toString(TTC));
                txtSommeFacture.setText(Double.toString(TTC));
                int encaisser = Integer.parseInt(txtSommeEncaisse.getText());
                int somme = (int) TTC - 3000;
                
                if((somme  > encaisser)||( TTC < encaisser)){
                    JOptionPane.showMessageDialog(principale.this,"ATTENTION LA SOMMME ENCAISEE EST INCORRECTe !! Valeur minimum : "+somme); 
                }

            } else  if (c == 3) {

                double RC2 = 48800;// RC= prime nette(61 000)  *80% pour Catégorie 4 
                txtRC.setText(Double.toString(RC2));
                RCM = RC2 * (int) 0.0875 * (nombreMois.getValue());
                txtPrimeNette.setText(Double.toString(RCM));

                double fg2 = RCM * 0.025;
                int fg2_int = (int) fg2;
                txtFG.setText(Integer.toString(fg2_int));

                double taxe2 = RCM * 3000 * 0.1;
                txtTaxe.setText(Double.toString(taxe2));


                Double  TTC2 = RCM + taxe2 + fg2;
                txtPT.setText(Double.toString(TTC2));
            }
            */
            }
    });
    /**
     * Creates new form principale
     */
    public void affiche_listeAssure(){
        try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql = "SELECT  operateur, clientAssure, attestation, numeroPolice, immatriculation FROM alldata";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                con.close();
                        
        } catch(SQLException ex){
            jTextArea1.setText(ex.getMessage()+"\n");
        }
    }
    
    public void affiche_HistoriqueAssure(){
        try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql = "SELECT  clientAssure, adresse, attestation, numeroPolice, genre FROM alldata";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                tabHistorique.setModel(DbUtils.resultSetToTableModel(rs));
                con.close();
                        
        } catch(SQLException ex){
            jTextArea1.setText(ex.getMessage()+"\n");
        }
    }
    
    public void afficheBorderaux(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
            String sql = "SELECT  operateur, groupe, clientAssure, attestation, dateEcheance, dateDelivrance, prime_total, primeNette, commission, numeroPolice, immatriculation FROM alldata";
            PreparedStatement ps = con.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            tabBorderaux.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
            
        } catch(SQLException ex){
            jTextArea1.setText(ex.getMessage()+"\n");
        }
    }
    
    public void afficheBorderauxPole(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
            String sql = "SELECT  operateur, groupe, clientAssure, attestation, dateEcheance, dateDelivrance, prime_total, primeNette, commission, numeroPolice, immatriculation FROM alldata WHERE `groupe`= 'Pole'";
            PreparedStatement ps = con.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            tabBorderaux.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
            
        } catch(SQLException ex){
            jTextArea1.setText(ex.getMessage()+"\n");
        }
    }
    
    public void afficheBord_attestation(){
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
            String sql = "SELECT  operateur, groupe, clientAssure, attestation, dateEcheance, dateDelivrance, prime_total, primeNette, commission, numeroPolice, immatriculation FROM alldata WHERE `attestation`="+ATT;
            PreparedStatement ps = con.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            tabBorderaux.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
            
        } catch(SQLException ex){
            jTextArea1.setText(ex.getMessage()+"\n");
        }
    }
    
    public void afficheBorderauxArgenciel(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
            String sql = "SELECT  operateur, groupe, clientAssure, attestation, dateEcheance, dateDelivrance, prime_total, primeNette, commission, numeroPolice, immatriculation FROM alldata WHERE `groupe`= 'Argenciel'";
            PreparedStatement ps = con.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            tabBorderaux.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
            
        } catch(SQLException ex){
            jTextArea1.setText(ex.getMessage()+"\n");
        }
    }
    
    public void update(){
        try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql = "UPDATE `alldata` SET `clientAssure`=?,`profession`=?,`adresse`=?,`telephone`=?,`dateEcheance`=?,`dateDelivrance`=?,`heure`=?,`prime_total`=?,`primeNette`=?,`nomCompagnie`=?,`marque`=?,`immatriculation`=?,`genre`=?,`puissanceMoteur`=?,`energie`=?,`nombrePlaces`=?,`nombreMois`=?,`talon`=?,`groupe`=?,`categorie`=?,`numeroPolice`=?,`attestation`=?,`tonnage`=?,`commission`=? WHERE `id`="+id;
                   String assure = txtAssure.getText();
                   String profession = txtProfession.getText();
                   String adresse = txtAdresse.getText();
                   String marque = txtMarque.getText();
                   String immat = txtImmat.getText();
                   int attestation = Integer.parseInt(txtAttest.getText());
                   int police = Integer.parseInt(txtPolice.getText());
                   String tel = txtTelephone.getText();
                   String genre = txtGenre.getText();                   
                   
                   //int fg = Integer.parseInt(txtFG.getText());
                   //int taxe = Integer.parseInt(txtTaxe.getText());
                   double pTotal = Double.parseDouble(txtPT.getText());
                   double pNette = Double.parseDouble(txtPrimeNette.getText());
                   int pt_int = (int)pTotal;
                   int pn_int = (int)pNette;
                   
                   SimpleDateFormat tdate = new SimpleDateFormat("yyyy-MM-dd");
                   String dateEche_update = tdate.format(dateEcheance.getDate());
                   String dateDeliv_update = tdate.format(dateDelivrance.getDate());
                   
                   Date date =new java.util.Date();
                   Timestamp sqlTime =new java.sql.Timestamp(date.getTime());
                   
                   int ta = cboTalon.getSelectedIndex();
                   int e = cboEnergie.getSelectedIndex();
                   int g = cboGroupe.getSelectedIndex();
                   int to = cboTonnage.getSelectedIndex();
                   int c = cboCategorie.getSelectedIndex();

                   String talon = cboTalon.getItemAt(ta);
                   String categorie = cboCategorie.getItemAt(c);
                   String energie = cboEnergie.getItemAt(e);
                   String groupe = cboGroupe.getItemAt(g);
                   String tonnage = cboTonnage.getItemAt(to);
                   
                   int nbPlace =  nombrePlaces.getValue();
                   int nbMois = nombreMois.getValue();
                   int nbChvx =  nombreChevaux.getValue();  
                   
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1,assure);
                ps.setString(2,profession);
                ps.setString(3, adresse);
                ps.setString(4, tel);
                ps.setString(5, dateEche_update);
                ps.setString(6, dateDeliv_update);
                ps.setTimestamp(7, sqlTime);
                ps.setInt(8, pt_int); // Corriger :) ------because we did an Error "String" type in our DATABASE...
                ps.setInt(9, pn_int);
                ps.setString(10, "ASSURANCE AUTOMOBILE Senegalaise");
                ps.setString(11, marque);
                ps.setString(12, immat);
                ps.setString(13, genre);
                ps.setInt(14, nbChvx);
                ps.setString(15, energie);
                ps.setInt(16, nbPlace);
                ps.setInt(17, nbMois);
                ps.setString(18, talon);
                ps.setString(19, groupe);
                ps.setString(20, categorie);
                ps.setInt(21, police);
                ps.setInt(22, attestation);
                ps.setString(23, tonnage);
                ps.setInt(24, 0);
                ps.executeUpdate(); 
                
                jTextArea1.setForeground(Color.green);
                jTextArea1.setText("****** Operation effectuée avec succés !\n");
                
                clearField();
                btnValider.setText("Enregistrer");
                con.close();
                        
        } catch(SQLException ex){
            jTextArea1.setText(ex.getMessage());
        }
        
    }
    
    public void clearField(){
                    txtAssure.setText(null);
                    txtProfession.setText(null);
                    txtAdresse.setText(null);
                    txtMarque.setText(null);
                    txtGenre.setText(null);
                    txtImmat.setText(null);
                    txtAttest.setText(null);
                    txtPolice.setText(null);
                    txtTelephone.setText(null);
                    txtPT.setText(null);
                    txtPrimeNette.setText(null);
                    txtTaxe.setText(null);
                    txtFG.setText(null);
                    txtRC.setText(null);
                    txtSommeFacture.setText(null);
                    txtSommeEncaisse.setText(null);
                    txtCoutPolice.setText(null);
                    
                    
                    cboTalon.setSelectedIndex(-1);
                    cboCategorie.setSelectedIndex(-1);
                    cboEnergie.setSelectedIndex(-1);
                    cboGroupe.setSelectedIndex(-1);
                    cboTonnage.setSelectedIndex(-1);
                    
                    dateDelivrance.getDate();
                    dateEcheance.getDate();
                    nombrePlaces.setValue(0);
                    nombreMois.setValue(0);
                    nombreChevaux.setValue(0);      
    }
    
    public void geler(){
        txtAssure.setEnabled(false);
        txtProfession.setEnabled(false);
        txtAdresse.setEnabled(false);
        txtMarque.setEnabled(false);
        txtGenre.setEnabled(false);
        txtImmat.setEnabled(false);
        txtAttest.setEnabled(false);
        txtPolice.setEnabled(false);
        txtTelephone.setEnabled(false);
        txtPT.setEnabled(false);
        txtPrimeNette.setEnabled(false);
        txtTaxe.setEnabled(false);
        txtFG.setEnabled(false);
        txtRC.setEnabled(false);                
                    
        cboTalon.setEnabled(false);
        cboCategorie.setEnabled(false);
        cboEnergie.setEnabled(false);
        cboGroupe.setEnabled(false);
        cboTonnage.setEnabled(false);
        nombrePlaces.setEnabled(false);
        nombreMois.setEnabled(true);
        nombreChevaux.setEnabled(false);  
    }
    
    public void geler_tout(){
        txtAssure.setEnabled(false);
        txtProfession.setEnabled(false);
        txtAdresse.setEnabled(false);
        txtMarque.setEnabled(false);
        txtGenre.setEnabled(false);
        txtImmat.setEnabled(false);
        txtAttest.setEnabled(false);
        txtPolice.setEnabled(false);
        txtTelephone.setEnabled(false);
        txtPT.setEnabled(false);
        txtPrimeNette.setEnabled(false);
        txtTaxe.setEnabled(false);
        txtFG.setEnabled(false);
        txtRC.setEnabled(false);
        
        dateDelivrance.setEnabled(false);
        dateEcheance.setEnabled(false);
        
        cboTalon.setEnabled(false);
        cboCategorie.setEnabled(false);
        cboEnergie.setEnabled(false);
        cboGroupe.setEnabled(false);
        cboTonnage.setEnabled(false);
        nombrePlaces.setEnabled(false);
        nombreMois.setEnabled(false);
        nombreChevaux.setEnabled(false);  
    }
    
    public void activer(){
        txtAssure.setEnabled(true);
        txtProfession.setEnabled(true);
        txtAdresse.setEnabled(true);
        txtMarque.setEnabled(true);
        txtGenre.setEnabled(true);
        txtImmat.setEnabled(true);
        txtAttest.setEnabled(true);
        txtPolice.setEnabled(true);
        txtTelephone.setEnabled(true);
        txtPT.setEnabled(true);
        txtPrimeNette.setEnabled(true);
        txtTaxe.setEnabled(true);
        txtFG.setEnabled(true);
        txtRC.setEnabled(true);                
                    
        cboTalon.setEnabled(true);
        cboCategorie.setEnabled(true);
        cboEnergie.setEnabled(true);
        cboGroupe.setEnabled(true);
        cboTonnage.setEnabled(true);
        nombrePlaces.setEnabled(true);
        nombreMois.setEnabled(true);
        nombreChevaux.setEnabled(true);  
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        tabPan = new javax.swing.JTabbedPane();
        acceuil = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        affNouvelle = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtPT = new javax.swing.JTextField();
        txtFG = new javax.swing.JTextField();
        txtTaxe = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtPrimeNette = new javax.swing.JTextField();
        txtRC = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtImmat = new javax.swing.JTextField();
        txtMarque = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtGenre = new javax.swing.JTextField();
        txtVehicule = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTelephone = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cboTonnage = new javax.swing.JComboBox<>();
        txtAttest = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPolice = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtCoutPolice = new javax.swing.JTextField();
        txtSommeFacture = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtSommeEncaisse = new javax.swing.JTextField();
        btnCalculer = new javax.swing.JButton();
        cboEnergie = new javax.swing.JComboBox<>();
        cboCategorie = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtAdresse = new javax.swing.JTextField();
        txtProfession = new javax.swing.JTextField();
        txtAssure = new javax.swing.JTextField();
        cboTalon = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dateDelivrance = new com.toedter.calendar.JDateChooser();
        cboGroupe = new javax.swing.JComboBox<>();
        dateEcheance = new com.toedter.calendar.JDateChooser();
        labelHeure = new javax.swing.JLabel();
        label12 = new javax.swing.JLabel();
        btnImprimer = new javax.swing.JButton();
        btnValider = new javax.swing.JButton();
        nombreChevaux = new com.toedter.components.JSpinField();
        jLabel47 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nombrePlaces = new com.toedter.components.JSpinField();
        nombreMois = new com.toedter.components.JSpinField();
        jLabel2 = new javax.swing.JLabel();
        lbl_retour1 = new javax.swing.JLabel();
        btnClearFields = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        listeAssure = new javax.swing.JPanel();
        btnModifier1 = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        lbl_retour = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnRenouvellement = new javax.swing.JButton();
        btnDuplicata = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        borderaux = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabBorderaux = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cboGroupeBorderaux = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        btnPrint1 = new javax.swing.JButton();
        btnSupprimer1 = new javax.swing.JButton();
        txTAttest_filter = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        historiqueAssure = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabHistorique = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btnSupprimer2 = new javax.swing.JButton();
        btnModifier2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnPrint2 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Assurance Automobile 2021 SRT3");
        setAlwaysOnTop(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 51, 0));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 605, 1570, 80));

        tabPan.setToolTipText("");

        acceuil.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\téléchargement.png")); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\Frame 1.png")); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\Frame 3.png")); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\Frame 2.png")); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\Frame 4.png")); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout acceuilLayout = new javax.swing.GroupLayout(acceuil);
        acceuil.setLayout(acceuilLayout);
        acceuilLayout.setHorizontalGroup(
            acceuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceuilLayout.createSequentialGroup()
                .addGroup(acceuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(acceuilLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19))
                    .addGroup(acceuilLayout.createSequentialGroup()
                        .addGap(429, 429, 429)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(410, Short.MAX_VALUE))
        );
        acceuilLayout.setVerticalGroup(
            acceuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceuilLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(acceuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(acceuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel3)
                    .addComponent(jLabel19))
                .addContainerGap(340, Short.MAX_VALUE))
        );

        tabPan.addTab("tab1", acceuil);

        affNouvelle.setBackground(new java.awt.Color(255, 255, 255));
        affNouvelle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(204, 0, 51));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("SOMME ENCAISSEE");
        jLabel57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, 140, 20));

        jLabel61.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("PRIME TOTAL");
        jLabel61.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 180, 30));

        jLabel53.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Fond de garantie");
        jLabel53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 540, 140, -1));

        txtPT.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        txtPT.setForeground(new java.awt.Color(178, 17, 17));
        txtPT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(159, 0, 0)));
        txtPT.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPTActionPerformed(evt);
            }
        });
        affNouvelle.add(txtPT, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 570, 212, 30));

        txtFG.setEditable(false);
        txtFG.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        txtFG.setForeground(new java.awt.Color(178, 17, 17));
        txtFG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(159, 0, 0)));
        txtFG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFGActionPerformed(evt);
            }
        });
        affNouvelle.add(txtFG, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 530, 212, 30));

        txtTaxe.setEditable(false);
        txtTaxe.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        txtTaxe.setForeground(new java.awt.Color(178, 17, 17));
        txtTaxe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTaxe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(159, 0, 0)));
        txtTaxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaxeActionPerformed(evt);
            }
        });
        affNouvelle.add(txtTaxe, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 212, 30));

        jLabel52.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Taxe");
        jLabel52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, 90, -1));

        jLabel51.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Prime Nette");
        jLabel51.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 140, -1));

        txtPrimeNette.setEditable(false);
        txtPrimeNette.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        txtPrimeNette.setForeground(new java.awt.Color(178, 17, 17));
        txtPrimeNette.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrimeNette.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(159, 0, 0)));
        txtPrimeNette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimeNetteActionPerformed(evt);
            }
        });
        affNouvelle.add(txtPrimeNette, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 212, 30));

        txtRC.setEditable(false);
        txtRC.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        txtRC.setForeground(new java.awt.Color(178, 17, 17));
        txtRC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(159, 0, 0)));
        txtRC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRCActionPerformed(evt);
            }
        });
        affNouvelle.add(txtRC, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 212, 30));

        jLabel27.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Responsabilité civile");
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 140, -1));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Immatriculation");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 120, 20));

        txtImmat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImmatActionPerformed(evt);
            }
        });
        affNouvelle.add(txtImmat, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, 210, 30));

        txtMarque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarqueActionPerformed(evt);
            }
        });
        affNouvelle.add(txtMarque, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 212, 30));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Marque");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 69, -1));

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Genre");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 69, -1));

        txtGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGenreActionPerformed(evt);
            }
        });
        affNouvelle.add(txtGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 212, 30));

        txtVehicule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVehiculeActionPerformed(evt);
            }
        });
        affNouvelle.add(txtVehicule, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 212, 30));

        jLabel11.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Véhicule");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 69, -1));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Telephone");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 69, -1));

        txtTelephone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelephoneActionPerformed(evt);
            }
        });
        affNouvelle.add(txtTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 212, 30));

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Energie ");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 69, -1));

        jLabel24.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Tonnage");
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 100, -1));

        cboTonnage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "+3.5 Tonnes", "-3.5 Tonnes" }));
        cboTonnage.setSelectedIndex(-1);
        affNouvelle.add(cboTonnage, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 190, 30));

        txtAttest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAttestActionPerformed(evt);
            }
        });
        affNouvelle.add(txtAttest, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 190, 30));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Attestation");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 300, 80, 20));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Police");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 69, -1));

        txtPolice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPoliceActionPerformed(evt);
            }
        });
        affNouvelle.add(txtPolice, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 190, 30));

        jLabel26.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("COUT DE POLICE");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 430, 120, -1));

        txtCoutPolice.setEditable(false);
        txtCoutPolice.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        txtCoutPolice.setForeground(new java.awt.Color(204, 0, 0));
        txtCoutPolice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCoutPolice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        txtCoutPolice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCoutPoliceActionPerformed(evt);
            }
        });
        affNouvelle.add(txtCoutPolice, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, 190, 30));

        txtSommeFacture.setEditable(false);
        txtSommeFacture.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        txtSommeFacture.setForeground(new java.awt.Color(204, 0, 0));
        txtSommeFacture.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSommeFacture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        txtSommeFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSommeFactureActionPerformed(evt);
            }
        });
        affNouvelle.add(txtSommeFacture, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 190, 30));

        jLabel55.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("SOMME FACTUREE");
        jLabel55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 470, 130, -1));

        txtSommeEncaisse.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        txtSommeEncaisse.setForeground(new java.awt.Color(204, 0, 0));
        txtSommeEncaisse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSommeEncaisse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        txtSommeEncaisse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSommeEncaisseActionPerformed(evt);
            }
        });
        txtSommeEncaisse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSommeEncaisseKeyPressed(evt);
            }
        });
        affNouvelle.add(txtSommeEncaisse, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 510, 190, 30));

        btnCalculer.setBackground(new java.awt.Color(255, 50, 50));
        btnCalculer.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        btnCalculer.setForeground(new java.awt.Color(255, 255, 255));
        btnCalculer.setText("Calculer");
        btnCalculer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCalculerMouseClicked(evt);
            }
        });
        btnCalculer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculerActionPerformed(evt);
            }
        });
        affNouvelle.add(btnCalculer, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 190, -1));

        cboEnergie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Essence", "Gazoile" }));
        cboEnergie.setSelectedIndex(-1);
        affNouvelle.add(cboEnergie, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 190, 30));

        cboCategorie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Catégorie 1: Particulier, Vehicule Utilitaire", "Catégorie 2: Camionette", "Catégorie 3: TPM-Transport Marchandise", "Catégorie 4: Bus Tata, Taxi" }));
        cboCategorie.setSelectedIndex(-1);
        cboCategorie.setAutoscrolls(true);
        affNouvelle.add(cboCategorie, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 190, 30));

        jLabel20.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Categorie");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 69, -1));

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Date échéance");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 110, -1));

        txtAdresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdresseActionPerformed(evt);
            }
        });
        affNouvelle.add(txtAdresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 212, 30));

        txtProfession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProfessionActionPerformed(evt);
            }
        });
        affNouvelle.add(txtProfession, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 212, 30));

        txtAssure.setToolTipText("");
        txtAssure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAssureActionPerformed(evt);
            }
        });
        affNouvelle.add(txtAssure, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 212, 30));

        cboTalon.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        cboTalon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HORSPOOL TALON JAUNE", "POOL TALON VERT" }));
        cboTalon.setSelectedIndex(-1);
        cboTalon.setToolTipText("");
        cboTalon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTalonMouseClicked(evt);
            }
        });
        cboTalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTalonActionPerformed(evt);
            }
        });
        affNouvelle.add(cboTalon, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 210, -1));

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Profession");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 130, 80, -1));

        jLabel50.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Nom Prénom Assuré");
        jLabel50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 150, -1));

        jLabel25.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Adresse");
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 69, -1));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Type de producteur");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 50, 140, 20));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Groupe");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 69, -1));

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Date délivrance");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 110, -1));
        affNouvelle.add(dateDelivrance, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 190, 30));

        cboGroupe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Argenciel", "Pole" }));
        cboGroupe.setSelectedIndex(-1);
        cboGroupe.setToolTipText("");
        cboGroupe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGroupeActionPerformed(evt);
            }
        });
        affNouvelle.add(cboGroupe, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 190, 30));

        dateEcheance.setEnabled(false);
        affNouvelle.add(dateEcheance, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 190, 30));

        labelHeure.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        labelHeure.setForeground(new java.awt.Color(255, 51, 51));
        labelHeure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeure.setText("00:00:00");
        labelHeure.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        affNouvelle.add(labelHeure, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 360, 170, 30));

        label12.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label12.setText("Consulter l'heure Actuelle");
        label12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        affNouvelle.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 330, 230, 30));

        btnImprimer.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimer.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        btnImprimer.setForeground(new java.awt.Color(102, 102, 102));
        btnImprimer.setText("Imprimer la carte grise");
        btnImprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimerMouseClicked(evt);
            }
        });
        btnImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimerActionPerformed(evt);
            }
        });
        affNouvelle.add(btnImprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 270, 290, 40));

        btnValider.setBackground(new java.awt.Color(255, 50, 50));
        btnValider.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        btnValider.setForeground(new java.awt.Color(255, 255, 255));
        btnValider.setText("Enregistrer");
        btnValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderActionPerformed(evt);
            }
        });
        affNouvelle.add(btnValider, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 220, 290, 40));
        affNouvelle.add(nombreChevaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, 60, -1));

        jLabel47.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Nombre de CV");
        jLabel47.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, 110, -1));

        jLabel21.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Nombre de places");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, 130, -1));

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Nombre de mois");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        affNouvelle.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 120, -1));
        affNouvelle.add(nombrePlaces, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 80, 60, -1));
        affNouvelle.add(nombreMois, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 50, 60, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\téléchargement__1_-removebg-preview.png")); // NOI18N
        affNouvelle.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 410, 200, 220));

        lbl_retour1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\left_26px.png")); // NOI18N
        lbl_retour1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_retour1MouseClicked(evt);
            }
        });
        affNouvelle.add(lbl_retour1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 30, 60));

        btnClearFields.setBackground(new java.awt.Color(255, 255, 255));
        btnClearFields.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        btnClearFields.setText("Effacer les champs ou les activés");
        btnClearFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFieldsActionPerformed(evt);
            }
        });
        affNouvelle.add(btnClearFields, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, 290, 40));

        jLabel38.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 51, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("*");
        affNouvelle.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 510, 20, 20));

        jLabel39.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(204, 51, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("*");
        affNouvelle.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 20, 20));

        jLabel40.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(204, 51, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("*");
        affNouvelle.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 20, 20));

        jLabel41.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 51, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("*");
        affNouvelle.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 20, 20));

        jLabel42.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(204, 51, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("*");
        affNouvelle.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 20, 20));

        jLabel43.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(204, 51, 0));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("*");
        affNouvelle.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 20, 20));

        jLabel44.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(204, 51, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("*");
        affNouvelle.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 20, 20));

        jLabel45.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(204, 51, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("*");
        affNouvelle.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 20, 20));

        jLabel46.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(204, 51, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("*");
        affNouvelle.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 20, 20));

        jLabel48.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(204, 51, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("*");
        affNouvelle.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 20, 20));

        jLabel49.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(204, 51, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("*");
        affNouvelle.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 20, 20));

        jLabel54.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(204, 51, 0));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("*");
        affNouvelle.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 20, 20));

        jLabel56.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(204, 51, 0));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("*");
        affNouvelle.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 20, 20));

        jLabel58.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(204, 51, 0));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("*");
        affNouvelle.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 20, 20));

        jLabel59.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(204, 51, 0));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("*");
        affNouvelle.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 20, 20));

        jLabel60.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(204, 51, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("*");
        affNouvelle.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, 20, 20));

        jLabel62.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(204, 51, 0));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("*");
        affNouvelle.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 20, 20));

        jLabel63.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(204, 51, 0));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("*");
        affNouvelle.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 50, 20, 20));

        jLabel64.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(204, 51, 0));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("*");
        affNouvelle.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, 20, 20));

        jLabel66.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(204, 51, 0));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("*");
        affNouvelle.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 110, 20, 20));

        tabPan.addTab("tab2", affNouvelle);

        listeAssure.setBackground(new java.awt.Color(255, 255, 255));
        listeAssure.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModifier1.setBackground(new java.awt.Color(255, 255, 255));
        btnModifier1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnModifier1.setForeground(new java.awt.Color(86, 159, 245));
        btnModifier1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\update_20px.png")); // NOI18N
        btnModifier1.setText("Modifier");
        btnModifier1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModifier1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModifier1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModifier1MouseExited(evt);
            }
        });
        btnModifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifier1ActionPerformed(evt);
            }
        });
        listeAssure.add(btnModifier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, 220, -1));

        btnSupprimer.setBackground(new java.awt.Color(204, 0, 0));
        btnSupprimer.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnSupprimer.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimer.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\delete_trash_20px.png")); // NOI18N
        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });
        listeAssure.add(btnSupprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 130, 220, -1));

        btnPrint.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 153, 204));
        btnPrint.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\print_48px.png")); // NOI18N
        btnPrint.setText("Imprimer la liste");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        listeAssure.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 360, 50));

        lbl_retour.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\back_arrow_40px.png")); // NOI18N
        lbl_retour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_retourMouseClicked(evt);
            }
        });
        listeAssure.add(lbl_retour, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 40, 40));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "operateur", "assure", "attestation", "police", "immatr"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        listeAssure.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 266, 1200, 370));

        jButton1.setBackground(new java.awt.Color(128, 193, 244));
        jButton1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\add_user_group_man_man_20px.png")); // NOI18N
        jButton1.setText("Ajouter un Nouveau");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        listeAssure.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 220, 30));

        btnRenouvellement.setBackground(new java.awt.Color(255, 255, 255));
        btnRenouvellement.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnRenouvellement.setForeground(new java.awt.Color(0, 204, 0));
        btnRenouvellement.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\double_tick_20px.png")); // NOI18N
        btnRenouvellement.setText("Renouvellement");
        btnRenouvellement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenouvellementActionPerformed(evt);
            }
        });
        listeAssure.add(btnRenouvellement, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 170, 220, 30));

        btnDuplicata.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnDuplicata.setText("Duplicata");
        btnDuplicata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuplicataActionPerformed(evt);
            }
        });
        listeAssure.add(btnDuplicata, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 210, 220, 30));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 51, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\Frame 6 copy.png")); // NOI18N
        listeAssure.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 390, 70));

        jLabel34.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\43483916-moderne-voiture-rouge-removebg-preview.png")); // NOI18N
        listeAssure.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 440, 220));

        tabPan.addTab("tab3", listeAssure);

        borderaux.setBackground(new java.awt.Color(255, 255, 255));
        borderaux.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabBorderaux.setAutoCreateRowSorter(true);
        tabBorderaux.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        tabBorderaux.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "operateur", "assure", "attestation", "police", "immatr", "null", "null", "null", "null", "null", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabBorderaux.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabBorderaux.setEditingColumn(0);
        tabBorderaux.setEditingRow(0);
        tabBorderaux.setFocusable(false);
        tabBorderaux.setGridColor(new java.awt.Color(255, 255, 255));
        tabBorderaux.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabBorderaux.setRowHeight(25);
        tabBorderaux.setSelectionBackground(new java.awt.Color(204, 0, 51));
        tabBorderaux.setShowVerticalLines(false);
        tabBorderaux.getTableHeader().setReorderingAllowed(false);
        tabBorderaux.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabBorderauxMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabBorderaux);

        borderaux.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 263, 1232, 514));

        jLabel28.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\la-meilleure-assurance-auto-.png")); // NOI18N
        borderaux.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, -1, 223));

        jLabel29.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\profile_60px.png")); // NOI18N
        jLabel29.setText("BORDERAUX HORSPOOL");
        borderaux.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 350, -1));

        cboGroupeBorderaux.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Argenciel", "Pole", "      " }));
        cboGroupeBorderaux.setSelectedIndex(-1);
        cboGroupeBorderaux.setToolTipText("");
        cboGroupeBorderaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGroupeBorderauxActionPerformed(evt);
            }
        });
        borderaux.add(cboGroupeBorderaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 130, 30));

        jLabel30.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Trier par groupe");
        borderaux.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 160, 30));

        btnPrint1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\print_48px.png")); // NOI18N
        btnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint1ActionPerformed(evt);
            }
        });
        borderaux.add(btnPrint1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 190, 70, -1));

        btnSupprimer1.setBackground(new java.awt.Color(204, 0, 0));
        btnSupprimer1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnSupprimer1.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimer1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\delete_trash_20px.png")); // NOI18N
        btnSupprimer1.setText("Supprimer");
        btnSupprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimer1ActionPerformed(evt);
            }
        });
        borderaux.add(btnSupprimer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 210, 170, -1));

        txTAttest_filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txTAttest_filterMousePressed(evt);
            }
        });
        txTAttest_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTAttest_filterActionPerformed(evt);
            }
        });
        txTAttest_filter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txTAttest_filterKeyPressed(evt);
            }
        });
        borderaux.add(txTAttest_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, 130, 31));

        jLabel31.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel31.setText("Attestation");
        borderaux.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 80, 32));
        borderaux.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 168, -1));
        borderaux.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 168, -1));

        jLabel32.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel32.setText("Date d'Echeance");
        borderaux.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 168, 20));

        jLabel33.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel33.setText("Date de délivrance");
        borderaux.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 165, 20));

        jButton2.setBackground(new java.awt.Color(102, 204, 255));
        jButton2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Filtrer");
        borderaux.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 168, 30));

        tabPan.addTab("tab4", borderaux);

        historiqueAssure.setBackground(new java.awt.Color(255, 255, 255));
        historiqueAssure.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tabHistorique.setAutoCreateRowSorter(true);
        tabHistorique.setFont(new java.awt.Font("Cambria", 0, 15)); // NOI18N
        tabHistorique.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "assure", "adresse", "attestation", "police", "genre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabHistorique.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabHistorique.setEditingColumn(0);
        tabHistorique.setEditingRow(0);
        tabHistorique.setFocusable(false);
        tabHistorique.setGridColor(new java.awt.Color(255, 255, 255));
        tabHistorique.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabHistorique.setRowHeight(25);
        tabHistorique.setSelectionBackground(new java.awt.Color(204, 0, 51));
        tabHistorique.setShowVerticalLines(false);
        tabHistorique.getTableHeader().setReorderingAllowed(false);
        tabHistorique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabHistoriqueMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabHistorique);
        if (tabHistorique.getColumnModel().getColumnCount() > 0) {
            tabHistorique.getColumnModel().getColumn(0).setResizable(false);
            tabHistorique.getColumnModel().getColumn(1).setResizable(false);
            tabHistorique.getColumnModel().getColumn(2).setResizable(false);
            tabHistorique.getColumnModel().getColumn(3).setResizable(false);
            tabHistorique.getColumnModel().getColumn(4).setResizable(false);
        }

        historiqueAssure.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 116, 920, 660));

        jPanel2.setBackground(new java.awt.Color(204, 51, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\Frame 8.png")); // NOI18N
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 365, 61));

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\back_arrow_60px.png")); // NOI18N
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 48, -1, -1));

        historiqueAssure.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -4, 1250, 120));

        btnSupprimer2.setBackground(new java.awt.Color(204, 0, 0));
        btnSupprimer2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnSupprimer2.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimer2.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\delete_trash_20px.png")); // NOI18N
        btnSupprimer2.setText("Supprimer");
        btnSupprimer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimer2ActionPerformed(evt);
            }
        });
        historiqueAssure.add(btnSupprimer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 210, 220, -1));

        btnModifier2.setBackground(new java.awt.Color(255, 255, 255));
        btnModifier2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnModifier2.setForeground(new java.awt.Color(86, 159, 245));
        btnModifier2.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\update_20px.png")); // NOI18N
        btnModifier2.setText("Modifier");
        btnModifier2.setEnabled(false);
        btnModifier2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModifier2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModifier2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModifier2MouseExited(evt);
            }
        });
        btnModifier2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifier2ActionPerformed(evt);
            }
        });
        historiqueAssure.add(btnModifier2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 170, 220, -1));

        jButton4.setBackground(new java.awt.Color(128, 193, 244));
        jButton4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\add_user_group_man_man_20px.png")); // NOI18N
        jButton4.setText("Ajouter");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        historiqueAssure.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 130, 220, 30));

        btnPrint2.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint2.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        btnPrint2.setForeground(new java.awt.Color(0, 153, 204));
        btnPrint2.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\print_48px.png")); // NOI18N
        btnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint2ActionPerformed(evt);
            }
        });
        historiqueAssure.add(btnPrint2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 250, 220, 90));

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\image Projet java\\téléchargement__1_-removebg-preview.png")); // NOI18N
        historiqueAssure.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 390, 250, 240));

        tabPan.addTab("tab5", historiqueAssure);

        getContentPane().add(tabPan, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, -56, 1510, -1));

        jPanel1.setBackground(new java.awt.Color(204, 51, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 610));

        jMenu1.setText("ASSURANCE");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Assurance Nouvelle");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Liste des Assurés");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Historique des assurés");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Liste des polices attribués");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("BORDERAUX");

        jMenuItem5.setText("Horspool");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("TPV");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("EXPIRATIONS");

        jMenuItem7.setText("Expiration mensuelle");
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("CAISSE");

        jMenuItem8.setText("Etat de la caisse");
        jMenu4.add(jMenuItem8);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1201, 723));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        tabPan.setSelectedIndex(2);
        jTextArea1.setText(null);
        affiche_listeAssure();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        tabPan.setSelectedIndex(4);
        affiche_HistoriqueAssure();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        tabPan.setSelectedIndex(1); 
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        tabPan.setSelectedIndex(1); 
        cboTalon.setSelectedIndex(-1);
        btnValider.setText("Enregistrer");
        affNouvelle.setBackground(Color.white);
        jTextArea1.setText("RAPPEL: les Astérix (*) traduit que leurs champs sont obligatoire à remplir \n");
        clearField();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPTActionPerformed

    private void txtFGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFGActionPerformed

    private void txtTaxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaxeActionPerformed

    private void txtPrimeNetteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimeNetteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimeNetteActionPerformed

    private void txtRCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRCActionPerformed

    private void txtImmatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImmatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImmatActionPerformed

    private void txtMarqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarqueActionPerformed

    private void txtGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGenreActionPerformed

    private void txtVehiculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVehiculeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVehiculeActionPerformed

    private void txtTelephoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelephoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelephoneActionPerformed

    private void txtAttestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAttestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAttestActionPerformed

    private void txtPoliceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPoliceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPoliceActionPerformed

    private void txtCoutPoliceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCoutPoliceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCoutPoliceActionPerformed

    private void txtSommeFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSommeFactureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSommeFactureActionPerformed

    private void txtSommeEncaisseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSommeEncaisseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSommeEncaisseActionPerformed

    private void btnCalculerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculerActionPerformed
        /*
        if (evt.getSource() == "btnCalculer"){
            System.out.println("btn calcul cliké");
            int encaisser = Integer.parseInt(txtSommeEncaisse.getText());
            int primeTotale = Integer.parseInt(txtPT.getText());
            int somme =  primeTotale - 3000;
                
                if((somme > encaisser)||( primeTotale < encaisser)){
                    JOptionPane.showMessageDialog(null,"ATTENTION LA SOMMME ENCAISEE EST INCORRECTe !! Valeur minimale : "+somme); 
                } else {
                    jTextArea1.append("Bien la somme encaissé est envisagiable ! ");
                }
        }
        */
    }//GEN-LAST:event_btnCalculerActionPerformed

    private void txtAdresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdresseActionPerformed

    private void txtProfessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProfessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfessionActionPerformed

    private void txtAssureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAssureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAssureActionPerformed

    private void cboTalonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTalonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTalonMouseClicked

    private void cboTalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTalonActionPerformed
        int n = cboTalon.getSelectedIndex();
        System.out.println("valeur de n index"+n);
        if(n==0){
            affNouvelle.setBackground(Color.yellow);
        }else if(n==1){
            affNouvelle.setBackground(Color.green);
        }
    }//GEN-LAST:event_cboTalonActionPerformed

    private void cboGroupeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGroupeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGroupeActionPerformed

    private void btnImprimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimerMouseClicked
        carteGrise carte = new carteGrise();
        carte.setVisible(true);
        carte.setAlwaysOnTop(true);
    }//GEN-LAST:event_btnImprimerMouseClicked

    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimerActionPerformed

    private void btnValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderActionPerformed
        
        if ( (evt.getSource() == btnValider) && (btnValider.getText() == "Enregistrer") || (btnValider.getText() == "Renouveller") || (btnValider.getText() == "Faire un duplicata") ){
         
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance", "root", "");
                String sql ="INSERT INTO `alldata`(`operateur`, `clientAssure`, `profession`, `adresse`, `telephone`, `dateEcheance`, `dateDelivrance`, `heure`, `prime_total`,`primeNette`, `nomCompagnie`, `marque`, `immatriculation`, `genre`, `puissanceMoteur`, `energie`, `nombrePlaces`, `nombreMois`, `talon`, `groupe`, `categorie`, `numeroPolice`, `attestation`, `tonnage`, `commission`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                int telephone = Integer.parseInt(txtTelephone.getText());

                double pnette = Double.parseDouble(txtPrimeNette.getText());
                int pnette_int = (int)pnette;
                int ptotal = Integer.parseInt(txtPT.getText());
                
                
                SimpleDateFormat tdate = new SimpleDateFormat("yyyy-MM-dd");
                String dateEche = tdate.format(dateEcheance.getDate());
                String dateDeliv = tdate.format(dateDelivrance.getDate());

                java.util.Date date =new java.util.Date();
                java.sql.Date sqlDate =new java.sql.Date(date.getTime());
                java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());

                String categorie = (String) cboCategorie.getSelectedItem();
                String energie = (String) cboEnergie.getSelectedItem();
                String tonnage = (String) cboTonnage.getSelectedItem();
                String groupe = (String) cboGroupe.getSelectedItem();
                String talon = (String) cboTalon.getSelectedItem();

                int police  = Integer.parseInt(txtPolice.getText());
                int attestation = Integer.parseInt(txtAttest.getText());
                int commission = (int) (pnette_int * 18) /100; // primeNette * 18% 

                try (
                    PreparedStatement ptmt = connection.prepareStatement(sql)) {
                    ptmt.setString(1, "MODOU NAR NONGO");
                    ptmt.setString(2, txtAssure.getText());
                    ptmt.setString(3, txtProfession.getText());
                    ptmt.setString(4, txtAdresse.getText());
                    ptmt.setInt(5, telephone);
                    ptmt.setString(6,dateEche);
                    ptmt.setString(7, dateDeliv);
                    ptmt.setTimestamp(8, sqlTime);
                    ptmt.setInt(9, ptotal); //--cocrrigé :) String DATABSE--------- Prime Total à calculer !!!------------
                    ptmt.setInt(10,pnette_int); //------Int in DATABASE----- Prime Nette à calculer !!!------------
                    ptmt.setString(11, "ASSURANCE AUTOMOBILE Senegalaise");
                    ptmt.setString(12,txtMarque.getText());
                    ptmt.setString(13,txtImmat.getText());
                    ptmt.setString(14,txtGenre.getText());
                    ptmt.setInt(15, nombreChevaux.getValue());
                    ptmt.setString(16, energie);
                    ptmt.setInt(17, nombrePlaces.getValue());
                    ptmt.setInt(18, nombreMois.getValue());
                    ptmt.setString(19,talon);
                    ptmt.setString(20, groupe);
                    ptmt.setString(21,categorie);
                    ptmt.setInt(22,police);
                    ptmt.setInt(23,attestation);
                    ptmt.setString(24, tonnage);
                    ptmt.setInt(25,commission); // -------COMMISSION CALCULEE --------

                    ptmt.executeUpdate();
                    //JOptionPane.showMessageDialog(null,"Informations de l'assuré ajoutées avec succés",null,JOptionPane.INFORMATION_MESSAGE);
                    jTextArea1.setForeground(Color.green);
                    jTextArea1.setText("**** Bravo ! les informations de l'assuré ont été insérées avec succés \n");
                    clearField();
                    affiche_listeAssure(); // réactuliser la table des assurés....
                }

            } catch (SQLException ex){
                //JOptionPane.showMessageDialog(null, ex.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                jTextArea1.setText(ex.getMessage());
            }
        } else {            
                if ((evt.getSource() == btnValider ) && (btnValider.getText()== "Modifier")){
                    update();
                    affiche_listeAssure();
                } /*else {
                if ((evt.getSource() == btnValider ) &&(btnValider.getText() == "Renouveller")){
                    update();
                    affiche_listeAssure();
                    }
                }*/
        }
    }//GEN-LAST:event_btnValiderActionPerformed

    private void btnModifier1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifier1MouseClicked

    }//GEN-LAST:event_btnModifier1MouseClicked

    private void btnModifier1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifier1MouseEntered
        btnModifier1.setBackground(new Color(4,128,4));
        btnModifier1.setForeground(Color.white);
    }//GEN-LAST:event_btnModifier1MouseEntered

    private void btnModifier1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifier1MouseExited
        btnModifier1.setBackground(new Color(255,255,255));
        btnModifier1.setForeground(Color.black);
    }//GEN-LAST:event_btnModifier1MouseExited

    private void btnModifier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifier1ActionPerformed

        if (evt.getSource() == btnModifier1){

            int ligne = jTable1.getSelectedRow();
            String value = jTable1.getModel().getValueAt(ligne, 2).toString();

            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql_recup = "Select * from  alldata where attestation="+value;
                System.out.println("Valeur ligne clique: "+value);
                PreparedStatement ps = con.prepareStatement(sql_recup);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    tabPan.setSelectedIndex(1);
                    
                    id = rs.getInt("id");
                    System.out.print("ID client cliqué :"+id);
                    txtAssure.setText(rs.getString("clientAssure"));
                    txtProfession.setText(rs.getString("profession"));
                    txtAdresse.setText(rs.getString("adresse"));
                    txtMarque.setText(rs.getString("marque"));
                    txtImmat.setText(rs.getString("immatriculation"));
                    txtAttest.setText(rs.getString("attestation"));
                    txtPolice.setText(rs.getString("numeroPolice"));
                    txtTelephone.setText(rs.getString("telephone"));
                    txtGenre.setText(rs.getString("genre"));
                    
                    cboTalon.setSelectedItem(rs.getString("talon"));
                    cboCategorie.setSelectedItem(rs.getString("categorie"));
                    cboEnergie.setSelectedItem(rs.getString("energie"));
                    cboGroupe.setSelectedItem(rs.getString("groupe"));
                    cboTonnage.setSelectedItem(rs.getString("tonnage"));
                    
                    dateDelivrance.setDate(rs.getDate("dateDelivrance"));
                    dateEcheance.setDate(rs.getDate("dateEcheance"));
                    nombrePlaces.setValue(rs.getInt("nombrePlaces"));
                    nombreMois.setValue(rs.getInt("nombreMois"));
                    nombreChevaux.setValue(rs.getInt("puissanceMoteur"));                   
                    
                    
                    btnValider.setText("Modifier");
                }

            } catch (SQLException ex){
                jTextArea1.setText(ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnModifier1ActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        if (evt.getSource() == btnSupprimer){

            int ligne = jTable1.getSelectedRow();
            String data = jTable1.getModel().getValueAt(ligne, 2).toString();
            
            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql_recup = "DELETE FROM alldata WHERE attestation = "+data;
                System.out.println("Valeur ligne clique: "+data);
                PreparedStatement ps = con.prepareStatement(sql_recup);
                ps.executeUpdate();
                jTextArea1.setForeground(Color.green);
                jTextArea1.setText("**** Suppression effectuée !! ****** \n ");
                affiche_listeAssure();   // refresh automatiquement la liste !!            

            } catch (SQLException ex){
                   jTextArea1.setText(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

    private void lbl_retourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_retourMouseClicked
        tabPan.setSelectedIndex(0);    
    }//GEN-LAST:event_lbl_retourMouseClicked

    private void lbl_retour1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_retour1MouseClicked
        tabPan.setSelectedIndex(0);
    }//GEN-LAST:event_lbl_retour1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        tabPan.setSelectedIndex(2); 
        affiche_listeAssure();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        tabPan.setSelectedIndex(1);
        cboTalon.setSelectedIndex(-1);
        btnValider.setText("Enregistrer");
        affNouvelle.setBackground(Color.white);
        jTextArea1.setText("RAPPEL: les Astérix (*) traduit que leurs champs sont obligatoire à remplir \n");
        clearField();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btnRenouvellementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenouvellementActionPerformed
        
        //-------- Recupere les donnees comme en modification ----------
            int ligne = jTable1.getSelectedRow();
            String value = jTable1.getModel().getValueAt(ligne, 2).toString();

            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql_recup = "Select * from  alldata where attestation="+value;
                System.out.println("Assure à renouvellement: "+value);
                PreparedStatement ps = con.prepareStatement(sql_recup);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    
                    tabPan.setSelectedIndex(1);
                    
                    geler(); //desactiver tous les champs
                    
                    id = rs.getInt("id");
                    System.out.print("ID client cliqué :"+id);
                    txtAssure.setText(rs.getString("clientAssure"));
                    txtProfession.setText(rs.getString("profession"));
                    txtAdresse.setText(rs.getString("adresse"));
                    txtMarque.setText(rs.getString("marque"));
                    txtImmat.setText(rs.getString("immatriculation"));
                    txtAttest.setText(rs.getString("attestation"));
                    txtPolice.setText(rs.getString("numeroPolice"));
                    txtTelephone.setText(rs.getString("telephone"));
                    txtGenre.setText(rs.getString("genre"));
                    
                    cboTalon.setSelectedItem(rs.getString("talon"));
                    cboCategorie.setSelectedItem(rs.getString("categorie"));
                    cboEnergie.setSelectedItem(rs.getString("energie"));
                    cboGroupe.setSelectedItem(rs.getString("groupe"));
                    cboTonnage.setSelectedItem(rs.getString("tonnage"));
                    
                    dateDelivrance.setDate(rs.getDate("dateDelivrance"));
                    dateEcheance.setDate(rs.getDate("dateEcheance"));
                    nombrePlaces.setValue(rs.getInt("nombrePlaces"));
                    nombreMois.setValue(rs.getInt("nombreMois"));
                    nombreChevaux.setValue(rs.getInt("puissanceMoteur"));                   
                    
                    //------Modifier le label du bouton 
                    btnValider.setText("Renouveller");
                    
                }

            } catch (SQLException ex){
                jTextArea1.setText(ex.getMessage());
            }        
        
    }//GEN-LAST:event_btnRenouvellementActionPerformed

    private void btnDuplicataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuplicataActionPerformed
        
        //-------- Recupere les donnees comme en modification ----------
            int ligne = jTable1.getSelectedRow();
            String value = jTable1.getModel().getValueAt(ligne, 2).toString();

            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql_recup = "Select * from  alldata where attestation="+value;
                System.out.println("Aattestation de l'assuré pour duplicata ==>: "+value);
                PreparedStatement ps = con.prepareStatement(sql_recup);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    
                    tabPan.setSelectedIndex(1);
                    
                    geler_tout(); //desactiver tous les champs sans exception
                    
                    id = rs.getInt("id");
                    System.out.print("ID client cliqué :"+id);
                    txtAssure.setText(rs.getString("clientAssure"));
                    txtProfession.setText(rs.getString("profession"));
                    txtAdresse.setText(rs.getString("adresse"));
                    txtMarque.setText(rs.getString("marque"));
                    txtImmat.setText(rs.getString("immatriculation"));
                    txtAttest.setText(rs.getString("attestation"));
                    txtPolice.setText(rs.getString("numeroPolice"));
                    txtTelephone.setText(rs.getString("telephone"));
                    txtGenre.setText(rs.getString("genre"));
                    
                    cboTalon.setSelectedItem(rs.getString("talon"));
                    cboCategorie.setSelectedItem(rs.getString("categorie"));
                    cboEnergie.setSelectedItem(rs.getString("energie"));
                    cboGroupe.setSelectedItem(rs.getString("groupe"));
                    cboTonnage.setSelectedItem(rs.getString("tonnage"));
                    
                    dateDelivrance.setDate(rs.getDate("dateDelivrance"));
                    dateEcheance.setDate(rs.getDate("dateEcheance"));
                    nombrePlaces.setValue(rs.getInt("nombrePlaces"));
                    nombreMois.setValue(rs.getInt("nombreMois"));
                    nombreChevaux.setValue(rs.getInt("puissanceMoteur"));                   
                    
                    //------Modifier le label du bouton 
                    btnValider.setText("Faire un duplicata");
                    
                }

            } catch (SQLException ex){
                jTextArea1.setText(ex.getMessage());
            }        
        
    }//GEN-LAST:event_btnDuplicataActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tabPan.setSelectedIndex(1);
        cboTalon.setSelectedIndex(-1);
        btnValider.setText("Enregistrer");
        affNouvelle.setBackground(Color.white);
        jTextArea1.setText("RAPPEL: les Astérix (*) traduit que leurs champs sont obligatoire à remplir \n");;
        clearField();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnClearFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFieldsActionPerformed
       if (evt.getSource() == btnClearFields){
           clearField();
           activer();
       }
    }//GEN-LAST:event_btnClearFieldsActionPerformed

    private void tabBorderauxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabBorderauxMouseClicked

    }//GEN-LAST:event_tabBorderauxMouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        tabPan.setSelectedIndex(3);
        txTAttest_filter.setText(null);
        jTextArea1.setText("");
        afficheBorderaux();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        tabPan.setSelectedIndex(3); 
    }//GEN-LAST:event_jLabel4MouseClicked

    private void cboGroupeBorderauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGroupeBorderauxActionPerformed
        if( cboGroupeBorderaux.getSelectedIndex() == 0){
            afficheBorderauxArgenciel();
        } else if (cboGroupeBorderaux.getSelectedIndex() == 1){
            afficheBorderauxPole();
        } else {
            afficheBorderaux();
        }
    }//GEN-LAST:event_cboGroupeBorderauxActionPerformed

    private void btnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrint1ActionPerformed

    private void btnSupprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimer1ActionPerformed
        if (evt.getSource() == btnSupprimer1){

            int ligne = tabBorderaux.getSelectedRow();
            String data = tabBorderaux.getModel().getValueAt(ligne, 3).toString();
            
            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql_recup = "DELETE FROM alldata WHERE attestation = "+data;
                System.out.println("attestation d'un assuré : "+data);
                PreparedStatement ps = con.prepareStatement(sql_recup);
                ps.executeUpdate();
                jTextArea1.setForeground(Color.green);
                jTextArea1.setText("******* Suppression effectuée depuis le borderaux Horspool!! \n ");
                afficheBorderaux();               

            } catch (SQLException ex){
                   jTextArea1.setText(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSupprimer1ActionPerformed

    private void txTAttest_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTAttest_filterActionPerformed
        /*if(txTAttest_filter.getText() != ""){
            afficheBord_attestation();  
        } else {
            afficheBorderaux();
        }
        */
    }//GEN-LAST:event_txTAttest_filterActionPerformed

    private void txtSommeEncaisseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSommeEncaisseKeyPressed
        
            if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || evt.getKeyChar() == '\u0008' ) {// + backspace button keyboard
               txtSommeEncaisse.setEditable(true);
            } else if (txtSommeEncaisse.getText() == "" ){               
               jTextArea1.setText("");
            } else {
               //txtSommeEncaisse.setEditable(false);
               jTextArea1.append("*** Attention : uniquement des chiffres (0-9) sont permis dans ce champ !!! \n");
            }
            
    }//GEN-LAST:event_txtSommeEncaisseKeyPressed

    private void btnCalculerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalculerMouseClicked
         System.out.println("btn calcul cliké");
            int encaisser = Integer.parseInt(txtSommeEncaisse.getText());
            int primeTotale = Integer.parseInt(txtPT.getText());
            int somme =  primeTotale - 3000;
                
                if((somme > encaisser)||( primeTotale < encaisser)){
                   jTextArea1.append("ATTENTION LA SOMMME ENCAISEE EST INCORRECTe !! Valeur minimale : "+somme+"\n"); 
                } else {
                    jTextArea1.setForeground(Color.green);
                    jTextArea1.setText("Bien la somme encaissé est envisagiable !\n ");
                }
    }//GEN-LAST:event_btnCalculerMouseClicked

    private void txTAttest_filterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txTAttest_filterMousePressed
        
    }//GEN-LAST:event_txTAttest_filterMousePressed

    private void txTAttest_filterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txTAttest_filterKeyPressed
       if ( evt.getKeyCode() != 0 ) {
          ATT = Integer.parseInt(txTAttest_filter.getText());
          afficheBord_attestation();  
       } else {
        jTextArea1.append("*** Ooups : que des chiffres y sont autorisés( !!! \n");
        afficheBorderaux(); 
       }
    }//GEN-LAST:event_txTAttest_filterKeyPressed

    private void tabHistoriqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabHistoriqueMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabHistoriqueMouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        tabPan.setSelectedIndex(4);
        affiche_HistoriqueAssure();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void btnSupprimer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimer2ActionPerformed
       if (evt.getSource() == btnSupprimer2){

            int ligne = tabHistorique.getSelectedRow();
            String data = tabHistorique.getModel().getValueAt(ligne, 2).toString();
            
            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assurance","root","");
                String sql_recup = "DELETE FROM alldata WHERE attestation = "+data;
                System.out.println("Valeur ligne clique: "+data);
                PreparedStatement ps = con.prepareStatement(sql_recup);
                ps.executeUpdate();
                jTextArea1.setForeground(Color.green);
                jTextArea1.setText("******* Suppression effectuée depuis historique !! ");
                affiche_HistoriqueAssure();  // refresh automatiquement la liste !!            

            } catch (SQLException ex){
                   jTextArea1.setText(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSupprimer2ActionPerformed

    private void btnModifier2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifier2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifier2MouseClicked

    private void btnModifier2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifier2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifier2MouseEntered

    private void btnModifier2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifier2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifier2MouseExited

    private void btnModifier2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifier2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifier2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       tabPan.setSelectedIndex(1);
        cboTalon.setSelectedIndex(-1);
        btnValider.setText("Enregistrer");
        affNouvelle.setBackground(Color.white);
        jTextArea1.setText("RAPPEL: les Astérix (*) traduit que leurs champs sont obligatoire à remplir \n");
        clearField();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrint2ActionPerformed

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        tabPan.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel36MouseClicked

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
            java.util.logging.Logger.getLogger(principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acceuil;
    private javax.swing.JPanel affNouvelle;
    private javax.swing.JPanel borderaux;
    private javax.swing.JButton btnCalculer;
    private javax.swing.JButton btnClearFields;
    private javax.swing.JButton btnDuplicata;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JButton btnModifier1;
    private javax.swing.JButton btnModifier2;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrint1;
    private javax.swing.JButton btnPrint2;
    private javax.swing.JButton btnRenouvellement;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnSupprimer1;
    private javax.swing.JButton btnSupprimer2;
    private javax.swing.JButton btnValider;
    private javax.swing.JComboBox<String> cboCategorie;
    private javax.swing.JComboBox<String> cboEnergie;
    private javax.swing.JComboBox<String> cboGroupe;
    private javax.swing.JComboBox<String> cboGroupeBorderaux;
    private javax.swing.JComboBox<String> cboTalon;
    private javax.swing.JComboBox<String> cboTonnage;
    private com.toedter.calendar.JDateChooser dateDelivrance;
    private com.toedter.calendar.JDateChooser dateEcheance;
    private javax.swing.JPanel historiqueAssure;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel label12;
    private javax.swing.JLabel labelHeure;
    private javax.swing.JLabel lbl_retour;
    private javax.swing.JLabel lbl_retour1;
    private javax.swing.JPanel listeAssure;
    private com.toedter.components.JSpinField nombreChevaux;
    private com.toedter.components.JSpinField nombreMois;
    private com.toedter.components.JSpinField nombrePlaces;
    private javax.swing.JTable tabBorderaux;
    private javax.swing.JTable tabHistorique;
    private javax.swing.JTabbedPane tabPan;
    private javax.swing.JTextField txTAttest_filter;
    private javax.swing.JTextField txtAdresse;
    public static javax.swing.JTextField txtAssure;
    private javax.swing.JTextField txtAttest;
    private javax.swing.JTextField txtCoutPolice;
    private javax.swing.JTextField txtFG;
    private javax.swing.JTextField txtGenre;
    private javax.swing.JTextField txtImmat;
    private javax.swing.JTextField txtMarque;
    private javax.swing.JTextField txtPT;
    private javax.swing.JTextField txtPolice;
    private javax.swing.JTextField txtPrimeNette;
    private javax.swing.JTextField txtProfession;
    private javax.swing.JTextField txtRC;
    private javax.swing.JTextField txtSommeEncaisse;
    private javax.swing.JTextField txtSommeFacture;
    private javax.swing.JTextField txtTaxe;
    private javax.swing.JTextField txtTelephone;
    private javax.swing.JTextField txtVehicule;
    // End of variables declaration//GEN-END:variables
}
