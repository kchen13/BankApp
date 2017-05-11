package controller;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soren Diehl
 */
public class LoginController implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private TextField userName;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private Label invalid;

    private boolean valid = false;

    private model.Model model;

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Soren Diehl
        Stage stage = (Stage) register.getScene().getWindow();
        Parent root;
        Scene scene = register.getScene();
        if (e.getSource() == register) {
            root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == login) {
            valid = model.validateUser(userName.getText(), password.getText());

            if (valid) {
                root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
                scene = new Scene(root);
            } else {
                invalid.setText("Invalid Username or Password!");
                invalid.setVisible(true);
            }
        }
        stage.setScene(scene);
        stage.show();
    }
  

       
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model = new model.Model();

    }

}
