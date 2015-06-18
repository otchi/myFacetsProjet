package org.amine.recherche;

import org.amine.utile.JsonArrayFactory;
import org.amine.utile.JsonObjectFactory;
import org.amine.utile.JsonUtile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
/*************************** class of construction of JSON request********************************************/
public class ElasticQuery {
	
	private JsonObject jsonObject=new JsonObject();
	

	
	public ElasticQuery(){
		this.jsonObject=new JsonObject();
		this.requestBone();
	}
	
/**************************************************************************************/
	// construction of main part of request shared by request
	private void requestBone(){
		
		JsonObjectFactory jof=(JsonObjectFactory)
				new JsonObjectFactory(null)
					.putElement(
					    new JsonObjectFactory("query")
					    	.putElement(
					    	    new JsonObjectFactory("filtered")
					    	    	.putElement(
					    	    		new JsonObjectFactory("query")
								  )
							.putElement(new JsonObjectFactory("filter")
											.putElement(
												new JsonObjectFactory("bool")
												    .putElement(
												    	new JsonArrayFactory("must"))
											)
										)
							   )
						  )
					.putElement(new JsonObjectFactory("facets")) 
					.putElement(new JsonArrayFactory("sort", new JsonArray()));
		
		jof.putProperty("size", "10")
		   .putProperty("from", "0");
		
		this.jsonObject=jof.get().getAsJsonObject();
		
	}
	
/*****************************************************************************************/
	// type of request match that construct match with words query
	public void match (String field,String value,String operator ){
		
		if(JsonUtile.selection("query,filtered,query,prefix",this.jsonObject)!=null)return;
		JsonUtile.selection("query,filtered,query",this.jsonObject)
								.getAsJsonObject()
								.add("match",
										new JsonObjectFactory(field)
							    			.putProperty("query", value)
							    			.putProperty("zero_terms_query","all")
							    			.putProperty("operator", operator).get());
	}
	
/*********************************************************************************************/
	//type prefix is query who search a field who contains a world contains a prefix
	public void prefix(String field,String prefix){
		if(JsonUtile.selection("query,filtered,query,match",this.jsonObject)!=null)return;
		JsonUtile.selection("query,filtered,query",this.jsonObject)
							.getAsJsonObject()
							.add("prefix",
									new JsonObjectFactory(null)
										.putProperty(field, prefix)
										.getJsonElement());
		
	}
	
/**********************************************************************************************/
	// a clause who specified how to sort results
	public void sort(String by,String order){
		
		this.jsonObject.get("sort").getAsJsonArray().add(
										new JsonObjectFactory(by)
											.putProperty("order", order)
									.get());
	

		
	}
	
/*************************************************************************************/
	// where begin to return result "to do a pagination"
	public void from (int from ){
		
		if(from>=0) this.jsonObject.addProperty("from", from);
		
	}
	
/*******************************************************************************************/
	// what is a size of returned result "to do a pagination"
	public void size (int size ){
		
		if(size>0) this.jsonObject.addProperty("size", size);
		
	}
/*******************************************************************************************/	
	// filter a result with range
	public void filterRange(String field,double gle,double lte){
	
		JsonUtile.selection("query,filtered,filter,bool,must", this.jsonObject)
				 .getAsJsonArray()
				 .add(  new JsonObjectFactory("range")
							.putElement( 
									new JsonObjectFactory(field )
										.putProperty("gte", gle)
										.putProperty("lte", lte))
							.get() 
					);
						 
	
	}
/**************************************************************************************/
	public void filterTerm(String field,String[] values ){
		
		JsonArrayFactory jsaf=new JsonArrayFactory(field);
		for(int i=0;i<values.length;i++){
			jsaf.putValue(values[i]);
		}
		JsonUtile.selection("query,filtered,filter,bool,must", this.jsonObject)
		 .getAsJsonArray().add(new JsonObjectFactory("terms").putElement(jsaf).get());
		
		
	}
/***************************************************************************************/
	// product facet with a with a given field
	public void termFacetting(String field) {
		this.jsonObject.get("facets")
					   .getAsJsonObject()
					   .add(field, new JsonObjectFactory(null)
					   					.putElement(
					   						new JsonObjectFactory("terms")
					   							.putProperty("field", field)
					   							.putProperty("size", "10"))
					   					.get()
					   		);
		
	}
	
/*******************************************************************/
	public JsonObject getJsonObject() {
		return jsonObject;
	}
	
/******************************************************************************************/
	/*******************************************************************************/
	
	
	
	
	

	public static void main(String args[]){
		
		ElasticQuery eq=new ElasticQuery();
		
		eq.from(10);
		eq.size(100);
		eq.filterRange("cylendres", 4, 4);
		eq.termFacetting("origine");
		eq.match("voiture","chevrolet","or");
		eq.sort("id", "asc");
		String[] s={"us"};
		eq.filterTerm("origine", s);
		
		System.out.println(	
				eq.getJsonObject());
	}
}
