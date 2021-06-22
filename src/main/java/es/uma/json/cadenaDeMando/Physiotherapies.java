package es.uma.json.cadenaDeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Physiotherapies extends DataSelectorChainOfResponsibility {
	
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	
	private static final String FIELD_SEPARATOR = ";";

	public Physiotherapies(DataSelectorChainOfResponsibility sucesor) {
		super(sucesor);
		// TODO Auto-generated constructor stub
	}
	
	public StringBuffer readData(String name ,JsonReader reader) throws IOException {
		StringBuffer physiotherapiesData = new StringBuffer();
		
		if(name.equals(PHYSIOTHERAPIES_TAGNAME)) {
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				physiotherapiesData.append(readEntry(reader)).append("\n");
				reader.endObject();
				
			}
			physiotherapiesData.append("\n");
			reader.endArray();
		}
		 else {
		    	if(sucesor !=null) {
		    		physiotherapiesData=super.readData(name,reader);
		    	}
		    	else {
		    		reader.skipValue();
					System.err.println("Category " + name + " not processed.");
		    	}
		    	
		    }
		
		return physiotherapiesData;
	}
	
	public String readEntry(JsonReader reader) throws IOException {
		String physiotherapiesName = null;
		String physiotherapiesImage=null;
		
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				physiotherapiesName = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				physiotherapiesImage = reader.nextString();
			}
			else {
				reader.skipValue();
			}
		}
		
		return physiotherapiesName + FIELD_SEPARATOR + physiotherapiesImage + FIELD_SEPARATOR;
	}
	

}
