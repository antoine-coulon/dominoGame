package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Joueur {

	private String nomJoueur;
	private String couleur;
	private int numeroJoueur;
	private List<Domino> listOfDominosPerPlayer = new ArrayList<>();
	private Grille grille; 
	static Scanner sc = new Scanner(System.in);
	
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
	
	public boolean canPlayerPutDomino(Domino d, Joueur j) {
		System.out.println("Vous devez d�sormais placer le domino s�lectionn�.");
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
								System.out.println("Tuile " + i + " bien plac�e !");
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
				/*System.out.println("Placement de la premi�re tuile ... " + d.tuile1.getTypeTuile());
				System.out.println(" Quelle colonne ? ");
				int x = sc.nextInt();
				sc.nextLine();
				System.out.println(" Quelle ligne ? ");
				int y = sc.nextInt();
				sc.nextLine();
				System.out.println("Placement de la deuxi�me tuile ... (Celle-ci doit �tre coll�e � la premi�re (haut, droite, gauche, bas) " + d.tuile2.getTypeTuile());
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
			
			System.out.println("Sur quelle ligne voulez-vous placer la tuile 1 de votre domino ? ");
			int y = sc.nextInt();
			sc.nextLine();
			
			
			// On v�rifie si la premi�re tuile peut �tre plac�e sinon on ne continue pas
			if(Grille.verifTuile(x, y, this) == true) {
				
				int choix = 0;
				
				
				System.out.println("Comment voulez-vous placer la tuile 2 ?");
				System.out.println("A droite de la tuile 1 ? Tapez 1");
				System.out.println("En haut de la tuile 1 ? Tapez 2");
				System.out.println("A gauche de la tuile 1 ? Tapez 3");
				System.out.println("En bas de la tuile 1 ? Tapez 4");
				choix = sc.nextInt();
				sc.nextLine();
			
				// v�rification pour tuile1 et tuile2
				if(grille.verificationTuileVide(x,y,choix) == true) {
					addTuile(x, y, d.tuile1);
					if(choix == 1) {
						addTuile(x+1, y, d.tuile2);
						return true;
						
					}
					// haut de la premiere tuile
					else if (choix == 2) {
						addTuile(x, y+1, d.tuile2);
						return true;
					}
					// gauche de la premiere tuile
					else if (choix == 3) {
						addTuile(x-1, y, d.tuile2);
						return true;
					}
					// bas de la premiere tuile
					else if (choix == 4) {
						addTuile(x, y-1, d.tuile2);
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