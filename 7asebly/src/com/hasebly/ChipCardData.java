package com.hasebly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChipCardData extends CardPresentData<String>{

	
	private static final String TRACK_TWO_TAG = "57"; 
	private static final String PAN_TAG = "5A"; 
	private static final String NAME_TAG = "5F 20"; 
	private static final String EXPIRY_DATE_TAG = "5F 24"; 
	
	private ArrayList<RApduData> rawData;
	protected Map<String, StorageApdu> data;
	
	
	private static final String[] SINGLE_TAGS = {"81","94","4F","82","50","5A","87","61","89","8A","8C","8D","8E","8F","83","84",
												"9D","73","70","A5","6F","91","42","90","92","86","71","72","80","88","93","95",
												"57","98","97","9A","99","9B","9C"};
	
	private static final String[] DOUBLE_TAGS = {"9F 01", "9F 40", "9F 02", "9F 04", "9F 03","9F 3A","9F 26"," 9F 42","9F 44",
												"9F 05", "5F 25", "5F 24", "9F 06", "9F 12", "5F 34", "9F 3B", "9F 43", "9F 36",
												"9F 07", "9F 08","9F 09", "5F 54", "5F 20", " 5F 0B", "9F 34", "9F 22", "9F 27",
												"9F 45", "9F 49","BF 0C", "9F 4C", "9F 2D", "9F 2E", "9F 2F", "9F 46", "9F 47",
												"9F 48", "9F 1E", "5F 53", "9F 0D", "9F 0E", "9F 0F", "9F 10", "9F 11", "5F 28",
												"5F 55", " 5F 56", "9F 32", "9F 18", "5F 50", "5F 2D", "9F 13", "9F 4D", "9F 4F",
												"9F 14", "9F 15", "9F 16", "9F 4E", "9F 17", "9F 39", "9F 38", "5F 30", "9F 4B",
												"9F 4A", "9F 33", "9F 1A", "9F 1B","9F 1C", "9F 1D", "9F 35","9F 1F", "9F 20","5F 2A",
												"5F 36", "9F 3C", "9F 3D", "9F 41", "9F 21", "9F 37","9F 23"};

 
	
	public ChipCardData(){
		super();
		rawData = new ArrayList<RApduData>();
		data = new HashMap<String, StorageApdu>();
	}
	
	public ChipCardData(ArrayList<RApduData> rawChipData){
		super();
		rawData = new ArrayList<RApduData>(rawChipData);
		data = new HashMap<String, StorageApdu>();
		setUsingRawChipData();
		setTrackTwo(getTrackTwoFromData(TRACK_TWO_TAG));
		setPan(getPanFromData(PAN_TAG));
		setName(getNameFromData(NAME_TAG));
		setExpiryDate(getExpiryFromData(EXPIRY_DATE_TAG));
	}
	
	public boolean isTagSingle(String tag)
	{
		for(int x = 0; x < SINGLE_TAGS.length; x++)
			if(SINGLE_TAGS[x].equals(tag))
				return true;
		return false;				
	}

	private void setUsingRawChipData() {
		
		for(int x = 0; x < rawData.size(); x++)
		{
			RApduData currentLine = rawData.get(x);
			String tag = "";
			for(int y = 2; y < rawData.size(); y ++)
			{
				ArrayList<Byte> temporaryArrayList = new ArrayList<Byte>();
				tag = currentLine.returnHexValue(y);
				if(isTagSingle(tag))
				{
					y++;
					tag += " " + currentLine.returnHexValue(y);
				}
				y++;
				for(int z = 0; z < currentLine.returnIntValue(y); z++)
				{
					y++;
					temporaryArrayList.add(currentLine.returnByteValue(y));
				}
				StorageApdu toBeEntered = new StorageApdu(temporaryArrayList,tag);
				data.put(tag, toBeEntered);
			}	
		}
			
	}

	@Override
	protected String getExpiryFromData(String Data) {
		String temp = data.get(Data).getStringDataFormat1();
		temp = temp.substring(0,temp.length() - 4);
		return temp;
	}

	@Override
	protected String getNameFromData(String Data) {
		return data.get(Data).getStringDataAsciiFormat();
	}

	@Override
	protected String getPanFromData(String Data) {
		return data.get(Data).getStringDataFormat1();
	}

	@Override
	protected String getTrackTwoFromData(String Data) {
		return null; //need to format how to do THIS
	}
	
	

	
}
