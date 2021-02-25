package sample;

import javafx.scene.control.Alert;

public class AlertWindow {

    Alert alert = null;

    public void Alert(Alert.AlertType alertType, String Title, String Header, String ContentText, boolean useShowAndWait) {
        alert = new Alert(alertType);
        alert.setTitle(Title);
        alert.setHeaderText(Header);
        alert.setContentText(ContentText);

        if(useShowAndWait) {
            alert.showAndWait();
        }
    }

    public Alert getAlert() {
        return alert;
    }
}
