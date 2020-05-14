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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
    
    ObservableList list = FXCollections.observableArrayList();
    
    @FXML
    private Label label;
    @FXML
    private ListView <String> listaCheques; 
    
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
        list.removeAll(list);
        listaCheques.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ResultSet folios;
        try{
        folios = Consultas.Consulta("SELECT folio FROM cheques");
        while (folios.next()){
            int folio = folios.getInt(1);
            list.add("Folio #" + folio);
            listaCheques.getItems().addAll(list);
        }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null , "Ocurrio un error al obtener folios" +
                    e.getMessage(),"Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
