package interfaceJeu;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartGameGUI extends Application {

	Scene scene1;
	@Override
    public void start(Stage primaryStage) throws IOException {
       
		 FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(new URL("../Template.fxml"));
	        VBox vbox = loader.<VBox>load();

	        Scene scene = new Scene(vbox,1,1);
	        primaryStage.setScene(scene);
	        primaryStage.show();
		
		
    }
 
 public static void main(String[] args) {
        launch(args);
    }
}
