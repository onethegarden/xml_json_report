package com.exa.myapp.test.web;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exa.myapp.test.service.TestVO;
import com.exa.myapp.test.service.TestXMLVO;
import com.exa.myapp.test.service.TestVO.Article;
import com.exa.myapp.test.service.TestVO.Contents;
import com.exa.myapp.test.service.TestVO.Header;

/**
 * Handles requests for the application test page.
 */
@Controller
public class TestJsonController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	/**
	 * JSON return
	 */
	@ResponseBody
	@RequestMapping(value = "/rest/testJSON", method = RequestMethod.GET)
	public TestVO returnJSON() {
		logger.info("##### Get TestVO");

		ArrayList<Contents> contentsList = new ArrayList<Contents>();
		contentsList.add(new Contents("입학일", "2020년 01월 01일"));
		contentsList.add(new Contents("졸업일", "2021년 01월 01일"));
		Article article = new Article("졸업", contentsList);
		
		return new TestVO(new Header("졸업증명서", "컴퓨터대학교", "컴퓨터공학과"), article);
	}

}