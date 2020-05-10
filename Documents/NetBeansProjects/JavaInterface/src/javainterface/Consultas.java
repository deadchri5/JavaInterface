package javainterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class Consultas{
    
    static Connection contacto = null;
    
    public static  Connection getConexion(){
        String url = "jdbc:sqlserver://DESKTOP-0J6KP8T\\NATIONALSOFT:1433;databaseName=softrestaurant95pro";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null , "no se establecion conexion" +
                    e.getMessage(),"error de conexion", JOptionPane.ERROR_MESSAGE);
        }
        
        try{
            contacto = DriverManager.getConnection(url, "sa", "National09"); 
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null , "Error" +
                    e.getMessage(),"error de conexion", JOptionPane.ERROR_MESSAGE);
        }
        
        return contacto;
        
    }
    
    
    public static void SqlConsulta() throws ClassNotFoundException, SQLException{
           
    }

    
}
