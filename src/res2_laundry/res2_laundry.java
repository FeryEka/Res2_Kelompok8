package res2_laundry;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.DB_form;

public final class res2_laundry extends javax.swing.JFrame {

    String KdPelanggan;
    DB_form conn;
    DefaultTableModel table;
    private Node head = null;
    private Connection con;
    public int harga, berat, bayar;
    private DefaultTableModel dtm;
    private Statement st;
    private ResultSet rs;
    private String sql;

    public res2_laundry() {
        initComponents();
        conn = new DB_form();
        tanggal();
        tampil();
        conn.Class();
        Fsimpan.setVisible(true);
        Kode_p.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void LinkedListPush(String[] data1) {
        Node lastNode = getLastNode();
        if (lastNode == null) {
            head = new Node(data1);
        } else {
            boolean cekData = true;
            Node Current = head;
            while (Current != null && cekData) {
                if (Current.data[0].equalsIgnoreCase(data1[0])) {
                    cekData = false;
                }
                Current = Current.next;
            }
            if (cekData) {
                lastNode.next = new Node(data1);
            } else {
                Current.data = data1;
            }
        }
    }

    private Node getLastNode() {
        if (head == null) {
            return null;
        }
        Node tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    public void Delete(String KdPelanggan) {
        if (head == null) {
            return;
        }
        Node prevNode = null;
        Node Current = head;
        while (Current.next != null && Current.data[0] != KdPelanggan) {
            prevNode = Current;
            Current = Current.next;
        }
        if (prevNode == null) {
            head = head.next;
            return;
        }
        if (Current == null) {
            System.out.println("A Node With that value does not exit");
            return;
        }
        prevNode.next = Current.next;
    }

    public void tampil() {
        Object[] field = {"KODE PELANGGAN", "TANGGAL", "NAMA PELANGGAN", "NO TELP", "ALAMAT", "BERAT PAKAIAN", "HARGA", "TOTAL BAYAR"};
        table = new DefaultTableModel(null, field);
        TABLE.setModel(table);
        if (head == null) {
            return;
        }
        Node tempNode = head;
        while (tempNode != null) {
            table.addRow(tempNode.data);

            tempNode = tempNode.next;
        }
    }

    private void insert() {
        String KdPelanggan = Kode_p.getText();
        String Tanggal = tanggal.getText();
        String Nama_Pelanggan = nama_p.getText();
        String No_Telp = no_tlp.getText();
        String Alamat = alamat.getText();
        String BeratPakaian = Berat_pakaian1.getText();
        String Harga = fharga.getText();
        String TotalBayar = Total_bayar.getText();
        String[] row = {KdPelanggan, Tanggal, Nama_Pelanggan, No_Telp, Alamat, BeratPakaian, Harga, TotalBayar};
        LinkedListPush(row);
        tampil();
    }

    private void simpan() {
        try {
            if (head == null) {
                return;
            }
            Node tempNode = head;
            while (tempNode != null) {
                String KdPelanggan = tempNode.data[0];
                String Tanggal = tempNode.data[1];
                String Nama_Pelanggan = tempNode.data[2];
                String No_Telp = tempNode.data[3];
                String Alamat = tempNode.data[4];
                String BeratPakaian = tempNode.data[5];
                String Harga = tempNode.data[6];
                String TotalBayar = tempNode.data[7];
                String sql = ("insert into inputan values (?,?,?,?,?,?,?,?)");
                PreparedStatement p = (com.mysql.jdbc.PreparedStatement) conn.cc.prepareStatement(sql);
                p.setString(1, KdPelanggan);
                p.setString(2, Tanggal);
                p.setString(3, Nama_Pelanggan);
                p.setString(4, No_Telp);
                p.setString(5, Alamat);
                p.setString(6, BeratPakaian);
                p.setString(7, Harga);
                p.setString(8, TotalBayar);
                p.executeUpdate();
                tempNode = tempNode.next;
                tampil();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DI SIMPAN !!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ADA KESALAHAN ! ! !");
        }
    }

    private void tanggal() {
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        tanggal.setText(s.format(ys));
    }

    private void setTabel() {
        int row = TABLE.getSelectedRow();
        Kode_p.setText((String) TABLE.getValueAt(row, 0));
        tanggal.setText((String) TABLE.getValueAt(row, 1));
        nama_p.setText((String) TABLE.getValueAt(row, 2));
        no_tlp.setText((String) TABLE.getValueAt(row, 3));
        alamat.setText((String) TABLE.getValueAt(row, 4));
        Berat_pakaian1.setText((String) TABLE.getValueAt(row, 5));
        fharga.setText((String) TABLE.getValueAt(row, 6));
        Total_bayar.setText((String) TABLE.getValueAt(row, 7));
    }

    private void Reset() {
        Kode_p.setText("");
        nama_p.setText("");
        no_tlp.setText("");
        alamat.setText("");
        Berat_pakaian1.setText("");
        fharga.setText("");
        Total_bayar.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tanggal = new javax.swing.JTextField();
        Kode_p = new javax.swing.JTextField();
        nama_p = new javax.swing.JTextField();
        no_tlp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        fharga = new javax.swing.JTextField();
        Total_bayar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TABLE = new javax.swing.JTable();
        Fsimpan = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        View = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelstatus = new javax.swing.JLabel();
        Finsert = new javax.swing.JButton();
        Berat_pakaian1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 255));
        getContentPane().setLayout(null);

        tanggal.setEditable(false);
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });
        getContentPane().add(tanggal);
        tanggal.setBounds(130, 90, 110, 30);
        getContentPane().add(Kode_p);
        Kode_p.setBounds(130, 50, 110, 30);
        getContentPane().add(nama_p);
        nama_p.setBounds(130, 130, 110, 30);
        getContentPane().add(no_tlp);
        no_tlp.setBounds(130, 170, 110, 30);

