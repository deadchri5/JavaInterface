package javainterface;

import java.awt.event.MouseEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    ObservableList list1 = FXCollections.observableArrayList();
    Consultas con = new Consultas();

    @FXML
    private Label label;
    @FXML
    private TextField totalSelect;
    @FXML
    private TextField sub;
    @FXML
    private DatePicker fechaInicio, fechaFin;
    @FXML
    private ListView<String> listaCheques;

 
    @FXML
    private void aceptar(ActionEvent event) {
        ObservableList<String> listaSeleccion = listaCheques.getSelectionModel().getSelectedItems();
        for (int i = 0; i < listaSeleccion.size(); i++) {
                String query = listaSeleccion.get(i);
                String [] parts = query.split("\\$");
                String eliminar = ("DELETE FROM cheques WHERE numcheque ="+parts[0]);
                Consultas.DeleteFolio(eliminar);
        }
        obtenerCheques();
        obtenerTotalCheques();
        totalSelect.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        listaCheques.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    }

    public void obtenerCheques() {
        list.removeAll(list);
        list1.removeAll(list1);
        sub.clear();
        totalSelect.clear();
        listaCheques.getItems().clear();
        if (fechaInicio.getValue() == null || fechaFin.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Ambos campos de fecha deben de estar llenos");
        } else {
            String query = "SELECT numcheque, total  FROM cheques where fecha >= '"
                    + fechaInicio.getValue() + "T00:00:00' AND cierre <= '" + fechaFin.getValue() + "T23:59:00'"
                    + "AND tarjeta<1";
            System.out.println(query);
            ResultSet folios;
            try {
                folios = Consultas.Consulta(query);
                while (folios.next()) {
                    int folio = folios.getInt(1);
                    float total = folios.getFloat(2);
                    list.add(folio+"                                                        $"+total );
                }
                listaCheques.getItems().addAll(list);
                
                if (list.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No se encontraron folios entre las fechas " + fechaInicio.getValue()
                            + " y " + fechaFin.getValue());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener folios"
                        + e.getMessage(), "Error de consulta", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
        public void obtenerTotalCheques() {
        list.removeAll(list);
        String tCheques;
        if (fechaInicio.getValue() == null || fechaFin.getValue() == null) {
        } else {
              tCheques = "SELECT total FROM cheques where fecha >= '"
                    + fechaInicio.getValue() + " 00:00:00' AND cierre <= '" + fechaFin.getValue() + " 23:59:00'"
                    + "AND tarjeta<1";
            ResultSet total;
            try {
                total = Consultas.Consulta(tCheques);
                while (total.next()) {
                    float totalCh = total.getFloat(1);
                    list.add(totalCh);
                    ObservableList<Float> listaTotal = list;
                  float totalSum=0;  
                  for (int e = 0; e < listaTotal.size(); e++) {
                  totalSum +=listaTotal.get(e);
                  sub.setText(""+totalSum);
                  }
                }
               
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener folios"
                        + e.getMessage(), "Error de consulta", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
        
    public void SelectTotal (){
        list.removeAll(list);
        String subtotal;
          ObservableList<String> listaSeleccion = listaCheques.getSelectionModel().getSelectedItems();    
             for (int i = 0; i < listaSeleccion.size(); i++) {
                 String query = listaSeleccion.get(i);
                 String [] parts = query.split("\\$");
                subtotal = ("SELECT total FROM cheques WHERE numcheque ="+parts[0]);
                ResultSet total;
                try {
                total = Consultas.Consulta(subtotal);
                while (total.next()) {
                    float totalSel = total.getFloat(1);
                    list.add(totalSel);
                  ObservableList<Float> listaSum = list;
                  float totalSum=0;  
                  for (int a = 0; a < listaSum.size(); a++) {
                  totalSum +=listaSum.get(a);
                  totalSelect.setText(""+totalSum);
                   }
                }
                }
                catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener folios"
                        + e.getMessage(), "Error de consulta", JOptionPane.ERROR_MESSAGE);
                 }       
             }
    }
           
              
    }
    
    

