package com.hasebly;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MagneticCardData extends CardPresentData<String>{
	
	private String trackOne;
	
	public MagneticCardData(){
		super();
	}
	
	public MagneticCardData(String fullTrackData) {
		setUsingFullTrackData(fullTrackData);		
	}

	public void setUsingFullTrackData(String fullTrackData){
		setTrackTwo(getTrackTwoFromData(fullTrackData));
		setPan(getPanFromData(fullTrackData));
		setName(getNameFromData(fullTrackData));
		setExpiryDate(getExpiryFromData(fullTrackData));
		getandsetTrackOneFromData(fullTrackData);
	}
	
	@Override
	protected String getExpiryFromData(String fullTrackData){
		String temoraryDate= "";
		Pattern pattern4 = Pattern.compile(Pattern.quote("^")+  ".*" + Pattern.quote("^")+ "(.*?)" + Pattern.quote("?"));
        Matcher matcher4 = pattern4.matcher(fullTrackData);
        while (matcher4.find()) {
        	temoraryDate+= matcher4.group(1);
        }
        temoraryDate = temoraryDate.substring(0,4);       
        temoraryDate = temoraryDate.trim();        
        return temoraryDate;		
	}

	@Override
	protected String getNameFromData(String fullTrackData) {
		String temporaryName = "";
		Pattern pattern3 = Pattern.compile(Pattern.quote("^") + "(.*?)" + Pattern.quote("^"));
        Matcher matcher3 = pattern3.matcher(fullTrackData);
        while (matcher3.find()) {
        	fullTrackData += matcher3.group(1);
        }		
        return temporaryName;
	}

	@Override
	protected String getPanFromData(String fullTrackData) {
		 String temporaryPan = "";
		 Pattern pattern = Pattern.compile(Pattern.quote("%")+ "(.*?)" + Pattern.quote("^"));
         Matcher matcher = pattern.matcher(fullTrackData);
         while (matcher.find()) {
        	 temporaryPan += matcher.group(1);
         }
         temporaryPan = temporaryPan.trim();
         temporaryPan = temporaryPan.replaceAll("[^\\d.]", "");
         return temporaryPan;
	}

	@Override
	protected String getTrackTwoFromData(String fullTrackData) {
		 String[] tracks = fullTrackData.split("\\?;");
		 return ";" + tracks[1];
	}

	private void getandsetTrackOneFromData(String fullTrackData){
		String[] tracks = fullTrackData.split("\\?;");
		 setTrackOne(tracks [0] + "?;");
	}
	
	public String getTrackOne() {
		return trackOne;
	}

	public void setTrackOne(String trackOne) {
		this.trackOne = trackOne;
	}

}
