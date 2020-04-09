package com.NewsMs.entity;

public class Author {
	private long authorId;
	private String authorName;
	private String aPwd;
	private String aName;

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", aPwd=" + aPwd + ", aName=" + aName
				+ "]";
	}

	public Author(long authorId, String authorName, String aPwd, String aName) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.aPwd = aPwd;
		this.aName = aName;
	}

	public Author() {
		super();
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getaPwd() {
		return aPwd;
	}

	public void setaPwd(String aPwd) {
		this.aPwd = aPwd;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

}
