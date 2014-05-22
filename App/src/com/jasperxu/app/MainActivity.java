package com.jasperxu.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void GoToStudyHandler(View view) {
		startActivity(new Intent(this, StudyMainActivity.class));
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		finish();
	}

	public void GoToDemoHandler(View view) {
		startActivity(new Intent(this, DemoMainActivity.class));
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		finish();
	}

	public void CloseHandler(View view) {
		finish();
	}
}
