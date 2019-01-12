package interfaceJeu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Domino;
import model.Jeu;
import model.Joueur;

public class GameTour implements Initializable{

	public AnchorPane anchorDominos = new AnchorPane();
	public AnchorPane containerDominos;
	public Label totalDominos = new Label();
	public Label currentPlayer;
	public Label currentPlayer2;
	
	public AnchorPane dominoWrapper1;
	public AnchorPane dominoWrapper2;
	public AnchorPane dominoWrapper3;
	public AnchorPane dominoWrapper4;
	
	public static int numDomino1;
	public static int numDomino2;
	public static int numDomino3;
	public static int numDomino4;
	
	public AnchorPane mainWrap;
	public AnchorPane domino1;
	public AnchorPane domino11;
	public AnchorPane domino2;
	public AnchorPane domino21;
	public AnchorPane domino3;
	public AnchorPane domino31;
	public AnchorPane domino4;
	public AnchorPane domino41;
	
	public ImageView dominoimg1 = new ImageView();
	public ImageView dominoimg11 = new ImageView();
	public ImageView dominoimg2 = new ImageView();
	public ImageView dominoimg21 = new ImageView();
	public ImageView dominoimg3 = new ImageView();
	public ImageView dominoimg31 = new ImageView();
	public ImageView dominoimg4 = new ImageView();
	public ImageView dominoimg41 = new ImageView();
	
	
	public AnchorPane onplayer1;
	public AnchorPane onplayer2;
	public AnchorPane onplayer3;
	public AnchorPane onplayer4;
	
	public Circle colorplayer1 = new Circle();
	public Circle colorplayer2 = new Circle();
	public Circle colorplayer3 = new Circle();
	public Circle colorplayer4 = new Circle();
	
	public Label pseudoplayer1 = new Label();
	public Label pseudoplayer2 = new Label();
	public Label pseudoplayer3 = new Label();
	public Label pseudoplayer4 = new Label();
	
	public Button btndomino1 = new Button();
	public Button btndomino2= new Button();
	public Button btndomino3= new Button();
	public Button btndomino4= new Button();
	
	

	
	 
	
	
	public static Joueur currentJoueur;
	public static List<Domino> tempList = new ArrayList<>();
	
