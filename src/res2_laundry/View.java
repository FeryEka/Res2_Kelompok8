package res2_laundry;

import java.sql.*;
import java.text.SimpleDateFormat;
import Koneksi.DB_form;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class View extends javax.swing.JFrame {

    DB_form con;
    private Object[][] tblBiodata = null;
    private String[] label = {"KODE PELANGGAN", "TANGGAL", "NAMA PELANGGAN", "NO TELP", "ALAMAT", "BERAT PAKAIAN", "HARGA", "TOTAL BAYAR", "BAYAR" ,"KEMBALIAN"};
    
    public View() {
        initComponents();
        con = new DB_form();
        con.Class();
        BacaTabel();
        Kode_p.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void BacaTabel() {
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select* from inputan order By KdPelanggan ";
            con.rs = con.ss.executeQuery(sql);
            ResultSetMetaData m = con.rs.getMetaData();
            int kolom = m.getColumnCount();
            int baris = 0;
            while (con.rs.next()) {
                baris = con.rs.getRow();
            }
            tblBiodata = new Object[baris][kolom];
            int x = 0;
            con.rs.beforeFirst();
            while (con.rs.next()) {
                tblBiodata[x][0] = con.rs.getString("KdPelanggan");
                tblBiodata[x][1] = con.rs.getString("Tanggal");
                tblBiodata[x][2] = con.rs.getString("Nama_Pelanggan");
                tblBiodata[x][3] = con.rs.getString("No_Telp");
                tblBiodata[x][4] = con.rs.getString("Alamat");
                tblBiodata[x][5] = con.rs.getString("BeratPakaian");
                tblBiodata[x][6] = con.rs.getString("Harga");
                tblBiodata[x][7] = con.rs.getString("TotalBayar");
                tblBiodata[x][8] = con.rs.getString("BayarUang");
                tblBiodata[x][9] = con.rs.getString("Kembalian");
                x++;
            }
            FTABELDATA.setModel(new DefaultTableModel(tblBiodata, label));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //format tanggal
    public final void tgl() {
        Date skrg = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMMM yyyy ");
        String tgl = format.format(skrg);
    }

    private void hapus() {
        try {
            String sql = "Delete from inputan Where KdPelanggan='" + Kode_p.getText() + "'";
            con.ss.executeUpdate(sql);
            con.ss.close();

            JOptionPane.showMessageDialog(null, "Data berhasil di Hapus !!");
            BacaTabel();
            Kode_p.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void update(){
        int ok = JOptionPane.showConfirmDialog(null, "Data Anda Akan Dirubah?", "Konfirmasi Update", JOptionPane.YES_NO_OPTION);
        try {
            String sql = "update inputan set KdPelanggan=?,Tanggal=?,Nama_Pelanggan=?,No_Telp=?,"
                    + "Alamat=?,BeratPakaian=?,Harga=?,TotalBayar=?,BayarUang=?,Kembalian=? where KdPelanggan='" + Kode_p.getText() + "'";
            PreparedStatement edit = con.cc.prepareStatement(sql);
            if (ok == 0) {
                try {

                    edit.setString(1, Kode_p.getText());
                    edit.setString(2, tanggal.getText());
                    edit.setString(3, nama_p.getText());
                    edit.setString(4, no_tlp.getText());
                    edit.setString(5, alamat.getText());
                    edit.setString(6, Berat_pakaian1.getText());
                    edit.setString(7, fharga.getText());
                    edit.setString(8, Total_bayar.getText());
                    edit.setString(9, bayaruang.getText());
                    edit.setString(10, kembalian.getText());

                    edit.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Update Data Berhasil");
                    View m = new View();
                    m.setVisible(true);
                    m.setLocationRelativeTo(null);
                    this.dispose();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Update Data Tidak Berhasil");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "TABEL SALAH");
        }
        
    }

    private void setTabel() {
        int row = FTABELDATA.getSelectedRow();
        Kode_p.setText((String) FTABELDATA.getValueAt(row, 0));
        tanggal.setText((String) FTABELDATA.getValueAt(row, 1));
        nama_p.setText((String) FTABELDATA.getValueAt(row, 2));
        no_tlp.setText((String) FTABELDATA.getValueAt(row, 3));
        alamat.setText((String) FTABELDATA.getValueAt(row, 4));
        Berat_pakaian1.setText((String) FTABELDATA.getValueAt(row, 5));
        fharga.setText((String) FTABELDATA.getValueAt(row, 6));
        Total_bayar.setText((String) FTABELDATA.getValueAt(row, 7));
        bayaruang.setText((String) FTABELDATA.getValueAt(row, 8));
        kembalian.setText((String) FTABELDATA.getValueAt(row, 9));
    }

    private void cari() {
        try {
            con.ss = con.cc.createStatement();
            String sql = "Select * from inputan Where KdPelanggan like '%" + cari.getText() + "%'" + "or Nama_Pelanggan like '%" + cari.getText() + "%'" + "or Alamat like '%" + cari.getText() + "%'";
            con.rs = con.ss.executeQuery(sql);
            ResultSetMetaData m = con.rs.getMetaData();
            int kolom = m.getColumnCount();
            int baris = 0;
            while (con.rs.next()) {
                baris = con.rs.getRow();
            }
            tblBiodata = new Object[baris][kolom];
            int x = 0;
            con.rs.beforeFirst();
            while (con.rs.next()) {
                tblBiodata[x][0] = con.rs.getString("KdPelanggan");
                tblBiodata[x][1] = con.rs.getString("Tanggal");
                tblBiodata[x][2] = con.rs.getString("Nama_Pelanggan");
                tblBiodata[x][3] = con.rs.getString("No_Telp");
                tblBiodata[x][4] = con.rs.getString("Alamat");
                tblBiodata[x][5] = con.rs.getString("BeratPakaian");
                tblBiodata[x][6] = con.rs.getString("Harga");
                tblBiodata[x][7] = con.rs.getString("TotalBayar");
                tblBiodata[x][8] = con.rs.getString("BayarUang");
                tblBiodata[x][9] = con.rs.getString("Kembalian");
                x++;
            }
            FTABELDATA.setModel(new DefaultTableModel(tblBiodata, label));
        } catch (Exception e) {
        }
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
        FTABELDATA = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Kode_p = new javax.swing.JTextField();
        nama_p = new javax.swing.JTextField();
        no_tlp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        fharga = new javax.swing.JTextField();
        Total_bayar = new javax.swing.JTextField();
        Berat_pakaian1 = new javax.swing.JTextField();
        tanggal = new javax.swing.JTextField();
        Delete = new javax.swing.JButton();
        kembali = new javax.swing.JButton();
        cari = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        bayaruang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        kembalian = new javax.swing.JTextField();
        Update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FTABELDATA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FTABELDATA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        FTABELDATA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FTABELDATAMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(FTABELDATA);

        jLabel1.setText("Tanggal");

        jLabel2.setText("Kode Pelanggan");

        jLabel3.setText("Nama Pelanggan");

        jLabel4.setText("No Telp");

        jLabel5.setText("Alamat");

        jLabel6.setText("Berat Pakaian (kg)");

        jLabel7.setText("Harga /kg");

        jLabel8.setText("Total Bayar");

        alamat.setColumns(20);
        alamat.setRows(5);
        jScrollPane2.setViewportView(alamat);

        fharga.setEditable(false);
        fharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fhargaActionPerformed(evt);
            }
        });

        Total_bayar.setEditable(false);
        Total_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Total_bayarActionPerformed(evt);
            }
        });
        Total_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Total_bayarKeyPressed(evt);
            }
        });

        tanggal.setEditable(false);
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });

        Delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        kembali.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        cari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("CARI");

        bayaruang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayaruangActionPerformed(evt);
            }
        });

        jLabel10.setText("Bayar");

        jLabel11.setText("Kembalian");

        kembalian.setEditable(false);
        kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalianActionPerformed(evt);
            }
        });
        kembalian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kembalianKeyPressed(evt);
            }
        });

        Update.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(no_tlp, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(59, 59, 59)
                                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(kembali)
                                        .addGap(18, 18, 18)
                                        .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(70, 70, 70)
                                            .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(40, 40, 40)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(Berat_pakaian1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(bayaruang, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(nama_p, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(40, 40, 40)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(50, 50, 50)
                                                    .addComponent(Total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(Kode_p, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(40, 40, 40)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(50, 50, 50)
                                                    .addComponent(fharga, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(47, 47, 47)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(Berat_pakaian1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(Kode_p, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(fharga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(nama_p, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(Total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bayaruang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(no_tlp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Delete)
                            .addComponent(kembali)
                            .addComponent(Update))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        hapus();
    }//GEN-LAST:event_DeleteActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        new Res2_Laundry().show();
        this.dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    private void FTABELDATAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FTABELDATAMouseClicked
        setTabel();
    }//GEN-LAST:event_FTABELDATAMouseClicked

    private void fhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fhargaActionPerformed

    }//GEN-LAST:event_fhargaActionPerformed

    private void Total_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Total_bayarKeyPressed

    }//GEN-LAST:event_Total_bayarKeyPressed

    private void Total_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Total_bayarActionPerformed

    }//GEN-LAST:event_Total_bayarActionPerformed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed

    }//GEN-LAST:event_tanggalActionPerformed

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyReleased
        cari();
    }//GEN-LAST:event_cariKeyReleased

    private void bayaruangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayaruangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayaruangActionPerformed

    private void kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianActionPerformed

    private void kembalianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kembalianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianKeyPressed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        update();
    }//GEN-LAST:event_UpdateActionPerformed

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
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Berat_pakaian1;
    private javax.swing.JButton Delete;
    private javax.swing.JTable FTABELDATA;
    private javax.swing.JTextField Kode_p;
    private javax.swing.JTextField Total_bayar;
    private javax.swing.JButton Update;
    private javax.swing.JTextArea alamat;
    private javax.swing.JTextField bayaruang;
    private javax.swing.JTextField cari;
    private javax.swing.JTextField fharga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kembali;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField nama_p;
    private javax.swing.JTextField no_tlp;
    private javax.swing.JTextField tanggal;
    // End of variables declaration//GEN-END:variables
}
