package javainterface;

import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javainterface.Consultas;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
    
    ArrayList<Integer> ListaFolios = new ArrayList<>();
    
    @FXML
    private Label label;
    
    @FXML
    private void aceptar(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        obtenerCheques();
    }    
    
    public void obtenerCheques(){
    ResultSet folios;
        try{
        folios = Consultas.Consulta("SELECT folio FROM cheques");
        while (folios.next()){
            int folio = folios.getInt(1);
            ListaFolios.add(folio);
        }
            System.out.println(ListaFolios);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null , "Ocurrio un error al obtener folios" +
                    e.getMessage(),"Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
