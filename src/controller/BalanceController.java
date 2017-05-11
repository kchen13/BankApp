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
 *
 * @author Soren Diehl
 * @author Kelby Chen
 */
public class BalanceController implements Initializable {

    //Soren Diehl
    //Kelby Chen
    ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Checking",
                    "Savings",
                    "Vacation Club"
            );
    @FXML
    private TextField balance;
    @FXML
    private Button mainMenu;
    @FXML
    private ComboBox<?> accountType;
    
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
        getAccountType().getItems().addAll(options);
        model = new model.Model();
    }

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Soren Diehl
        //Kelby Chen
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        Parent root;
        Scene scene = mainMenu.getScene();
        if (e.getSource() == mainMenu) {
            root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == accountType) {
            updateBalance();
        }
        stage.setScene(scene);
        stage.show();
    }

    public void updateBalance() {
        //Kelby Chen

        //Updates the balance depending on selection in combo box
        String account = (String) accountType.getValue();
        double bal = model.checkBalance(account);
        balance.setText(cf.format(bal));
    }

    /**
     * @return the accountDD
     */
    public ComboBox getAccountType() {
        //Soren Diehl
        return accountType;
    }
}
