package com.jasperxu.app.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.jasperxu.app.DemoMainActivity;
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
			BookInfo bookInfo = GetBookInfo();

			TextView BookGuidView = (TextView) this.findViewById(R.id.BookGuidView);
			BookGuidView.setText(bookInfo.getName());

			PageSize = bookInfo.getPageSize();

			for (int i = 0; i < bookInfo.Directory.size(); i++) {
				BookInfo.Directory directory = bookInfo.Directory.get(i);
				final int Index = directory.Index;

				TextView tv = new TextView(this);
				tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));// 设置TextView的布局
				// tv1.setPadding(50, 100, 0, 0);//设置边距
				tv.setText(directory.Name + (directory.HaveVideos ? " video" : ""));
				tv.setAutoLinkMask(Linkify.ALL);
				setTextViewSizebyHeadingLevels(tv, directory.HeadingLevels);
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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// -----------------------------------------------------------

		// for (int i = 0; i < 50; i++) {
		// final int final_i = i;
		// TextView tv = new TextView(this);
		// tv.setLayoutParams(new
		// ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		// ViewGroup.LayoutParams.WRAP_CONTENT));// 设置TextView的布局
		// // tv1.setPadding(50, 100, 0, 0);//设置边距
		// tv.setText(String.format("第%1$ 4d页", i + 1));
		// tv.setAutoLinkMask(Linkify.ALL);
		// tv.setTextSize(20);
		// tv.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Intent intent = new Intent(getBaseContext(), ContentActivity.class);
		// intent.putExtra("BookGuid", BookGuid);
		// intent.putExtra("Index", final_i + 1);
		// startActivity(intent);
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_left);
		// finish();
		// }
		// });
		// layout.addView(tv);// 将TextView 添加到子View 中
		// }
	}

	private void setTextViewSizebyHeadingLevels(TextView textView, int headingLevels) {
		int left = 30;
		textView.setPadding((headingLevels - 1) * left, 0, 0, 0);
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

	private BookInfo GetBookInfo() throws Exception {
		String DirPath = Environment.getExternalStorageDirectory() + "/com.jasperxu.app/" + BookGuid + "/";
		JSONObject json = (JSONObject) (new JSONTokener(FileHelp.getStringFromFile(DirPath + "directory.json")))
				.nextValue();
		BookInfo bookInfo = new BookInfo();

		bookInfo.setName(json.getString("Name"));
		bookInfo.setPageSize(json.getInt("PageSize"));

		JSONArray Directory = json.getJSONArray("Directory");
		for (int i = 0; i < Directory.length(); i++) {
			JSONObject tempJson = Directory.getJSONObject(i);
			BookInfo.Directory temp = bookInfo.new Directory();
			temp.Name = tempJson.getString("Name");
			temp.Index = tempJson.getInt("Index");
			temp.HeadingLevels = tempJson.getInt("HeadingLevels");
			temp.HaveVideos = tempJson.getBoolean("HaveVideos");
			bookInfo.Directory.add(temp);
		}

		JSONArray Pages = json.getJSONArray("Pages");
		for (int i = 0; i < Pages.length(); i++) {
			JSONObject tempJson = Pages.getJSONObject(i);
			BookInfo.Page temp = bookInfo.new Page();
			temp.Index = tempJson.getInt("Index");

			JSONArray Videos = tempJson.getJSONArray("Videos");
			for (int j = 0; j < Videos.length(); j++) {
				temp.Videos.add(Videos.getString(j));
			}

			bookInfo.Pages.add(temp);
		}

		((ApplicationBookInfo) getApplication()).setBookInfo(bookInfo);

		return bookInfo;
	}

	public void GoBackHandler(View view) {
		startActivity(new Intent(this, DemoMainActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}
}
