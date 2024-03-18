import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {  
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/main.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();

            
        } catch (IOException e) {}
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}