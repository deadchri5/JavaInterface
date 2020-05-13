package javainterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class Consultas{
    
    static Connection contacto = null;
    
    public static  Connection getConexion(String server, String user, String password) {
        String url = "jdbc:sqlserver://"+server+":1433;databaseName=softrestaurant95pro";
        System.out.println(url);
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null , "no se establecion conexion" +
                    e.getMessage(),"error de conexion", JOptionPane.ERROR_MESSAGE);
        }

        try{
            contacto = DriverManager.getConnection(url, user, password); 
            System.out.println("Conectado correctamente a la base de datos");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null , "Error" +
                    e.getMessage(),"error de conexion", JOptionPane.ERROR_MESSAGE);
        }

        return contacto;
        
    }
    
    
    public static ResultSet Consulta(String consulta){
       Connection cn;
       PreparedStatement pst;
       ResultSet rs = null;
       try{
           cn = contacto;
           pst = cn.prepareStatement(consulta);
           rs = pst.executeQuery();
           
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null , "Error" +
                    e.getMessage(),"error de conexion", JOptionPane.ERROR_MESSAGE);
       }
        return rs;    
    }

    
}
