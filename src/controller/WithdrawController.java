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
 * @author Dustin DiMarcello
 */
public class WithdrawController implements Initializable {

    ObservableList<String> options //Soren Diehl + Kelby Chen
            = FXCollections.observableArrayList(
                    "Checking",
                    "Savings",
                    "Vacation Club"
            );
    ObservableList<String> expenses //Soren Diehl + Kelby Chen
            = FXCollections.observableArrayList(
                    "Food",
                    "Fuel",
                    "Medical",
                    "Rent",
                    "School",
                    "Misc"
            );

    @FXML
    private TextField amount; 
    @FXML
    private Label balance; 
    @FXML
    private Button execute; 
    @FXML
    private Button cancel; 
    @FXML
    private ComboBox accountDD; 
    @FXML
    private TextArea desc; 
    @FXML
    private ComboBox expenseDD;
    @FXML
    private Label errorLabel;
    
    String expense;
    String descript;
    model.Model model;
    NumberFormat cf = NumberFormat.getCurrencyInstance();

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Soren Diehl
        //Kelby Chen
        //Dustin DiMarcello
        Stage stage = (Stage) getCancel().getScene().getWindow();
        Parent root;
        Scene scene = getCancel().getScene();
        if (e.getSource() == accountDD) {
            updateBalance();
        }
        if (e.getSource() == getExecute()) {
            
            if (accountDD.getValue() != null && amount.getText() != null && 
                    isNumeric(amount.getText())) {
                if(Double.parseDouble(amount.getText()) <
                        model.checkBalance(accountDD.getValue().toString())){
                if(expenseDD.getValue() == null){
                    expense = "NA";}
                else expense = expenseDD.getValue().toString();
                if(desc.getText() == null){
                    descript = "";
                }
                else {descript = desc.getParagraphs().toString(); 
                model.createWithdrawal(accountDD.getValue().toString(), 
                Double.parseDouble(amount.getCharacters().toString()), expense,
                descript);}
                errorLabel.setText("Withdraw Sucess");
                errorLabel.setVisible(true);
                updateBalance();
                }else{
                errorLabel.setText("Insufficient Funds");
                errorLabel.setVisible(true);
                }
            }
            else {
            errorLabel.setText("Invalid Input");
            errorLabel.setVisible(true);
            }
        }
        if (e.getSource() == getCancel()) {
            root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            scene = new Scene(root);
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kelby Chen
        //Dustin DiMarcello
        getAccountDD().getItems().addAll(options);
        getExpenseDD().getItems().addAll(expenses);
        model = new model.Model();
    }
    public void updateBalance() {
        //Kelby Chen
        //Soren Diehl
        //Dustin DiMarcello

        //Updates current balance
        double bal = Double.parseDouble(Double.toString(model.checkBalance(accountDD.getValue().toString())));
        balance.setText(cf.format(bal));
    }
    
    /**
     * @return the amount
     */
    public TextField getAmount() {
        return amount;
    }

/*
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

    /**
     * @return the accountDD
     */
    public ComboBox getAccountDD() {
        return accountDD;
    }

    /**
     * @return the desc
     */
    public TextArea getDesc() {
        return desc;
    }

    /**
     * @return the expenseDD
     */
    public ComboBox getExpenseDD() {
        return expenseDD;
    }

}
