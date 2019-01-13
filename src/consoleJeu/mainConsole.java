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
		
		System.out.println(sautDeLigne+ " ----------- DOMI ' NATIONS ----------- " + sautDeLigne);
		
		System.out.println("Bienvenue sur le jeu Domi'Nations ! " + sautDeLigne);
		System.out.println("Pour jouer, 2 à 4 joueurs sont requis.");
		System.out.println("Combien de joueurs veulent jouer ?" + sautDeLigne);
	
		boolean tryInput = false;
		while(!tryInput){
			try{
					int nbJoueurs = sc.nextInt();
					
					switch(nbJoueurs){
						case 2 : 
							
							System.out.println("Lancement du jeu à 2 joueurs..." + sautDeLigne);
							sc.nextLine();
							setJoueurs(nbJoueurs);
							tryInput = true;
							break;
						case 3 :
							System.out.println("Lancement du jeu à 3 joueurs..."+sautDeLigne);
							sc.nextLine();
							setJoueurs(nbJoueurs);
							tryInput = true;
							break;
						case 4 :
							System.out.println("Lancement du jeu à 4 joueurs..." + sautDeLigne);	
							sc.nextLine();
							setJoueurs(nbJoueurs);
							tryInput = true;	
							break;
						default : 
							System.out.println("Nombre de joueurs invalide");
							
				
					}
			
			} catch (InputMismatchException e){
				
				System.out.println("Veuillez saisir un nombre valide (2-3-4)");
				sc.nextLine();
				tryInput = false;
			} 
		
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
				System.out.println("Joueur " + nomJoueur + " est prêt à jouer!" + sautDeLigne);
				Jeu.joueurs.add(joueur);
				//grilles.add(grille);
				
			}
			catch(Exception e){
				System.out.println("Veuillez saisir un nom valide");
				
				}
		
			
		}
		System.out.println("Avant de commencer, il faut que chaque joueur choisisse une couleur de territoire.");
	
		System.out.println("Voici l'ordre de sélection des couleurs, tiré au hasard et sans triche" + sautDeLigne);
		Jeu.shufflePlayers();
		System.out.println(sautDeLigne);
		
		boolean validColor = false;
		while(!validColor){
				System.out.println("Sélectionnez votre couleur parmi les couleurs disponibles : ");
				try{
					couleur(Jeu.joueurs);
					validColor = true;
				}
				catch (Exception e){
					validColor = false;
					System.err.println("Erreur couleur joueur");
				}
		}
		
		/*for(int j = 0; j < joueurs.size(); j++){
			System.out.println(joueurs.get(j).nomJoueur + " " + joueurs.get(j).numeroJoueur);
		}*/
		lancerJeu(nbJoueurs);
		break;
		}
		return Jeu.joueurs;
	}
	
	public static void lancerJeu(int nbJoueurs){
		boolean isOver = false;
		
		System.out.println("Le jeu va démarrer!" + sautDeLigne);
		System.out.println("Voici les données actuelles du jeu : ");
		//getDataGame(joueurs);
		
			int nombreDominos = adaptGame(nbJoueurs);
			if(nombreDominos != 0){
			try {
				Domino.initListsOfDominos(nombreDominos);
				
				while (Domino.dominosNbJoueurs.size() != 0) {
					System.out.println("Nombre de dominos restant : " + Domino.dominosNbJoueurs.size() + sautDeLigne + "--------------------------" + sautDeLigne);
					
					ArrayList<Domino> dominosToPlay = Domino.pickRandomsDominos(nbJoueurs);
				
					displayArrayList(dominosToPlay);
					System.out.println("--------------------------"  + sautDeLigne + "Les dominos sont posés. Chaque joueur en choisit un qui lui convient par sa position dans la liste");
					
			
					
					
					while(dominosToPlay.size() != 0) {
						for(int i = 0; i < Jeu.joueurs.size(); i++) {
							System.out.print(sautDeLigne + "Voici votre royaume actualisé");
							Jeu.joueurs.get(i).getGrilleJoueur();
							System.out.println(Jeu.joueurs.get(i).getNomJoueur() + " choisissez votre domino : ");
							System.out.println(sautDeLigne);
							
								
									boolean isValid = false;
									while(!isValid) {
										try {
											int dominoToPick = sc.nextInt();
											if(dominoToPick == 1 || dominoToPick == 2 || dominoToPick == 3 || dominoToPick == 4) {
											Domino d = dominosToPlay.get(dominoToPick-1);
											
											Jeu.joueurs.get(i).piocheDomino(d);
											dominosToPlay.remove(d);
											
											//Jeu.joueurs.get(i).canPlayerPutDomino(d);
											Jeu.joueurs.get(i).placerDomino(d);
											
											System.out.println(sautDeLigne + "Voici votre royaume actualisé");
											Jeu.joueurs.get(i).getGrilleJoueur();
											
											System.out.println("-------------------------------");
											System.out.println(sautDeLigne + "Liste des dominos actualisée : " + sautDeLigne);

											System.out.println("-------------------------------");
											System.out.println(sautDeLigne);
									
											displayArrayList(dominosToPlay);
											isValid = true;
											} else {
												System.out.println("Saisissez un nombre valide");
												isValid = false;
											}
										}
										catch(Exception e) {
											System.out.println("Veuillez saisir un domino par son index");
											isValid = false;
										}
									}
									
						
						}
					
				
					}
					System.out.println(sautDeLigne + "Passage au tour suivant, tenez vous prêt!" + sautDeLigne);
					Jeu.changeOrder();
					
				
					//displayArrayList(dominosToPlay);
				}
				
				System.out.println("Partie finie! Calculons les points..." + sautDeLigne);
				List<Joueur> classement = Joueur.classementGame();
				for(int i = 0; i < classement.size(); i++) {
					System.out.println(i+1 + ". " + classement.get(i).getNomJoueur());
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
		
			System.out.println(dom.get(i).getNumero() + " | " + dom.get(i).tuile1.typeTuile + " " + dom.get(i).tuile2.getNbCouronnes() + " - " + dom.get(i).tuile2.typeTuile + " " +
			dom.get(i).tuile2.getNbCouronnes() );
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
	        System.out.println(joueurs.get(i).getNomJoueur() + " : choisissez votre couleur par son nom." + sautDeLigne);  
	    
	        boolean nvalidColor = false;
	        while(!nvalidColor) {
	        	try {
					String playerInput = sc.nextLine();
					if(co.contains(playerInput)){
						joueurs.get(i).setCouleur(playerInput);
						co.remove(playerInput);
						nvalidColor = true;
						System.out.println(joueurs.get(i).getNomJoueur() + " a selectionné sa couleur." + sautDeLigne); 
					}
					else{
						nvalidColor = false;
						System.err.println("Cette couleur n'est pas présente dans la liste. Recommencez");
					}
					
					 
				} catch (Exception e) {
					System.err.println("Vérifiez la saisie de la couleur");
					nvalidColor = false;
				} 
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
