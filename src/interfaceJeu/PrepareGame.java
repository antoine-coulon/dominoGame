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
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Grille;
import model.Jeu;
import model.Joueur;

public class PrepareGame implements Initializable {

	public AnchorPane anchorWrapper;
	public AnchorPane mainAnchor;
	public Pane paneError = new Pane();
	public Button configDone;
	public Button goBack;
	public ChoiceBox<String> choiceNbJoueurs = new ChoiceBox<>();
	public ChoiceBox<String> typePlayer1 = new ChoiceBox<>();
	public ChoiceBox<String> typePlayer2 = new ChoiceBox<>();
	public ChoiceBox<String> typePlayer3 = new ChoiceBox<>();
	public ChoiceBox<String> typePlayer4 = new ChoiceBox<>();
	private FadeTransition fadeIn = new FadeTransition(
		    Duration.millis(1000)
		);
	
	public Label msgError;
	public AnchorPane thirdPlayer;
	public AnchorPane fourthPlayer;
	public TextField nameJoueur1;
	public TextField nameJoueur2;
	public TextField nameJoueur3;
	public TextField nameJoueur4;
	static List<TextField> nomPlayer = new ArrayList<>();
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fadeIn.setNode(msgError);

	    fadeIn.setFromValue(0.0);
	    fadeIn.setToValue(1.0);
	    fadeIn.setCycleCount(1);
	    fadeIn.setAutoReverse(false);
	    
	    
	    
		ObservableList<String> availableChoices = FXCollections.observableArrayList("2", "3", "4"); 
		ObservableList<String> playerType = FXCollections.observableArrayList("Human", "IA"); 
		choiceNbJoueurs.setValue(availableChoices.get(0));
		choiceNbJoueurs.setItems(availableChoices);
		typePlayer1.setValue(playerType.get(0));
		typePlayer1.setItems(playerType);
		typePlayer2.setValue(playerType.get(0));
		typePlayer2.setItems(playerType);
		typePlayer3.setValue(playerType.get(0));
		typePlayer3.setItems(playerType);
		typePlayer4.setValue(playerType.get(0));
		typePlayer4.setItems(playerType);
		choiceNbJoueurs.getSelectionModel().selectedItemProperty().addListener((value) -> displayNbInput());
		
	}
	
	public String displayNbInput() {
		String selectedChoice = choiceNbJoueurs.getSelectionModel().getSelectedItem();
		if(selectedChoice != null) {
			if(selectedChoice.equals("2")) {
				//System.out.println("2");
				thirdPlayer.setVisible(false);
				fourthPlayer.setVisible(false);

			}
			else if(selectedChoice.equals("3")) {
				//System.out.println("3");
				thirdPlayer.setVisible(true);
				fourthPlayer.setVisible(false);
			}
			else if(selectedChoice.equals("4")) {
				//System.out.println("4");
				thirdPlayer.setVisible(true);
				fourthPlayer.setVisible(true);
			}
			else {
				System.out.println("else here");
			}
		}
		return selectedChoice;
		
	}
	
	
	public void saveGameConfig(ActionEvent event) throws IOException {
		//startGame.setVisible(true);

		try {
		if(displayNbInput().equals("2")) {
			
			String pseudoP1 = nameJoueur1.getText();
			String typeP1 = typePlayer1.getValue();
			String pseudoP2 = nameJoueur2.getText();
			String typeP2 = typePlayer2.getValue();
			System.out.println("2" + pseudoP1 + pseudoP2);
			
			if(pseudoP1.trim().isEmpty() || pseudoP2.trim().isEmpty()) {
				paneError.setVisible(true);
				fadeIn.playFromStart();
			}
			else {
				
				Grille grille1 = new Grille(pseudoP1);
				Grille grille2 = new Grille(pseudoP2);
				//Grille grille = new Grille(nomJoueur);
				Joueur player1 = new Joueur(1, pseudoP1, null, grille1);
				Joueur player2 = new Joueur(2, pseudoP2, null, grille2);
				Jeu.joueurs.add(player1);
				Jeu.joueurs.add(player2);
				Jeu.fillColor();
				goNext(event);
			}
			
			
		} else if(displayNbInput().equals("3")) {
			
			String pseudoP1 = nameJoueur1.getText();
			String typeP1 = typePlayer1.getValue();
			String pseudoP2 = nameJoueur2.getText();
			String typeP2 = typePlayer2.getValue();
			String pseudoP3 = nameJoueur3.getText();
			String typeP3 = typePlayer3.getValue();

			if(pseudoP1.trim().isEmpty() || pseudoP2.trim().isEmpty() || pseudoP3.trim().isEmpty()) {
				msgError.setVisible(true);
				fadeIn.playFromStart();
				
			}
			else {
				Grille grille1 = new Grille(pseudoP1);
				Grille grille2 = new Grille(pseudoP2);
				Grille grille3 = new Grille(pseudoP3);
				Joueur player1 = new Joueur(1, pseudoP1, null, grille1);
				Joueur player2 = new Joueur(2, pseudoP2, null, grille2);
				Joueur player3 = new Joueur(3, pseudoP3, null, grille3);
				Jeu.joueurs.add(player1);
				Jeu.joueurs.add(player2);
				Jeu.joueurs.add(player3);
				Jeu.fillColor();
				goNext(event);
			}
			
		} else if(displayNbInput().equals("4")) {
			
			String pseudoP1 = nameJoueur1.getText();
			String typeP1 = typePlayer1.getValue();
			String pseudoP2 = nameJoueur2.getText();
			String typeP2 = typePlayer2.getValue();
			String pseudoP3 = nameJoueur3.getText();
			String typeP3 = typePlayer3.getValue();
			String pseudoP4 = nameJoueur4.getText();
			String typeP4 = typePlayer4.getValue();
			System.out.println("4" + pseudoP1 + pseudoP2 + pseudoP3 + pseudoP4);
			
			if(pseudoP1.trim().isEmpty() || pseudoP2.trim().isEmpty() || pseudoP3.trim().isEmpty() || pseudoP4.trim().isEmpty()) {
				msgError.setVisible(true);
				fadeIn.playFromStart();
			}
			else {
				Grille grille1 = new Grille(pseudoP1);
				Grille grille2 = new Grille(pseudoP2);
				Grille grille3 = new Grille(pseudoP3);
				Grille grille4 = new Grille(pseudoP4);
				Joueur player1 = new Joueur(1, pseudoP1, null, grille1);
				Joueur player2 = new Joueur(2, pseudoP2, null, grille2);
				Joueur player3 = new Joueur(3, pseudoP3, null, grille3);
				Joueur player4 = new Joueur(4, pseudoP4, null, grille4);
				Jeu.joueurs.add(player1);
				Jeu.joueurs.add(player2);
				Jeu.joueurs.add(player3);
				Jeu.joueurs.add(player4);
				Jeu.fillColor();
				goNext(event);
			}
		}
		
		/*
		
			for (Node node : mainAnchor.getChildren()) {
			    System.out.println("Id: " + node.getId());
			    if (node instanceof TextField) {
			    	System.out.println("Yes");
			    	System.out.println(((TextField)node).getText());
			       String test = ((TextField)node).getText();
			       System.out.println(test);
			    }
			}
		*/
		
		/*Parent root = FXMLLoader.load(getClass().getResource("SetupGame.fxml"));
		Scene nextScene = new Scene(root);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(nextScene);
		window.show();*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goNext(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SetupGame.fxml"));
		Scene nextScene = new Scene(root);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(nextScene);
		window.show();
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
