package com.jasperxu.app.study;

import com.jasperxu.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class GestureActivity extends Activity implements
		GestureDetector.OnGestureListener {
	GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_gesture);

		detector = new GestureDetector(this, this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		return detector.onTouchEvent(motionEvent);
	}

	@Override
	public boolean onDown(MotionEvent motionEvent) {
		Toast.makeText(this, "onDown", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void onShowPress(MotionEvent motionEvent) {
		Toast.makeText(this, "onShowPress", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onSingleTapUp(MotionEvent motionEvent) {
		Toast.makeText(this, "onSingleTapUp", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2,
			float v, float v2) {
		Toast.makeText(this, "onScroll", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void onLongPress(MotionEvent motionEvent) {
		Toast.makeText(this, "onLongPress", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2,
			float v, float v2) {
		Toast.makeText(this, "onFling", Toast.LENGTH_SHORT).show();
		return false;
	}
}
