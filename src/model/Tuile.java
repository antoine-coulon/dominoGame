package model;

public class Tuile {

	public int nbCouronnes;
	public String typeTuile;
	
	public Tuile(int nbCouronnes, String typeTuile){
		this.nbCouronnes = nbCouronnes;
		this.typeTuile = typeTuile;
	}
	
	public String getTypeTuile(){
		return this.typeTuile;
	}
	
	public int getNbCouronnes(){
		return this.getNbCouronnes();
	}
	
	public String toString() {
		return this.getTypeTuile();
	}
	
}
