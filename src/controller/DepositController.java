package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soren Diehl
 * @author Kelby Chen
 */
/**
 * Initializes the controller class.
 */
public class DepositController implements Initializable {

    //Soren Diehl
    //Kelby Chen
    ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Checking",
                    "Savings",
                    "Vacation Club"
            );
    @FXML
    private Label feedback;
    
    @FXML
    private TextField amount; //user entered withdrawl amount
    @FXML
    private Label balance; //uneditable display of current account balance
    @FXML
    private TextArea desc;//user entered option description
    @FXML
    private ComboBox accountDD = new ComboBox(); //Drop down to select account type
    @FXML
    private Button execute; //process withdraw request
    @FXML
    private Button cancel; //return to main menu
    @FXML
    private Button logout; //logout, returns back to login page

    model.Model model;

    /**
     * @param url
     * @param rb
     */
    NumberFormat cf = NumberFormat.getCurrencyInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Soren Diehl
        //Kelby Chen

        //Populates combo boxes
        getAccountDD().getItems().addAll(options);
        model = new model.Model();
    }

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Soren Diehl
        Stage stage = (Stage) getCancel().getScene().getWindow();
        Parent root;
        Scene scene = getCancel().getScene();
        if (e.getSource() == getCancel()) {
            root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == getExecute()) {
            if(accountDD.getValue() != null){
            if (isNumeric(amount.getText())) {
                model.createDeposit(accountDD.getValue().toString(),
                        Double.parseDouble(amount.getText()),
                        desc.getText());
                feedback.setText("Deposit Complete");
                feedback.setVisible(true);
            }
            else{
                feedback.setText("Amount must be a number");
                feedback.setVisible(true);
            }
        }else{feedback.setText("Please choose an account");
                feedback.setVisible(true);}
        }
        if (e.getSource() == accountDD) {
            selectAccount();
        }
        stage.setScene(scene);
        stage.show();
    }

    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void selectAccount() {
        //Kelby Chen
        String account = (String) accountDD.getValue();
        double bal = model.checkBalance(account);
        balance.setText(cf.format(bal));
    }

    /**
     * @return the amount
     */
    public TextField getAmount() {
        //Soren Diehl
        return amount;
    }

    /**
     * @return the description
     */
    public TextArea getDesc() {
        //Soren Diehl
        return desc;
    }

    /**
     * @return the accountDD
     */
    public ComboBox getAccountDD() {
        //Soren Diehl
        return accountDD;
    }

    /**
     * @return the execute
     */
    public Button getExecute() {
        //Soren Diehl
        return execute;
    }

    /**
     * @return the cancel
     */
    public Button getCancel() {
        //Soren Diehl
        return cancel;
    }

}
