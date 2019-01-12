package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Domino implements Comparable<Domino> {
	
	static List<Domino> tousLesDominos = new ArrayList<>();
	public static List<Domino> dominosNbJoueurs = new ArrayList<>();
	public Tuile tuile1;
	public Tuile tuile2;
	int numeroDomino;
	static final int NB_TUILES_IN_DOMINO = 2;
	
	public Domino() {}
	public Domino(int numeroDomino ,Tuile t1, Tuile t2){
		this.numeroDomino = numeroDomino;
		this.tuile1 = t1;
		this.tuile2 = t2;
	}
	
	public static void initListsOfDominos(int nombreDominos) throws IOException {
		remplirDominoListe();
		pickDominos(nombreDominos);
	}
	
	public static void remplirDominoListe() throws IOException{
		DominoReader dr = new DominoReader();
		//final File file = dr.getResource("dominos.csv");
		//tousLesDominos = dr.readFile(file);
		dr.read("dominos.csv");
	}
	
	/*
	public static void main(String[] args) throws IOException{
		remplirDominoListe();
	}
	*/
	public void remplirDominos(Domino domino){
		//System.out.println("ajouté!");
		Domino.tousLesDominos.add(domino);
		//this.displayListeDominos();
	}
	
	public static void pickDominos(int nbDominos) {
		for(int i = 0; i < nbDominos ; i++) {
			Domino randomDomino = tousLesDominos.get(new Random().nextInt(Domino.tousLesDominos.size()));
			dominosNbJoueurs.add(randomDomino);
			tousLesDominos.remove(randomDomino);
		}
		
	}
	
	public void displayListeDominos(){
		//Collections.shuffle(this.tousLesDominos);
		for(int i = 0; i < Domino.tousLesDominos.size(); i++){
			System.out.println("Domino : " + Domino.tousLesDominos.get(i).getNumero() + ", type Tuile 1 : " + Domino.tousLesDominos.get(i).tuile1.getTypeTuile() + " , nb Couronnes Tuile 1 : " + this.tousLesDominos.get(i).tuile1.nbCouronnes + " , type Tuile 2 : " + this.tousLesDominos.get(i).tuile2.getTypeTuile() + " nb Couronnes Tuile 2 : " + this.tousLesDominos.get(i).tuile2.nbCouronnes);
		
		}
	}

	
	
	public void displayListe(){
		//Collections.shuffle(this.tousLesDominos);
		for(int i = 0; i < dominosNbJoueurs.size(); i++){
			System.out.println("Domino : " + dominosNbJoueurs.get(i).getNumero() + ", type Tuile 1 : " + dominosNbJoueurs.get(i).tuile1.getTypeTuile() + " , nb Couronnes Tuile 1 : " + dominosNbJoueurs.get(i).tuile1.nbCouronnes + " , type Tuile 2 : " + dominosNbJoueurs.get(i).tuile2.getTypeTuile() + " nb Couronnes Tuile 2 : " + dominosNbJoueurs.get(i).tuile2.nbCouronnes);
		
		}
	}

	
	public static ArrayList<Domino> pickRandomsDominos(int nombreDominos){
	
	
				int nbDominosToPick = 0;
				if(nombreDominos == 2){
					nbDominosToPick = 4;
				}
				else if(nombreDominos == 3){
					nbDominosToPick = 3;
				}
				else{
					nbDominosToPick = 4;
				}
				
				
				
				//System.out.println(this.tousLesDominos.size());
				ArrayList<Domino> shuffledDominos = new ArrayList<>();
				for(int i = 0; i < nbDominosToPick ; i++ ){
					if(Domino.dominosNbJoueurs.size() > 0) {
						Domino randomDomino = dominosNbJoueurs.get(new Random().nextInt(Domino.dominosNbJoueurs.size()));
						shuffledDominos.add(randomDomino);
						dominosNbJoueurs.remove(randomDomino);
					} else {
						System.out.println("FINI, NOMBRE DE DOMINOS == 0");
						return null;
					}
					
				}
				
			
				Collections.sort(shuffledDominos);
				/*for(int j = 0; j < shuffledDominos.size() ; j++)
				{
					System.out.println("Numéro : " + shuffledDominos.get(j).numeroDomino + " | Type T1 : " + shuffledDominos.get(j).tuile1.typeTuile + " | Type T2 : " + shuffledDominos.get(j).tuile2.typeTuile);
				}*/
				return shuffledDominos;
		
		 
	}
	
	public int getNumero(){
		return this.numeroDomino;
	}
	
	public Tuile getTuile1() {
		return this.tuile1;
	}
	
	public Tuile getTuile2() {
		return this.tuile2;
	}
	
	public String getTypeTuile1() {
		return this.tuile1.getTypeTuile();
	}
	
	public String getTypeTuile2() {
		return this.tuile2.getTypeTuile();
	}
	
	public Tuile getTuileWithIndex(int index) {
		if(index == 1) {
			return this.tuile1;
		}
		else {
			return this.tuile2;
		}
	}
	
	@Override
	public int compareTo(Domino o) {
		int numero = o.getNumero();
		return this.numeroDomino-numero;
	}
	
	
	
		
	
	
	
}
