package com.jasperxu.app.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.jasperxu.app.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author Jasper
 * 
 */
public class ContentActivity extends Activity implements GestureDetector.OnGestureListener {

	GestureDetector detector;
	final int FLIP_DISTANCE = 150;

	int Index;
	String BookGuid;
	int PageSize;
	BookInfo bookInfo;
	ArrayList<String> Videos = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_content);

		// 得到传过来的Intent对象
		Intent intent = getIntent();
		Index = intent.getIntExtra("Index", 1);
		PageSize = intent.getIntExtra("PageSize", 1);
		BookGuid = intent.getStringExtra("BookGuid");

		bookInfo = ((ApplicationBookInfo) getApplication()).getBookInfo();

		detector = new GestureDetector(this, this);

		ShowPage();
	}

	public void ShowPage() {
		ImageView BookImage = (ImageView) this.findViewById(R.id.BookImage);
		try {
			FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory() + "/com.jasperxu.app/"
					+ BookGuid + "/contents/" + String.format("%1$04d.jpg", Index));
			Bitmap bitmap = BitmapFactory.decodeStream(fis);
			BookImage.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void ShowVideo() {
		BookInfo.Page page = null;
		for (int i = 0; i < bookInfo.Pages.size(); i++) {
			if (bookInfo.Pages.get(i).Index == Index) {
				page = bookInfo.Pages.get(i);
				break;
			}
		}

		if (page != null) {
			LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
			// 说明有视频
			for (int i = 0; i < page.Videos.size(); i++) {
				final String video = page.Videos.get(i);
				TextView textView = new TextView(this);
				textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				textView.setText("视频"+String.valueOf(i+1));
				textView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(getBaseContext(), VideoActivity.class);
						intent.putExtra("BookGuid", BookGuid);
						intent.putExtra("Index", Index);
						intent.putExtra("PageSize", PageSize);
						intent.putExtra("video", video);
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
						finish();
					}
				});
				layout.addView(textView);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		menu.add(0, R.string.go_back, 0, this.getString(R.string.go_back));
		
		//添加视频的菜单
		BookInfo.Page page = null;
		for (int i = 0; i < bookInfo.Pages.size(); i++) {
			if (bookInfo.Pages.get(i).Index == Index) {
				page = bookInfo.Pages.get(i);
				break;
			}
		}

		if (page != null) {
			// 说明有视频
			for (int i = 0; i < page.Videos.size(); i++) {
				menu.add(0, i, 0, "视频"+String.valueOf(i+1));
				Videos.add(page.Videos.get(i));
			}
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.string.go_back:
			Intent intent = new Intent(this, DirectoryActivity.class);
			intent.putExtra("BookGuid", BookGuid);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			finish();
			break;

		default:
			String video = Videos.get(item.getItemId());
			Intent intent2 = new Intent(getBaseContext(), VideoActivity.class);
			intent2.putExtra("BookGuid", BookGuid);
			intent2.putExtra("Index", Index);
			intent2.putExtra("PageSize", PageSize);
			intent2.putExtra("video", video);
			startActivity(intent2);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			finish();
			
			break;
		}
		return true;
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