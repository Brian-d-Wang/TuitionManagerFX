package Project3;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main used for launching the GUI
 * @author Brian Wang, Kyle Sia
 */
public class Main extends Application {

    /**
     * Starts the JavaFX for tuition
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
            primaryStage.setTitle("Tuition Manager");
            primaryStage.setScene(new Scene(root, 650, 550));
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calls launch method for the GUI
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
