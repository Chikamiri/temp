import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainSceneController {

    @FXML
    private Button Btn;

    @FXML
    void Close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void Ex1Clicked(ActionEvent event) {
        try{
            FXMLLoader PanelLoader= new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
            Parent loginPanel=PanelLoader.load();
            
            Stage login = new Stage();
            login.setScene(new Scene(loginPanel));
            login.setResizable(false);
            login.initStyle(StageStyle.UNDECORATED);
            login.show();

            Stage currentStage=(Stage)Btn.getScene().getWindow();
            currentStage.close();

        }catch(IOException e){e.printStackTrace();}
    }

}
