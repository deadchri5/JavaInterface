package javainterface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javainterface.Consultas;

public class LoginController implements Initializable {
    @FXML
    private TextField txtServer;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private void login (ActionEvent event)
    {
        String server, user, password;
        server = txtServer.getText();
        user = txtLogin.getText();
        password = txtPassword.getText();
        Consultas.getConexion(server, user, password);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
