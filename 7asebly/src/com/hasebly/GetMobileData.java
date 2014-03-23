package com.hasebly;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class GetMobileData extends DialogFragment {
	public interface AlertListener{
        public void onMobileDialogPositiveClick(DialogFragment dialog,String mobileNumber, String mPin);
    }


    AlertListener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            mListener = (AlertListener) activity;
        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement Code");
        }
    }
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertview = inflater.inflate(R.layout.dialog_get_mobile_data, null);
        final EditText mobilenumber = (EditText) alertview.findViewById(R.id.MobileNoEntry);
        final EditText mPin = (EditText) alertview.findViewById(R.id.MPinInput);        
        builder.setView(alertview)
        
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
          
                        mListener.onMobileDialogPositiveClick(GetMobileData.this, mobilenumber.getText().toString()
                        		, mPin.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });


        return builder.create();

    }


}
