package com.insight.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

		private Logger log = LoggerFactory.getLogger(this.getClass());
		
		@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
		public String defaultExceptionHandler (HttpServletRequest request, Exception exception, Model model) {
			
			model.addAttribute("exception", exception);
			log.error("exception", exception);
			
			return "error/error_default";
		
		}
}
