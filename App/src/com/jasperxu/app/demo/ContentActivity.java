package com.jasperxu.app.demo;

import com.jasperxu.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author Jasper
 * 
 */
public class ContentActivity extends Activity implements GestureDetector.OnGestureListener {

	GestureDetector detector;
	final int FLIP_DISTANCE = 50;

	int Index;
	String BookGuid;
	int PageSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_content);

		// 得到传过来的Intent对象
		Intent intent = getIntent();
		Index = intent.getIntExtra("Index", 1);
		PageSize = intent.getIntExtra("PageSize", 1);
		BookGuid = intent.getStringExtra("BookGuid");

		TextView IndexView = (TextView) this.findViewById(R.id.IndexView);
		IndexView.setText(String.valueOf(Index));
		TextView BookGuidView = (TextView) this.findViewById(R.id.BookGuidView);
		BookGuidView.setText(BookGuid);

		detector = new GestureDetector(this, this);

		ShowPage();
		ShowVideo();
	}

	public void ShowPage() {

	}

	public void ShowVideo() {

	}

	public void GoBackHandler(View view) {
		Intent intent = new Intent(this, DirectoryActivity.class);
		intent.putExtra("BookGuid", BookGuid);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		ScrollView scroll = (ScrollView) this.findViewById(R.id.scroll);

		detector.onTouchEvent(ev);
		scroll.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
			if (PageSize > Index) {
				Intent intent = new Intent(this, ContentActivity.class);
				intent.putExtra("Index", Index + 1);
				intent.putExtra("BookGuid", BookGuid);
				intent.putExtra("PageSize", PageSize);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				finish();
				return true;
			} else {
				return false;
			}
		} else if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
			if (Index > 1) {
				Intent intent = new Intent(this, ContentActivity.class);
				intent.putExtra("Index", Index - 1);
				intent.putExtra("BookGuid", BookGuid);
				intent.putExtra("PageSize", PageSize);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
				finish();
				return true;
			} else {
				Intent intent = new Intent(this, DirectoryActivity.class);
				intent.putExtra("BookGuid", BookGuid);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
				finish();
				return true;
			}
		}
		return false;
	}
}