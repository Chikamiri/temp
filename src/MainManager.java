import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainManager {
    @FXML
    private ImageView background;

    @FXML
    private Label user_welcome;

    public void setUsername(String username) {
        user_welcome.setText(username);
    }
    // I should load fxml instead of make a new window @@

    @FXML
    void Book(ActionEvent event) {

    }

    @FXML
    void Logout(ActionEvent event) {

    }

    @FXML
    void View(ActionEvent event) {

    }

    @FXML
    void ViewBookings(ActionEvent event) {

    }

    @FXML
    void Close(ActionEvent event) {
        Platform.exit();
    }

    public class Background_Main implements Initializable {
        public void initialize(URL location, ResourceBundle resources) {
            File file = new File("src/Media/Background.jpg");
            Image image = new Image(file.toURI().toString());
            background.setImage(image);
        }
    }
}
