
package ge.edu.tsu.view;

import ge.edu.tsu.model.table.Table;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Runner extends Application {
    
    public static boolean onePlayer = true;
    public static boolean firstPlayerTurn = true;
    public static Table currTable;
    
    private static Stage stage = null;
    
    public static void setScene(Scene scene) {
        stage.setScene(scene);
    }
    
    public static Stage getStage() {
        return stage;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("X&0");
        primaryStage.setResizable(false);
        primaryStage.show();
        stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
