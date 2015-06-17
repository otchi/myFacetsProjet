package org.amine.recherche;


public enum QueryKind{
	MATCH_OR("match","or"),MATCH_AND("match","and"),PREFIX("prefix","standard");
	private String queryKind;
	private String operator;
	
	public String getOperator() {
		return operator;
	}
	private QueryKind(String queryKind,String operator) {
		this.queryKind=queryKind;
		this.operator=operator;
		// TODO Auto-generated constructor stub
	}
	public String getQueryKind() {
		return queryKind;
	}
}
