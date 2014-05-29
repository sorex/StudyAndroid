package com.jasperxu.app.study;

import java.io.File;

import com.jasperxu.app.R;
import com.jasperxu.app.StudyMainActivity;
import com.jasperxu.app.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_video);
	}
	
	public void GoBackHandler(View view) {
		startActivity(new Intent(this, StudyMainActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}
	
	public void RunHandler(View view) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		File tempVideo = new File(Environment.getExternalStorageDirectory() + "/com.jasperxu.app/videos/48248402c8514c9ca2e22d33fd2e0204.mp4");
		if (tempVideo.exists()) {
			VideoView videoView = (VideoView)findViewById(R.id.videoView);
//			videoView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//					ViewGroup.LayoutParams.WRAP_CONTENT));
//			videoView.setLayoutParams(new ViewGroup.LayoutParams(500,500));
			MediaController mediaController = new MediaController(this);
			
			
			videoView.setVideoPath(tempVideo.getAbsolutePath());
			videoView.setMediaController(mediaController);
			mediaController.setMediaPlayer(videoView);
			videoView.requestFocus();

		}
	}
}
