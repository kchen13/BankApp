package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soren Diehl
 */
public class TransactionHistoryController_1 implements Initializable {

     ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Amount",
                    "Expense Category",
                    "Description Contents"
            );
    
     
    @FXML
    private Button mainMenu;
    @FXML
    private ComboBox searchDD;
    @FXML
    private TableView transList;
    @FXML
    private TextField param;
    @FXML
    private Button search;
    @FXML
    private Label feedback;
    
    //Soren Diehl
    model.Model mo = new model.Model();
    ArrayList<model.Transaction> searchResult;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        searchDD.setItems(options);
    }

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Soren Diehl
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        Parent root;
        Scene scene = mainMenu.getScene();
        //if user selects main menu button, returns screen to main menu
        if (e.getSource() == mainMenu) {
            root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            scene = new Scene(root);
        }
        if(e.getSource() == search){
            if(searchDD.getValue() != null && param.getText().toString() != ""){
                if(searchDD.getValue().equals("Amount") && !isNumeric(param.getText())){
                  feedback.setText("Amount must be numeric");
                feedback.setVisible(true);  
                }
                else if (mo.findTransactions(searchDD.getValue().toString(),
                    param.getText()).size() == 0){
                feedback.setText("No Transactions Match Your Search criteria");
                feedback.setVisible(true);
            }
            else{
                transList.getColumns().clear();
                transList.setEditable(false);
                
                //Building the columns and setting their values
                TableColumn ATcol = new TableColumn("Account Type");
                ATcol.setMinWidth(200);
                ATcol.setCellValueFactory(
                new PropertyValueFactory<model.Transaction, String>("accName"));
          
                TableColumn TTcol = new TableColumn("Transaction Type");
                TTcol.setMinWidth(200);
                TTcol.setCellValueFactory(
                new PropertyValueFactory<model.Transaction,String>("type"));
                        
                TableColumn amtcol = new TableColumn("Amount");
                amtcol.setMinWidth(30);
                amtcol.setCellValueFactory(
                new PropertyValueFactory<model.Transaction,Number>("amt"));
                
                TableColumn datecol = new TableColumn("Date");
                datecol.setMinWidth(50);
                datecol.setCellValueFactory(
                new PropertyValueFactory<model.Transaction,Date>("transDate"));
                
                TableColumn catcol = new TableColumn("Expense Category");
                catcol.setMinWidth(100);
                catcol.setCellValueFactory(
                new PropertyValueFactory<model.Transaction,String>("category"));
                
                TableColumn desccol = new TableColumn("Description");
                desccol.setMinWidth(100);
                desccol.setCellValueFactory(
                new PropertyValueFactory<model.Transaction,String>("desc"));

               searchResult = mo.findTransactions(searchDD.getValue().
                        toString(), param.getText());
               ObservableList oSearchResult = 
                       FXCollections.observableArrayList(searchResult);
                
                transList.setItems(oSearchResult);
                transList.refresh();
                transList.getColumns().addAll(ATcol,TTcol,amtcol,datecol,catcol,desccol);
             

                feedback.setText("Search Complete");
                feedback.setVisible(true);
            }
        }else{
                feedback.setText("Please choose a search field and enter a parameter");
                feedback.setVisible(true);
            }

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
}
