package com.example.gomi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditorActivity extends Activity{
	
	@Override
	public void onCreate(Bundle bundleData) {
		Log.i("edit", "onCreate() start");
		super.onCreate(bundleData);
		setContentView(R.layout.activity_edit);
		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			CharSequence text = bundle.getCharSequence("mainText");
			EditText editText = (EditText) findViewById(R.id.editText);
			editText.setText(text);
			Log.i("edit", "mainText: " + text);
		}
		
		Button button = (Button) findViewById(R.id.commitButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("edit", "commitButton clicked");
				Intent intent = new Intent(EditorActivity.this, MainActivity.class);
				
				EditText editText = (EditText) findViewById(R.id.editText);
				CharSequence text = editText.getText();
				intent.putExtra("edittedText", text);

				Log.i("edit", "edittedText: " + text);
				
				setResult(RESULT_OK, intent);
				finish();
			}
		}
		);
	}
	
	@Override
	public void finish() {
		Log.i("edit", "finish() start");
		super.finish();
	}

}
