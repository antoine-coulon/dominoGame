package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Jeu {
	
	static Domino dom = new Domino();
	
	public static List<Joueur> joueurs = new ArrayList<>();
	public static List<Joueur> changingOrder = new ArrayList<>();
	public static List<Grille> grilles = new ArrayList<>();
	public static ArrayList<String> co = new ArrayList<>();
	public static List<Domino> dominosFromGui = new ArrayList<>();
	public static Map<Integer, Domino> doms = new HashMap<>();
	public static Map<Joueur, Integer> orderSet = new HashMap<>();
	
	public static List<Joueur> allPlayersWholeGame = new ArrayList<>();
	

	static String sautDeLigne = System.getProperty("line.separator"); 

	
	
	
	
	public static Domino getDominoGUI(int index) {
		Domino d = doms.get(index);
		doms.remove(index);
		dominosFromGui.remove(d);
		System.out.println("DOMINO RETIRÉ : " + d.getNumero());
		return d;
	}
	public static void dominosGUI(Domino dom) {
		doms.put(dom.getNumero(), dom);
		dominosFromGui.add(dom);
	}
	
	
	public static void shufflePlayers() {
		Collections.shuffle(joueurs);
		displayArrayListGeneric(joueurs);
	}
	
	public static void displayArrayListGeneric(List<Joueur> joueurs){
		for(int i =0 ; i < joueurs.size(); i++){
			System.out.println(joueurs.get(i).getNomJoueur());
		}
	}
	
	public static void displayArrayList(ArrayList<Domino> dom){
		
		for(int i =0 ; i < dom.size(); i++){
		
			System.out.println(dom.get(i).getNumero() + " | " + dom.get(i).tuile1.typeTuile + " - " + dom.get(i).tuile2.typeTuile);
			System.out.println(sautDeLigne);
		}
	}
	
	
	public static boolean couleur(String couleur, Joueur j) throws IOException{
		
		
		//System.out.println("Nombre de joueurs : " + joueurs.size());
	//	System.out.println("Couleurs disponibles" + sautDeLigne);
		
			try {
					j.setCouleur(couleur);
					co.remove(couleur);
					return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
	      
	
	
	}
	
	public static void lancerJeu(int nbJoueurs){
		boolean isOver = false;
		System.out.println("Le jeu va démarrer!" + sautDeLigne);
		System.out.println("Voici les données actuelles du jeu : " + sautDeLigne);
		//getDataGame(joueurs);
		
			int nombreDominos = adaptGame(Jeu.joueurs.size());
			System.err.println("NOMBRE DOMI LNACER JEU : " + nombreDominos);
			if(nombreDominos != 0 && Domino.dominosNbJoueurs.size() != 48 && Domino.dominosNbJoueurs.size() != 36 && Domino.dominosNbJoueurs.size() != 24  ){
			try {
				Domino.initListsOfDominos(nombreDominos);
					//displayArrayList(dominosToPlay);
		
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			}
	}
	
	public static Joueur playerTour() {
		if (Domino.dominosNbJoueurs.size() > 0) {
			System.out.println("Dominos nb joueurs " + Domino.dominosNbJoueurs.size());
			//if(list.size() > 0) {
				//System.out.println("List size " + list.size());
				System.out.println("Changing size :" + changingOrder.size());
				
				if(joueurs.size() > 0) {
					// Tour suivant
					System.out.println("First round pick " + joueurs.size());
					Joueur j = joueurs.get(0);
					//joueurs.remove(j);
					return j;
				}
				else {
					// Tour suivant
					changeOrder();
					System.out.println("Change order TOUR SUIVANT : " + changingOrder.size());
					if(changingOrder.size() > 0) {
						System.out.println("Changing ordrer " + changingOrder.size());
						Joueur j = changingOrder.get(0);
						changingOrder.remove(j);
						return j;
					} else {
						System.err.println("CHANGING ORDER");
					}
				}
			}
			return null;
		//}
		//return null;
	}
	
	public static void handleOrder(Joueur j, Domino d) {
		
		/* Dans le cas où le nombre de joueurs = 2 , il faut gérer l'ajout de 4 dominos dans la hashmap
		 * 
		 * Or les clés de même valeur (ici les joueurs) seront écrasées et remplacées par les nouvelles
		 * Quand un joueur jouera son deuxième domino, il ecrasera l'ancien et pour gérer les tours il nous faut toutes les informations du tour
		 * 
		 * Donc on garde le domino avec le numero le plus petit pour chaque joueur
		 * Ainsi, celui qui a le plus petit nombre parmi les dominos jouera en premier
		 * 
		 */
		if(joueurs.size() == 2) {
			if(orderSet.containsKey(j)) {
				//Le joueur est déjà dans la HashMap -> il a déjà joué lors de ce tour
				int numero = orderSet.get(j);
				if(d.getNumero() < numero) {
					// si Le nouveau numéro du domino choisi est plus petit que l'ancien: on écrase l'ancien value 
					orderSet.put(j, d.getNumero());
				}
			} else {
				orderSet.put(j, d.getNumero());
			}
		} else {
			System.out.println("Pas dans le cas avec deux joueurs");
			orderSet.put(j, d.getNumero());
		}
		
		//System.out.println("Données joueurs et domino ajouté à l'orderSet");
	}
	
	public static void changeOrder() {
		
		System.out.println("Trying to change order after the tour..");
		
		System.out.println("Taille de l'order set : " + orderSet.size());
		orderSet = orderSet.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		
		Iterator it = orderSet.entrySet().iterator();
		
		
		/* Cas où il y a deux joueurs */
		
		
			joueurs.clear();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        
		        Joueur j = (Joueur) pair.getKey();
		        System.out.println("On parcourt les joueurs qui ont joué : ");
		      // System.out.println(j.getNomJoueur() + " " + j.getCouleurJoueur());
		        changingOrder.add(j);
		        joueurs.add(j);
		       // System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		
		
		
	}
	
	public static List<Domino> getDominos() {
		
			System.out.println("Nombre de dominos restant : " + Domino.dominosNbJoueurs.size() + sautDeLigne);
			
			int nombreDominos = adaptGame(allPlayersWholeGame.size());
			ArrayList<Domino> dominosToPlay = Domino.pickRandomsDominos(nombreDominos);
		
			displayArrayList(dominosToPlay);
			System.out.println("Les dominos sont posés. Chaque joueur en choisit un qui lui convient");
			return dominosToPlay;
	}
	
	
	public static int adaptGame(int nbJoueurs){
		if(nbJoueurs == 2){
			return 24;
		}
		else if(nbJoueurs == 3){
			return 36;
		}
		else if(nbJoueurs == 4){
			return 48;
		}
		else{
			return 0;
		}
	}
	
	public List<Joueur> classementGame(){
		return null;
		
	}
	
	public static void fillColor() {
		co.add("bleu");
		System.out.println("vleu");
		co.add("jaune");
		System.out.println("jaune");
		co.add("rouge");
		System.out.println("rouge");
		co.add("vert");
		System.out.println("vert");
	}


	public static void setupOrder(Joueur currentJoueur, Domino dom2) {
		orderSet.put(currentJoueur, dom2.getNumero());
		changingOrder.add(currentJoueur);
		
	}
	
	
}