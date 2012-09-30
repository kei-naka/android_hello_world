package com.example.gomi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class MainActivity extends Activity {
	
	private boolean showImage = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i("main", "onCreate() start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // invisible main image
        ImageView mainImage = (ImageView) findViewById(R.id.mainImage);
        mainImage.setVisibility(View.INVISIBLE);
        
        // set version information
        setVersionInfo();
        
        // action when "editButton" is clicked
        Button button = (Button) findViewById(R.id.editButton);
        button.setOnClickListener(editButtonListener);

        // action when "showImageButton" is clicked 
        Button showImageButton = (Button) findViewById(R.id.showImageButton);
        showImageButton.setOnClickListener(showImageButtonListener);
    }
    
    private void setVersionInfo() {
        try {
        	PackageManager pckManager = this.getPackageManager();
        	PackageInfo pckInfo = pckManager.getPackageInfo(this.getPackageName(), PackageManager.GET_META_DATA);
        	TextView verTextView = (TextView) findViewById(R.id.verTextView);
        	CharSequence text = "code: " + pckInfo.versionCode + " / name: " + pckInfo.versionName;
        	verTextView.setText(text);
        	Log.i("main", (String) text);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    private OnClickListener editButtonListener = new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		Log.i("main", "editButton clicked");
    		
    		Intent intent = new Intent(MainActivity.this, EditorActivity.class);
    		TextView textView = (TextView) findViewById(R.id.mainText);
    		CharSequence text = textView.getText();
    		Log.i("main", "mainText: " + text);
    		
    		intent.putExtra("mainText", text);
    		startActivityForResult(intent, 1);
    	}
    };
    
    private OnClickListener showImageButtonListener = new OnClickListener() {
    	@Override
    	public void onClick (View v) {
    		Log.i("main", "showImageButton.onClick start");
    		ImageView mainImage = (ImageView) findViewById(R.id.mainImage);

    		if (showImage) {
    			mainImage.setVisibility(View.INVISIBLE);
    			showImage = false;
    			Log.i("main", "visibility is set to false");
    		} else {
    			mainImage.setVisibility(View.VISIBLE);
    			showImage = true;
    			Log.i("main", "visibility is set to true");
    		}
    	}
    };
    
    @Override
    public void onActivityResult(int req, int res, Intent data) {
    	Log.i("main", "onActivityResult start");
    	
    	if (req == 1) {
    		if (res == RESULT_OK) {
    			TextView textView = (TextView) findViewById(R.id.mainText);
    			CharSequence text = data.getCharSequenceExtra("edittedText");
    			textView.setText(text);
    			Log.i("main", "edittedText: " + text);
    		}
    	}
    }
}
