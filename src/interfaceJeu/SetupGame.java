package interfaceJeu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Jeu;
import model.Joueur;

public class SetupGame implements Initializable {
	
	public AnchorPane playerAnchor;
	public Label msgError;
	public Button validateColor;
	public ChoiceBox<String> choiceNbJoueurs = new ChoiceBox<>();
	public ChoiceBox<String> typePlayer1 = new ChoiceBox<>();
	public ChoiceBox<String> typePlayer2 = new ChoiceBox<>();
	public ChoiceBox<String> typePlayer3 = new ChoiceBox<>();
	public ChoiceBox<String> typePlayer4 = new ChoiceBox<>();
	private FadeTransition fadeIn = new FadeTransition(
		    Duration.millis(1000)
	);

	public List<Joueur> temporary = new ArrayList<>();
	
	
	public Label nomPlayer;
	public ChoiceBox<String> colorPlayer = new ChoiceBox<>();
	ObservableList<String> colors = FXCollections.observableArrayList(); 
	
	public TextField nameJoueur2;
	public TextField nameJoueur3;
	public TextField nameJoueur4;
	//static List<TextField> nomPlayer = new ArrayList<>();
	
	public SetupGame() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		fadeIn.setNode(playerAnchor);
		fadeIn.setNode(msgError);
	    fadeIn.setFromValue(0.0);
	    fadeIn.setToValue(1.0);
	    fadeIn.setCycleCount(1);
	    fadeIn.setAutoReverse(false);
	    
	    
	    Jeu.shufflePlayers();
		colorPlayer.setItems(colors);
		colors.addAll(Jeu.co);
		for(int i=0; i < Jeu.joueurs.size(); i++) {
			temporary.add(Jeu.joueurs.get(i));
		}
		
		try {
			putNames(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for(int i=0; i < Jeu.joueurs.size(); i++) {
			
			System.out.println(Jeu.joueurs.get(i).getNomJoueur());
			String nom = Jeu.joueurs.get(i).getNomJoueur();
			nomPlayer.setText(nom);
			//playerAnchor.getChildren().add(nomPlayer);
		}
		*/
	}
	
	public void putNames(ActionEvent event) throws IOException {
		if(temporary.size() > 0) {
			System.out.println(temporary.get(0).getNomJoueur());
			String nom = temporary.get(0).getNomJoueur();
			nomPlayer.setText(nom);
		}
		else {
			System.out.println("SALAM");
			goNext(event);
		}
		
	}
	
	public void onValidate(ActionEvent event) throws IOException{
		System.out.println("On validate");
		try {
		/*System.out.println("size ! " + temporary.size());
		for(int i = 0; i < temporary.size(); i++) {
			System.out.println(temporary.get(i));
		}
		*/

		if(temporary.size() >= 1) {
		
			String selectedColor = colorPlayer.getSelectionModel().getSelectedItem();
			System.out.println("selected : " + selectedColor);
			if(selectedColor == null) {
				msgError.setVisible(true);
				fadeIn.playFromStart();
			} else {
				msgError.setVisible(false);
				System.out.println("Selected color : " + selectedColor);
				System.err.println(temporary.size());
				// get le joueur de la liste de joueurs avec le nom de la liste temporaire
				int index = Jeu.joueurs.indexOf(temporary.get(0));
				Joueur j = Jeu.joueurs.get(index);
				System.out.println(j.getNomJoueur());
				j.setCouleur(selectedColor);
				colors.remove(selectedColor);
				colorPlayer.getSelectionModel().clearSelection();
				// une fois que l'on a agit sur le premier utilisateur, on le remove
				temporary.remove(temporary.get(0));
				System.err.println(temporary.size());
				playerAnchor.setVisible(false);
				putNames(event);
				playerAnchor.setVisible(true);
			}
			
		//System.out.println("Nouveau 0 : " + temporary.get(0).getNomJoueur());
		} else {
			
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goNext(ActionEvent event) throws IOException {
	
		/*Parent root = FXMLLoader.load(getClass().getResource("GameTour.fxml"));
		Scene nextScene = new Scene(root);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(nextScene);
		window.show();*/
		boolean test = true;
		FXMLLoader loader = new FXMLLoader(
		    getClass().getResource(
		        "./public/GameTour.fxml"
		    )
		);
		Parent root = (Parent) loader.load();
		GameTour controller = loader.getController();
		System.out.println(controller);
		controller.newTour(test);

		Scene nextScene = new Scene(root);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(nextScene);
		window.show();
	}
	
	public void goBackAction(ActionEvent event) throws IOException {
		//startGame.setVisible(true);
		try {
		Parent root = FXMLLoader.load(getClass().getResource("./public/PrepareGame.fxml"));
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
