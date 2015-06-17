package org.amine.utile;

import java.util.ArrayList;


public class Pile<E> extends ArrayList<E> implements IPile<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public E depile() {
		// TODO Auto-generated method stub
		E output=null;
		if(!empty()) { output=this.get(0);
				this.remove(0);
		}
		return output;
	}

	public boolean empty() {
		// TODO Auto-generated method stub
	
		return (this.size()!=0)?true:false;
	}

	public void empile(E input) {
		// TODO Auto-generated method stub
		this.add(0, input);
	}

}
