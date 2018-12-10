package Koneksi;
import java.sql.*;

public class DB_form {
    public Connection cc;
    public Statement ss;
    public ResultSet rs;
    
    public void Class(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cc=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_laundry","root","");
            System.out.println("Koneksi OK!!");
        }catch (Exception e){
            System.out.println(e);
        }
        
    }

    public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
