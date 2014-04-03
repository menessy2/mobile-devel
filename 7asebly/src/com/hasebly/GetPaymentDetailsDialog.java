
package com.hasebly;



import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class GetPaymentDetailsDialog extends DialogFragment implements OnEditorActionListener {
	
	/*this class extends the android.support.v4.app version of DialogFragment this is chosen for backward compatibility
	 * as a result the activity calling this class must extend FragmentActivity NOT Activity 
	 * it overrides the OncreatDialog method specifically to be able to use the functionality that
	 *  comes with positive negative buttons which are parts of "dialog"
	 * -this class defines an interface that is implemented by the calling class to be able to extract
	 *  data when the dialog is closing (user presses submit)
	 * - it harnesses the Card.io library so the application housing this class must include the following activities 
	 * in its manifest as well as the following permissions for this class to function properly
	 *  Activities :
	    <activity android:name="io.card.payment.CardIOActivity" android:configChanges="keyboardHidden|orientation" />
     	<activity android:name="io.card.payment.DataEntryActivity" android:screenOrientation="portrait"/>
     * Permissions:
        <uses-permission android:name="android.permission.INTERNET" />
     	<uses-permission android:name="android.permission.CAMERA" />
     	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     	<uses-permission android:name="android.permission.VIBRATE" />
     * also to use Card.io an App token that is obtained after registration on the cardio website must be provided 
     * as a string in the class, the bellow token was obtained after registration it is essential for cardio to work
 */
	
	    private static final String MY_CARDIO_APP_TOKEN = "c7088748a0864365819069184022d4ca";
		final String TAG = getClass().getName();
		String resultStr;
		private TextView resultTextView;
		private static int MY_SCAN_REQUEST_CODE = 100; // arbitrary int used to link onActivityResult with
		//the calling startActivtyFor Result


    public interface GetPaymentDetailsDialogListener {
    	public void onDialogPositiveClick(DialogFragment dialog ,PaymentDetails fields);
    	public void onDialogNegativeClick(DialogFragment dialog ,PaymentDetails feilds);
    }
    GetPaymentDetailsDialogListener mlistener;
      
    public static void PayWithCardio(FragmentActivity d)
	{

	Intent scanIntent = new Intent(d, CardIOActivity.class);

	// required for authentication with card.io
	scanIntent.putExtra(CardIOActivity.EXTRA_APP_TOKEN, MY_CARDIO_APP_TOKEN);

	// customize these values to suit your needs.
	scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false); // default: true
	scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
	scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false

	// hides the manual entry button
	// if set, developers should provide their own manual entry mechanism in the app
	scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false); // default: false

	// MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
	d.startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
}

public void onActivityResult(int requestCode, int resultCode, Intent data) {
	//super.onActivityResult(requestCode, resultCode, data);

	
	if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) 
	{
		
		CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

		// Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
		resultStr =  scanResult.cardNumber;
		
				
	}
	else {
		resultStr = "Scan was canceled.";
	}
	
	   LayoutInflater inflater = getActivity().getLayoutInflater();
	    final View view =inflater.inflate(R.layout.dialogforpan, null);
	    EditText pan=(EditText)view.findViewById(R.id.edittext_dialog_pan);
	    pan.setText(resultStr);	
}

 void setDataFields (View view ,PaymentDetails dataFields)
 {
	    dataFields.name = (EditText) view.findViewById(R.id.edittext_dialog_name);
	    dataFields.email = (EditText) view.findViewById(R.id.edittext_dialog_email);
	    dataFields.cvv = (EditText) view.findViewById(R.id.edittext_dialog_cvv);
	    dataFields.expdate = (EditText) view.findViewById(R.id.edittext_dialog_expdate);
	    dataFields.pan = (EditText) view.findViewById(R.id.edittext_dialog_pan);
 }
    
    

    @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		  		
    		  
    		  final FragmentActivity callingAct= getActivity();
    		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    		    // Get the layout inflater
    		    LayoutInflater inflater = getActivity().getLayoutInflater();
    		    final View view =inflater.inflate(R.layout.dialogforpan, null);
    		    final PaymentDetails dataFields = new PaymentDetails();
    		    setDataFields(view , dataFields);

    		    builder.setView(view)
    		    // Add action buttons
    		           .setPositiveButton("Sumbit", new DialogInterface.OnClickListener() {
    		               @Override
    		               public void onClick(DialogInterface dialog, int id) {
    		            	  mlistener.onDialogPositiveClick(GetPaymentDetailsDialog.this ,dataFields);
    		            	   
    		            	   
    		            	   
    		            	   
    		               }
    		           })
    		           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		               public void onClick(DialogInterface dialog, int id) {
    		            	   mlistener.onDialogNegativeClick(GetPaymentDetailsDialog.this , dataFields);
    		         		 

    		                   
    		               }
    		           });      
    		    
    		    CheckBox ck = (CheckBox)view.findViewById(R.id.checkBox2);
    		    final LinearLayout ll = (LinearLayout) view.findViewById(R.id.pan_row);
				final LinearLayout l2 = (LinearLayout) view.findViewById(R.id.expdate_row);
				final LinearLayout l3 = (LinearLayout) view.findViewById(R.id.name_row);
				final LinearLayout l4 = (LinearLayout) view.findViewById(R.id.email_row);
				final Button scanButton = (Button) view.findViewById(R.id.scanCard_button);
				scanButton.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
		            	   PayWithCardio(callingAct);

					}});
		    	 ck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		    	       
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							
							
							
							// TODO Auto-generated method stub
							if (isChecked)
							{
								ll.setVisibility(View.GONE);
								l2.setVisibility(View.GONE);
								l3.setVisibility(View.GONE);
								l4.setVisibility(View.VISIBLE);


							}
							else
							{
								ll.setVisibility(View.VISIBLE);
								l2.setVisibility(View.VISIBLE);
								l3.setVisibility(View.VISIBLE);
								l4.setVisibility(View.GONE);
								
							}
							}
							
						}

		    	    );
    		    return builder.create();
    	}	


class PaymentDetails
{
	EditText name;
	EditText pan;
	EditText email;
	EditText cvv;
	EditText expdate;
}



	@Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
       
            this.dismiss();
            return true;
        
    }
}
