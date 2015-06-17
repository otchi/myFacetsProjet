package org.amine.utile;

import com.google.gson.JsonElement;

public interface JsonElementFactory {
	public JsonElementFactory putElements(JsonElementArrayFactory joa);
	public JsonElementFactory putElement(JsonElementFactory jof);
	public JsonElement get();
	public String getName();
	public JsonElement getJsonElement();
}
