package com.hasebly;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CardNotPresentDialog extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_card_not_present_dialog);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card_not_present_dialog, menu);
		return true;
	}

}
