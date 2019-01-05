package interfaceJeu;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.Domino;
import model.Jeu;
import model.Joueur;

public class GameTour implements Initializable{

	public AnchorPane anchorDominos = new AnchorPane();
	public Label totalDominos = new Label();
	
	public AnchorPane dominoWrapper1;
	public AnchorPane dominoWrapper2;
	public AnchorPane dominoWrapper3;
	public AnchorPane dominoWrapper4;
	
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Jeu.lancerJeu(Jeu.joueurs.size());
		displayPlayers(Jeu.joueurs.size());
		displayDominosPicked();
		currentJoueur = setPlayerToPlay(displayDominosPicked());
		
	}
	
	private Joueur setPlayerToPlay(List<Domino> doms) {
		currentJoueur = Jeu.playerTour(doms);
		System.out.println("CURRENT : " + currentJoueur);
		return currentJoueur;
	}
	
	public void setPlayer(Joueur j) {
		currentJoueur = j;
	}
	
	public Domino dominoPicked1() {
		System.out.println("Domino 1 picked by player");
		Domino dom = Jeu.dominosFromGui.get(0);
		Jeu.orderSet.put(currentJoueur, dom);
		//System.out.print(currentJoueur.getNomJoueur());
		currentJoueur.listOfDominosPerPlayer.add(dom);
		return dom;
	}
	
	public Domino dominoPicked2() {
		System.out.println("Domino 2 picked by player");
		Domino dom = Jeu.dominosFromGui.get(1);
		
		Jeu.setupOrder(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);

		return dom;
	}
	
	public Domino dominoPicked3() {
		System.out.println("Domino 3 picked by player");
		Domino dom = Jeu.dominosFromGui.get(2);
		Jeu.orderSet.put(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);
		return dom;
	}
	
	public Domino dominoPicked4() {
		System.out.println("Domino 4 picked by player");
		Domino dom = Jeu.dominosFromGui.get(3);
		Jeu.orderSet.put(currentJoueur, dom);
		currentJoueur.listOfDominosPerPlayer.add(dom);
		return dom;
	}
	

	public List<Domino> displayDominosPicked() {
		int nbDominos = Jeu.adaptGame(Jeu.joueurs.size());
		anchorDominos.setVisible(true);
		totalDominos.setText(Integer.toString(Domino.dominosNbJoueurs.size()));
		
		List<Domino> dominos = new ArrayList<>();
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
		}
		return dominos;
	}

	private void createDomino(Domino dom, int i, int param) {
		if(param == 3) {
			String typeTuile1 = dom.getTuile1().typeTuile;
			String typeTuile2 = dom.getTuile2().typeTuile;
			int nbCouronnes1 = dom.getTuile1().getNbCouronnes();
			int nbCouronnes2 = dom.getTuile2().getNbCouronnes();
			if(i == 0) {
			
			Image img = new Image(typeTuile1+nbCouronnes1+".png");
			Image img2 = new Image(typeTuile2+nbCouronnes2+".png");
			System.out.println(typeTuile1+nbCouronnes1+".png");
			System.out.println(typeTuile2+nbCouronnes2+".png");
			
			dominoimg1.setImage(img);
			dominoimg11.setImage(img2);
			
			Jeu.dominosGUI(dom);
			
			}
			else if(i == 1) {
				Image img = new Image(typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image(typeTuile2+nbCouronnes2+".png");
				System.out.println(typeTuile1+nbCouronnes1+".png");
				System.out.println(typeTuile2+nbCouronnes2+".png");
				
				dominoimg2.setImage(img);
				dominoimg21.setImage(img2);
				Jeu.dominosGUI(dom);
			}
			else if(i == 2) {
				Image img = new Image(typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image(typeTuile2+nbCouronnes2+".png");
				System.out.println(typeTuile1+nbCouronnes1+".png");
				System.out.println(typeTuile2+nbCouronnes2+".png");
				
				dominoimg3.setImage(img);
				dominoimg31.setImage(img2);
				Jeu.dominosGUI(dom);
			}
			else if(i == 3) {
				Image img = new Image(typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image(typeTuile2+nbCouronnes2+".png");
				System.out.println(typeTuile1+nbCouronnes1+".png");
				System.out.println(typeTuile2+nbCouronnes2+".png");
				
				dominoimg4.setImage(img);
				dominoimg41.setImage(img2);
				Jeu.dominosGUI(dom);
			}
		}
		else {
			String typeTuile1 = dom.getTuile1().typeTuile;
			String typeTuile2 = dom.getTuile2().typeTuile;
			int nbCouronnes1 = dom.getTuile1().getNbCouronnes();
			int nbCouronnes2 = dom.getTuile2().getNbCouronnes();
			if(i == 0) {
				Image img = new Image(typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image(typeTuile2+nbCouronnes2+".png");
			
				dominoimg1.setImage(img);
				dominoimg11.setImage(img2);
				Jeu.dominosGUI(dom);
			}
			else if(i == 1) {
				Image img = new Image(typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image(typeTuile2+nbCouronnes2+".png");
			
				dominoimg2.setImage(img);
				dominoimg21.setImage(img2);
				Jeu.dominosGUI(dom);
			}
			else if(i == 2) {
				Image img = new Image(typeTuile1+nbCouronnes1+".png");
				Image img2 = new Image(typeTuile2+nbCouronnes2+".png");
			
				dominoimg3.setImage(img);
				dominoimg31.setImage(img2);
				Jeu.dominosGUI(dom);
			}
		}		
	}
	
	public void displayPlayers(int nbPlayers) {
		if(nbPlayers == 2) {
			onplayer3.setVisible(false);
			onplayer4.setVisible(false);
			String nom1 = Jeu.joueurs.get(0).getNomJoueur();
			String nom2 = Jeu.joueurs.get(1).getNomJoueur();			
			String color1 = Jeu.joueurs.get(0).getCouleurJoueur();
			String color2 = Jeu.joueurs.get(1).getCouleurJoueur();
			pseudoplayer1.setText(nom1);
			pseudoplayer2.setText(nom2);
			
			colorplayer1.setFill(getAdaptativeColor(color1));
			colorplayer2.setFill(getAdaptativeColor(color2));
			
			
		}
		else if(nbPlayers == 3) {
			onplayer3.setVisible(true);
			String nom1 = Jeu.joueurs.get(0).getNomJoueur();
			String nom2 = Jeu.joueurs.get(1).getNomJoueur();
			String nom3 = Jeu.joueurs.get(2).getNomJoueur();
			String color1 = Jeu.joueurs.get(0).getCouleurJoueur();
			String color2 = Jeu.joueurs.get(1).getCouleurJoueur();
			String color3 = Jeu.joueurs.get(2).getCouleurJoueur();
			pseudoplayer1.setText(nom1);
			pseudoplayer2.setText(nom2);
			pseudoplayer3.setText(nom3);
			
			
			colorplayer1.setFill(getAdaptativeColor(color1));
			colorplayer2.setFill(getAdaptativeColor(color2));
			colorplayer2.setFill(getAdaptativeColor(color3));
		}
		else {
			onplayer3.setVisible(true);
			onplayer4.setVisible(true);
		
			String nom1 = Jeu.joueurs.get(0).getNomJoueur();
			String nom2 = Jeu.joueurs.get(1).getNomJoueur();
			String nom3 = Jeu.joueurs.get(2).getNomJoueur();
			String nom4 = Jeu.joueurs.get(3).getNomJoueur();
			String color1 = Jeu.joueurs.get(0).getCouleurJoueur();
			String color2 = Jeu.joueurs.get(1).getCouleurJoueur();
			String color3 = Jeu.joueurs.get(2).getCouleurJoueur();
			String color4 = Jeu.joueurs.get(3).getCouleurJoueur();
			pseudoplayer1.setText(nom1);
			pseudoplayer2.setText(nom2);
			pseudoplayer3.setText(nom3);
			pseudoplayer4.setText(nom4);
			
			colorplayer1.setFill(getAdaptativeColor(color1));
			colorplayer2.setFill(getAdaptativeColor(color2));
			colorplayer3.setFill(getAdaptativeColor(color3));
			colorplayer4.setFill(getAdaptativeColor(color4));
			
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

}
