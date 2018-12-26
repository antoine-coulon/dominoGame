package interfaceJeu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrepareGame implements Initializable {

	public Button configDone;
	public Button goBack;
	public ChoiceBox<String> choiceNbJoueurs = new ChoiceBox<>();
	public AnchorPane thirdPlayer;
	public AnchorPane fourthPlayer;
	public TextField namePlayer1;
	public TextField namePlayer2;
	
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> availableChoices = FXCollections.observableArrayList("2", "3", "4"); 
		choiceNbJoueurs.setItems(availableChoices);
		choiceNbJoueurs.getSelectionModel().selectedItemProperty().addListener((value) -> displayNbInput());
		
	}
	
	public void displayNbInput() {
		String selectedChoice = choiceNbJoueurs.getSelectionModel().getSelectedItem();
		if(selectedChoice != null) {
			if(selectedChoice.equals("2")) {
				System.out.println("2");
				
				thirdPlayer.setVisible(false);
				fourthPlayer.setVisible(false);
			}
			else if(selectedChoice.equals("3")) {
				System.out.println("3");
				TextField namePlayer3 = new TextField();
				thirdPlayer.setVisible(true);
				fourthPlayer.setVisible(false);
			}
			else if(selectedChoice.equals("4")) {
				System.out.println("4");
				TextField namePlayer3 = new TextField();
				TextField namePlayer4 = new TextField();
				thirdPlayer.setVisible(true);
				fourthPlayer.setVisible(true);
			}
			else {
				System.out.println("else here");
			}
		}
		
	}
	
	
	public void saveGameConfig(ActionEvent event) throws IOException {
		//startGame.setVisible(true);
		
		try {
			
			/*for (Node node : anchorPane.getChildren()) {
			    System.out.println("Id: " + node.getId());
			    if (node instanceof TextField) {
			        // clear
			        ((TextField)node).setText("");
			    }
			}*/
		Parent root = FXMLLoader.load(getClass().getResource("SetupGame.fxml"));
		Scene nextScene = new Scene(root);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(nextScene);
		window.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goBackAction(ActionEvent event) throws IOException {
		//startGame.setVisible(true);
		try {
		Parent root = FXMLLoader.load(getClass().getResource("IndexGame.fxml"));
		Scene nextScene = new Scene(root);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(nextScene);
		window.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
