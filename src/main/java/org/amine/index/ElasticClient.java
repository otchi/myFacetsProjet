package org.amine.index;

import java.util.Hashtable;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

public class ElasticClient {
	private JestClient client;
	private static Hashtable<String, ElasticClient> clients=new Hashtable<String, ElasticClient>();
	
	
	private ElasticClient (String nodeURL ){
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig
		       .Builder(nodeURL)
		       .multiThreaded(true)
		       .build());
		this.client= factory.getObject();
	}
	
	public static ElasticClient getElasticClient(String nodeURL){
		if(clients.containsKey(nodeURL)){
			return(ElasticClient.clients.get(nodeURL));
		}else{
			ElasticClient ec=new ElasticClient(nodeURL);
			clients.put(nodeURL,ec);
			return ec;
		}
	}

	public JestClient getClient() {
		return client;
	}
	

}
