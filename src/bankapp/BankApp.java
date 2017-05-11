package bankapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Soren Diehl
 * @author Phil O'Connell <pxo4@psu.edu>
 */
public class BankApp extends Application {
    //Soren Diehl

    @Override
    public void start(Stage stage) throws Exception {
        //modified by Soren Diehl
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
