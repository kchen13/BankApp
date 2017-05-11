package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kelby Chen
 */
public class RegisterController implements Initializable {
    //Kelby Chen

    @FXML
    private TextField firstName;
    @FXML
    private TextField middleInitial;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField passwordConfirm;
    @FXML
    private Button execute;
    @FXML
    private Button cancel;
    @FXML
    private Label exists;

    private model.Model model;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kelby Chen
        model = new model.Model();
    }

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Kelby Chen
        Stage stage = (Stage) getCancel().getScene().getWindow();
        Parent root;
        Scene scene = getCancel().getScene();
        if (e.getSource() == getCancel()) {
            //Cancel button returns to login screen
            root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == getExecute()) {
            if (pwCheck(password.getText(), passwordConfirm.getText())
                    && textfieldCheck()) {
                //If both password fields are equal and textfields are not null
                //register the user 
                registerUser();
            } else {
                if (!pwCheck(password.getText(), passwordConfirm.getText())) {
                    //If password fields are not equal,notify the user
                    exists.setText("Passwords Do Not Match!");
                    exists.setVisible(true);
                }
                if (!textfieldCheck()) {
                    //If any textfields are empty, clear them and change the prompt
                    //text accordingly to notify user
                    findEmptyTextfield();
                    exists.setText("Please complete all fields");
                    exists.setVisible(true);
                }
            }
        }
        stage.setScene(scene);
        stage.show();
    }

    public void registerUser() {
        //Kelby Chen
        //checks to make sure user email is unique
        if (model.uniqueUser(email.getText())) {
            //Executes createUser Method from Model class
            model.createUser(firstName.getText(), lastName.getText(), middleInitial.getText(), email.getText(), passwordConfirm.getText());
            exists.setText("User Registered");
            exists.setVisible(true);
            cancel.setText("Exit");
        } else {
            exists.setText("Email already in use!");
            exists.setVisible(true);
        }

    }

    public boolean pwCheck(String pw, String confirm) {
        //Kelby Chen

        //Returns boolean after comparing both password fields
        return pw.equals(confirm);
    }

    public boolean textfieldCheck() {
        //Kelby Chen
        String first = firstName.getText();
        String last = lastName.getText();
        String middle = middleInitial.getText();
        String emailStr = email.getText();
        String pw = password.getText();
        String pwConfirm = passwordConfirm.getText();
        boolean bool = true;
        //Returns boolean after checking all textfields for null
        bool = stringNullCheck(first);
        bool = stringNullCheck(last);
        bool = stringNullCheck(middle);
        bool = stringNullCheck(emailStr);
        bool = stringNullCheck(pw);
        bool = stringNullCheck(pwConfirm);
        return bool;
    }

    public void findEmptyTextfield() {
        //Kelby Chen
        String first = firstName.getText();
        String last = lastName.getText();
        String middle = middleInitial.getText();
        String emailStr = email.getText();
        String pw = password.getText();
        String pwConfirm = passwordConfirm.getText();
        //Finds empty textfield, clears it and notifies user
        if (!stringNullCheck(first)) {
            firstName.clear();
            firstName.setPromptText("!!! You must enter a first name !!!");
        }

        if (!stringNullCheck(last)) {
            lastName.clear();
            lastName.setPromptText("!!! You must enter a last name !!!");
        }

        if (!stringNullCheck(middle)) {
            middleInitial.clear();
            middleInitial.setPromptText("!!! You must enter a middle initial name !!!");
        }

        if (!stringNullCheck(emailStr)) {
            email.clear();
            email.setPromptText("!!! You must enter a email address !!!");
        }

        if (!stringNullCheck(pw)) {
            password.clear();
            password.setPromptText("!!! You must enter a password !!!");
        }

        if (!stringNullCheck(pwConfirm)) {
            passwordConfirm.clear();
            passwordConfirm.setPromptText("!!! You must confirm your password !!!");
        }
    }

    public boolean stringNullCheck(String str) {
        //Kelby Chen
        //Checks if string is null, returns false if not
        return !(str == null | str.length() == 0);
    }

    /**
     * @return the execute
     */
    public Button getExecute() {
        return execute;
    }

    /**
     * @return the cancel
     */
    public Button getCancel() {
        return cancel;
    }
}
