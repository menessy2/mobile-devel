package com.example.whatsapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Chat extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		List<InstantMessage> messages =new ArrayList<InstantMessage>();
		InstantMessage test = new InstantMessage (InstantMessage.ME,"testingg");
	  //adapter = new DiscussArrayAdapter(getApplicationContext(), R.layout.listitem_discuss);

		
		for ( int i=0 ; i<=12 ; i++)
		{
			messages.add( new InstantMessage (i%2 , String.valueOf(i)));
		}
		
		final ChatArrayAdapter adapter = new ChatArrayAdapter(getApplicationContext(), R.layout.chatelement,messages);
		ListView chatList = (ListView)findViewById(R.id.chatlist);
		chatList.setAdapter(adapter);
		final EditText messageToSend = (EditText)findViewById(R.id.textInput);
		 Button send = (Button) findViewById(R.id.btnSend);
		 send.setOnClickListener( new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				InstantMessage im = new InstantMessage(1,messageToSend.getText().toString());
				adapter.add(im);
				messageToSend.setText(" ");
				
			}});
		//test.who=InstantMessage.other;
		//for ( int i=0 ; i<=12 ; i++)
		//{
			adapter.add(test);
		//}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}

}
