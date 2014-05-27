package com.jasperxu.app.study;

import com.jasperxu.app.R;
import com.jasperxu.app.StudyMainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

public class FileAndPathActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_file_and_path);
	}

	public void GoBackHandler(View view) {
		startActivity(new Intent(this, StudyMainActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}

	public void RunHandler(View view) {

		new AlertDialog.Builder(FileAndPathActivity.this)
				.setTitle("提示标题")
				.setMessage(
				// /data/data/com.jasperxu.app/databases/data
						"this.getDatabasePath(\"data\")："
								+ this.getDatabasePath("data")
								+
								// /data/data/com.jasperxu.app/cache
								"\nthis.getCacheDir()：" + this.getCacheDir()
								+
								// /data/data/com.jasperxu.app/files
								"\nthis.getFilesDir()：" + this.getFilesDir()
								+
								// /storage/emulated/0
								"\nEnvironment.getExternalStorageDirectory()："
								+ Environment.getExternalStorageDirectory())
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						dialogInterface.cancel();
					}
				}).show();
	}
}
