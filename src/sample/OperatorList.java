package sample;

import javafx.scene.control.Alert;
import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperatorList {

    private String filePath = System.getProperty("user.dir")+"\\ops.json";

    public List<String> Name = new ArrayList<>();
    public List<String> UUID = new ArrayList<>();

    public void ReadOps() {
        InputStream is = null;

        try {
            is = new FileInputStream(filePath);

            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = null;

            line = buf.readLine();

            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }

            String fileAsString = sb.toString();
            EditJSON(fileAsString);

        } catch (IOException e) {
            AlertWindow alertWindow = new AlertWindow();

            alertWindow.Alert(Alert.AlertType.ERROR, "File not found", null, "ops.json file not found, please move this app into the same folder as your ops.json file.", true);
            e.printStackTrace();
        }
    }

    public void EditJSON(String jsonArray) {

        // Reads jsonArray data as string converts it to an array
        JSONArray json = new JSONArray(jsonArray);

        for (int i = 0; i < json.length(); i++)
        {
            // and spits out each item in array
            String name = json.getJSONObject(i).getString("name");
            String uuid = json.getJSONObject(i).getString("uuid");

            Name.add(name);
            UUID.add(uuid);
        }
    }
}
