package com.hasebly;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class DialogForInput extends DialogFragment {
	

	protected class DialogView
	{
		EditText name;
		EditText pan;
		EditText cvv;
		EditText expdate;
		EditText email;
		
	}
	
	
	public interface DialogListenerInterface {
        public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogNegativeClick(DialogFragment dialog);
    }
    
    // Use this instance of the interface to deliver action events
    DialogListenerInterface mListener;
    static DialogView datacontainers ;

    
    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListenerInterface) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DialogListenerInterface");
        }
    }

	

	static EditText dataItem1;
	static EditText dataItem2; 
	static Editable data2_text;
	 static Editable data1_text;
	 private int requestType;
	 
	 public DialogForInput() {
		 super();
	 }

	
	 
	 
	 
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		System.out.println("inside onDestroy");
	
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
		    View view =null;
		    datacontainers = new DialogView();
		    if (requestType == 2)
		    {   // Inflate and set the layout for the dialog
			    // Pass null as the parent view because its going in the dialog layout
			    
		    	 view = inflater.inflate(R.layout.dialog, null);
			    	datacontainers.email= (EditText) view.findViewById(R.id.email);
			    	datacontainers.cvv= (EditText) view.findViewById(R.id.cvv);
				    
		    }
		    else if(requestType== 1)
		    {
		    	view = inflater.inflate(R.layout.dialogforpan, null);
		    	datacontainers.name= (EditText) view.findViewById(R.id.name);
		    	 datacontainers.pan=(EditText) view.findViewById(R.id.Pan);
		    	 datacontainers.cvv=(EditText) view.findViewById(R.id.dialogforpan_cvv);
		    	 datacontainers.expdate=(EditText) view.findViewById(R.id.expdate);
		    }
		   
		    
		    builder.setView(view)
		    // Add action buttons
		           .setPositiveButton("Sumbit", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		                   // sign in the user ...
		            	   mListener.onDialogPositiveClick(DialogForInput.this);
		            	 
		            	   
		            	   
		            	   
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