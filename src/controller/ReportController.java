package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author Shawn
 */
//initializes the ReportController class
public class ReportController implements Initializable {

    ObservableList<String> reportTypes
            = FXCollections.observableArrayList(
                    "Checking",
                    "Savings",
                    "Vacation Club"
            );
    

    
    @FXML
    private ComboBox reportMenu = new ComboBox();
   
    @FXML
    private Button mmButton;
    
    @FXML
    private Button genButton;
    
    model.Model model;
    
    @FXML
    CategoryAxis hAxis = new CategoryAxis(); 
    
    @FXML
    NumberAxis vAxis = new NumberAxis();
    
    @FXML
    private BarChart<String, Number> reportBC = new BarChart<>(hAxis,vAxis);
    
    
            

    //action listeners for FXML controls
    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        //Shawn Conway
        //Soren Diehl
        Stage stage = (Stage) getMmButton().getScene().getWindow();
        Parent root;
        Scene scene = getMmButton().getScene();
        //if user selects main menu button, returns screen to main menu
        if (e.getSource() == getMmButton()) {
            root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            scene = new Scene(root);
        }
        
        if (e.getSource() == getGenButton()){
            
            reportBC.getData().clear();
            hAxis.setLabel("Category");
            vAxis.setLabel("Amount");
            if(reportMenu.getValue() != null){
            
            
            reportBC.setTitle(reportMenu.getValue().toString() + " Expense Report:");
            
            
            model.countExpenses(reportMenu.getValue().toString());
            XYChart.Series dataSeries1 = new XYChart.Series();
            
            dataSeries1.setName(reportMenu.getValue().toString());
            
            dataSeries1.getData().add(new XYChart.Data<String,Number>("Food",model.getFoodExp()));
            dataSeries1.getData().add(new XYChart.Data<String,Number>("Fuel",model.getFuelExp()));
            dataSeries1.getData().add(new XYChart.Data<String,Number>("Medical",model.getMedExp()));
            dataSeries1.getData().add(new XYChart.Data<String,Number>("Rent",model.getRentExp()));
            dataSeries1.getData().add(new XYChart.Data<String,Number>("School",model.getSchoolExp()));
            dataSeries1.getData().add(new XYChart.Data<String,Number>("Misc",model.getMiscExp()));
            
            
            reportBC.setAnimated(false);
            reportBC.getData().add(dataSeries1);

            reportBC.setVisible(true);
            
            
            
            
            
         
            }
        }
            
            stage.setScene(scene);
            stage.show();
        }
        
        

        
    

    //initializes the Report screen
    @Override
    public void initialize(URL location, ResourceBundle resources) {

          model = new model.Model();
          reportMenu.getItems().addAll(reportTypes);
         
          
    }

    //returns ReportTypes
    public ObservableList<String> getReportTypes() {
        return reportTypes;
    }

    //returns ReportMenu
    public ComboBox getReportMenu() {
        return reportMenu;
    }

    //returns MmButton
    public Button getMmButton() {
        return mmButton;
    }

    public Button getGenButton() {
        return genButton;
    }
    
    

    public void genBarChart() {
        //will generate bar chart using model.Persistence.readTransactions()
    }

    public void genPieChart() {
        //will generate pie chart using model.Persistence.readTransactions()
    }

    public void genGraph() {
        //will generate graph using model.Persistence.readTransactions()
    }

}
