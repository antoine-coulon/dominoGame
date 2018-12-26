package model;

public class Grille {
	Tuile[][] tableau = new Tuile[9][9];	// Création de la grille
											// tableauleau 2d d'objets vide
	
	Joueur j;
	String nomGrille;
	
	public static final int tailleGrille=9;
	public Grille(String nomGrille) {
		this.tableau[4][4] = new Tuile (0,"Chateau");
		this.nomGrille = nomGrille;
	}
	
	public String getNomGrille() {
		return this.nomGrille;
	}
	
	public void putTuile(int x, int y, Tuile t) {
		this.tableau[y][x] = t;
	}

	public static boolean verifTuile(int x, int y, Joueur j) {
		System.out.print("Vérification de la tuile... : ");
		//j.getGrilleJoueur();
		if(j.getGrille().tableau[x][y] == null) {
			System.out.println("Pour x : " + x + " et y : " + y + " l'endroit est vide");
			return true;
		}
		else {
			System.out.println("Pour x : " + x + " et y : " + y + " l'endroit est occupé");
			return false;
		}
	}
	

	public static void displayGrille(Grille g) {
		
		System.out.println("Displaying the grid : ");
		
		for(int i = 0; i < g.tableau.length; i++) {
			for(int j = 0; j < g.tableau[i].length; j++) {
				if(g.tableau[i][j] == null) {
					//System.out.print(g.tableau[i][j] + " ");
					System.out.print("vide" + "        ");
				}
				else {
					//g.tableau[i][j].getTypeTuile();
					System.out.print(g.tableau[i][j].getTypeTuile() + "        ");
					
				}
				
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/*
	 * Verifier si tuile a coté = meme type
	 * Verifier si il y a de la place
	 * Vérifier si on est dépasse pas la taille de la grille
	 */
	
	
	public boolean verificationTuileVide(int x, int y, int choix) {
		// droite de la premiere tuile
		if(choix == 1) {
			if(tableau[x][y]==null && tableau[x+1][y]==null) {
				return true;
			}
			else {
				return false;
			}
		}
		// haut de la premiere tuile
		else if (choix == 2) {
			if(tableau[x][y]== null && choix==2 && tableau[x][y+1]==null) {
				return true;
			}
			else {
				return false;
			}
		}
		// gauche de la premiere tuile
		else if (choix == 3) {
			if(tableau[x][y]==null && choix==3 && tableau[x-1][y] == null) {
				return true;
			}
			else {
				return false;
			}
		}
		// bas de la premiere tuile
		else if (choix == 4) {
			if(tableau[x][y]==null && choix==4 && tableau[x][y-1]==null) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/*
	public boolean verificationTaille() {
		
		int colonnemin= 10;
		int colonnemax= -1;
		int lignemin= 10;
		int lignemax= -1;
		
		for(int ligne=0;ligne<tailleGrille;ligne++) {
			for(int colonne=0;colonne<tailleGrille;colonne++) {
				if(tableau[colonne][ligne] != null) {
					if(colonne>colonnemax) {
						colonnemax=colonne;
					}
					if (colonne<colonnemin) {
						colonnemin=colonne;
					}
				}
			}
		}
		//Gérer le cas où pas de domino placé, c a d ou min = 10 et max = -1
		if (colonnemax-colonnemin >=5) {
			return false;
		}
		
		for(int colonne =0;colonne<tailleGrille;colonne++) {
			for (int ligne=0;ligne<tailleGrille;ligne++) {
				if (tableau[colonne][ligne] != null) {
					if (ligne>lignemax) {
						lignemax=ligne;
					}
					if (ligne<lignemin) {
						lignemin=ligne;
					}
				}
			}
		}
		
		if (lignemax-lignemin >=5) {
			return false;
		}
		return true;
	}

	*/
	
	public boolean verificationTaille() { // Fonction permettant de vérifier si on ne dépasse pas 5*5 de taille de grille de jeu
		int top=0;
		int bottom=0;
		int left=0;
		int right=0;
		
		for(int colonne=0;colonne<tailleGrille;colonne++) {
			for(int ligne=0;ligne<tailleGrille;ligne++) {
				if(tableau[colonne][ligne]!=null) {
					top=ligne;
					break;
				}
			}
			for(int ligne=tailleGrille-1;ligne>=0;ligne--) {
				if(tableau[colonne][ligne]!=null) {
					bottom=ligne;
					break;
				}
			}
			if(bottom-top >=5) {
				return false;
			}
		}
		
		
		for(int ligne=0;ligne<tailleGrille;ligne++) {
			for(int colonne=0;colonne<tailleGrille;colonne++) {
				if(tableau[colonne][ligne]!=null) {
					left=ligne;
					break;
				}
			}
			
			for(int colonne=tailleGrille-1;colonne>=0;colonne--) {
				if(tableau[colonne][ligne]!=null) {
					right=ligne;
					break;
				}
			}
			
			if(right-left >=5) {
				return false;
			}
		}
		
		return true;
	}
	
}