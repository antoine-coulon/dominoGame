package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
	
	static Domino dom = new Domino();
	static String sautDeLigne = System.getProperty("line.separator"); 
	static List<Joueur> joueurs = new ArrayList<>();
	static List<Grille> grilles = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

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
		
		/*for(int j = 0; j < joueurs.size(); j++){
			System.out.println(joueurs.get(j).nomJoueur + " " + joueurs.get(j).numeroJoueur);
		}*/
		System.out.println("Avant lancement de joueur : " + nbJoueurs);
		lancerJeu(nbJoueurs);
		break;
		}
		return joueurs;
	}
	
	public static void lancerJeu(int nbJoueurs){
		boolean isOver = false;
		System.out.println("Le jeu va démarrer!" + sautDeLigne);
		System.out.println("Voici les données actuelles du jeu : " + sautDeLigne);
		//getDataGame(joueurs);
		
			int nombreDominos = adaptGame(nbJoueurs);
			if(nombreDominos != 0){
			try {
				Domino.initListsOfDominos(nombreDominos);
				
				while (Domino.dominosNbJoueurs.size() != 0) {
					System.out.println("Nombre de dominos restant : " + Domino.dominosNbJoueurs.size() + sautDeLigne);
					
					ArrayList<Domino> dominosToPlay = Domino.pickRandomsDominos(nombreDominos);
				
					displayArrayList(dominosToPlay);
					System.out.println("Les dominos sont posés. Chaque joueur en choisit un qui lui convient");
					while(dominosToPlay.size() != 0) {
						for(int i = 0; i < joueurs.size(); i++) {
							System.out.println(sautDeLigne);
							System.out.println(joueurs.get(i).getNomJoueur() + " choisissez votre domino : ");
							System.out.println(sautDeLigne);
							
								try {
									int dominoToPick = sc.nextInt();
									Domino d = dominosToPlay.get(dominoToPick);
									sc.nextLine();
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
						
						}
					
				
					}
				
					//displayArrayList(dominosToPlay);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			}
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
	
	
	public static void main(String[] args) throws Exception {
        initJeu();
    }
	
	
}
