package interfaceJeu;
	
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class IndexGame implements Initializable{
	
	public Button startGame;
	private AnchorPane rootPane;
	
	public void launchGameAction(ActionEvent event) throws IOException {
		//startGame.setVisible(true);
		try {
		Parent root = FXMLLoader.load(getClass().getResource("./public/prepareGame.fxml"));
		Scene nextScene = new Scene(root);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(nextScene);
		window.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
