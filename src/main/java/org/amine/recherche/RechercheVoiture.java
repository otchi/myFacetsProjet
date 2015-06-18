package org.amine.recherche;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.amine.facets.VoitureFacet;
import org.amine.facets.VoitureFacet.Term;
import org.amine.index.ElasticClient;
import org.amine.index.Voiture;
import org.amine.utile.JsonUtile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class RechercheVoiture {
	private static String INDEX="vehicule";
	private JestClient client;
	private ElasticQuery eQuery;
	
	public RechercheVoiture(String nodeURL){
		this.client=ElasticClient.getElasticClient(nodeURL).getClient();
		eQuery=new ElasticQuery();
	}
	
	public VoitureResponce getResults(String facet) throws IOException{
		System.out.println("************************************************************************************************\n"
				+ "*********************************************************************************************************");
		System.out.println("-----------------------------------------------request--------------------------------------------------------");
		System.out.println(this.eQuery.getJsonObject().toString()+"\n\n\n");
		
		Search search = (Search) new Search.Builder(this.eQuery
														.getJsonObject()
														.toString()
													)
								// multiple index or types can be added!!! cool
        									.addIndex(INDEX)
        									.build();

		JestResult result = this.client.execute(search);
		
		
		System.out.println("-----------------------------------------------response--------------------------------------------------------");
		System.out.println(result.getJsonString()+"\n\n\n");

		
		JsonArray arrayHits=JsonUtile.selection("hits,hits", 
												result.getJsonObject())
									 .getAsJsonArray();
		
		Iterator<JsonElement> jIter=arrayHits.iterator();
		ArrayList<Voiture> voitures=new ArrayList<Voiture>();
		;;
		while(jIter.hasNext()){	
			voitures.add(Voiture.convetJsonToVoiture(
													jIter.next()
													.getAsJsonObject().get("_source")
													.getAsJsonObject()
													)
						);
		}
	System.out.println("total-------------->"+JsonUtile.selection("hits,total",result.getJsonObject()));
	System.out.println("************************************************************************************************\n"
			+ "*********************************************************************************************************");
	VoitureResponce voitureResp=  new VoitureResponce(Integer.parseInt(
														result.getJsonObject()
														.get("hits").getAsJsonObject()
														.get("total").getAsString()
											  ),
											  voitures);
	
	if(result.getJsonObject().get("facets")!=null){
		voitureResp.putFacets(
				result.getJsonObject()
					  .get("facets")
					  .getAsJsonObject() ,
					   facet);
	
	}
	return voitureResp;
		//return null;
	
	}
	
	
	public void putPagination(int from, int size
		) throws IOException{
		
		this.eQuery.sort("id", "asc");
		this.eQuery.from(from);
		this.eQuery.size(size);
	}
	
	public void putQuery(HashMap<String, String> fieldsSelect,
							QueryKind queryKind){
		Set<String> keys=fieldsSelect.keySet();
		Iterator<String> keysIter=keys.iterator();
		while(keysIter.hasNext()){
			String key=keysIter.next();
			if(queryKind.equals(QueryKind.MATCH_AND)||queryKind.equals(QueryKind.MATCH_OR))
				this.eQuery.match(key, fieldsSelect.get(key),queryKind.getOperator());
			if(queryKind.equals(QueryKind.PREFIX))
				this.eQuery.prefix(key, fieldsSelect.get(key));
		}
	}
	
	public void putIntervaleFilter(String field,double gle,double lte){
		this.eQuery.filterRange(field,gle, lte);
	}
	public void putTermFilter(String field,String[] values){
		this.eQuery.filterTerm(field, values);
	}
	
	public void putFacetting(String field){
		this.eQuery.termFacetting(field);
	}
/***********************************************************************************************************************/	

/****************************************************************************************************/

	/***********************************************************************************************/
	public static void main(String args[]) throws IOException{
		HashMap<String, String> param=new HashMap<String, String>();
		param.put("voiture", "");
		RechercheVoiture rv=new RechercheVoiture("http://localhost:9200");
		rv.putPagination(20, 20);
		rv.putQuery(param, QueryKind.MATCH_AND);
		rv.putIntervaleFilter("cylendres", 5, 6);
		rv.putFacetting("origine");
		System.out.println(rv.eQuery.getJsonObject());
		VoitureResponce voitureResp=rv.getResults("origine");
		VoitureFacet myFacet=voitureResp.getMyFacet();
		Iterator<Term> facetIter=myFacet.getFacetting().iterator();
		
		while(facetIter.hasNext())System.out.println(facetIter.next().getTerm());
		
	}


}
