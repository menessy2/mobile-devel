package com.hasebly;

public class CardNotPresentData extends CardData<String>{

	String cvv;
	

	public CardNotPresentData() {
	}
	
	public CardNotPresentData(String expiryDate, String pan, String name, String cvv){
		super(expiryDate,pan,name);
		setCvv(cvv);
	}
	
	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	@Override
	protected String getExpiryFromData(String Data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNameFromData(String Data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPanFromData(String Data) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
