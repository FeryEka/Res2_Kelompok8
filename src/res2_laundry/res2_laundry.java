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
import Koneksi.DB_form;

public final class Res2_Laundry extends javax.swing.JFrame {

    String KdPelanggan;
    DB_form conn;
    DefaultTableModel table;
    private Node head = null;
    private Connection con;
    public double harga, Bayar, Tbayar, bayar, bkembalian;
    private DefaultTableModel dtm;
    private Statement st;
    private ResultSet rs;
    private String sql;

    public Res2_Laundry() {
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
        Object[] field = {"KODE PELANGGAN", "TANGGAL", "NAMA PELANGGAN", "NO TELP", "ALAMAT", "BERAT PAKAIAN", "HARGA", "TOTAL BAYAR", "BAYAR","KEMBALIAN"};
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
        String BayarUang = bayaruang.getText();
        String Kembalian = kembalian.getText();
        String[] row = {KdPelanggan, Tanggal, Nama_Pelanggan, No_Telp, Alamat, BeratPakaian, Harga, TotalBayar, Kembalian, BayarUang};
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
                String BayarUang = tempNode.data[8];
                String Kembalian = tempNode.data[9];
                String sql = ("insert into inputan values (?,?,?,?,?,?,?,?,?,?)");
                PreparedStatement p = (com.mysql.jdbc.PreparedStatement) conn.cc.prepareStatement(sql);
                p.setString(1, KdPelanggan);
                p.setString(2, Tanggal);
                p.setString(3, Nama_Pelanggan);
                p.setString(4, No_Telp);
                p.setString(5, Alamat);
                p.setString(6, BeratPakaian);
                p.setString(7, Harga);
                p.setString(8, TotalBayar);
                p.setString(9, BayarUang);
                p.setString(10, Kembalian);
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
        bayaruang.setText((String) TABLE.getValueAt(row, 8));
        kembalian.setText((String) TABLE.getValueAt(row, 9));
    }

    private void Reset() {
        Kode_p.setText("");
        nama_p.setText("");
        no_tlp.setText("");
        alamat.setText("");
        Berat_pakaian1.setText("");
        Total_bayar.setText("");
        bayaruang.setText("");
        kembalian.setText("");
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
        kembalian = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bayaruang = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 255));

        tanggal.setEditable(false);
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });

        alamat.setColumns(20);
        alamat.setRows(5);
        jScrollPane1.setViewportView(alamat);

        fharga.setEditable(false);
        fharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fhargaActionPerformed(evt);
            }
        });
        fharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fhargaKeyPressed(evt);
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

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        Fsimpan.setText("Simpan");
        Fsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FsimpanActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        View.setText("View Data");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });

        jLabel1.setText("Tanggal");

        jLabel2.setText("Kode Pelanggan");

        jLabel3.setText("Nama Pelanggan");

        jLabel4.setText("No Telp");

        jLabel5.setText("Alamat");

        jLabel6.setText("Berat Pakaian (kg)");

        jLabel7.setText("Harga /kg");

        jLabel8.setText("Total Bayar");

        Finsert.setText("Insert");
        Finsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinsertActionPerformed(evt);
            }
        });

        Berat_pakaian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Berat_pakaian1ActionPerformed(evt);
            }
        });

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

        jLabel9.setText("Kembalian");

        jLabel10.setText("Bayar");

        bayaruang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayaruangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Finsert, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(View, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Fsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(590, 590, 590)
                        .addComponent(jLabelstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(fharga, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(Kode_p, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Berat_pakaian1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(nama_p, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(no_tlp, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bayaruang, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(Kode_p, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(Berat_pakaian1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1))
                    .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(fharga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(nama_p, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(no_tlp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bayaruang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Finsert)
                                    .addComponent(Delete)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(View)
                                        .addComponent(Fsimpan)))))
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fhargaActionPerformed
        
    }//GEN-LAST:event_fhargaActionPerformed

    private void FsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FsimpanActionPerformed
        simpan();
    }//GEN-LAST:event_FsimpanActionPerformed

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed
        View m = new View();
        m.setVisible(true);
        m.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_ViewActionPerformed

    private void TABLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLEMouseClicked
        setTabel();
    }//GEN-LAST:event_TABLEMouseClicked

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
        Double berat = Double.parseDouble(Berat_pakaian1.getText());
        Double a = berat * 6000;
        System.out.println(a);
        String b = Double.toString(a);
        Total_bayar.setText(b);
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
        Double berat = Double.parseDouble(Berat_pakaian1.getText());
        harga = 6000;
        Double Tbayar = berat * harga;
        Total_bayar.setText(String.valueOf(Tbayar));
        fharga.setText(String.valueOf(harga));
    }//GEN-LAST:event_Berat_pakaian1ActionPerformed

    private void kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianActionPerformed

    private void kembalianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kembalianKeyPressed
        // TODO add your handling code here:
        Double berat = Double.parseDouble(Berat_pakaian1.getText());
        Double bayar = Double.parseDouble(bayaruang.getText());
        harga = 6000;
        Tbayar = berat * harga;
        Double z = bayar - Tbayar;
        System.out.println(z);
        String d = Double.toString(z);
        kembalian.setText(d);
        kembalian.setEnabled(false);
    }//GEN-LAST:event_kembalianKeyPressed

    private void fhargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fhargaKeyPressed
        // TODO add your handling code here:
        int harga = 6000;
        System.out.println(harga);
        String c = Integer.toString(harga);
        fharga.setText(c);
        fharga.setEnabled(false);
    }//GEN-LAST:event_fhargaKeyPressed

    private void bayaruangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayaruangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayaruangActionPerformed

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
            java.util.logging.Logger.getLogger(Res2_Laundry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Res2_Laundry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Res2_Laundry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Res2_Laundry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Res2_Laundry().setVisible(true);
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
    private javax.swing.JButton View;
    private javax.swing.JTextArea alamat;
    private javax.swing.JTextField bayaruang;
    private javax.swing.JTextField fharga;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelstatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField nama_p;
    private javax.swing.JTextField no_tlp;
    private javax.swing.JTextField tanggal;
    // End of variables declaration//GEN-END:variables
}
