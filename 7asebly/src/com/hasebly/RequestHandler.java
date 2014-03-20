package com.hasebly;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestHandler {
	
	private JSONObject responce;
	private boolean sucess;
	private HashMap<String, String> responceVariables;
	
	
	public RequestHandler(JSONObject responce)
	{
		this.responce = responce;
		responceVariables = new HashMap<String, String>();
		formatresponce();
	}



	private void formatresponce() {
		
		if(responce.has("FAILED"))
		{
			sucess = false;
			populateMapFromArray("FAILED");
		}
		else if(responce.has("OK"))
		{
			sucess = true;
			populateMapFromArray("OK");
		}
		else
		{
		// TODO: YALAHWY 	
		}
		
	}

	protected boolean isSuccessful()
	{
		return sucess;
	}
	
	public HashMap<String, String> getResponceVariables() {
		return responceVariables;
	}

	private void populateMapFromArray(String status) {
		
		try {
			JSONArray responcesArray = responce.getJSONArray(status);
			for(int x = 0; x < responcesArray.length(); x++)
			{
				JSONObject tempObject = responcesArray.getJSONObject(x);
				for(Iterator<String> uselessIterator = tempObject.keys(); uselessIterator.hasNext();)
					responceVariables.put(uselessIterator.next(), tempObject.optString(uselessIterator.next()));
			}
			
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
