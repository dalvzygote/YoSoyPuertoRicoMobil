package org.yspr.yosoypuertoricomobil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CustomIAPRActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yo_soy_pr);
		Intent recieved_intent = getIntent();
		String recieved_word = recieved_intent.getStringExtra("iam_word");
		TextView tv = (TextView)findViewById(R.id.iam_word);
		tv.setText(recieved_word);
	}
}
	
 