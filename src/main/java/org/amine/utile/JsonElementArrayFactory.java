package org.amine.utile;

import java.util.ArrayList;

public class JsonElementArrayFactory extends ArrayList<JsonElementFactory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JsonElementArrayFactory put(JsonElementFactory jso){
		this.add(jso);
		return this;
	}
	
}
