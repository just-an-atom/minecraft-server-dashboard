package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Players {

    private String uuid;
    private String username;

    public Players(String username, String uuid) {
        this.uuid = uuid;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
