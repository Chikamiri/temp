import java.io.File;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainManager {
    @FXML
    private ImageView background;

    @FXML
    private Label user_welcome;

    private String username;

    public void setUsername(String username) {
        this.username = username;
        initializeBg();
    }

    @FXML
    void Book(ActionEvent event) {

    }

    @FXML
    void Close(ActionEvent event) {
        Platform.exit();
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

    public void initializeBg() {
        File file = new File("src/Media/Background.jpg");
        Image image = new Image(file.toURI().toString());
        background.setImage(image);

        user_welcome.setText(username);
    }
}
