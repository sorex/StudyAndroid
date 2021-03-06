package com.jasperxu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileHelp {

	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		reader.close();
		return sb.toString();
	}

	public static String getStringFromFile(String filePath) throws Exception {
		File fl = new File(filePath);
		FileInputStream fin = new FileInputStream(fl);
		String ret = convertStreamToString(fin);
		// Make sure you close all streams.
		fin.close();
		return ret;
	}

	public static void downFile(final String FileUrl, final String SavePath, final String FileName) throws Exception {
		Thread thread = new Thread(new Runnable() {
			public void run() {

				// String saveDir = "com.jasperxu.app/";
				// String saveFileName = "logo_index2.png";
				//
				// // 获取外置sd卡路径，并加上目录，目录要以/结尾
				// String savePath = Environment.getExternalStorageDirectory()
				// + "/" + saveDir;

				URL url;
				HttpURLConnection urlConn = null;
				InputStream input = null;

				try {
					url = new URL(FileUrl);
					urlConn = (HttpURLConnection) url.openConnection();
					urlConn.setRequestMethod("GET");
					urlConn.setConnectTimeout(5000);
					urlConn.setDoInput(true);
					urlConn.setUseCaches(false);
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
					File dir = new File(SavePath);
					dir.mkdirs();

					// 创建文件
					file = new File(SavePath + FileName);
					if (file.exists())
						file.delete();
					file.createNewFile();

					output = new FileOutputStream(file);
					// 一次读取4k的内容
					byte[] buffer = new byte[4096];

					int length;
					while ((length = input.read(buffer)) > 0) {
						output.write(buffer, 0, length);
					}

					output.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (output != null)
							output.close();
						if (input != null)
							input.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
