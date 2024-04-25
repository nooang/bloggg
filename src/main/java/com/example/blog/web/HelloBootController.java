package com.example.blog.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloBootController {
	@GetMapping("hello")
	public ResponseEntity<String> hello() {
		StringBuffer html = new StringBuffer();
		html.append("<!DOCTYPE html>\n");
		html.append("<html lang=\"en\">");
		html.append("<head>");
		html.append("  <meta charset=\"UTF-8\">");
		html.append("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		html.append("  <title>Hello Boot</title>");
		html.append("</head>");
		html.append("<body>");
		html.append("  <div>안녕하세요.</div>");
		html.append("  <div>Spring Boot에서 응답하였습니다.</div>");
		html.append("</body>");
		html.append("</html>");
		System.out.println(html);
		return new ResponseEntity<>(html.toString(), HttpStatus.OK);
	}
	
	@GetMapping("helloboot")
	public ModelAndView helloboot() {
		ModelAndView mav = new ModelAndView("helloboot");
		mav.addObject("myname", "김태현");
		return mav;
	}
}
