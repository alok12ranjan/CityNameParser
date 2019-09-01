package com.hsbc.parser.cityNameParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This is a simple program which will parser the city name which
 * has prefix with letter T based upon reading JSON from the URL.
 * 
 */
public class CityNameParserApp 
{
	static Logger logger = Logger.getLogger(CityNameParserApp.class.getName());
     
    public static void main( String[] args ) throws SecurityException, IOException
    {
    	FileHandler logHandler = new FileHandler("CityName.log");
    	SimpleFormatter formatter = new SimpleFormatter();  
    	logHandler.setFormatter(formatter); 
    	logger.addHandler(logHandler);
        logger.setUseParentHandlers(false);
        try {
			URL jsonUrl = new URL("https://urldefense.proofpoint.com/v2/url?u=https-3A__samples.openweathermap.org_data_2.5_box_city-3Fbbox-3D12-2C32-2C15-2C37-2C10-26appid-3Db6907d289e10d714a6e88b30761fae22&d=DwMGaQ&c=_EdSgJoS8igo01XnekBu_azVXoUPxJkwz9O2AzwhBbE&r=yGXramQpQzxL8vCRHR8gA2S69e5mC4SqN0XAMp-SsBRr_PPAHpEDDfISSdDZ_Ufb&m=GVUYIKHm6zzG5Raw7ufiEE0E-XUDUhKhr8-ufwstAXU&s=Ns7yqCzGm8FL1ZSHcARuWG8WGHgUY6ayET2rqBduXnA&e=");
			BufferedReader br = new BufferedReader(new InputStreamReader(jsonUrl.openStream()));
			String cityListData = "";
			while (null != (cityListData = br.readLine())) {
				JSONObject cityListObj = new JSONObject(cityListData);
		        JSONArray arr = cityListObj.getJSONArray("list");
		        for (int i = 0; i < arr.length(); i++) {
		        	JSONObject cityObj = arr.getJSONObject(i);
		            String cityName = cityObj.getString("name");
		            if(cityName.trim().toLowerCase().startsWith("t")) {
		            	logger.info(cityObj.toString());
		            }
		        }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}
