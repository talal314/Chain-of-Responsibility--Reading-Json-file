package es.uma.json.cadenaDeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class RescueMedicinePresentations extends DataSelectorChainOfResponsibility {
	
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";


	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";

	private static final String FIELD_SEPARATOR = ";";
	
	public RescueMedicinePresentations(DataSelectorChainOfResponsibility sucesor) {
		super(sucesor);
		// TODO Auto-generated constructor stub
	}

	
	// Parses the contents of a medicine list.
		public StringBuffer readData(String name,JsonReader reader) throws IOException {
			
				
			StringBuffer rescueMedicinePresentationData = new StringBuffer();
			if(name.equals(RESCUEMEDPRES_TAGNAME)) {
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				rescueMedicinePresentationData.append(readEntry(reader)).append("\n");
				reader.endObject();
				
			}
			rescueMedicinePresentationData.append("\n");
			reader.endArray();
	
			
			}
			 else {
			    	if(sucesor !=null) {
			    		rescueMedicinePresentationData=super.readData(name,reader);
			    	}
			    	else {
			    		reader.skipValue();
						System.err.println("Category " + name + " not processed.");
			    	}
			    	
			    }
			return rescueMedicinePresentationData;
		}

		// Parses the contents of a rescue medicine presentation entry
		public String readEntry(JsonReader reader) throws IOException {
			String medRef = null;
			String aiRef = null;
			String inhRef = null;
			String dose = null;
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(MEDREF_FIELD_TAGNAME)) {
					medRef = reader.nextString();
				} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
					aiRef = reader.nextString();
				} else if (name.equals(INHREF_FIELD_TAGNAME)) {
					StringBuffer res = new StringBuffer();
					reader.beginArray();
					while (reader.hasNext()) {
						res.append(reader.nextString()).append(",");
					}
					reader.endArray();
					res.deleteCharAt(res.length() - 1);
					inhRef = new String(res);
				} else if (name.equals(DOSE_FIELD_TAGNAME)) {
					StringBuffer res = new StringBuffer();
					reader.beginArray();
					while (reader.hasNext()) {
						res.append(reader.nextString()).append(",");
					}
					reader.endArray();
					res.deleteCharAt(res.length() - 1);
					dose = new String(res);
				} else {
					reader.skipValue();
				}

			}
			
			return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
		}

	
}
