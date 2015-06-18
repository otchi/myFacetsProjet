package org.amine.utile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonArrayFactory implements JsonElementFactory{
	private String name=null;
	private JsonArray jsonElement=new JsonArray();
	
	public JsonArrayFactory(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
		jsonElement=new JsonArray();
	}
	
	public JsonArrayFactory(String name,JsonArray jsa){
		if(name!=null) 
			this.name=name;
		
			this.jsonElement=jsa;
	}
	
	
	

	public JsonElement get() {
		// TODO Auto-generated method stub
		if(name==null) 
			return this.jsonElement;
		else{
			JsonObject jso=new JsonObject();
			jso.add(this.name, this.jsonElement);
			return jso;
		}
	}
	public JsonElementFactory putElements(JsonElementArrayFactory joa) {
		// TODO Auto-generated method stub
			for(JsonElementFactory jof:joa){
				this.jsonElement.add(jof.get());
			}
		return this;
	}

	public JsonElementFactory putElement(JsonElementFactory jof) {
		// TODO Auto-generated method stub
		this.jsonElement.add(jof.get());
		return this;
	}
	
	public JsonElementFactory putValue(String value){
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("", value);
		this.jsonElement.add(jsonObject.get(""));
		return this;
	}
	
	public JsonElement getJsonElement() {
		// TODO Auto-generated method stub
		return this.jsonElement;
	}
	public String getName() {
		return name;
	}

}
