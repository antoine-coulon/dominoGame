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
	
	public void setTypeTuile(String type) {
		this.typeTuile = type;
	}
	
	public int getNbCouronnes(){
		return this.nbCouronnes;
	}
	
	public String toString() {
		return this.getTypeTuile();
	}
	
}
