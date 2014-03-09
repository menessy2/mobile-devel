package com.example.whatsapp;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;


public class ContactsList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts_list);
		
		
		ListView list =(ListView) findViewById(R.id.list);
		ArrayList<HaseblyWhatsappListData> dataList = new ArrayList<HaseblyWhatsappListData>();
		dataList=getContactsData();
		

		ContactsListAdapter adapter = null;
		try {
			adapter = new  ContactsListAdapter(this,dataList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
		Log.i("BEFORE", "<<------------- Before SetAdapter-------------->>");

		list.setAdapter(adapter);

		Log.i("AFTER", "<<------------- After SetAdapter-------------->>");

		
		
		
		
		
//	@SuppressWarnings("deprecation")
	//SimpleCursorAdapter	adapter2 = new SimpleCursorAdapter(this, R.layout.activity_contacts_list , c, columns , views);
	//CursorLoader cr = new CursorLoader(this);	
	list.setAdapter(adapter);
	
	}
	
	
	//----getting contacts
	public ArrayList<HaseblyWhatsappListData> getContactsData()
	{

		Uri allContacts =ContactsContract.Contacts.CONTENT_URI;

	
	
		String[] columns = new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};
	
		// the cursor object will now point to the results of the query 
	// it will point to the data ( ID and HAS_PHONE_NUMBER) of the contact 
	//whose HAS_PHONE_NUMBER = 1
	Cursor c= getContentResolver().query(allContacts ,columns,ContactsContract.Contacts.HAS_PHONE_NUMBER+"=1" , null , null, null);
 
	//populate a list with the retrieved data to be passed to the Adapter
	//along with the phone numbers which are still to retrieved
	ArrayList<HaseblyWhatsappListData> dataList =  null; // data list is an array list that will
	// hold the data that will populate the contact list it will be passed to the adapter 
	dataList =new ArrayList<HaseblyWhatsappListData>();
	JSONObject j = new JSONObject();
	
	if (c.moveToFirst())
	{  System.out.println("looking for phone");
		HaseblyWhatsappListData dataitem =null;
		do{  
			System.out.println("looking for phone1");
			dataitem = new HaseblyWhatsappListData();
			String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
			dataitem.contactName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			//getting the phone number
			int hasPhone = c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
			System.out.println("looking for phone2");
			try {
				if(hasPhone==1)
				{
					Cursor phoneCursor = getContentResolver().
							query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI ,
									null,
									ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"="+contactId,
									null,
									null);
					System.out.println("looking for phone4");
					//dataitem.contactId=
					if (phoneCursor.moveToFirst())
					{System.out.println(phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
					dataitem.contactId=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					phoneCursor.close();
					System.out.println("looking for phone5");
					
					j.accumulate("number", dataitem.contactId);
					
					dataList.add(dataitem);
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			dataitem=null;

		}while(c.moveToNext());
		c.close();

			
	}
	System.out.println(j.toString());
	return dataList;
	}
	//----getting contacts-end
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts_list, menu);
		return true;
	}

}
