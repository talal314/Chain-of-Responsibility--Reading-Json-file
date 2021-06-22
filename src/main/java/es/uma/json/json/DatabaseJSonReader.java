package es.uma.json.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

import es.uma.json.cadenaDeMando.DataSelectorChainOfResponsibility;
import es.uma.json.cadenaDeMando.Medicines;
import es.uma.json.cadenaDeMando.Physiotherapies;
import es.uma.json.cadenaDeMando.RescueMedicinePresentations;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

	public DatabaseJSonReader() {
	}

	public String parse(String jsonFileName) throws IOException {
		
		Medicines m = new Medicines(null);
		Physiotherapies p = new Physiotherapies(m);
		RescueMedicinePresentations r = new RescueMedicinePresentations(p);
		
		DataSelectorChainOfResponsibility c = new DataSelectorChainOfResponsibility(r);

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));
		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		while (reader.hasNext()) {
			
			String name = reader.nextName();
				
			readData.append(c.readData(name,reader)) ;


		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}

}
