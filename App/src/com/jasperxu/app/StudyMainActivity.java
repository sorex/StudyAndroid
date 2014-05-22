package com.jasperxu.app;

import com.jasperxu.app.study.AddViewByCodeActivity;
import com.jasperxu.app.study.DownloadFileActivity;
import com.jasperxu.app.study.GestureActivity;
import com.jasperxu.app.study.JsonParseActivity;
import com.jasperxu.app.study.SQLiteActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudyMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_main);
	}

	public void GoBackHandler(View view) {
		startActivity(new Intent(this, MainActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}

	public void CloseHandler(View view) {
		finish();
	}

	public void AddViewByCodeHandler(View view) {
//      //写法一，创建Intent
//      Intent intent = new Intent();
//      intent.setClass(StudyMainActivity.this, AddViewByCodeActivity.class);
//      startActivity(intent);

      //写法二，直接调用startActivity
		startActivity(new Intent(this, AddViewByCodeActivity.class));

		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

		//如果不关闭当前的会出现好多个页面
		finish();

//      Intent intent = new Intent();
//      intent.setClass(Bmi.this, Report.class);
//      Bundle bundle = new Bundle();   //bundle带参数跳转
//      bundle.putString("KEY_HEIGHT",field_height.getText().toString());
//      bundle.putString("KEY_WEIGHT",field_weight.getText().toString());
//      intent.putExtras(bundle);
//      startActivity(intent);
	}

	public void DownloadFileHandler(View view) {
		startActivity(new Intent(this, DownloadFileActivity.class));
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		finish();
	}

	public void JsonParseHandler(View view) {
		startActivity(new Intent(this, JsonParseActivity.class));
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		finish();
	}

	public void SQLiteHandler(View view) {
		startActivity(new Intent(this, SQLiteActivity.class));
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		finish();
	}

	public void GestureHandler(View view) {
		startActivity(new Intent(this, GestureActivity.class));
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		finish();
	}
}
