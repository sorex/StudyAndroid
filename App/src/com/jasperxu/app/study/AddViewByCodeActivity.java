package com.jasperxu.app.study;

import com.jasperxu.app.R;
import com.jasperxu.app.StudyMainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddViewByCodeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_add_view_by_code);
	}

	public void GoBackHandler(View view) {
		startActivity(new Intent(this, StudyMainActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}

	public void RunHandler(View view) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		for (int i = 0; i < 20; i++) {
			final int final_i = i;
			TextView tv = new TextView(this);
			tv.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));// 设置TextView的布局
			// tv1.setPadding(50, 100, 0, 0);//设置边距
			tv.setText(i + ".这是一个动态添加的内容！");
			tv.setAutoLinkMask(Linkify.ALL);
			tv.setTextSize(20);
			tv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					new AlertDialog.Builder(AddViewByCodeActivity.this)
							.setTitle("提示标题")
							.setMessage("这是提示内容" + final_i)
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialogInterface,
												int i) {
											dialogInterface.cancel();
										}
									}).show();
				}
			});
			layout.addView(tv);// 将TextView 添加到子View 中
		}
	}
}
