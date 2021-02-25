package sample;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Alert;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

    private String pastID = "jDgL8S5c";

    public String serverVersion;
    public String updateLocation;
    public String patchNotesStr;

    public void CallUpdater() {

        try {
            // This is ware the JSON file is stored (On pastebin)
            String sURL = "https://pastebin.com/raw/" + pastID; //just a string

            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element


            JSONObject json = new JSONObject(root.toString());
            serverVersion = json.getString("serverVersion");
            updateLocation = json.getString("updateLocation");
            patchNotesStr = json.getString("patchNotes");

            Main main = new Main();

            System.out.println("Client Version " + main.clientVersion);
            System.out.println("Server Version " + serverVersion);

        } catch (Exception e) {
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.Alert(Alert.AlertType.ERROR, "Unable to get update info", null, null, true);

            e.printStackTrace();
        }

    }
}
