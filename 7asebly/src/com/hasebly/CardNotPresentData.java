package com.hasebly;


import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

public class CardNotPresentData extends CardData<String> implements DialogForInput.DialogListenerInterface{
	   
	    private static final String MY_CARDIO_APP_TOKEN = "c7088748a0864365819069184022d4ca";
		final String TAG = getClass().getName();
		String email;
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}


		private static int MY_SCAN_REQUEST_CODE = 100; // arbitrary int


	String cvv;
	
	

	public CardNotPresentData() {
	}
	
	public CardNotPresentData(String expiryDate, String pan, String name, String cvv){
		super(expiryDate,pan,name);
		setCvv(cvv);
	}
	@SuppressLint("NewApi")
	public static void PayWithPan(Activity d)
	
			throws ArrayIndexOutOfBoundsException{ 
			DialogForInput test;
			test= new DialogForInput();
			Bundle args =new Bundle();
			args.putInt("requestType",1);
			test.setArguments(args);
			//Activity d =new Activity();
			 FragmentManager fm = d.getFragmentManager();
			 test.show(fm,"hello");
			 
		}
		
		@SuppressLint("NewApi")
		public static void PayWithCvv(Activity d)
				throws ArrayIndexOutOfBoundsException{ 
			DialogForInput test;
				test= new DialogForInput();
				Bundle args =new Bundle();
				args.putInt("requestType",2);
				
				test.setArguments(args);
				//Activity d =new Activity();
				 FragmentManager fm = d.getFragmentManager();
				 test.show(fm,"hello");
				 
			}
		public static void PayWithCardio(Activity d)
		{
	
		Intent scanIntent = new Intent(d, CardIOActivity.class);

		// required for authentication with card.io
		scanIntent.putExtra(CardIOActivity.EXTRA_APP_TOKEN, MY_CARDIO_APP_TOKEN);

		// customize these values to suit your needs.
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: true
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false

		// hides the manual entry button
		// if set, developers should provide their own manual entry mechanism in the app
		scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false); // default: false

		// MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
		d.startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//super.onActivityResult(requestCode, resultCode, data);

		String resultStr;
		if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
			
			CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

			// Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
			resultStr = "Card Number: " + scanResult.cardNumber + "\n";
		  	this.setPan(scanResult.cardNumber);
			// Do something with the raw number, e.g.:
			// myService.setCardNumber( scanResult.cardNumber );

			if (scanResult.isExpiryValid()) {
				this.setExpiryDate(scanResult.expiryMonth + "/" + scanResult.expiryYear );
			}

			if (scanResult.cvv != null) { 
				// Never log or display a CVV
				this.setCvv(scanResult.cvv);
			}

			
		}
		else {
			resultStr = "Scan was canceled.";
		}

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
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub

   	   
   	   this.setName(DialogForInput.datacontainers.name.getText().toString());
   	this.setPan(DialogForInput.datacontainers.pan.getText().toString());
   	this.setExpiryDate(DialogForInput.datacontainers.expdate.getText().toString());
   	this.setCvv(DialogForInput.datacontainers.cvv.getText().toString());
   	this.setEmail(DialogForInput.datacontainers.email.getText().toString());
   	
	}


	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub

		
	}
	

}
