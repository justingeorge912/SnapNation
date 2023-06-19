/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.kiteretsu;

import Db.DBHelper;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Justin
 */
public class Voting_Window extends javax.swing.JFrame {

    /**
     * Creates new form Voting_Window
     */
    public Voting_Window(int cid, int pid) {
        initComponents();
        
       File file = new File("C:\\Users\\Justin\\Documents\\NetBeansProjects\\Kiteretsu\\src\\main\\java\\Images\\Voting_Window.jpg");
       BufferedImage bim = null;//new BufferedImage(jLabel2.getWidth(),jLabel2.getHeight(),BufferedImage.TYPE_INT_ARGB);
       try{
       bim = ImageIO.read(file);
       }
       catch(IOException e){}
       
       ImageIcon ic = new ImageIcon(bim);
       //Image img = ic.getImage();
       jLabel2.setIcon(ic);
        
         try{
                    this.cid=cid;
                    this.pid=pid;
                    Connection con = DBHelper.getConnection();
                    Statement st = con.createStatement();
                    String sql = "select caption,image from participants where part_id="+pid+" AND contest_id="+cid;
                    ResultSet res = st.executeQuery(sql);
                    while(res.next()){
                        byte[] img = res.getBytes("image");
                        ImageIcon image = new ImageIcon(img);
                        Image im = image.getImage();
                        Image myimg = im.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon newImage = new ImageIcon(myimg);
                        imgLabel.setIcon(newImage);
                        jLabel3.setText(res.getString("caption"));
                           
                    }
                    
         }
         catch(SQLException e){
             JOptionPane.showMessageDialog(null, e);
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    int cid,pid;

    private Voting_Window() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imgLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(imgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 360, 260));

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 360, -1, -1));

        jButton1.setText("Like");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 80, 40));

        jButton2.setText("Go Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 80, 40));

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Voting_list frame = new Voting_list(cid);
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                try{
                Connection con = DBHelper.getConnection();
                Statement st = con.createStatement();
                String sql = "select vote_count from participants where part_id="+pid+" AND contest_id="+cid;
                ResultSet res = st.executeQuery(sql);
                if(res.next()){
                    
                int count = res.getInt("vote_count");
                count++;
                
                sql="update participants set vote_count=? where part_id=? and contest_id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, count);
                stmt.setInt(2, pid);
                stmt.setInt(3, cid);
                int s = stmt.executeUpdate();
                if(s==1){
                    JOptionPane.showMessageDialog(null, "Voting Done Successfully!");
                    User_page frame = new User_page();
                    frame.setVisible(true);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Voting Failed! Please try later");
                }

                }
                catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Voting_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Voting_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Voting_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Voting_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Voting_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
