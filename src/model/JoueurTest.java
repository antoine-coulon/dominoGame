package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JoueurTest {

	// D'abord tu dois import le Package JUNIT 
	
	@Test
	void test() {
			Grille g = new Grille("Antoine");
			Grille g1 = new Grille("Zak");
			Joueur j2 = new Joueur(6, "Zak", "bleu", g1);
			Joueur j = new Joueur(1, "Antoine", "vert", g);
			Tuile t1 = new Tuile(0, "Mer");
			Tuile t2 = new Tuile(1, "Mine");
			Tuile t3 = new Tuile(2, "Montagne");
			Tuile t4 = new Tuile(3, "Prairie");
			Domino d = new Domino(25, t1, t2);
			Domino d2 = new Domino(49, t1, t4);
			Domino d3 = new Domino(1, t3, t2); 
			
			
			// assertTrue == vérification que la fonction avec les paramètres donnés retourne bien true
			/* Sinon tu peux aussi écrire :
			 * assert(g.verificationTaille(1,0,2) == true) <=> assertTrue 
			 */
			
			
			
			
			/* 
			 * Recap : 
			 * 
			 * - choix 1 = deuxième tuile située à x+1 de la première
			 * - choix 2 = deuxième tuile située à y-1 de la deuxième
			 * - choix 3 = ......                à x-1 ..............
			 * - choix 4 = ..................... à y+1 ...............
			 *  
			 */
			
			// vérification que pour la grille d'Antoine, le domino xy(1;1) avec le choix == 2
			assertTrue(g.verificationTaille(1, 1, 2));
			assertTrue(g.verificationTaille(4, 5, 3));
			assertTrue(g.verificationTaille(2, 3, 1));
			assertTrue(g.verificationTaille(1, 2, 2));
			
			
			// vérification que chaque grille est bien propre à chaque joueur
			assert(j.getGrille().getNomGrille() == "Antoine");
			
			// assert false marche aussi, tu peux vérifier que cette ligne est fausse -> pas de grille avec le nom "Pierre"
			assertFalse(j2.getGrille().getNomGrille() == "Pierre");
			assert(j2.getGrille().getNomGrille() == "Zak");
			
			// vérification que placer le domino aux coordonnées 2,2 avec le choix 1 retourne bien true
			// or, retourne faux donc surement une erreur dans la fonction -> à voir
			assertTrue(j.placerDominoXY(d, 2, 2, 1));
			assertFalse(j.placerDominoXY(d2, 2, 2, 1));
			assertFalse(j.placerDominoXY(d3, 4,4,1));
			assertTrue(j2.placerDominoXY(d, 2, 2, 1));
			
			
	}

}
