import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import Account.Account.Auth;
//import java.security.NoSuchAlgorithmException;
//import Account.Account.Auth;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPanel {

    @FXML
    private Button Btn;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void loginBtn(ActionEvent event) {
        String user_get = username.getText();
        String pwd_get = password.getText();

        try {
            if (!Auth.authUser(user_get, pwd_get)) {
                System.out.println("Failed");
                Auth.registerUser(user_get,pwd_get);
                
            } else {
                System.out.println("Success");
                try{
                    FXMLLoader manager = new FXMLLoader(getClass().getResource("/FXML/MainManager.fxml"));
                        Parent toManager = manager.load();
        
                        Stage openManager = new Stage();
                        openManager.setScene(new Scene(toManager));
                        openManager.setResizable(false);
                        openManager.initStyle(StageStyle.UNDECORATED);
                        openManager.show();

                        Stage currentStage=(Stage)Btn.getScene().getWindow();
                        currentStage.close();
                }catch(IOException e){e.printStackTrace();}

            }
        }

        catch (NoSuchAlgorithmException ex) {
            System.err.println("Error");
            ex.printStackTrace();
        }
    }

    @FXML 
    void CloseBtn(ActionEvent event) {
        Platform.exit();
    }

    @FXML// Joke
    void EasterEgg(ActionEvent event) {
        try {
            FXMLLoader Easter = new FXMLLoader(getClass().getResource("/FXML/EasterEgg.fxml"));
            Parent Joke = Easter.load();

            Stage temp = new Stage();
            temp.setScene(new Scene(Joke));
            temp.setResizable(false);
            temp.initStyle(StageStyle.UNDECORATED);
            temp.show();

            Stage currentStage = (Stage) Btn.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
