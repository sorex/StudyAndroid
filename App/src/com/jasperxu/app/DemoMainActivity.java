package com.jasperxu.app;

import com.jasperxu.app.demo.DirectoryActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class DemoMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_main);

		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		Button btn = new Button(this);
		btn.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		btn.setText("人教版六年级数学下册");
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getBaseContext(),
						DirectoryActivity.class);
				intent.putExtra("BookGuid", "人教版六年级数学下册 GUID");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
				finish();
			}
		});
		layout.addView(btn);
	}
}
