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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * @author Kelby Chen
 * @author Soren Diehl
 */

public class BalanceController implements Initializable {
    @FXML
    private TextField balance;
    @FXML
    private Button mainMenu;
    @FXML
    private ComboBox<?> accountType;

    //List for drop down boxes
    ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Checking",
                    "Savings",
                    "Vacation Club"
            );

    //Format for currency
    NumberFormat cf = NumberFormat.getCurrencyInstance();

    //Model instance
    model.Model model;

    /**
     * Initializes the controller class
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populates combo boxes
        getAccountType().getItems().addAll(options);
        model = new model.Model();
    }

    /**
     * Listeners for combo box and returning to main menu
     * @param e
     * @throws IOException
     */
    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        Parent root;
        Scene scene = mainMenu.getScene();
        
        //Returns to main menu
        if (e.getSource() == mainMenu) {
            root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            scene = new Scene(root);
        }
        
        //Updates balance respective to combobox selection
        if (e.getSource() == accountType) {
            updateBalance();
        }
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets balance text box according to accountType
     */
    public void updateBalance() {
        String account = (String) accountType.getValue();
        double bal = model.checkBalance(account);
        balance.setText(cf.format(bal));
    }

    /**
     * Returns type of account selected
     * @return accountType
     */
    public ComboBox getAccountType() {
        return accountType;
    }
}
