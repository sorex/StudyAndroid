package com.jasperxu.app.demo;

import android.app.Application;

public class ApplicationBookInfo extends Application{
	private BookInfo value;

    public BookInfo getBookInfo() {
        return value;
    }

    public void setBookInfo(BookInfo value) {
        this.value = value;
    }
}
