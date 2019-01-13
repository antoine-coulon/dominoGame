package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Joueur {

	private String nomJoueur;
	private String couleur;
	private int numeroJoueur;
	public List<Domino> listOfDominosPerPlayer = new ArrayList<>();
	public List<Domino> listOfDominosPut = new ArrayList<>();
	private Grille grille; 
	static String sautDeLigne = System.getProperty("line.separator"); 

	public List<Tuile> territoire = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
	public static final int TAILLE_GRILLE=9;
	
	public Joueur(int numeroJoueur, String nomJoueur, String couleur, Grille grille){
		this.nomJoueur = nomJoueur;
		this.numeroJoueur = numeroJoueur;
		this.grille = grille;
	}
	
	public String getNomJoueur(){	// getter nom du joueur
		return this.nomJoueur;
	}
	
	public int getNumeroJoueur(){	// getter numéro du joueur
		return this.numeroJoueur;
	}
	
	public String getCouleurJoueur(){ // getter couleur du joueur
		return this.couleur;
	}
	
	public void setCouleur(String couleur){
		this.couleur = couleur;
	}
	
	public Grille getGrille() {
		return this.grille;
	}
	
	public void getGrilleJoueur() {
		System.out.println(sautDeLigne + "Grille courante appartenant à : " + this.getNomJoueur());
		Grille.displayGrille(this.grille);
	}
	
	public void checkCurrentListOfDominos() {
		for(int i = 0; i < this.listOfDominosPerPlayer.size(); i++) {
			this.listOfDominosPerPlayer.get(i).getNumero();
		}
	}
	
	/* Fonction qui permet à un joueur de piocher un domino puis d'adapter l'ordre de jeu.
	 * En effet, on place le joueur et le domino dans une HashMap puis une fois le tour fini on trie cette HashMap
	 * afin de retourner un ordre de joueur en rapport avec les dominos piochés.
	 */
	
	public void piocheDomino(Domino d) {
		this.listOfDominosPerPlayer.add(d);
		Jeu.handleOrder(this, d);
		Domino.dominosNbJoueurs.remove(d);
		System.out.println(sautDeLigne + "Domino N°" + d.getNumero() + " a été sélectionné!");
	}
	
	/* Fonction d'ajout d'une tuile */
	
	public void addTuile(int x, int y, Tuile t) {
		System.out.println(sautDeLigne + "Ajout de la tuile de coordonnées (x,y) = " + "(" + x + "," +y+")" + sautDeLigne);
		this.grille.putTuile(x, y, t);
	}
	
	// Fonction permettant d'afficher le classement en fin de partie
	public static List<Joueur> classementGame() {
		Map<Joueur, Integer> classement = new HashMap<>();
		List<Joueur> classementFinal = new ArrayList<>();
		for(int i = 0; i < Jeu.joueurs.size(); i++) {
			int score = Jeu.joueurs.get(i).parcoursGrille(Jeu.joueurs.get(i).getGrille());
			System.out.println("Score du joueur " + Jeu.joueurs.get(i).getNomJoueur() + " est : " + score);
			classement.put(Jeu.joueurs.get(i), score);
		}
		
		System.out.println("Les scores ont été récupérés. Calcul du classement ... ");
		
		classement = classement.entrySet().stream()
                .sorted(Map.Entry.<Joueur,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		Iterator it = classement.entrySet().iterator();
		
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        Joueur j = (Joueur) pair.getKey();
	        System.out.println(j.getNomJoueur() + " avec un score de : " + pair.getValue());
	        //changingOrder.add(j);
	        //joueurs.add(j);
	        classementFinal.add(j);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    return classementFinal;
	}
	
	// Fonction permettant de parcourir une grille de jeu en récupérant son score à l'aide d'une grille temporaire
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
	
	
	/* Fonction qui permet de compter les points du territoire d'un joueur */
	
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
	
	
	
	
	
	/* Fonction qui permet à l'utilisateur de placer un domino correctement. 
	 * Tant que le choix n'est pas valide, il ne peut pas le poser.
	 * Une option de passer son tour a été ajoutée, pour permettre à un joueur de piocher un domino qu'il ne voudrait pas utiliser pour son royaume,
	 * mais uniquement le retirer et empêcher à autre joueur de l'obtenir.
	 * 
	 * */
	
	public boolean placerDomino(Domino d) {
		try {
		
			
			this.getGrilleJoueur();
			
			
			boolean isOk = false;
			
			while(!isOk) {
				
				System.out.println("Si vous ne pouvez pas jouer ou que vous vouliez simplement faire de l'anti-jeu, tapez 0 " + sautDeLigne);
				System.out.println("Sinon, tapez un autre nombre");
				
				try{
					int playingChoice = sc.nextInt();
					sc.nextLine();
					if(playingChoice == 0) {
						isOk = false;
						return true;
					}
					else {
						isOk = true;
					}
				} catch(Exception e) {
					
				}
				
				
				
				System.out.println(sautDeLigne + "Sur quelle colonne voulez-vous placer la tuile 1 de votre domino ? ");
				int x = sc.nextInt();
				sc.nextLine();
				x--;
				
				System.out.println(sautDeLigne + "Sur quelle ligne voulez-vous placer la tuile 1 de votre domino ? ");
				int y = sc.nextInt();
				sc.nextLine();
				y--;
				
				if(x < 0 || x > Grille.TAILLE_GRILLE && y < 0 || y > Grille.TAILLE_GRILLE) {
					System.out.println(sautDeLigne + "Saisissez un nombre entre 0 et " + Grille.TAILLE_GRILLE);
					isOk = false;
				}
				else {
					isOk = true;
					int choix = 0;
					
					do {
						System.out.println(sautDeLigne + "Comment voulez-vous placer la tuile 2 ?");
						System.out.println(sautDeLigne + "A droite de la tuile 1 ? Tapez 1");
						System.out.println(sautDeLigne + "En haut de la tuile 1 ? Tapez 2");
						System.out.println(sautDeLigne + "A gauche de la tuile 1 ? Tapez 3");
						System.out.println(sautDeLigne + "En bas de la tuile 1 ? Tapez 4");
						choix = sc.nextInt();
						sc.nextLine();
					} while (choix != 1 && choix !=2 && choix !=3 && choix !=4);
					
				
				
					/* Fonctions permettant de vérifier que le placement du domino :
					 * 1. Se fait bien à un emplacement disponible
					 * 2. Se place bien dans le royaume de taille 5x5
					 * 3. Se place bien au moins à côté d'une tuile adjacente de même type
					 * 
					 * Fonctions imbriquées pour permettre de retourner l'erreur associée à l'une des trois vérifications
					 */
					
						
					if(this.getGrille().verificationTuileVide(x,y,choix)) {
						
						if(this.getGrille().verificationTaille(x, y, choix)) {
						
							if(this.getGrille().verificationTuilesAdjacentes(x, y, choix, d)) {
								addTuile(x, y, d.tuile1);
								if(choix == 1) {
									addTuile(x+1, y, d.tuile2);
									isOk = true;
									return true;
									
								}
								// haut de la premiere tuile
								else if (choix == 2) {
									addTuile(x, y-1, d.tuile2);
									isOk = true;
									return true;
								}
								// gauche de la premiere tuile
								else if (choix == 3) {
									addTuile(x-1, y, d.tuile2);
									isOk = true;
									return true;
								}
								// bas de la premiere tuile
								else if (choix == 4) {
									addTuile(x, y+1, d.tuile2);
									isOk = true;
									return true;
								}
								isOk = true;
								return true;
							} else {
								System.err.println(sautDeLigne + "Placement refusé. Le domino doit avoir au moins une tuile adjacente de même type.");
								isOk = false;
							}
							
						} else {
							System.err.println(sautDeLigne + "Placement refusé. Le domino doit être placé dans un royaume de taille 5x5. Veuillez recommencer");
							isOk = false;
						}
					
					} else {
						System.err.println(sautDeLigne + "Placement invalide. Veuillez recommencer");
						isOk = false;
					}
				}
				}
					
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/* Fonction correspondant à "placerDomino" ci-dessus mais adaptée pour la classe JoueurTest 
	 * 
	 * FONCTION DE TEST
	 * 
	 * 
	 * */ 
	
	public boolean placerDominoXY(Domino d, int x, int y, int choix) {
		x=x-1;
		y=y-1;
		try {
			if(this.getGrille().verifTuile(x, y, this) == true) {
				// vérification pour tuile1 et tuile2
				
				if(this.getGrille().verificationTuileVide(x,y,choix) == true && this.getGrille().verificationTaille(x, y, choix) == true && this.getGrille().verificationTuilesAdjacentes(x, y, choix, d) ) {
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