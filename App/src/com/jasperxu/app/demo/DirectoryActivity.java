package com.jasperxu.app.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.jasperxu.app.R;
import com.jasperxu.util.FileHelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DirectoryActivity extends Activity {

	String BookGuid;
	int PageSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_directory);

		// 得到传过来的Intent对象
		Intent intent = getIntent();
		BookGuid = intent.getStringExtra("BookGuid");

		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

		try {
			JSONObject bookInfo = GetBookJsonObject();

			TextView BookGuidView = (TextView) this.findViewById(R.id.BookGuidView);
			BookGuidView.setText(bookInfo.getString("Name"));

			PageSize = bookInfo.getInt("PageSize");
			JSONArray Directory = bookInfo.getJSONArray("Directory");

			for (int i = 0; i < Directory.length(); i++) {
				final JSONObject tempObject = Directory.getJSONObject(i);

				final int Index = tempObject.getInt("Index");
				boolean HaveVideos = tempObject.getBoolean("HaveVideos");

				TextView tv = new TextView(this);
				tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));// 设置TextView的布局
				// tv1.setPadding(50, 100, 0, 0);//设置边距
				tv.setText(tempObject.getString("Name") + (HaveVideos ? " video" : ""));
				tv.setAutoLinkMask(Linkify.ALL);
				setTextViewSizebyHeadingLevels(tv, tempObject.getInt("HeadingLevels"));
				tv.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(getBaseContext(), ContentActivity.class);
						intent.putExtra("BookGuid", BookGuid);
						intent.putExtra("Index", Index);
						intent.putExtra("PageSize", PageSize);
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
						finish();
					}
				});
				layout.addView(tv);// 将TextView 添加到子View 中
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// -----------------------------------------------------------

		for (int i = 0; i < 50; i++) {
			final int final_i = i;
			TextView tv = new TextView(this);
			tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));// 设置TextView的布局
			// tv1.setPadding(50, 100, 0, 0);//设置边距
			tv.setText(String.format("第%1$ 4d页", i + 1));
			tv.setAutoLinkMask(Linkify.ALL);
			tv.setTextSize(20);
			tv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(getBaseContext(), ContentActivity.class);
					intent.putExtra("BookGuid", BookGuid);
					intent.putExtra("Index", final_i + 1);
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
					finish();
				}
			});
			layout.addView(tv);// 将TextView 添加到子View 中
		}
	}

	private void setTextViewSizebyHeadingLevels(TextView textView, int headingLevels) {
		switch (headingLevels) {
		case 1:
			textView.setTextSize(38);
			break;
		case 2:
			textView.setTextSize(34);
			break;
		case 3:
			textView.setTextSize(30);
			break;
		case 4:
			textView.setTextSize(26);
			break;
		case 5:
			textView.setTextSize(22);
			break;

		default:
			textView.setTextSize(18);
			break;
		}

	}

	private JSONObject GetBookJsonObject() throws JSONException, Exception {

		String DirPath = Environment.getExternalStorageDirectory() + "/com.jasperxu.app/" + BookGuid + "/";

		return (JSONObject) (new JSONTokener(FileHelp.getStringFromFile(DirPath + "directory.json"))).nextValue();
	}

	public void GoBackHandler(View view) {
		Intent intent = new Intent(this, DirectoryActivity.class);
		intent.putExtra("BookGuid", BookGuid);
		startActivity(new Intent(this, DirectoryActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}
}
