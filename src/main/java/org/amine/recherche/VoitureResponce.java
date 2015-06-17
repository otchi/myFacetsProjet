package org.amine.recherche;

import java.util.ArrayList;
import java.util.Iterator;

import org.amine.facets.VoitureFacet;
import org.amine.index.Voiture;

import com.google.gson.JsonObject;

	public class VoitureResponce{
	private int count;
	private ArrayList<Voiture> responses=new ArrayList<Voiture>();
	private VoitureFacet myFacet;
	public VoitureResponce(int count, ArrayList<Voiture> responses) {
		super();
		this.count = count;
		this.responses = responses;
	}
	public int getCount() {
		return count;
	}
	public Iterator<Voiture> getResponses() {
		return responses.iterator();
	}

	public VoitureFacet getMyFacet() {
		return myFacet;
	}
	
	public void putFacets(JsonObject jsonFacet,String name){
		this.myFacet=new VoitureFacet(jsonFacet, name);
	}
}
