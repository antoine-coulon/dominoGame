package consoleJeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Domino;
import model.Grille;
import model.Jeu;
import model.Joueur;

public class mainConsole {


	
	public static Scanner sc = new Scanner(System.in);
	static String sautDeLigne = System.getProperty("line.separator"); 
	public static boolean partieEnCours;
	
	public static void initJeu(){
		
		
		
		System.out.println("Bienvenue sur le jeu Domi'Nations ! " + sautDeLigne);
		System.out.println("Pour jouer, 2 � 4 joueurs sont requis.");
		System.out.println("Combien de joueurs veulent jouer ?" + sautDeLigne);
	
	
		while(partieEnCours==true){
			try{
					int nbJoueurs = sc.nextInt();
					switch(nbJoueurs){
						case 2 : 
							
							System.out.println("Lancement du jeu � 2 joueurs...");
							sc.nextLine();
							setJoueurs(nbJoueurs);
							break;
						case 3 :
							System.out.println("Lancement du jeu � 3 joueurs...");
							sc.nextLine();
							setJoueurs(nbJoueurs);
							break;
						case 4 :
							System.out.println("Lancement du jeu � 4 joueurs..." );	
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
			
			System.out.println("Saisissez le nom du joueur " + i);
			
			try{
				
				String nomJoueur = sc.nextLine();
				Grille grille = new Grille(nomJoueur);
				Joueur joueur = new Joueur(i, nomJoueur, null, grille);
				System.out.println("Joueur " + nomJoueur + " est pr�t � jouer!" + sautDeLigne);
				Jeu.joueurs.add(joueur);
				//grilles.add(grille);
				
			}
			catch(Exception e){
				System.out.println("Veuillez saisir un nom valide");
				
				}
		
			
		}
		System.out.println("Avant de commencer, il faut que chaque joueur choisisse une couleur de territoire." + sautDeLigne);
	
		System.out.println("Voici l'ordre de s�lection des couleurs, tir� au hasard et sans triche" + sautDeLigne);
		Jeu.shufflePlayers();
		System.out.println(sautDeLigne);
		while(true){
				System.out.println("S�lectionnez votre couleur parmi les couleurs disponibles : ");
				try{
					couleur(Jeu.joueurs);
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
		return Jeu.joueurs;
	}
	
	public static void lancerJeu(int nbJoueurs){
		boolean isOver = false;
		System.out.println("Le jeu va d�marrer!" + sautDeLigne);
		System.out.println("Voici les donn�es actuelles du jeu : " + sautDeLigne);
		//getDataGame(joueurs);
		
			int nombreDominos = adaptGame(nbJoueurs);
			if(nombreDominos != 0){
			try {
				Domino.initListsOfDominos(nombreDominos);
				
				while (Domino.dominosNbJoueurs.size() != 0) {
					System.out.println("Nombre de dominos restant : " + Domino.dominosNbJoueurs.size() + sautDeLigne);
					
					ArrayList<Domino> dominosToPlay = Domino.pickRandomsDominos(nombreDominos);
				
					displayArrayList(dominosToPlay);
					System.out.println("Les dominos sont pos�s. Chaque joueur en choisit un qui lui convient");
					while(dominosToPlay.size() != 0) {
						for(int i = 0; i < Jeu.joueurs.size(); i++) {
							System.out.println(sautDeLigne);
							System.out.println(Jeu.joueurs.get(i).getNomJoueur() + " choisissez votre domino : ");
							System.out.println(sautDeLigne);
							
								try {
									int dominoToPick = sc.nextInt();
									Domino d = dominosToPlay.get(dominoToPick);
									sc.nextLine();
									Jeu.joueurs.get(i).piocheDomino(d);
									dominosToPlay.remove(d);
									
									Jeu.joueurs.get(i).canPlayerPutDomino(d);
									System.out.println("Liste des dominos actualis�e : ");
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
			System.out.println("Joueur n�"+joueurs.get(i).getNumeroJoueur() + " - pseudo : " + joueurs.get(i).getNomJoueur() + " - couleur : " + joueurs.get(i).getCouleurJoueur());
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
				
				System.out.println(joueurs.get(i).getNomJoueur() + " a selectionn� sa couleur." + sautDeLigne);  
			} catch (Exception e) {
				System.err.println("V�rifiez la saisie de la couleur");
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
