package org.amine.facets;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class VoitureFacet {
	private String name;
	private ArrayList<Term> facetting;
	
	
	public VoitureFacet(JsonObject jsonFacet,String name){
		this.name=name;
		JsonArray terms=jsonFacet.get(name).getAsJsonObject()
								 .get("terms").getAsJsonArray();
		JsonObject term; 
		int size=terms.size();
		facetting=new ArrayList<VoitureFacet.Term>();
		for(int i=0;i<size;i++){
			term=terms.get(i).getAsJsonObject();
			this.facetting.add(new Term(
										 term.get("term").getAsString(), 
										 term.get("count").getAsInt())
								);
		}
	}
	
	public ArrayList<Term> getFacetting(){
		return this.facetting;
	}
	public String getName() {
		return name;
	}
	
	



/********************************************************************************************************************/

	public class Term{
		private String term;
		private int count;
		private boolean isCheked;
		
		public Term(String term,int count){
			this.term=term;
			this.count=count;
			this.isCheked=true;
					
		}

		public String getTerm() {
			return term;
		}

		public int getCount() {
			return count;
		}

		public boolean getIsCheked() {
			return isCheked;
		}

		public void setCheked(boolean isCheked) {
			this.isCheked = isCheked;
		}
	}
	
	public static void main(String args[]){
		JsonParser parse=new JsonParser(); 
		JsonObject jso=parse.parse( "{"
		    +  " \"origine\": {"
	        +" \"_type\": \"terms\","
	        +" \"missing\": 0,"
	        +" \"total\": 152,"
	        +" \"other\": 0,"
	        +" \"terms\": ["
	         +   "{"
	          +    " \"term\": \"us\","
	           +    "\"count\": 110"
	            +"},"
	            +"{"
	             +  "\"term\": \"japan\","
	             +  "\"count\": 34"
	           +" }"
	          
	         +"]"
	     +" }}").getAsJsonObject();
		VoitureFacet myFacets=new VoitureFacet(jso,"origine");
		Iterator<Term> facetIter=myFacets.getFacetting().iterator();
		while(facetIter.hasNext()){
			Term t=facetIter.next();
			System.out.println(t.getTerm()+"///"+t.getCount());
		}
		
	}


}
