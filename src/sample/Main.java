package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Optional;

public class Main extends Application {

    public String clientVersion = "0.0.3";

    private int height = 900;
    private int width = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Minecraft Server Dashbord v" + clientVersion);
        primaryStage.setScene(new Scene(root, height, width));
        primaryStage.show();

        Updater updater = new Updater();
        updater.CallUpdater();

        if (!clientVersion.equals(updater.serverVersion)) {
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.Alert(Alert.AlertType.CONFIRMATION, "NEW update ready", "You can now update to version " + updater.serverVersion, "If you would like to update Minecraft Server Dashboad to version " + updater.serverVersion + " you can do so by pressing ok on this prompt.\nPatch Notes: \n"+updater.patchNotesStr, false);
            Optional<ButtonType> result = alertWindow.getAlert().showAndWait();

            if(result.get() == ButtonType.OK) {

                // URL has to be Https
                URL packageURl = new URL(updater.updateLocation);
                File outputDir = new File(System.getProperty("user.dir"));

                Unzipper unzipper = new Unzipper();
                unzipper.unpackArchive(packageURl, outputDir);

            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
