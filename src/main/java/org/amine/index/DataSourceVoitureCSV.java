package org.amine.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataSourceVoitureCSV {
	private BufferedReader br;
	private int id=1;
	public DataSourceVoitureCSV(String path) throws IOException{
		br=new BufferedReader(new FileReader(new File(path)));
		int i=0;
		while(br.readLine()!=null && i++<1){}
		
	}
	
	public Voiture getNextVoiture() throws IOException{
		String voitureStr;
		Voiture voiture=null;
		int i;
		if((voitureStr=br.readLine())!=null){
			i=0; 
			String[] s=voitureStr.split(";");
			voiture=new Voiture(this.id++,s[i++],Float.parseFloat(s[i++]), 
									Byte.parseByte(s[i++]),Float.parseFloat(s[i++]),
									(short)Double.parseDouble(s[i++]),(short)Double.parseDouble(s[i++]),
									Float.parseFloat(s[i++]), Short.parseShort(s[i++]), s[i++]);
		}
		
		return voiture;
	}
	
	public void dataSourceClose() throws IOException{
		br.close();
	}
	
	
	
	public static void main(String args[]) throws IOException{
		DataSourceVoitureCSV dsVoitureCSV=new DataSourceVoitureCSV("C:\\Users\\aoa\\Desktop\\car.csv");
		dsVoitureCSV.getNextVoiture();	
	}
}
