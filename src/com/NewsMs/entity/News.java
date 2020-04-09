package com.NewsMs.entity;

import java.sql.Timestamp;

public class News {
	private long newsId;
	private String newsTitlie;
	private String newsContent;
	private long authorId;
	private Timestamp newsUptime;
	private String newClass;

	public News(long newsId, String newsTitlie, String newsContent, long authorId, Timestamp newsUptime,
			String newClass) {
		super();
		this.newsId = newsId;
		this.newsTitlie = newsTitlie;
		this.newsContent = newsContent;
		this.authorId = authorId;
		this.newsUptime = newsUptime;
		this.newClass = newClass;
	}

	public News() {
		super();
	}

	public News(long newsId, String newstitle) {
		this.newsId=newsId;
		this.newsTitlie=newstitle;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsTitlie=" + newsTitlie + ", newsContent=" + newsContent + ", authorId="
				+ authorId + ", newsUptime=" + newsUptime + ", newClass=" + newClass + "]";
	}

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitlie() {
		return newsTitlie;
	}

	public void setNewsTitlie(String newsTitlie) {
		this.newsTitlie = newsTitlie;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public Timestamp getNewsUptime() {
		return newsUptime;
	}

	public void setNewsUptime(Timestamp newsUptime) {
		this.newsUptime = newsUptime;
	}

	public String getNewClass() {
		return newClass;
	}

	public void setNewClass(String newClass) {
		this.newClass = newClass;
	}

}
