package com.example.whatsapp;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ChatArrayAdapter extends ArrayAdapter<InstantMessage> {
	
	//dataMembers:
	private List<InstantMessage> IMs = new ArrayList<InstantMessage>();

	public ChatArrayAdapter(Context context, int textViewResourceId, List<InstantMessage> messages)
	{
		super(context, textViewResourceId);

		this.IMs= messages;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(InstantMessage object) {
		// TODO Auto-generated method stub
		super.add(object);
		IMs.add(object);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
		IMs.clear();
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return IMs.size();
	}

	@Override
	public InstantMessage getItem(int position) {
		// TODO Auto-generated method stub
		return IMs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

		

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.chatelement, null);
		}

		LinearLayout wrapper = (LinearLayout) row.findViewById(R.id.chatElementWrapper);

		InstantMessage message = getItem(position);

		TextView textBody = (TextView) row.findViewById(R.id.chatElementBody);

		textBody.setText(message.money);

		textBody.setBackgroundResource((message.who==0) ? R.drawable.bubble_yellow : R.drawable.bubble_green);
		wrapper.setGravity((message.who==0) ? Gravity.RIGHT: Gravity.LEFT);

		return row;
	}

}
