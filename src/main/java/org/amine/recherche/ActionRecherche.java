package org.amine.recherche;



import io.searchbox.core.search.facet.TermsFacet.Term;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.amine.index.Voiture;


/**
 * Servlet implementation class ActionRecherche
 */
public class ActionRecherche extends HttpServlet {
	private HttpSession  session ;
	private HttpServletRequest request;
	private boolean newSearch=true;
	private int size=10;
	private QueryKind queryKind;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
/*    public ActionRecherche() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}*/
	public void surf(){
		int from=0;
		newSearch=false;
	    if(session.getAttribute("from")!=null){
	    	if(request.getParameter("next")!=null) from=size;
			if(request.getParameter("last")!=null) from=-size;
			from=(Integer)session.getAttribute("from")+from;
		 }
	    if(from<0)from=0;
	    if(request.getParameter("search")!=null){ 
	    	from=0;
	    	newSearch=true;
	    	
	    	
	    }
	    
	    session.setAttribute("from",from);
	}

	
	public int search(QueryKind queryKind) throws IOException{
		 RechercheVoiture rechercheVoiture=new RechercheVoiture("http://localhost:9200");
		   HashMap<String, String> fieldsSelect=new HashMap<String, String>();
		   
	    if((!newSearch)&&((Integer)session.getAttribute("from")) >= (Integer)session.getAttribute("count"))
	    	session.setAttribute("from", (Integer)session.getAttribute("from")-size);
	    if(request.getParameter("us")!=null) 
	    	System.out.println("++++++!!!!!!!!!!!!!!!!!!!+++++++++++++++"+(request.getParameter("us").equals("on")));
	    fieldsSelect.put("voiture",(request.getParameter("searchByName").toLowerCase()));
	    
	    rechercheVoiture.putPagination((Integer)session.getAttribute("from"), size);
	    rechercheVoiture.putQuery(fieldsSelect, queryKind);
	    
	    
	    if(!request.getParameter("minVitesse").equals("")&&!request.getParameter("maxVitesse").equals("")){
	    	rechercheVoiture.putIntervaleFilter("vitesseMax",
	    			Integer.parseInt(request.getParameter("minVitesse")),
	    			Integer.parseInt(request.getParameter("maxVitesse")));
	    	
	    		request.setAttribute("minVitesse",request.getParameter("minVitesse"));
	    		request.setAttribute("maxVitesse",request.getParameter("maxVitesse"));
	    	
	    }
	    rechercheVoiture.putFacetting("origine");
		
		VoitureResponce voitureResp=rechercheVoiture.getResults("origine");	
		Iterator<Voiture> voitures=voitureResp.getResponses();
		//Iterator<Term> facet=voitureResp.getMyFacet().getFacettingIterator();
		
		if(newSearch){
			session.setAttribute("count",voitureResp.getCount());
		}													
		request.setAttribute("searchByName",request.getParameter("searchByName"));
		request.setAttribute("voitures", voitures);
		
		
		if(this.session.getAttribute("origineFacet")==null){
			this.session.setAttribute("origineFacet",voitureResp.getMyFacet().getFacetting() );
			}else{
			
			}
		
		request.setAttribute("facet", voitureResp.getMyFacet().getFacetting().iterator());
		return voitureResp.getCount();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//if((session.getAttribute("count")==null)) session.setAttribute("count",true);
		this.request=request;
		session = request.getSession();
	    
		if(!request.getParameter("size").equals("")){
		    	size=Integer.parseInt(request.getParameter("size"));
		    	request.setAttribute("size",size);
		 }else {
			size=10;
		}
		this.surf();
		if(newSearch || this.queryKind.equals(QueryKind.MATCH_AND)){
			
			if(this.search(QueryKind.MATCH_AND)>0){
				this.queryKind=QueryKind.MATCH_AND;
				newSearch=false;
			}	
	    }
		if(newSearch || this.queryKind.equals(QueryKind.MATCH_OR)){
			if(this.search(QueryKind.MATCH_OR)>0){
				this.queryKind=QueryKind.MATCH_OR;
				newSearch=false;
			}	
	    }
		if(newSearch || this.queryKind.equals(QueryKind.PREFIX)){
			if(this.search(QueryKind.PREFIX)>0){
				this.queryKind=QueryKind.PREFIX;
				newSearch=false;
			}	;
	    }
	    
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
	


}
