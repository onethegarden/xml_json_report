package com.exa.myapp.test.service;

import java.io.Serializable;
import java.util.ArrayList;

public class TestVO {
	private Header header;
	private Article article;

	public TestVO() {

	}

	public TestVO(Header header, Article article) {
		super();
		this.header = header;
		this.article = article;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}


	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}


	public static class Header implements Serializable {
		private String certknd;
		private String certfg;
		private String department;

		public Header() {
		}

		public Header(String certknd, String certfg, String department) {
			super();
			this.certknd = certknd;
			this.certfg = certfg;
			this.department = department;
		}

		public String getCertknd() {
			return certknd;
		}

		public void setCertknd(String certknd) {
			this.certknd = certknd;
		}

		public String getCertfg() {
			return certfg;
		}

		public void setCertfg(String certfg) {
			this.certfg = certfg;
		}

		public String getDepartment() {
			return department;
		}

		public void setIrofcNm(String department) {
			this.department = department;
		}

	}

	public static class Article implements Serializable {
		private static final long serialVersionUID = 1L;
		private String fg;
		private ArrayList<Contents> contents;

		public Article() {

		}

		public Article(String fg, ArrayList<Contents> contents) {
			this.fg = fg;
			this.contents = contents;
		}

		public String getFg() {
			return fg;
		}

		public void setFg(String fg) {
			this.fg = fg;
		}

		public ArrayList<Contents> getContents() {
			return contents;
		}

		public void setContents(ArrayList<Contents> contents) {
			this.contents = contents;
		}

	}

	public static class Contents implements Serializable {
		private static final long serialVersionUID = 1L;
		private String fg;
		private String val;

		public Contents() {

		}

		public Contents(String fg, String val) {
			super();
			this.fg = fg;
			this.val = val;
		}

		public String getFg() {
			return fg;
		}

		public void setFg(String fg) {
			this.fg = fg;
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}

	}

}
