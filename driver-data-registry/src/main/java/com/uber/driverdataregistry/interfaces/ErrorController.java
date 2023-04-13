package com.uber.driverdataregistry.interfaces;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController extends AbstractErrorController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> handleError(HttpServletRequest request) {
		Map<String, Object> errorAttributes = super.getErrorAttributes(request, ErrorAttributeOptions.defaults());

		Object path = errorAttributes.get("path");
		Object status = errorAttributes.get("status");
		Object error = errorAttributes.get("error");
		Object message = errorAttributes.get("message");

		logger.info("An error occurred while accessing {}: {} {}, {}", path, status, error, message);

		return errorAttributes;
	}
}