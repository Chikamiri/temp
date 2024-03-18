import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

//I know this is a joke :P
public class EasterEgg implements Initializable{
    @FXML
    private MediaView mediaview;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        file=new File("D:\\KTPM\\temp\\src\\Background.mp4");
        media=new Media(file.toURI().toString());
        mediaPlayer=new MediaPlayer(media);
        mediaview.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

    }
    
}
