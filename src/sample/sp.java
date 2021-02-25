package sample;
// sp is short for Server Properties

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

// made a class to easily get data from other files
public class sp {
    private static ArrayList<String> lines = new ArrayList<String>();

    private static ArrayList<String> formattedValue = new ArrayList<String>();
    private static ArrayList<String> formattedVar = new ArrayList<String>();

    @FXML public String broadcastRconToOps;
    @FXML public String viewDistance;
    @FXML public String maxBuildHeight;
    @FXML public String serverIP;
    @FXML public String levelSeed;
    @FXML public String rconPort;
    @FXML public String gamemode;
    @FXML public String serverPort;
    @FXML public String allowNether;
    @FXML public String enableCommandBlock;
    @FXML public String enableRcon;
    @FXML public String enableQuery;
    @FXML public String opPermissionLevel;
    @FXML public String preventProxyConnections;
    @FXML public String generatorSettings;
    @FXML public String resourcePack;
    @FXML public String levelName;
    @FXML public String rconPassword;
    @FXML public String playerIdleTimeout;
    @FXML public String motd;
    @FXML public String queryPort;
    @FXML public String forceGamemode;
    @FXML public String hardcore;
    @FXML public String whiteList;
    @FXML public String broadcastConsoleToOps;
    @FXML public String pvp;
    @FXML public String spawnNpcs;
    @FXML public String generateStructures;
    @FXML public String spawnAnimals;
    @FXML public String snooperEnabled;
    @FXML public String difficulty;
    @FXML public String functionPermissionLevel;
    @FXML public String networkCompressionThreshold;
    @FXML public String levelType;
    @FXML public String spawnMonsters;
    @FXML public String maxTickTime;
    @FXML public String enforceWhitelist;
    @FXML public String useNativeTransport;
    @FXML public String maxPlayers;
    @FXML public String resourcePackSha1;
    @FXML public String spawnProtection;
    @FXML public String onlineMode;
    @FXML public String allowFlight;
    @FXML public String maxWorldSize;

    public String ReadProp()
    {
        lines.clear();
        formattedValue.clear();
        formattedVar.clear();

        String filePath = System.getProperty("user.dir")+"\\server.properties";

        String content = "";

        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }

                ReadData();
            }
        }
        catch (IOException e)
        {
            AlertWindow alertWindow = new AlertWindow();

            alertWindow.Alert(Alert.AlertType.ERROR, "File not found", null, "Server.properties file not found, please move this app into the same folder as your server.properties file.", true);
            e.printStackTrace();
        }

        return content;
    }

    // all data with vars
    private void ReadData() {
        ParseData();

        motd = ValueFetcher("motd");
        maxPlayers = ValueFetcher("max-players");
        levelName = ValueFetcher("level-name");
        serverPort = ValueFetcher("server-port");
        gamemode = ValueFetcher("gamemode");
        difficulty = ValueFetcher("difficulty");
        pvp = ValueFetcher("pvp");
    }

    private void ParseData() {
        for (String i: lines)
        {
            if(i.contains("="))
            {
                String val = i.substring(i.indexOf("=")+1);
                val.trim();
                formattedValue.add(val);

                String Var = i.substring(0, i.indexOf("="));
                Var.trim();
                formattedVar.add(Var);
            }
        }
    }

    private String ValueFetcher(String varName) {

        String temp;

        temp = formattedValue.get(formattedVar.indexOf(varName));

        return temp;
    }
}
