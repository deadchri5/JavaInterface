package javainterface;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private Label label;
    @FXML
    private DatePicker fechaInicio, fechaFin;
    @FXML
    private ListView<Integer> listaCheques;

    @FXML
    private void aceptar(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        listaCheques.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void obtenerCheques() {
        list.removeAll(list);
        listaCheques.getItems().clear();
        if (fechaInicio.getValue() == null || fechaFin.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Ambos campos de fecha deben de estar llenos");
        } else {
            String query = "SELECT folio FROM cheques where fecha >= '"
                    + fechaInicio.getValue() + " 00:00:00' AND cierre <= '" + fechaFin.getValue() + " 23:59:00'"+
                    "AND tarjeta<1";
            ResultSet folios;
            try {
                folios = Consultas.Consulta(query);
                while (folios.next()) {
                    int folio = folios.getInt(1);
                    list.add(folio);
                }
                    listaCheques.getItems().addAll(list);
                if (list.size() == 0){
                    JOptionPane.showMessageDialog(null, "No se encontraron folios entre las fechas " + fechaInicio.getValue() + 
                            " y " + fechaFin.getValue());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener folios"
                        + e.getMessage(), "Error de consulta", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public class Folios{
        private String folio;
        
        public String getFolio(){
            return folio;
        }
        
        public void setFolio(String f){
            folio = f;
        }
   }
    
    Folios fn = new Folios();
    
    public void borrarFolio(Folios fn){
        
    }

}
