package ge.edu.tsu.controller;

import ge.edu.tsu.view.Runner;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainFXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void actionOnOnePlayerButton() {
        Runner.onePlayer = true;
        Random r = new Random();
        Runner.firstPlayerTurn = r.nextBoolean();
        loadPlayScene();
    }

    public void actionOnTwoPlayerButton() {
        Runner.onePlayer = false;
        loadPlayScene();
    }

    public void loadPlayScene() {
        Runner r = new Runner();
        Parent root;
        try {
            root = FXMLLoader.load(r.getClass().getResource("PlayFXML.fxml"));
            Scene scene = new Scene(root);

            Runner.setScene(scene);
            Runner.getStage().setResizable(true);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
