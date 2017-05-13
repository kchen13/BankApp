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
 * @author Kelby Chen
 * @author Soren Diehl
 */

public class DepositController implements Initializable {
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
    private Label feedback;
    
    //List for drop down boxes
    ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Checking",
                    "Savings",
                    "Vacation Club"
            );
    model.Model model;
    
    //Format for currency
    NumberFormat cf = NumberFormat.getCurrencyInstance();
    
    /**
     * Initializes the controller class
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populates combo boxes
        getAccountDD().getItems().addAll(options);
        model = new model.Model();
    }
    /**
     * Listeners for screen items
     * @param e
     * @throws IOException 
     */
    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) getCancel().getScene().getWindow();
        Parent root;
        Scene scene = getCancel().getScene();
        
        //Returns to main menu
        if (e.getSource() == getCancel()) {
            root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            scene = new Scene(root);
        }
        
        //Executes deposit
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
        
        //Sets account for deposit
        if (e.getSource() == accountDD) {
            selectAccount();
        }
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Error handling for non numeric entry
     * @param str
     * @return 
     */
    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    /**
     * Sets balance text box for selected account
     */
    public void selectAccount() {
        String account = (String) accountDD.getValue();
        double bal = model.checkBalance(account);
        balance.setText(cf.format(bal));
    }

    /**
     * @return the amount
     */
    public TextField getAmount() {
        return amount;
    }

    /**
     * @return the description
     */
    public TextArea getDesc() {
        return desc;
    }

    /**
     * @return the accountDD
     */
    public ComboBox getAccountDD() {
        return accountDD;
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
