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
		
			int nombreDominos = adaptGame(nbJoueurs);
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
					System.out.println("TOJOURS PREMIER TOUR");
					System.out.println("First round pick " + joueurs.size());
					Joueur j = joueurs.get(0);
					//joueurs.remove(j);
					return j;
				}
				else {
					System.out.println("TOUR SUIVAAAAAANT");
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
		orderSet.put(j, d.getNumero());
	}
	
	public static void changeOrder() {
		orderSet = orderSet.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		
		Iterator it = orderSet.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        Joueur j = (Joueur) pair.getKey();
	        System.out.println("On parcourt les joueurs qui ont joué : ");
	        System.out.println(j.getNomJoueur() + " " + j.getCouleurJoueur());
	        changingOrder.add(j);
	        joueurs.clear();
	        joueurs.add(j);
	        //System.out.println(pair.getKey() + " = " + pair.getValue());
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
			/*while(dominosToPlay.size() != 0) {
				for(int i = 0; i < joueurs.size(); i++) {
					//System.out.println(sautDeLigne);
					//System.out.println(joueurs.get(i).getNomJoueur() + " choisissez votre domino : ");
					System.out.println(sautDeLigne);
					
						try {
							//int dominoToPick = sc.nextInt();
							Domino d = dominosToPlay.get(dominoToPick);
							//sc.nextLine();
							joueurs.get(i).piocheDomino(d);
							dominosToPlay.remove(d);
							
							joueurs.get(i).canPlayerPutDomino(d, joueurs.get(i));
							System.out.println("Liste des dominos actualisée : ");
							System.out.println(sautDeLigne);
							System.out.println("-------------------------------");
							System.out.println(sautDeLigne);
						
							displayArrayList(dominosToPlay);
						}
						catch(InputMismatchException e) {
							e.printStackTrace();
						}
				
				}*/
			
		
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
	

		/*
	}
	
	public static void initJeu(){
		
		
		
			System.out.println("Bienvenue sur le jeu Domi'Nations ! " + sautDeLigne);
			System.out.println("Pour jouer, 2 à 4 joueurs sont requis." + sautDeLigne);
			System.out.println("Combien de joueurs veulent jouer ?" + sautDeLigne);
		
		
		while(true){
			try{
					int nbJoueurs = sc.nextInt();
					switch(nbJoueurs){
						case 2 : 
							
							System.out.println("Lancement du jeu à 2 joueurs" +sautDeLigne);
							sc.nextLine();
							setJoueurs(nbJoueurs);
							break;
						case 3 :
							System.out.println("Lancement du jeu à 3 joueurs" +sautDeLigne);
							sc.nextLine();
							setJoueurs(nbJoueurs);
							break;
						case 4 :
							System.out.println("Lancement du jeu à 4 joueurs" + sautDeLigne);	
							sc.nextLine();
							setJoueurs(nbJoueurs);
							break;
						default:
							System.err.println("Nombre de joueurs invalide. Recommencez.");
							
							break;
					}	
				
			
			} catch (Exception e){
				e.printStackTrace();
				
				
			} 
			break;
		}
		
			
		
	}
	
	public static List<Joueur> setJoueurs(int nbJoueurs){
		
		while(true){
		for(int i = 1; i <= nbJoueurs; i++){
			
			System.out.println("Saisissez le nom du joueur " + i + sautDeLigne);
			
			try{
				
				String nomJoueur = sc.nextLine();
				Grille grille = new Grille(nomJoueur);
				Joueur joueur = new Joueur(i, nomJoueur, null, grille);
				System.out.println("Joueur " + nomJoueur + " est prêt à jouer!" + sautDeLigne);
				joueurs.add(joueur);
				grilles.add(grille);
				
			}
			catch(Exception e){
				System.out.println("Veuillez saisir un nom valide");
				
				}
		
			
		}
		System.out.println("Avant de commencer, il faut que chaque joueur choisisse une couleur de territoire." + sautDeLigne);
	
		System.out.println("Voici l'ordre de sélection des couleurs, tiré au hasard et sans triche" + sautDeLigne);
		Collections.shuffle(joueurs);
		displayArrayListGeneric(joueurs);
		System.out.println(sautDeLigne);
		while(true){
				System.out.println("Sélectionnez votre couleur parmi les couleurs disponibles : ");
				try{
					couleur(joueurs);
					break;
				}
				catch (IOException e){
					System.err.println("Erreur couleur joueur");
					e.printStackTrace();
				}
		}
		
		//for(int j = 0; j < joueurs.size(); j++){
		//	System.out.println(joueurs.get(j).nomJoueur + " " + joueurs.get(j).numeroJoueur);
		//}
		System.out.println("Avant lancement de joueur : " + nbJoueurs);
		lancerJeu(nbJoueurs);
		break;
		}
		return joueurs;
	}
	
	
	
	
	public static void getDataGame(List<Joueur> joueurs) {
		for(int i =0 ; i < joueurs.size(); i++){
			System.out.println("Joueur n°"+joueurs.get(i).getNumeroJoueur() + " - pseudo : " + joueurs.get(i).getNomJoueur() + " - couleur : " + joueurs.get(i).getCouleurJoueur());
		}
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
	
	
	public static ArrayList<String> couleur(List<Joueur> joueurs) throws IOException{
		ArrayList<String> co = new ArrayList<>();
		co.add("bleu");
		co.add("jaune");
		co.add("rouge");
		co.add("vert");
		
		System.out.println("Nombre de joueurs : " + joueurs.size());
		System.out.println("Couleurs disponibles" + sautDeLigne);
		for(int i =0 ; i < joueurs.size(); i++){
			
			for(int j = 0; j < co.size(); j++){
				System.out.println("couleur : " + co.get(j));
			}
	        System.out.println(joueurs.get(i).getNomJoueur() + " : choisissez votre couleur par son nom.");  
	    
	        
			try {
				String playerInput = sc.nextLine();
				if(co.contains(playerInput)){
					joueurs.get(i).setCouleur(playerInput);
					co.remove(playerInput);
				}
				else{
					System.err.println("Couleur n'existe pas dans la liste");
				}
				
				System.out.println(joueurs.get(i).getNomJoueur() + " a selectionné sa couleur." + sautDeLigne);  
			} catch (Exception e) {
				System.err.println("Vérifiez la saisie de la couleur");
				e.printStackTrace();
			} 
	      
		}
	
		return co;
	}
	
	public void displayArrayListCouleur(ArrayList<String> ar){
		
	}
	
	*/
	
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
	public static void main(String[] args) throws Exception {
       // initJeu();
		
    }


	public static void setupOrder(Joueur currentJoueur, Domino dom2) {
		orderSet.put(currentJoueur, dom2.getNumero());
		changingOrder.add(currentJoueur);
		
	}
	
	
}