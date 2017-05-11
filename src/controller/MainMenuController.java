package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Soren Diehl
 * @author Kelby Chen
 */
public class MainMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button deposit;
    @FXML
    private Button withdraw;
    @FXML
    private Button transfer;
    @FXML
    private Button reports;
    @FXML
    private Button BI;
    @FXML
    private Button TH;

    @FXML
    private Label dateField;

    @FXML
    private Label hello;

    @FXML
    private Button logout; //logout, returns back to login page

    model.Model model;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kelby Chen
        model = new model.Model();
        hello.setText("Welcome " + model.getUserName() + "!");
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String reportDate = sdf.format(date);
        dateField.setText(reportDate);
        
        
    }

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Soren Diehl
        //Kelby Chen
        Stage stage = (Stage) getWithdraw().getScene().getWindow();
        Parent root;
        Scene scene = getWithdraw().getScene();
        
        if (e.getSource() == getWithdraw()) {
            root = FXMLLoader.load(getClass().getResource("/view/Withdraw.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == getDeposit()) {
            root = FXMLLoader.load(getClass().getResource("/view/Deposit.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == getTransfer()) {
            root = FXMLLoader.load(getClass().getResource("/view/Transfer.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == getReports()) {
            root = FXMLLoader.load(getClass().getResource("/view/Report.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == getTH()) {
            root = FXMLLoader.load(getClass().getResource("/view/TransactionHistory.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == getBI()) {
            root = FXMLLoader.load(getClass().getResource("/view/Balance.fxml"));
            scene = new Scene(root);
        }
        if (e.getSource() == logout()) {
            root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            scene = new Scene(root);
        }

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return the deposit
     */
    public Button getDeposit() {
        return deposit;
    }

    /**
     * @return the withdraw
     */
    public Button getWithdraw() {
        return withdraw;
    }

    /**
     * @return the transfer
     */
    public Button getTransfer() {
        return transfer;
    }

    /**
     * @return the reports
     */
    public Button getReports() {
        return reports;
    }

    /**
     * @return the BI
     */
    public Button getBI() {
        return BI;
    }

    /**
     * @return the TH
     */
    public Button getTH() {
        return TH;
    }

    /**
     * @return the dateField
     */
    public Label getDateField() {
        return dateField;
    }

    /**
     * @return logout
     */
    public Button logout() {
        return logout;
    }

}
