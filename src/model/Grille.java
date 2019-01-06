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

	public boolean verifTuile(int x, int y, Joueur j) {
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
	
	public boolean verificationTuileVide(int x, int y, int choix) {
		// droite de la premiere tuile
		if(choix == 1) {
			if(tableau[y][x]==null && tableau[y][x+1]==null) {
				return true;
			}
			else {
				return false;
			}
		}
		// haut de la premiere tuile
		else if (choix == 2) {
			if(tableau[y][x]== null && choix==2 && tableau[y+1][x]==null) {
				return true;
			}
			else {
				return false;
			}
		}
		// gauche de la premiere tuile
		else if (choix == 3) {
			if(tableau[y][x]==null && choix==3 && tableau[y][x-1] == null) {
				return true;
			}
			else {
				return false;
			}
		}
		// bas de la premiere tuile
		else if (choix == 4) {
			if(tableau[y][x]==null && choix==4 && tableau[y-1][x]==null) {
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
	
	public boolean verificationTaille(int x, int y, int choix) { // Fonction qui retourne faux si en posant un domino on dépasse la grille de jeu 5x5	
		int x2=0,y2=0;											// écriture des coordonnées de la deuxième tuile en fonction du choix
		
		if (choix==1){
			x2=x+1;
			y2=y;
		}
		else if (choix==2){
			y2=y-1;
			x2=x;
		}
		else if (choix==3){
			x2=x-1;
			y2=y;
		}
		else if (choix==4){
			x2=x;
			y2=y+1;
		}

		else {
			System.out.println("Erreur !");
		}

		tableau[y][x] = new Tuile(0,"temporaire");			// on place temporairement une tuile pour effectuer nos vérifications
		tableau[y2][x2]= new Tuile(0,"temporaire");

		int colonnemin= 10;
		int colonnemax= -1;
		int lignemin= 10;
		int lignemax= -1;
		

		for(int ligne=0;ligne<tailleGrille;ligne++) {					// on parcourt la grille en récupérant les indices des tuiles aux extrémités
			for(int colonne=0;colonne<tailleGrille;colonne++) {
				if(tableau[ligne][colonne] != null) {
					if(colonne>colonnemax) {
						colonnemax=colonne;
					}
					if (colonne<colonnemin) {
						colonnemin=colonne;
					}
				}
			}
		}

		if (colonnemax-colonnemin >=5) { // si on a un dépassement, la fonction retourne faux
			tableau[y][x]=null;		// on vide les positions temporaires utilisées pour faire notre vérification
			tableau[y2][x2]=null;
			return false;
		}
		
		for(int colonne =0;colonne<tailleGrille;colonne++) {
			for (int ligne=0;ligne<tailleGrille;ligne++) {
				if (tableau[ligne][colonne] != null) {
					if (ligne>lignemax) {
						lignemax=ligne;
					}
					if (ligne<lignemin) {
						lignemin=ligne;
					}
				}
			}
		}
		
		tableau[y][x]=null;		// on vide les positions temporaires utilisées pour faire notre vérification
		tableau[y2][x2]=null;
		
		if (lignemax-lignemin >=5) {
			return false;
		}

		return true;
	}
	
	/*
	 * Fonction permettant de vérifier si au moins une tuile adjacente au domino posé est de même type
	 */
	public boolean verificationTuilesAdjacentes (int x, int y, int choix, Domino domino) {
		int x2=0,y2=0;
		
		if (choix==1) {
			x2=x+1;
			y2=y;
			
			boolean un = tableau[y+1][x].getTypeTuile() == domino.getTypeTuile1();
			boolean deux = tableau[y][x-1].getTypeTuile() == domino.getTypeTuile1();
			boolean trois = tableau[y-1][x].getTypeTuile() == domino.getTypeTuile1();

			boolean quatre = tableau[y2+1][x2].getTypeTuile() == domino.getTypeTuile2();
			boolean cinq = tableau[y2][x2+1].getTypeTuile() == domino.getTypeTuile2();
			boolean six = tableau[y2-1][x2].getTypeTuile() == domino.getTypeTuile2();
			
			if (un==true || deux==true || trois==true || quatre==true || cinq ==true  || six == true) {
				return true;
			}
			
			}
		
		else if (choix==2) {
			y2=y-1;
			x2=x;
			
			boolean un = tableau[y][x-1].getTypeTuile() == domino.getTypeTuile1();
			boolean deux = tableau[y][x+1].getTypeTuile() == domino.getTypeTuile1();
			boolean trois = tableau[y-1][x].getTypeTuile() == domino.getTypeTuile1();

			boolean quatre = tableau[y2][x2-1].getTypeTuile() == domino.getTypeTuile2();
			boolean cinq = tableau[y2][x2+1].getTypeTuile() == domino.getTypeTuile2();
			boolean six = tableau[y2+1][x2].getTypeTuile() == domino.getTypeTuile2();
			
			if (un==true || deux==true || trois==true || quatre==true || cinq ==true  || six == true) {
				return true;
			}
		}
		
		else if (choix==3) {
			x2=x-1;
			y2=y;
			
			boolean un = tableau[y+1][x].getTypeTuile() == domino.getTypeTuile1();
			boolean deux = tableau[y][x+1].getTypeTuile() == domino.getTypeTuile1();
			boolean trois = tableau[y-1][x].getTypeTuile() == domino.getTypeTuile1();

			boolean quatre = tableau[y2+1][x2].getTypeTuile() == domino.getTypeTuile2();
			boolean cinq = tableau[y2][x2-1].getTypeTuile() == domino.getTypeTuile2();
			boolean six = tableau[y2-1][x2].getTypeTuile() == domino.getTypeTuile2();
			
			if (un==true || deux==true || trois==true || quatre==true || cinq ==true  || six == true) {
				return true;
			}
			
		}
		
		else if(choix==4){
			x2=x;
			y2=y+1;
			
			boolean un = tableau[y][x-1].getTypeTuile() == domino.getTypeTuile1();
			boolean deux = tableau[y][x+1].getTypeTuile() == domino.getTypeTuile1();
			boolean trois = tableau[y+1][x].getTypeTuile() == domino.getTypeTuile1();

			boolean quatre = tableau[y2][x2-1].getTypeTuile() == domino.getTypeTuile2();
			boolean cinq = tableau[y2][x2+1].getTypeTuile() == domino.getTypeTuile2();
			boolean six = tableau[y2-1][x2].getTypeTuile() == domino.getTypeTuile2();
			
			if (un==true || deux==true || trois==true || quatre==true || cinq ==true  || six == true) {
				return true;
			}
		}
		else {
			System.out.println("Erreur dans la lecture du choix ! ");
		
		}
		
		return false;
		
	}

}