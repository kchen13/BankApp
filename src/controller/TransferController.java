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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Soren Diehl
 * @author Kelby Chen
 */
public class TransferController implements Initializable {

    ObservableList<String> options //Soren Diehl + Kelby Chen
            = FXCollections.observableArrayList(
                    "Checking",
                    "Savings",
                    "Vacation Club"
            );

    @FXML
    private TextField amount;
    @FXML
    private Button execute;
    @FXML
    private Button cancel;
    @FXML
    private ComboBox<?> accountRecipDD;
    @FXML
    private ComboBox<?> accountDD;
    @FXML
    private Label balanceFrom;
    @FXML
    private Label balanceTo;
    @FXML
    private TextArea desc;
    @FXML
    private Label transFeedback;

    model.Model model;

    //Kelby Chen
    //Currency Formatter
    NumberFormat cf = NumberFormat.getCurrencyInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kelby Chen
        getAccountDD().getItems().addAll(options);
        getAccountRecipDD().getItems().addAll(options);
        model = new model.Model();
    }

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Soren Diehl
        //Kelby Chen
        Stage stage = (Stage) getCancel().getScene().getWindow();
        Parent root;
        Scene scene = getCancel().getScene();

        if (e.getSource() == getCancel()) {
            root = FXMLLoader.load(getClass()
                    .getResource("/view/MainMenu.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == accountDD) {
            updateFromBalance();
        }
        if (e.getSource() == accountRecipDD) {
            updateToBalance();
        }
        if (e.getSource() == getExecute()) {
            double amt = Double.parseDouble(amount.getText());
            if (model.checkBalance(accountDD.getValue().toString()) > amt) {
                model.createTransfer(accountDD.getValue().toString(), amt,
                        desc.getText(), accountRecipDD.getValue().toString());
                transFeedback.setText("Transfer Complete");
                transFeedback.setVisible(true);
                updateFromBalance();
                updateToBalance();
            } else {
                transFeedback.setText("Insufficient Funds");
                transFeedback.setVisible(true);
            }
        }
        stage.setScene(scene);
        stage.show();
    }

    public void updateFromBalance() {
        //Kelby Chen
        //Soren Diehl

        //Updates current balance
        double bal = Double.parseDouble(Double.toString(model.checkBalance(accountDD.getValue().toString())));
        balanceFrom.setText(cf.format(bal));
    }

    public void updateToBalance() {
        //Kelby Chen
        //Soren Diehl

        //Updates current balance
        double bal = Double.parseDouble(Double.toString(model.checkBalance(accountRecipDD.getValue().toString())));
        balanceTo.setText(cf.format(bal));
    }

    /**
     * @return the cancel
     */
    public Button getCancel() {
        return cancel;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    /**
     * @return the execute
     */
    public Button getExecute() {
        return execute;
    }

    /**
     * @return the accountDD
     */
    public ComboBox getAccountDD() {
        return accountDD;
    }

    /**
     * @return the accountDD
     */
    public ComboBox getAccountRecipDD() {
        return accountRecipDD;
    }
}
