package org.amine.utile;

public interface IPile<T> {
	public T depile();
	public void empile(T input);
	public boolean empty();
}
