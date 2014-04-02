package com.hasebly;



import com.hasebly.GetPaymentDetailsDialog.PaymentDetails;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class CardNotPresentData extends CardData<String> implements GetPaymentDetailsDialog.GetPaymentDetailsDialogListener{
	   
	    
		String email;
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}




	String cvv;
	
	

	public CardNotPresentData() {
	}
	
	public CardNotPresentData(String expiryDate, String pan, String name, String cvv){
		super(expiryDate,pan,name);
		setCvv(cvv);
	}
	 public static void showEditDialog(FragmentActivity d) {
	    	//FragmentActivity d =(FragmentActivity)this;
	        FragmentManager fm = d.getSupportFragmentManager();
	        GetPaymentDetailsDialog editNameDialog = new GetPaymentDetailsDialog();
	        editNameDialog.show(fm, "dlg_edit_name");
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
	public void onDialogPositiveClick(DialogFragment dialog ,PaymentDetails fields) {
		// TODO Auto-generated method stub

   	   
   	   this.setName(fields.name.getText().toString());
   	this.setPan(fields.pan.getText().toString());
   	this.setExpiryDate(fields.expdate.getText().toString());
   	this.setCvv(fields.cvv.getText().toString());
   	this.setEmail(fields.email.getText().toString());
   	
	}



	@Override
	public void onDialogNegativeClick(DialogFragment dialog ,PaymentDetails fields) {
		// TODO Auto-generated method stub
		
	}
	

}
