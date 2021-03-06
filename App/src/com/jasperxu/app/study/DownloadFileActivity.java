package com.jasperxu.app.study;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.jasperxu.app.R;
import com.jasperxu.app.StudyMainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

public class DownloadFileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_download_file);
	}

	public void GoBackHandler(View view) {
		startActivity(new Intent(this, StudyMainActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}

	public void RunHandler(View view) {

		// 不可以在UI的主线程中使用网络
		new Thread(new Runnable() {
			public void run() {
				String downloadUrl = "http://m.baidu.com/static/index/logo_index2.png";
				String saveDir = "com.jasperxu.app/";
				String saveFileName = "logo_index2.png";

				// 获取外置sd卡路径，并加上目录，目录要以/结尾
				String savePath = Environment.getExternalStorageDirectory()
						+ "/" + saveDir;

				URL url;
				HttpURLConnection urlConn = null;
				InputStream input = null;

				try {
					url = new URL(downloadUrl);
					urlConn = (HttpURLConnection) url.openConnection();
					urlConn.setRequestMethod("GET");
					urlConn.setConnectTimeout(5000);
					urlConn.setDoInput(true);
					urlConn.setUseCaches(false);
					// urlConn.setRequestProperty("Content-type", "image/png");
					urlConn.connect();
					input = urlConn.getInputStream();
				} catch (MalformedURLException e) {
					e.getMessage();
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				File file = null;
				OutputStream output = null;
				try {
					// 创建目录
					File dir = new File(savePath);
					dir.mkdir();

					// 创建文件
					file = new File(savePath + saveFileName);
					if (file.exists())
						file.delete();
					file.createNewFile();

					output = new FileOutputStream(file);
					int FILESIZE = 4 * 1024;
					byte[] buffer = new byte[FILESIZE];

					/*
					 * 真机测试，这段可能有问题，请采用下面网友提供的 while((input.read(buffer)) !=
					 * -1){ output.write(buffer); }
					 */

					/* 网友提供 begin */
					int length;
					while ((length = (input.read(buffer))) > 0) {
						output.write(buffer, 0, length);
					}
					/* 网友提供 end */

					output.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
