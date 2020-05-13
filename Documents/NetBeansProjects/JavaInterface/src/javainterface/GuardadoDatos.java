package javainterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GuardadoDatos {
    
    public GuardadoDatos(){
    }
    
    public void escribir(String server, String login, String password){
        File file = new File("datosJavaInterface.bin");
        FileOutputStream fileOS;
        try {
            fileOS = new FileOutputStream(file);
            ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
            Datos datos = new Datos();
            datos.setServer(server);
            datos.setLogin(login);
            datos.setPassword(password);
            objectOS.writeObject(datos);
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
        catch (IOException ex){
            System.out.println("Error: " + ex);
        }
    }
    
    public String leer(){
        String datosUsuario = "";
        File file = new File("datosJavaInterface.bin");
        try {
            FileInputStream fileOS = new FileInputStream(file);
            ObjectInputStream objectOS = new ObjectInputStream(fileOS);
            Object data = objectOS.readObject();
            Datos datos = (Datos) data;
            datosUsuario = datos.getServer()+"/"+datos.getLogin()+"/"+datos.getPassword();
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("Error: " + ex);
        }
        return datosUsuario;
    }
    
}
