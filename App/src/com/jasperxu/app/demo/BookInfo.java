package com.jasperxu.app.demo;

import java.util.ArrayList;

public class BookInfo {
	private String Name;
	private int PageSize;
	public ArrayList<Directory> Directory = new ArrayList<Directory>();
	public ArrayList<Page> Pages = new ArrayList<Page>();
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	
	public class Directory{
		public int Index;
		public String Name;
		public int HeadingLevels;
		public boolean HaveVideos;
	}
	
	public class Page{
		public int Index;
		public ArrayList<String> Videos = new ArrayList<String>();
	}
}
