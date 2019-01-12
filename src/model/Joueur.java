package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Joueur {

	private String nomJoueur;
	private String couleur;
	private int numeroJoueur;
	public List<Domino> listOfDominosPerPlayer = new ArrayList<>();
	public List<Domino> listOfDominosPut = new ArrayList<>();
	private Grille grille; 

	public List<Tuile> territoire = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
	public static final int TAILLE_GRILLE=9;
	
	public Joueur(int numeroJoueur, String nomJoueur, String couleur, Grille grille){
		this.nomJoueur = nomJoueur;
		this.numeroJoueur = numeroJoueur;
		this.grille = grille;
	}
	
	public String getNomJoueur(){
		return this.nomJoueur;
	}
	
	public int getNumeroJoueur(){
		return this.numeroJoueur;
	}
	
	public String getCouleurJoueur(){
		return this.couleur;
	}
	
	public void setCouleur(String couleur){
		this.couleur = couleur;
	}
	
	public Grille getGrille() {
		return this.grille;
	}
	
	public void getGrilleJoueur() {
		System.out.println("Showing the grid of : " + this.getNomJoueur());
		Grille.displayGrille(this.grille);
	}
	
	public void piocheDomino(Domino d) {
		this.listOfDominosPerPlayer.add(d);
		Domino.dominosNbJoueurs.remove(d);
		System.out.println("Domino picked!");
	}
	
	public void checkCurrentListOfDominos() {
		for(int i = 0; i < this.listOfDominosPerPlayer.size(); i++) {
			this.listOfDominosPerPlayer.get(i).getNumero();
		}
	}
	
	public boolean canPlayerPutDomino(Domino d) {
		System.out.println("Vous devez désormais placer le domino sélectionné.");
		System.out.println("Rappel de votre grille courante : ");
		//grille.displayGrille(this.getGrilleJoueur());
		//this.getGrilleJoueur()
		this.getGrilleJoueur();
		if(this.checkPositionInGrid(d) == true) {
			System.out.println("OUI IL PEUT PLACER");
			Grille.displayGrille(this.getGrille());
			return true;
		}
		else {
			System.out.println("NON IL NE PEUT PAS PLACER");
			Grille.displayGrille(this.getGrille());
			return false;
		}
		
	}
	
	public void addTuile(int x, int y, Tuile t) {
		System.out.println("Tuile coord x : "  + x + " y : " +y);
		this.grille.putTuile(x, y, t);
	}
	
	public int parcoursGrille(Grille grille) {
		Grille grilleTemporaire = grille;
		Grille.displayGrille(grilleTemporaire);
		int scoreTerritoire=0;
		int scoreTotal=0;
		for(int i=0;i<TAILLE_GRILLE;i++){
			for(int j=0;j<TAILLE_GRILLE;j++) {
				if(grilleTemporaire.tableau[i][j]!=null) {
					String type=grilleTemporaire.tableau[i][j].getTypeTuile();
					scoreTerritoire = comptagePointsTerritoire(j,i,type, grilleTemporaire);
					territoire.clear();
					scoreTotal=scoreTotal+scoreTerritoire;
				}
			}
		}
		Grille.displayGrille(grilleTemporaire);
		return scoreTotal;
	}
	
	public int comptagePointsTerritoire(int x, int y, String type, Grille grilleTemporaire) {

		if(grilleTemporaire.tableau[y][x].getTypeTuile()!="Chateau") {
			territoire.add(grilleTemporaire.tableau[y][x]);
		}
		
		if (x+1<=TAILLE_GRILLE-1) {
			if (grilleTemporaire.tableau[y][x+1] !=null && grilleTemporaire.tableau[y][x+1].getTypeTuile()==grilleTemporaire.tableau[y][x].getTypeTuile()) {
		//		territoire.add(grilleTemporaire.tableau[y][x]);
				grilleTemporaire.tableau[y][x] = null;

				comptagePointsTerritoire(x+1,y,type,grilleTemporaire);
			}
		}

		if(x-1>=0) {
			if(grilleTemporaire.tableau[y][x-1] != null && grilleTemporaire.tableau[y][x-1].getTypeTuile()==grilleTemporaire.tableau[y][x].getTypeTuile()) {
			//	territoire.add(grilleTemporaire.tableau[y][x]);
				grilleTemporaire.tableau[y][x] = null;

				comptagePointsTerritoire(x-1,y,type,grilleTemporaire);
			}
		}
		
		if(y-1>=0) {
			if(grilleTemporaire.tableau[y-1][x] !=null && grilleTemporaire.tableau[y-1][x].getTypeTuile()==grilleTemporaire.tableau[y][x].getTypeTuile()) {
			//	territoire.add(grilleTemporaire.tableau[y][x]);
				grilleTemporaire.tableau[y][x] = null;

				comptagePointsTerritoire(x,y-1,type,grilleTemporaire);
			}
		}

		if (y+1<=TAILLE_GRILLE-1) {
			if(grilleTemporaire.tableau[y+1][x] != null && grilleTemporaire.tableau[y+1][x].getTypeTuile()==grilleTemporaire.tableau[y][x].getTypeTuile()) {
			//	territoire.add(grilleTemporaire.tableau[y][x]);
				grilleTemporaire.tableau[y][x] = null;
				
				comptagePointsTerritoire(x,y+1,type,grilleTemporaire);
			}
		}
		
		grilleTemporaire.tableau[y][x]= null;

		// Parcourir la liste territoire pour get le nb de couronnes total et le multiplier par size
		int nombreCouronnesTotal=0;
		int nombreCouronnes=0;
		for(int i=0;i<territoire.size();i++) {
			Tuile element = territoire.get(i);
			nombreCouronnes=element.getNbCouronnes();
			nombreCouronnesTotal=nombreCouronnesTotal + nombreCouronnes;
			
		}
		
		int scoreTerritoire=nombreCouronnesTotal*territoire.size();
			return scoreTerritoire;
	}
	
	public boolean checkPositionInGrid(Domino d) {
		boolean isValid = false;
		System.out.println("Placez maintenant votre domino : ");
	
		
				//for(int i = 1; i < Domino.NB_TUILES_IN_DOMINO+1; i++) {
					while(!isValid) {
						try {
							
							if(placerDomino(d) == true) {
								
							}
							isValid = true;
							break;
							/*
							System.out.println("Placement de la tuile " + i + " ... " + d.tuile1.getTypeTuile());
							System.out.println(" Quelle colonne ? ");
							int x = sc.nextInt();
							sc.nextLine();
							System.out.println(" Quelle ligne ? ");
							int y = sc.nextInt();
							sc.nextLine();
							
							
							if(Grille.verifTuile(x, y, this) == true) {
								System.out.println("Tuile " + i + " bien placée !");
								addTuile(x, y, d.getTuileWithIndex(i));
								isValid = true;
								break;
							}
							else {
								isValid = false;
								System.out.println("Recommencez...");
							}*/
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				//}
				return true;
				//hasPlayed = true;
				/*System.out.println("Placement de la première tuile ... " + d.tuile1.getTypeTuile());
				System.out.println(" Quelle colonne ? ");
				int x = sc.nextInt();
				sc.nextLine();
				System.out.println(" Quelle ligne ? ");
				int y = sc.nextInt();
				sc.nextLine();
				System.out.println("Placement de la deuxième tuile ... (Celle-ci doit être collée à la première (haut, droite, gauche, bas) " + d.tuile2.getTypeTuile());
				System.out.println(" Quelle colonne ? ");
				int x1 = sc.nextInt();
				sc.nextLine();
				System.out.println(" Quelle ligne ? ");
				int y1 = sc.nextInt();
				sc.nextLine();
			*/
				

	}
	
	
	
	
	public boolean placerDomino(Domino d) {
		try {
		
			System.out.println("Sur quelle colonne voulez-vous placer la tuile 1 de votre domino ? ");
			int x = sc.nextInt();
			sc.nextLine();
			x--;
			
			System.out.println("Sur quelle ligne voulez-vous placer la tuile 1 de votre domino ? ");
			int y = sc.nextInt();
			sc.nextLine();
			y--;
			
			
			// On vérifie si la première tuile peut être placée sinon on ne continue pas
			if(this.getGrille().verifTuile(x, y, this) == true) {
				
				int choix = 0;
				
				do {
					System.out.println("Comment voulez-vous placer la tuile 2 ?");
					System.out.println("A droite de la tuile 1 ? Tapez 1");
					System.out.println("En haut de la tuile 1 ? Tapez 2");
					System.out.println("A gauche de la tuile 1 ? Tapez 3");
					System.out.println("En bas de la tuile 1 ? Tapez 4");
					choix = sc.nextInt();
					sc.nextLine();
				} while (choix != 1 && choix !=2 && choix !=3 && choix !=4);
				
			
				// vérification pour tuile1 et tuile2
				if(this.getGrille().verificationTuileVide(x,y,choix) == true && this.getGrille().verificationTaille(x, y, choix) == true && this.getGrille().verificationTuilesAdjacentes(x, y, choix, d)) {
					addTuile(x, y, d.tuile1);
					if(choix == 1) {
						addTuile(x+1, y, d.tuile2);
						return true;
						
					}
					// haut de la premiere tuile
					else if (choix == 2) {
						addTuile(x, y-1, d.tuile2);
						return true;
					}
					// gauche de la premiere tuile
					else if (choix == 3) {
						addTuile(x-1, y, d.tuile2);
						return true;
					}
					// bas de la premiere tuile
					else if (choix == 4) {
						addTuile(x, y+1, d.tuile2);
						return true;
					}
					
					return true;
				
				}
				else {
					System.out.println("Not ok");
					return false;
				}
			}
			else {
				System.out.println("Recommencez...");
				return false;
			}
			
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean placerDominoXY(Domino d, int x, int y, int choix) {
		x=x-1;
		y=y-1;
		try {
			if(this.getGrille().verifTuile(x, y, this) == true) {
				// vérification pour tuile1 et tuile2
				
<<<<<<< HEAD
				if(this.getGrille().verificationTuileVide(x,y,choix) == true) {
=======
				if(this.getGrille().verificationTuileVide(x,y,choix) == true && this.getGrille().verificationTaille(x, y, choix) == true && this.getGrille().verificationTuilesAdjacentes(x, y, choix, d) ) {
>>>>>>> origin/master
					addTuile(x, y, d.tuile1);
					if(choix == 1) {
						addTuile(x+1, y, d.tuile2);
						return true;
						
					}
					// haut de la premiere tuile
					else if (choix == 2) {
						addTuile(x, y-1, d.tuile2);
						return true;
					}
					// gauche de la premiere tuile
					else if (choix == 3) {
						addTuile(x-1, y, d.tuile2);
						return true;
					}
					// bas de la premiere tuile
					else if (choix == 4) {
						addTuile(x, y+1, d.tuile2);
						return true;
					}
					
					return true;
				
				}
				else {
					System.out.println("Not ok");
					return false;
				} 
			}
			else {
				System.out.println("Recommencez...");
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}