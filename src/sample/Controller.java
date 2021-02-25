package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Controller {

    @FXML private Label maxPlayersFX;
    @FXML private Label levelNameFX;
    @FXML private Label motdFX;
    @FXML private Label portFX;
    @FXML private Label difficultyFX;
    @FXML private Label titleFX;
    @FXML private Label pvpFX;
    @FXML private Label reloaddingFX;
    @FXML private ImageView serverIconFX;

    @FXML private TableView<Players> TableFX;
    @FXML private TableColumn<Players, String> usernameFX;
    @FXML private TableColumn<Players, String> uuidFX;

    @FXML private TableView<Players> TableOpFX;
    @FXML private TableColumn<Players, String> usernameOpFX;
    @FXML private TableColumn<Players, String> uuidOpFX;

    private PlayerList playerList = new PlayerList();
    private OperatorList operatorList = new OperatorList();
    private Process process;
    private Main main;


    @FXML
    public void initialize() {
        main = new Main();

        LoadData();
    }

    public void LoadData() {
        playerList.UUID.clear();
        playerList.Name.clear();

        operatorList.UUID.clear();
        operatorList.Name.clear();

        playerList.ReadUsercache();
        operatorList.ReadOps();

        sp sp = new sp();
        sp.ReadProp();

        titleFX.setText("Minecraft Server Dashboard v" + main.clientVersion);
        maxPlayersFX.setText(sp.maxPlayers);
        levelNameFX.setText(sp.levelName);
        motdFX.setText(sp.motd);
        portFX.setText(sp.serverPort);
        difficultyFX.setText(sp.difficulty);
        pvpFX.setText(sp.pvp);

        File file = new File(System.getProperty("user.dir")+"\\pack.png");
        Image image = new Image(file.toURI().toString());

        if(!image.isError()) {
            serverIconFX.setImage(image);
        }

        LoadPlayers();
    }

    private void LoadPlayers() {

        // Username
        usernameFX.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameOpFX.setCellValueFactory(new PropertyValueFactory<>("username"));
        // UUID
        uuidFX.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        uuidOpFX.setCellValueFactory(new PropertyValueFactory<>("uuid"));

        TableFX.setItems(getPlayerList());
        TableOpFX.setItems(getPlayerList());
    }

    // add your data here from any source
    public ObservableList<Players> getPlayerList() {

        ObservableList<Players> playersList = FXCollections.observableArrayList();

        for (String uuid : playerList.UUID) {

            String username = playerList.Name.get(playerList.UUID.indexOf(uuid));

            playersList.addAll(new Players(username, uuid));
        }

        return playersList;
    }

    private void FileChosser() {
        try {
            System.out.println("Directory created");
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Any File", "*.jar", "*.bat", "*.exe");
            chooser.getExtensionFilters().add(extFilter);
            File filePath = chooser.showOpenDialog(new Stage());

            // Create one directory
            new File("msd").mkdir();

            String filePathStr = filePath.toString();
            filePathStr = filePathStr.replace("\\", "\\\\");

            BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\msd\\server.json"));

            writer.write("{\"serverPath\":\""+filePathStr+"\"}");

            Desktop.getDesktop().open(filePath);

            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void StarServer() {
        File serverFile = new File(System.getProperty("user.dir")+"\\msd\\server.json");

        try{
            if (!serverFile.exists()) {
                FileChosser();
            } else {

                InputStream is = null;

                try {
                    is = new FileInputStream("C:\\Users\\agjac\\Documents\\GitHub\\Minecraft-Server-Dashboard\\Server-Dashboard\\msd\\server.json");

                    BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                    String line = null;

                    line = buf.readLine();

                    StringBuilder sb = new StringBuilder();

                    while (line != null) {
                        sb.append(line).append("\n");
                        line = buf.readLine();
                    }

                    String fileAsString = sb.toString();

                    JSONObject json = new JSONObject(fileAsString);
                    String serverPath = json.getString("serverPath");

                    File fileServerPath = new File(serverPath);

                    Desktop.getDesktop().open(fileServerPath);

                    System.out.println("Loading from saved path");

                } catch (IOException e) {
                    AlertWindow alertWindow = new AlertWindow();

                    alertWindow.Alert(Alert.AlertType.ERROR, "Unable to find file", "Unable to find file", "Looks like the file you're looking for dose not exist.", true);
                    e.printStackTrace();
                }
            }
        }catch (Exception e){

            try {
                FileChosser();
            } catch (Exception e1) {
                AlertWindow alertWindow = new AlertWindow();
                alertWindow.Alert(Alert.AlertType.ERROR, "Unable to start server", null, "Can't run your server file, please make sure this file is in the same folder as your server file.", true);

                e1.printStackTrace();
            }
        }
    }

    public void StopServer() {
        try {
            System.out.println("Closing server");
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.Alert(Alert.AlertType.ERROR, "SKSKSKKSKSK AND I OOP", "SKSKSKKSKSK AND I OOP", "SKSKSKSKSK this dose not work skskskksksk", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
