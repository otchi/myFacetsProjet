package org.amine.index;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;

import java.io.IOException;

public class IndexVoitures {
	
    private JestClient client;
    
	public IndexVoitures(String nodeURL){
		this.client=ElasticClient.getElasticClient(nodeURL).getClient();
	}
	public void indexVoitures(String path) throws IOException{
		DataSourceVoitureCSV dsVoitureCSV=new DataSourceVoitureCSV(path);
		Voiture voiture;
		while((voiture=dsVoitureCSV.getNextVoiture())!=null){	
			Index index = new Index.Builder(voiture).index("vehicule").type("voiture").build();
			this.client.execute(index);
		}
	}
	
	
	
	
	
	
	public static void main(String args[]) throws IOException{
	      new IndexVoitures("http://localhost:9200").indexVoitures("C:\\Users\\aoa\\Desktop\\car.csv");
	      System.out.println("cccc");
	}

}
