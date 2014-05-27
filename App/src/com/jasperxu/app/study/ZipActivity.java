package com.jasperxu.app.study;

import com.jasperxu.app.R;
import com.jasperxu.app.StudyMainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ZipActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_zip);
	}
	
	public void GoBackHandler(View view) {
		startActivity(new Intent(this, StudyMainActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}
	
	public void RunHandler(View view) {
		
	}
}
