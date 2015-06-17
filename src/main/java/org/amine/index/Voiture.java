package org.amine.index;

import com.google.gson.JsonObject;

import io.searchbox.annotations.JestId;

public class Voiture {
	@JestId
	private int id;
	private String voiture;
	private float MPG;
	private byte cylendres;
	private float vitesseMax;
	private short nbrChauveaux;
	private short poid;
	private float acceleration;
	private short model;
	private String origine;
  
public Voiture(int id,String voiture, float MPG, byte cylendres, float vitesseMax,
		short nbrChauveaux, short poid, float acceleration, short model,
		String origine) {
	
	super();
	this.id=id;
	this.voiture = voiture;
	this.MPG = MPG;
	this.cylendres = cylendres;
	this.vitesseMax = vitesseMax;
	this.nbrChauveaux = nbrChauveaux;
	this.poid = poid;
	this.acceleration = acceleration;
	this.model = model;
	this.origine = origine;
}



public int getId() {
	return id;
}

public String getVoiture() {
	return voiture;
}
public float getMPG() {
	return MPG;
}
public short getCylendres() {
	return cylendres;
}
public float getVitesseMax() {
	return vitesseMax;
}
public short getNbrChauveaux() {
	return nbrChauveaux;
}
public int getPoid() {
	return poid;
}
public float getAcceleration() {
	return acceleration;
}
public short getModel() {
	return model;
}
public String getOrigine() {
	return origine;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return this.id+"|\t"+this.voiture+"|\t"+this.MPG+"|\t"+this.cylendres+"|\t"+
			this.vitesseMax+"|\t"+this.nbrChauveaux+"|\t"+this.poid+"|\t"+
			this.acceleration+"|\t"+this.model+"|\t"+this.origine;
}



public static Voiture convetJsonToVoiture(JsonObject jsonVoiture){
	return new Voiture(jsonVoiture.get("id").getAsInt(),
			jsonVoiture.get("voiture").getAsString(),
			jsonVoiture.get("MPG").getAsFloat(),
			jsonVoiture.get("cylendres").getAsByte(),
			jsonVoiture.get("vitesseMax").getAsFloat(), 
			jsonVoiture.get("nbrChauveaux").getAsShort(),
			jsonVoiture.get("poid").getAsShort(), 
			jsonVoiture.get("acceleration").getAsFloat(),
			jsonVoiture.get("model").getAsShort(),
			jsonVoiture.get("origine").getAsString());
}
}
