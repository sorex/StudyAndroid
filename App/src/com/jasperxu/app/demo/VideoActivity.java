package com.jasperxu.app.demo;

import java.io.File;

import com.jasperxu.app.R;
import com.jasperxu.app.R.layout;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	int Index;
	int PageSize;
	String BookGuid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_video);
		
		//接受参数
		// 得到传过来的Intent对象
		Intent intent = getIntent();
		Index = intent.getIntExtra("Index", 1);
		PageSize = intent.getIntExtra("PageSize", 1);
		BookGuid = intent.getStringExtra("BookGuid");
		
		String video = intent.getStringExtra("video");
		
		
		File tempVideo = new File(Environment.getExternalStorageDirectory() + "/com.jasperxu.app/videos/"+ video);
		if (tempVideo.exists()) {
			VideoView videoView = (VideoView)findViewById(R.id.videoView);
			MediaController mediaController = new MediaController(this);
			
			videoView.setVideoPath(tempVideo.getAbsolutePath());
			videoView.setMediaController(mediaController);
			mediaController.setMediaPlayer(videoView);
			videoView.requestFocus();
			videoView.start();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		menu.add(0, R.string.go_back, 0, this.getString(R.string.go_back));

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.string.go_back:
			Intent intent = new Intent(this, ContentActivity.class);
			intent.putExtra("Index", Index);
			intent.putExtra("BookGuid", BookGuid);
			intent.putExtra("PageSize", PageSize);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			finish();
			break;

		default:
			break;
		}
		return true;
	}
}
