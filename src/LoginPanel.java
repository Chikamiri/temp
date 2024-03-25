import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import Account.Account;
import Account.Account.Auth;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPanel {

    @FXML
    private Button Btn;

    @FXML
    private Label LoginStatus;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    public static String user_out;

    @FXML
    void loginBtn(ActionEvent event) {
        String user_get = username.getText();
        String pwd_get = password.getText();

        try {
            if (!Auth.authUser(user_get, pwd_get)) {
                LoginStatus.setText("Login Failed! Please check your input or register!");

            } else {
                System.out.println("Success");
                user_out = user_get;
                try {
                    FXMLLoader manager = new FXMLLoader(getClass().getResource("/FXML/MainManager.fxml"));
                    Parent toManager = manager.load();

                    MainManager managerController = manager.getController();
                    managerController.setUsername(user_out);

                    Stage openManager = new Stage();
                    openManager.setScene(new Scene(toManager));
                    openManager.setResizable(false);
                    openManager.initStyle(StageStyle.UNDECORATED);
                    openManager.show();

                    Stage currentStage = (Stage) Btn.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

    @FXML
    void RegBtn(ActionEvent event) {
        String user_get = username.getText();
        String pwd_get = password.getText();
        if(pwd_get.length()<8) {
            LoginStatus.setText("At be at least 8 characters long!");
            return;
        }
        try {
            boolean registered=Account.Auth.auth_register(user_get, pwd_get);
            if (registered) {
                System.out.println(registered);
                Account.Auth.registerUser(user_get, pwd_get);
                LoginStatus.setText("Registered");
            }
            else LoginStatus.setText("Username already exists!");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @FXML // Joke
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
