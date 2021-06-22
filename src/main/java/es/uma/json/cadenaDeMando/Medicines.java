package es.uma.json.cadenaDeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Medicines extends DataSelectorChainOfResponsibility{
	
	private static final String MEDICINES_TAGNAME = "medicines";
	
	private static final String NAME_FIELD_TAGNAME = "name";
	
	
	
	public Medicines(DataSelectorChainOfResponsibility sucesor) {
		super(sucesor);	
		
		
	}
	
	
	
	public StringBuffer readData(String name ,JsonReader reader) throws IOException {
		
		StringBuffer medicineData = new StringBuffer();
	  if(name.equals(MEDICINES_TAGNAME)) {
		reader.beginArray();
		while (reader.hasNext()) {	
			reader.beginObject();	
			medicineData.append(readEntry(reader)).append("\n");	
			reader.endObject();
			
			}	
			medicineData.append("\n");	
			reader.endArray();
	    }		
	    else {
	    	if(sucesor !=null) {
	    		medicineData = super.readData(name,reader);
	    	}
	    	else {
	    		reader.skipValue();
				System.err.println("Category " + name + " not processed.");
	    	}
	    	
	    }
		return medicineData;
		
	}

	// Parses the contents of a medicine.
	public String readEntry(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String medName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return medName;
	}

}
