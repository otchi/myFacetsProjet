package org.amine.utile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonObjectFactory implements JsonElementFactory {
	
	
	private String name=null;
	private JsonObject jsonElement=new JsonObject();
/************************************************************************************/	
	public JsonObjectFactory(String name){
		this.name=name;
		jsonElement=new JsonObject();
	}
	
/***************************************************************************************/
	
	public JsonObjectFactory(String name,JsonObject jso){
			if(name!=null) 
				this.jsonElement=jso;
			else {
				this.jsonElement=jso.get(name).getAsJsonObject();
				this.name=name;
				}
	}
	
/****************************************************************************************/
	
	public JsonElementFactory putElements(JsonElementArrayFactory joa){
		for(JsonElementFactory jof:joa){
			this.jsonElement.add(jof.getName(),jof.getJsonElement());
		}
		return this;
	}
	
/*****************************************************************************************/
	
	public JsonElementFactory putElement(JsonElementFactory jof){
		this.jsonElement.add(jof.getName(),jof.getJsonElement());
		return this;
	}
	
/*****************************************************************************************/
	
	public JsonObjectFactory putProperty(String name,String value){
		jsonElement.addProperty(name, value);
		return this;
	}
	
/****************************************************************************************/
	
	public JsonObjectFactory putProperty(String name,Number value){
		jsonElement.addProperty(name, value);
		return this;
	}
	
/*****************************************************************************************/
	
	public JsonElement get() {
		if(name==null) 
			return this.jsonElement;
		else{
			JsonObject jso=new JsonObject();
			jso.add(this.name, this.jsonElement);
			return jso;
		}
	}
	
/********************************************************************************************/

	public String getName() {
		return name;
	}
	
/**********************************************************************************************/
	public JsonElement getJsonElement() {
		// TODO Auto-generated method stub
		return jsonElement;
	}
	
/**********************************************************************************************/
	/**********************************************************************************/
	/********************************************************************************/
	
	
public static void main(String args[]){
System.out.println(
		new JsonObjectFactory(null)
			.putElement(
			 new JsonObjectFactory("query")
				 .putElement(
				  new JsonObjectFactory("filtered")
				      .putElement(
					   new JsonObjectFactory("query")
						   .putElements(
							new JsonElementArrayFactory()
							    .put(
							    new JsonArrayFactory("cc", 
							    		new JsonArray())).put(new JsonArrayFactory("tt"))))
										)
							).get());
}




		
}
