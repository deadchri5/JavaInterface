package javainterface;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javainterface.GuardadoDatos;


public class LoginController implements Initializable {
    
    GuardadoDatos datos = new GuardadoDatos();
    
    @FXML
    private TextField txtServer;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML 
    private javafx.scene.control.Button login;
    @FXML
    private CheckBox cbRecuerdame;
    
    @FXML
    private void loginAction(ActionEvent event) throws Exception {
        String  server, user, password;
        server = txtServer.getText();
        user = txtLogin.getText();
        password = txtPassword.getText();
        Stage loginStage = (Stage) login.getScene().getWindow();
        Connection con = Consultas.getConexion(server, user, password);
        if (con != null){
            if(cbRecuerdame.isSelected()){
                datos.escribir(server, user, password);
            }
            loginStage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.getIcons().add( new Image(
            JavaInterface.class.getResourceAsStream( "/resources/icon.png" ))); 
            stage.setTitle("Aplicacion SQL Server y JAVA");
            stage.show();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null , "Ocurrio un error inersperado al abrir la interfaz" +
                    e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }

        @Override
        public void initialize(URL url, ResourceBundle rb) {
        File file = new File("datosJavaInterface.bin");
        this.txtServer.setText("localhost");
        if (file.exists()){
            String datosLog = datos.leer();
            String [] dataParts = datosLog.split("/");
            this.txtServer.setText(dataParts[0]);
            this.txtLogin.setText(dataParts[1]);
            this.txtPassword.setText(dataParts[2]);
        }
    }    
    
}
