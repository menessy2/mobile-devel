package com.hasebly;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;


public class Signature extends android.support.v4.app.DialogFragment {
	
	public interface AlertListener{
        public void onCodeDialogPositiveClick(DialogFragment dialog,String code);
        public void onCodeDialogNegativeClick(DialogFragment dialog);
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
        View alertview = inflater.inflate(R.layout.dialog_signature, null);
        final GestureOverlayView signature = (GestureOverlayView) alertview.findViewById(R.id.signaturePad);
        builder.setView(alertview)
        


                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	signature.setDrawingCacheEnabled(true);
                        Bitmap image = Bitmap.createBitmap(signature.getDrawingCache());
                        signature.setDrawingCacheEnabled(false);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.PNG, 90, stream);
                        byte [] byte_arr = stream.toByteArray();
                        String image_str = Base64.encodeToString(byte_arr,Base64.DEFAULT);
                        image_str = image_str.replaceAll("\\s", "");
                        image_str = image_str.replaceAll("\\n", "");
                        image_str = image_str.replace("=","*");
                        mListener.onCodeDialogPositiveClick(Signature.this, image_str);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onCodeDialogNegativeClick(Signature.this);
                    }
                });


        return builder.create();

    }

}