        alamat.setColumns(20);
        alamat.setRows(5);
        jScrollPane1.setViewportView(alamat);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(110, 210, 240, 100);

        fharga.setText("6000");
        fharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fhargaActionPerformed(evt);
            }
        });
        getContentPane().add(fharga);
        fharga.setBounds(400, 90, 110, 30);

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
        getContentPane().add(Total_bayar);
        Total_bayar.setBounds(400, 130, 110, 30);

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(530, 50, 73, 23);

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(620, 50, 73, 23);

        TABLE.setModel(new javax.swing.table.DefaultTableModel(
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
        TABLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLEMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TABLE);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 320, 830, 150);

        Fsimpan.setText("Simpan");
        Fsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(Fsimpan);
        Fsimpan.setBounds(360, 290, 73, 23);

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        getContentPane().add(Update);
        Update.setBounds(450, 290, 73, 23);

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        getContentPane().add(Delete);
        Delete.setBounds(540, 290, 80, 23);

        View.setText("View Data");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });
        getContentPane().add(View);
        View.setBounds(630, 290, 120, 23);

        jLabel1.setText("Tanggal");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 100, 50, 14);

        jLabel2.setText("Kode Pelanggan");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 60, 110, 14);

        jLabel3.setText("Nama Pelanggan");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 130, 110, 14);

        jLabel4.setText("No Telp");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 170, 50, 14);

        jLabel5.setText("Alamat");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 210, 50, 14);

        jLabel6.setText("Berat Pakaian (kg)");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(280, 50, 120, 14);

        jLabel7.setText("Harga /kg");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(280, 90, 70, 14);

        jLabel8.setText("Total Bayar");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(280, 130, 70, 14);
        getContentPane().add(jLabelstatus);
        jLabelstatus.setBounds(590, 10, 140, 20);

        Finsert.setText("Insert");
        Finsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinsertActionPerformed(evt);
            }
        });
        getContentPane().add(Finsert);
        Finsert.setBounds(760, 290, 80, 23);

        Berat_pakaian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Berat_pakaian1ActionPerformed(evt);
            }
        });
        getContentPane().add(Berat_pakaian1);
        Berat_pakaian1.setBounds(400, 50, 110, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fhargaActionPerformed

    }//GEN-LAST:event_fhargaActionPerformed

    private void FsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FsimpanActionPerformed
        simpan();

    }//GEN-LAST:event_FsimpanActionPerformed

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed
        Veiw m = new Veiw();
        m.setVisible(true);
        m.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_ViewActionPerformed

    private void TABLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLEMouseClicked

        setTabel();
    }//GEN-LAST:event_TABLEMouseClicked

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed

    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed

        Delete(KdPelanggan);
        tampil();
        Delete.setVisible(true);
    }//GEN-LAST:event_DeleteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Reset();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Total_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Total_bayarKeyPressed
        String harga = fharga.getText();
        int berat = Integer.parseInt(Berat_pakaian1.getText());
        int c = berat * 6000;
        System.out.println(c);
        String d = Integer.toString(c);
        Total_bayar.setText(d);
        Total_bayar.setEnabled(false);
    }//GEN-LAST:event_Total_bayarKeyPressed

    private void Total_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Total_bayarActionPerformed

    }//GEN-LAST:event_Total_bayarActionPerformed

    private void FinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinsertActionPerformed
        insert();
    }//GEN-LAST:event_FinsertActionPerformed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed

    }//GEN-LAST:event_tanggalActionPerformed

    private void Berat_pakaian1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Berat_pakaian1ActionPerformed
        berat = Integer.parseInt(Berat_pakaian1.getText());
        harga = 6000;
        bayar = berat * 6000;
        Total_bayar.setText(String.valueOf(bayar));
        fharga.setText(String.valueOf(harga));
    }//GEN-LAST:event_Berat_pakaian1ActionPerformed

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
            java.util.logging.Logger.getLogger(res2_laundry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(res2_laundry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(res2_laundry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(res2_laundry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new res2_laundry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Berat_pakaian1;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Finsert;
    private javax.swing.JButton Fsimpan;
    private javax.swing.JTextField Kode_p;
    private javax.swing.JTable TABLE;
    private javax.swing.JTextField Total_bayar;
    private javax.swing.JButton Update;
    private javax.swing.JButton View;
    private javax.swing.JTextArea alamat;
    private javax.swing.JTextField fharga;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelstatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nama_p;
    private javax.swing.JTextField no_tlp;
    private javax.swing.JTextField tanggal;
    // End of variables declaration//GEN-END:variables
}
