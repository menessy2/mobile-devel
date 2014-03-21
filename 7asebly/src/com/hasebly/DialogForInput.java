package com.example.mylib;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class DialogForInput extends DialogFragment {
	

	private static EditText dataItem1, dataItem2; 
	private static Editable data2_text;
	 private static Editable data1_text;
	 private int requestType;
	 
	 public DialogForInput() {
		 super();
	 }

	
	 
	 
	 
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		System.out.println("inside onDestroy");
		System.out.println(data2_text);
		System.out.println(data1_text);
		//EditText pin = (EditText) findViewById(R.id.pin);
		
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		System.out.println("inside onDetach");
		super.onDetach();
	}

	@ Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		Bundle bundle = this.getArguments();
		requestType=bundle.getInt("requestType");//savedInstanceState.getInt("requestType", 2);   
		
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		    // Get the layout inflater
		    LayoutInflater inflater = getActivity().getLayoutInflater();
		   
		    View view = inflater.inflate(R.layout.dialog, null);
		    dataItem1= (EditText) view.findViewById(R.id.name);
		    dataItem2=(EditText) view.findViewById(R.id.pin);
		    // Inflate and set the layout for the dialog
		    // Pass null as the parent view because its going in the dialog layout
		    
		    //set the texts that appear in the dialog
		    TextView text1 = (TextView) view.findViewById(R.id.dialog_request1_text);
		    TextView text2 = (TextView) view.findViewById(R.id.dialog_request2_text);
		    
		    if (requestType == 1)
		    {
		    	text1.setText("Enter Name");
		    	text2.setText("Enter Pin");
		    	
		    }else if(requestType== 2)
		    {
		    	text1.setText("Enter Email");
		    	text2.setText("Enter CVV");
		    }
		    
		    
		    
		    builder.setView(view)
		    // Add action buttons
		           .setPositiveButton("Sumbit", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		                   // sign in the user ...
		            	   DialogForInput.data2_text = DialogForInput.dataItem2.getText();
		            	   DialogForInput.data1_text = DialogForInput.dataItem1.getText();
		            	   
		            	   
		            	   
		               }
		           })
		           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                   DialogForInput.this.getDialog().cancel();
		               }
		           });      
		    return builder.create();
	}

}