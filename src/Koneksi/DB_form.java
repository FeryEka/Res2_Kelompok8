/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

/**
 *
 * @author USER
 */
import java.sql.*;

public class DB_form {

    public Connection cc;
    public Statement ss;
    public ResultSet rs;

    public void Class() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cc = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_laundry", "root", "");
            System.out.println("Koneksi OK!!");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
