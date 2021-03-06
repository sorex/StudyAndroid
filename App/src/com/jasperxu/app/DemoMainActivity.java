package com.jasperxu.app;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.jasperxu.app.demo.DirectoryActivity;
import com.jasperxu.util.FileHelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class DemoMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_main);

		// 下载数据包，下载视频，解压数据包
		//DownFiles();

		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		Button btn = new Button(this);
		btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		btn.setText("人教版六年级数学下册");
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getBaseContext(), DirectoryActivity.class);
				intent.putExtra("BookGuid", "e1844e31afa44ea3b01053f8c6e6e075");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				finish();
			}
		});
		layout.addView(btn);
	}

	public void DownFiles() {
		String savePathString = Environment.getExternalStorageDirectory() + "/com.jasperxu.app/";
		String fileNameString = "e1844e31afa44ea3b01053f8c6e6e075.zip";

		try {
			// 下载数据包文件
			FileHelp.downFile("http://192.168.1.102/temp/e1844e31afa44ea3b01053f8c6e6e075.zip", savePathString
					+ "temp/", fileNameString);

			// 下载视频文件
			FileHelp.downFile("http://192.168.1.102/temp/videos/48248402c8514c9ca2e22d33fd2e0204.mp4", savePathString
					+ "videos/", "48248402c8514c9ca2e22d33fd2e0204.mp4");
			FileHelp.downFile("http://192.168.1.102/temp/videos/a36e42d201884aa8bb030a0d500495e3.mp4", savePathString
					+ "videos/", "a36e42d201884aa8bb030a0d500495e3.mp4");
			FileHelp.downFile("http://192.168.1.102/temp/videos/ebd811b1513a45199a67d4b83a7e02c3.mp4", savePathString
					+ "videos/", "ebd811b1513a45199a67d4b83a7e02c3.mp4");

			// 解压文件
			unpackZip(savePathString, savePathString + "temp/" + fileNameString);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean unpackZip(String unzipPath, String zipFullName) {
		InputStream is;
		ZipInputStream zis;
		try {
			String filename;
			is = new FileInputStream(zipFullName);
			zis = new ZipInputStream(new BufferedInputStream(is));
			ZipEntry ze;
			byte[] buffer = new byte[1024];
			int count;

			while ((ze = zis.getNextEntry()) != null) {
				// zapis do souboru
				filename = ze.getName();

				// Need to create directories if not exists, or
				// it will generate an Exception...
				if (ze.isDirectory()) {
					File fmd = new File(unzipPath + filename);
					fmd.mkdirs();
					continue;
				}

				FileOutputStream fout = new FileOutputStream(unzipPath + filename);

				// cteni zipu a zapis
				while ((count = zis.read(buffer)) != -1) {
					fout.write(buffer, 0, count);
				}

				fout.close();
				zis.closeEntry();
			}

			zis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