	public static List<Domino> listEachTour = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	

	}
	
	public void newTour(boolean condition) {
		System.out.println("Condition : " + condition);
		if(condition) {
				System.out.println("Rentré dans la condition...");
				if(Jeu.joueurs.size() > 0) {
					Jeu.lancerJeu(Jeu.joueurs.size());
					displayPlayers(Jeu.allPlayersWholeGame.size());
					displayDominosPicked();
					currentJoueur = setPlayerToPlay();
				} else {
					
					if(listEachTour.size() == 0) {
						Jeu.lancerJeu(Jeu.changingOrder.size());
						displayPlayers(Jeu.allPlayersWholeGame.size());
						displayDominosPicked();
						currentJoueur = setPlayerToPlay();
					} else {
						
						displayPlayers(Jeu.allPlayersWholeGame.size());
						displayDominos();
						currentJoueur = setPlayerToPlay();
						
					}
					
				}			
		} else {
			displayPlayers(Jeu.allPlayersWholeGame.size());
			displayDominos();
			currentJoueur = setPlayerToPlay();
			System.out.println("Juste un retour en ar");
			System.out.println("Maintenant le joueur courant est : " + currentJoueur.getNomJoueur()) ;
		}
		
	}
	
	private Joueur setPlayerToPlay() {
		//currentJoueur = Jeu.playerTour(doms);
		System.out.println("------------------ Set player to play so removing player ------------------ ");
		currentJoueur = Jeu.playerTour();
		//System.out.println("CURRENT : " + currentJoueur.getNomJoueur());
		
		currentPlayer.setText(GameTour.currentJoueur.getNomJoueur());
		currentPlayer2.setText(GameTour.currentJoueur.getNomJoueur());
		return currentJoueur;
	}
	
	public void setPlayer(Joueur j) {
		currentJoueur = j;
	}
	
	
	
	
	
	public void dominoPicked1(ActionEvent event) throws IOException {
		/*System.out.println("Domino 1 picked by player");
		Domino dom = Jeu.dominosFromGui.get(0);
		System.out.println("Dominopciked1 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		Jeu.orderSet.put(currentJoueur, dom);
		
		System.out.print("Domino 1 picked by " + currentJoueur.getNomJoueur());
		
		currentJoueur.listOfDominosPerPlayer.add(dom);
		Jeu.dominosFromGui.remove(dom);*/
		//goGrid(event);
		
		System.out.println("Domino 1 picked by player");
		//Domino dom = Jeu.dominosFromGui.get(0);
		
		Domino dom = Jeu.getDominoGUI(numDomino1);
		System.out.println("Dominopciked1 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		//Jeu.setupOrder(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);
		//System.out.println("Dominopciked1 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		
		//Jeu.orderSet.put(currentJoueur, dom);
		containerDominos.getChildren().remove(dominoWrapper1);
		dominoWrapper1.setVisible(false);
		goGrid(event);
		
		//System.out.print("Domino 1 picked by " + currentJoueur.getNomJoueur());
		
		//currentJoueur.listOfDominosPerPlayer.add(dom);
		
	
//		return dom;
		
	}

	public Domino dominoPicked2(ActionEvent event) throws IOException  {
		/*System.out.println("Domino 2 picked by player");
		//Domino dom = Jeu.dominosFromGui.get(0);
		
		Domino dom = Jeu.getDominoGUI(numDomino1);
		Jeu.setupOrder(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);

		return dom;*/
		
		System.out.println("Domino 2 picked by player");
		//Domino dom = Jeu.dominosFromGui.get(0);
		
		Domino dom = Jeu.getDominoGUI(numDomino2);
		System.out.println("Dominopciked2 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		//Jeu.setupOrder(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);
		//System.out.println("Dominopciked1 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		//Jeu.orderSet.put(currentJoueur, dom);
		containerDominos.getChildren().remove(dominoWrapper2);
		dominoWrapper2.setVisible(false);
		goGrid(event);
		return dom;
	}
	
	public Domino dominoPicked3(ActionEvent event) throws IOException {
		/*System.out.println("Domino 3 picked by player");
		Domino dom = Jeu.dominosFromGui.get(2);
		Jeu.orderSet.put(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);
		return dom;*/
		
		System.out.println("Domino 3 picked by player");
		//Domino dom = Jeu.dominosFromGui.get(0);
		
		Domino dom = Jeu.getDominoGUI(numDomino3);
		System.out.println("Dominopciked3 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		//Jeu.setupOrder(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);
		//System.out.println("Dominopciked1 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		//Jeu.orderSet.put(currentJoueur, dom);
		containerDominos.getChildren().remove(dominoWrapper3);
		dominoWrapper3.setVisible(false);
		goGrid(event);
		return dom;
	}
	
	public Domino dominoPicked4(ActionEvent event) throws IOException {
		/*System.out.println("Domino 4 picked by player");
		Domino dom = Jeu.dominosFromGui.get(3);
		Jeu.orderSet.put(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);
		return dom;*/
		
		System.out.println("Domino 4 picked by player");
		//Domino dom = Jeu.dominosFromGui.get(0);
		
		Domino dom = Jeu.getDominoGUI(numDomino4);
		System.out.println("Dominopciked4 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		//Jeu.setupOrder(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);
		//System.out.println("Dominopciked1 : " + " num : " + dom.getNumero() + " t1" + dom.getTypeTuile1() + " t2" + dom.getTypeTuile2());
		//Jeu.orderSet.put(currentJoueur, dom);
		containerDominos.getChildren().remove(dominoWrapper4);
		//dominoWrapper4.setVisible(false);
		goGrid(event);
		return dom;
	}
	

	public List<Domino> displayDominosPicked() {
		int nbDominos = Jeu.adaptGame(Jeu.joueurs.size());
		anchorDominos.setVisible(true);
		String total = Integer.toString(Domino.dominosNbJoueurs.size());
		totalDominos.setText(total);
		
		/*List<Domino> dominos = new ArrayList<>();
		dominos.addAll(Jeu.getDominos());
		for(int i = 0; i < dominos.size(); i++) {
			System.out.println(dominos.get(i).getNumero());
			if(dominos.size() == 4) {
				Domino dom = dominos.get(i);
				createDomino(dom, i, 3);
			}
			else {
				Domino dom = dominos.get(i);
				createDomino(dom, i, 2);
			}
		}*/
		
		listEachTour.addAll(Jeu.getDominos());
		
		if(Jeu.joueurs.size() > 0) {
			System.out.println("Dans le if Jeu.joueurs.size > 0 ");
			for(int i = 0; i < listEachTour.size(); i++) {
				System.out.println("List each tour size : " + listEachTour.size());
				System.out.println("Players size : " + Jeu.joueurs.size());
				System.out.println(listEachTour.get(i).getNumero());
				if(Jeu.joueurs.size() == 4 || Jeu.joueurs.size() == 2 && listEachTour.size() == 4) {
					Domino dom = listEachTour.get(i);
					createDomino(dom, i, 3);
				}
				else if(Jeu.joueurs.size() == 3 && listEachTour.size() == 3){
					Domino dom = listEachTour.get(i);
					createDomino(dom, i, 2);
				}
				else {
					System.out.println("Dans le else bien sûr");
					Domino dom = listEachTour.get(i);
					createDomino(dom, i, Jeu.joueurs.size() - 1);
				}
			}
			return listEachTour;
		} else {
			
			for(int i = 0; i < listEachTour.size(); i++) {
				System.out.println("List each tour size : " + listEachTour.size());
				System.out.println("Players size else : " + Jeu.changingOrder.size());
				System.out.println(listEachTour.get(i).getNumero());
				if(Jeu.changingOrder.size() == 4 || Jeu.changingOrder.size() == 2 && listEachTour.size() == 4) {
					Domino dom = listEachTour.get(i);
					createDomino(dom, i, 3);
				}
				else if(Jeu.changingOrder.size() == 3 && listEachTour.size() == 3){
					Domino dom = listEachTour.get(i);
					createDomino(dom, i, 2);
				}
				else {
					System.out.println("Creating new domino");
					Domino dom = listEachTour.get(i);
					createDomino(dom, i, listEachTour.size() - 1);
				}
			}
			return listEachTour;
			
			
		}
		
		
		
	}
	
	public List<Domino> displayDominos() {
		/*int nbDominos = Jeu.adaptGame(Jeu.joueurs.size());
		
		String total = Integer.toString(Domino.dominosNbJoueurs.size());
		System.out.println("total : " + total);
		totalDominos.setText(total);
		
		
		
		for(int i = 0; i < Jeu.dominosFromGui.size(); i++) {
			System.out.println(Jeu.dominosFromGui.get(i).getNumero());
			if(Jeu.joueurs.size() == 4 && Jeu.dominosFromGui.size() == 4) {
				Domino dom = Jeu.dominosFromGui.get(i);
				createDomino(dom, i, 3);
			}
			else if (Jeu.joueurs.size() == 3 && Jeu.dominosFromGui.size() == 3) {
				Domino dom = Jeu.dominosFromGui.get(i);
				createDomino(dom, i, 2);
			}
			else {
				Domino dom = Jeu.dominosFromGui.get(i);
				System.out.println("Nothing, bcs all was setup");
				System.out.println("Jeu dominos " + Jeu.dominosFromGui.size());
				createDomino(dom, i, Jeu.dominosFromGui.size());
			}
		}*/
		
		for(int i = 0; i < listEachTour.size(); i++) {
			System.out.println("List each tour size : " + listEachTour.size());
			System.out.println("Players size : " + Jeu.joueurs.size());
			System.out.println(listEachTour.get(i).getNumero());
			if(Jeu.joueurs.size() == 4 || Jeu.joueurs.size() == 2 && listEachTour.size() == 4) {
				Domino dom = listEachTour.get(i);
				createDomino(dom, i, 3);
			}
			else if(Jeu.joueurs.size() == 3 && listEachTour.size() == 3){
				Domino dom = listEachTour.get(i);
				createDomino(dom, i, 2);
			}
			else {
				System.out.println("Dans le else bien sûr");
				Domino dom = listEachTour.get(i);
				createDomino(dom, i, Jeu.joueurs.size() - 1);
			}
		}
		
		return Jeu.dominosFromGui;
	}

	private void createDomino(Domino dom, int i, int param) {
		if(param == 3) {
			String typeTuile1 = dom.getTuile1().typeTuile;
			String typeTuile2 = dom.getTuile2().typeTuile;
			int nbCouronnes1 = dom.getTuile1().getNbCouronnes();
			int nbCouronnes2 = dom.getTuile2().getNbCouronnes();
			if(i == 0) {
			
			Image img = new Image("./model/public/"+typeTuile1+nbCouronnes1+".png");
			Image img2 = new Image("./model/public/"+typeTuile2+nbCouronnes2+".png");
			System.out.println("./model/public/"+typeTuile1+nbCouronnes1+".png");
			System.out.println("./model/public/"+typeTuile2+nbCouronnes2+".png");
			
			dominoimg1.setImage(img);
			dominoimg11.setImage(img2);
			numDomino1 = dom.getNumero();
			Jeu.dominosGUI(dom);
			
			}
			else if(i == 1) {
				Image img = new Image("./model/public/"+typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image("./model/public/"+typeTuile2+nbCouronnes2+".png");
				System.out.println(typeTuile1+nbCouronnes1+".png");
				System.out.println(typeTuile2+nbCouronnes2+".png");
				
				dominoimg2.setImage(img);
				dominoimg21.setImage(img2);
				numDomino2 = dom.getNumero();
				Jeu.dominosGUI(dom);
			}
			else if(i == 2) {
				Image img = new Image("./model/public/"+typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image("./model/public/"+typeTuile2+nbCouronnes2+".png");
				System.out.println(typeTuile1+nbCouronnes1+".png");
				System.out.println(typeTuile2+nbCouronnes2+".png");
				
				dominoimg3.setImage(img);
				dominoimg31.setImage(img2);
				numDomino3 = dom.getNumero();
				Jeu.dominosGUI(dom);
			}
			else if(i == 3) {
				Image img = new Image("./model/public/"+typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image("./model/public/"+typeTuile2+nbCouronnes2+".png");
				System.out.println(typeTuile1+nbCouronnes1+".png");
				System.out.println(typeTuile2+nbCouronnes2+".png");
				
				dominoimg4.setImage(img);
				dominoimg41.setImage(img2);
				numDomino4 = dom.getNumero();
				Jeu.dominosGUI(dom);
			}
		}
		else {
			String typeTuile1 = dom.getTuile1().typeTuile;
			String typeTuile2 = dom.getTuile2().typeTuile;
			int nbCouronnes1 = dom.getTuile1().getNbCouronnes();
			int nbCouronnes2 = dom.getTuile2().getNbCouronnes();
			if(i == 0) {
				Image img = new Image("./model/public/"+typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image("./model/public/"+typeTuile2+nbCouronnes2+".png");
			
				dominoimg1.setImage(img);
				dominoimg11.setImage(img2);
				numDomino1 = dom.getNumero();
				Jeu.dominosGUI(dom);
			}
			else if(i == 1) {
				Image img = new Image("./model/public/"+typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image("./model/public/"+typeTuile2+nbCouronnes2+".png");
			
				dominoimg2.setImage(img);
				dominoimg21.setImage(img2);
				numDomino2 = dom.getNumero();
				Jeu.dominosGUI(dom);
			}
			else if(i == 2) {
				Image img = new Image("./model/public/"+typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image("./model/public/"+typeTuile2+nbCouronnes2+".png");
			
				dominoimg3.setImage(img);
				dominoimg31.setImage(img2);
				numDomino3 = dom.getNumero();
				Jeu.dominosGUI(dom);
			}
		}		
	}
	
	public void displayPlayers(int nbPlayers) {
		if(nbPlayers == 2) {
			onplayer3.setVisible(false);
			onplayer4.setVisible(false);
			String nom1 = Jeu.allPlayersWholeGame.get(0).getNomJoueur();
			String nom2 = Jeu.allPlayersWholeGame.get(1).getNomJoueur();			
			String color1 = Jeu.allPlayersWholeGame.get(0).getCouleurJoueur();
			String color2 = Jeu.allPlayersWholeGame.get(1).getCouleurJoueur();
			pseudoplayer1.setText(nom1);
			pseudoplayer2.setText(nom2);
			
			colorplayer1.setFill(getAdaptativeColor(color1));
			colorplayer2.setFill(getAdaptativeColor(color2));
			
			
		}
		else if(nbPlayers == 3) {
			onplayer3.setVisible(true);
			String nom1 = Jeu.allPlayersWholeGame.get(0).getNomJoueur();
			String nom2 = Jeu.allPlayersWholeGame.get(1).getNomJoueur();
			String nom3 = Jeu.allPlayersWholeGame.get(2).getNomJoueur();
			String color1 = Jeu.allPlayersWholeGame.get(0).getCouleurJoueur();
			String color2 = Jeu.allPlayersWholeGame.get(1).getCouleurJoueur();
			String color3 = Jeu.allPlayersWholeGame.get(2).getCouleurJoueur();
			pseudoplayer1.setText(nom1);
			pseudoplayer2.setText(nom2);
			pseudoplayer3.setText(nom3);
			
			
			colorplayer1.setFill(getAdaptativeColor(color1));
			colorplayer2.setFill(getAdaptativeColor(color2));
			colorplayer2.setFill(getAdaptativeColor(color3));
		}
		else if (nbPlayers == 4) {
			onplayer3.setVisible(true);
			onplayer4.setVisible(true);
		
			String nom1 = Jeu.allPlayersWholeGame.get(0).getNomJoueur();
			String nom2 = Jeu.allPlayersWholeGame.get(1).getNomJoueur();
			String nom3 = Jeu.allPlayersWholeGame.get(2).getNomJoueur();
			String nom4 = Jeu.allPlayersWholeGame.get(3).getNomJoueur();
			String color1 = Jeu.allPlayersWholeGame.get(0).getCouleurJoueur();
			String color2 = Jeu.allPlayersWholeGame.get(1).getCouleurJoueur();
			String color3 = Jeu.allPlayersWholeGame.get(2).getCouleurJoueur();
			String color4 = Jeu.allPlayersWholeGame.get(3).getCouleurJoueur();
			pseudoplayer1.setText(nom1);
			pseudoplayer2.setText(nom2);
			pseudoplayer3.setText(nom3);
			pseudoplayer4.setText(nom4);
			
			colorplayer1.setFill(getAdaptativeColor(color1));
			colorplayer2.setFill(getAdaptativeColor(color2));
			colorplayer3.setFill(getAdaptativeColor(color3));
			colorplayer4.setFill(getAdaptativeColor(color4));
			
		}
		else {
			
		}
	}
	
	
	public Paint getAdaptativeColor(String color) {
		if(color == "jaune") {
			return Color.YELLOW;
		}
		else if(color == "bleu") {
			return Color.BLUE;
		}
		else if(color == "rouge") {
			return Color.RED;
		}
		else {
			return Color.GREEN;
		}
		
	}
	
	
	public void goGrid(ActionEvent event) throws IOException {
		try {
			
			
			
			Parent root = FXMLLoader.load(getClass().getResource("./public/GridTour.fxml"));
			Scene nextScene = new Scene(root);
			
			
			
			Stage window = (Stage) mainWrap.getScene().getWindow();
			window.setScene(nextScene);
			window.show();
				//Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
				//window.setScene(nextScene);
				//window.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
