package xws_pi_bezb.exceptions;


import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import groovy.util.logging.Slf4j;
import xws_pi_bezb.controllers.LogRegKontroler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	//TODO Handle exceptions
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "InterruptedException occured")
	@ExceptionHandler(InterruptedException.class)
	public void InterruptedException(HttpServletRequest req, Exception e) {
		logger.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
	}
	

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "SQLException occured")
	@ExceptionHandler(SQLException.class)
	public void handleSQLException(HttpServletRequest req, Exception e) {
		logger.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
	}
	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DateTimeParseException occured")
	@ExceptionHandler(DateTimeParseException.class)
	public void handleDateTimeParseException(HttpServletRequest req, Exception e) {
		logger.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ParceException occured")
	@ExceptionHandler(ParseException.class)
	public void handleParseException(HttpServletRequest req, Exception e) {		
		logger.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "NullPointerException occured - unauthorized access")
	@ExceptionHandler(NullPointerException.class)
	public void handleNullPointerException(HttpServletRequest req, Exception e) {	
		logger.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Exception occured")
	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest req, Exception e) {	
		logger.error("ERROR: " + req.getRequestURI() + ", MESSAGE: " + e.getMessage());
	}

}