package xws_pi_bezb.exceptions;


import java.text.ParseException;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import groovy.util.logging.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	//TODO Handle exceptions
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DateTimeParseException occured")
	@ExceptionHandler(DateTimeParseException.class)
	public void handleSQLException(HttpServletRequest req, Exception e) {
		e.printStackTrace();
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DateTimeParseException occured")
	@ExceptionHandler(DateTimeParseException.class)
	public void handleDateTimeParseException(HttpServletRequest req, Exception e) {
		//log.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
		e.printStackTrace();
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ParceException occured")
	@ExceptionHandler(ParseException.class)
	public void handleParseException(HttpServletRequest req, Exception e) {		
		//log.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
		e.printStackTrace();
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "NullPointerException occured - unauthorized access")
	@ExceptionHandler(NullPointerException.class)
	public void handleNullPointerException(HttpServletRequest req, Exception e) {	
		//log.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
		e.printStackTrace();
	}
	
/*
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Exception occured")
	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest req, Exception e) {	
		//log.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
		System.out.println("ostali excepsn");
	}
*/
}