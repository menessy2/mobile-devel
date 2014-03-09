package com.example.whatsapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactsListAdapter extends BaseAdapter {
	
	private Activity callingActivity;
	private List<HaseblyWhatsappListData> itemdata = new ArrayList<HaseblyWhatsappListData>();

	public ContactsListAdapter( Activity callingActivity , ArrayList<HaseblyWhatsappListData> data) {
		// TODO Auto-generated constructor stub
		this.callingActivity=callingActivity;
		this.itemdata = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemdata.size();

	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return itemdata.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View vi = arg1 ;
		System.out.println("inside get view");
		
		try {
			
			if(arg1==null)
			{
				//inflate the view with the properXML
				vi = new View(callingActivity);

			LayoutInflater inflater = (LayoutInflater)callingActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi=inflater.inflate(R.layout.contactlist, null);
			}
			
			//link the HaseblyWhatsappListWidgets object to vi's widgets

			HaseblyWhatsappListWidgets data = new HaseblyWhatsappListWidgets();
			//data.contactPic= (ImageView) vi.findViewById(R.id.contactslist_contactimage);
			data.contactId= (TextView) vi.findViewById(R.id.contactslist_contactid);
			data.contactStatus= (TextView) vi.findViewById(R.id.contactslist_contactstatus);

			//set the values for the widgets;
			data.contactId.setText(itemdata.get(position).contactName);
			data.contactStatus.setText("SHOW ME THE MONEY!");

			
			//return the view
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vi;
	}
	
	
}
