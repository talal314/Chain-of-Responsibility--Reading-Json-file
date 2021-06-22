package es.uma.json.cadenaDeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class DataSelectorChainOfResponsibility{

	protected DataSelectorChainOfResponsibility sucesor;
	protected String name;
	
	public DataSelectorChainOfResponsibility(DataSelectorChainOfResponsibility sucesor) {
		this.sucesor=sucesor;
	
	
	}
	
	public StringBuffer readData(String name, JsonReader reader) throws IOException{
		return sucesor.readData(name, reader);
	}
	
	
}
