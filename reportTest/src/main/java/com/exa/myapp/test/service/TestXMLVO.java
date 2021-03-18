package com.exa.myapp.test.service;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.exa.myapp.test.service.TestVO.Article;

@XmlRootElement(name = "certificate")
@XmlType(propOrder = { "header", "article" })
public class TestXMLVO {
	private Header header;
	private Article article;

	public TestXMLVO() {

	}

	public TestXMLVO(Header header, Article article) {
		super();
		this.header = header;
		this.article = article;
	}

	@XmlElement(name = "header")
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@XmlElement(name = "article")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	/* Declare Header tag */
	@XmlRootElement(name = "header")
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

		@XmlElement(name = "certknd")
		public String getCertknd() {
			return certknd;
		}

		public void setCertknd(String certknd) {
			this.certknd = certknd;
		}

		@XmlElement(name = "certfg")
		public String getCertfg() {
			return certfg;
		}

		public void setCertfg(String certfg) {
			this.certfg = certfg;
		}

		@XmlElement(name = "department")
		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		
	}

	@XmlRootElement(name = "article")
	@XmlType(propOrder = { "fg", "contents" })
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

		@XmlElement(name = "fg")
		public String getFg() {
			return fg;
		}

		public void setFg(String fg) {
			this.fg = fg;
		}

		@XmlElement(name = "contents")
		public ArrayList<Contents> getContents() {
			return contents;
		}

		public void setContents(ArrayList<Contents> contents) {
			this.contents = contents;
		}

	}

	@XmlRootElement(name = "contents")
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
