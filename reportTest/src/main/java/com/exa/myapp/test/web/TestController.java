package com.exa.myapp.test.web;

import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exa.myapp.test.service.TestXMLVO;
import com.exa.myapp.test.service.TestXMLVO.Contents;
import com.exa.myapp.test.service.TestXMLVO.Header;
import com.exa.myapp.test.service.TestXMLVO.Article;

/**
 * Handles requests for the application test page.
 */
@Controller
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "home";
	}

	/**
	 * XML return
	 */
	@ResponseBody
	@RequestMapping(value = "/rest/testXML", method = RequestMethod.GET)
	public TestXMLVO returnXML() {
		logger.info("##### Get TestXMLVO");

		ArrayList<Contents> contentsList = new ArrayList<Contents>();
		contentsList.add(new Contents("입학일", "2020년 01월 01일"));
		contentsList.add(new Contents("졸업일", "2021년 01월 01일"));
		Article article = new Article("졸업", contentsList);
		return new TestXMLVO(new Header("졸업증명서", "컴퓨터대학교", "컴퓨터공학과"), article);
	}

}