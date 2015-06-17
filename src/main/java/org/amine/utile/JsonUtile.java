package org.amine.utile;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public final class JsonUtile {
	
	public static JsonElement selection(String query,JsonElement jse){
		String[] items=query.split(",");
		if(items.length==0) return jse;
		int i=0;
		
		JsonElement inter=null;
		if(jse.isJsonObject())inter=jse.getAsJsonObject();
		if(jse.isJsonArray())inter=jse.getAsJsonArray();
		if(jse.isJsonPrimitive()) return null;
		
		while((inter!=null)&&i<items.length){
			if(inter.isJsonObject())
				inter=inter.getAsJsonObject().get(items[i]);
			else
				if(inter.isJsonArray())
					inter=inter.getAsJsonArray().get(Integer.parseInt(items[i]));
				else
					if(inter.isJsonPrimitive()) return null;
			i++;
		};
		
		return inter;
	}
	
	
	
	
	public static void main(String args[]){
		System.out.println("cc");
		JsonParser parse=new JsonParser();
		JsonElement je=parse.parse("{\"query\":"
				+ 		"{"
			 + 			"\"filtered\":{"
		   +				" \"query\":{"
		    +						 "\"bool\": {\"must\": ["
		     + 												 "{\"c\":\"v\"}"
		     +											"]}}}}}");
		
		System.out.println(JsonUtile.selection("query,filtered,query,bool,must,0", je).getAsJsonObject());
	
	}
	
	

}
