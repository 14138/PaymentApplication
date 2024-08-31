/**
 * 
 */
package com.demo.valantic.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.demo.valantic.model.ErrorResponse;

/**
 * 
 */
@RestControllerAdvice
 public class GlobalExceptionHandler {

	 @ExceptionHandler(BadRequestException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ResponseBody
	 public ErrorResponse handleBadRequestException(BadRequestException ex) 
	 {

	     ErrorResponse errorResponse = new ErrorResponse();
	     errorResponse.setMessage("Bad Request. Validate request Body");
	     return errorResponse;
	 }
	 
	 @ExceptionHandler(Exception.class)
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 @ResponseBody
	 public ErrorResponse handleDefaultException(Exception ex) {
	     ErrorResponse response = new ErrorResponse();
	     response.setMessage(ex.getMessage());
	     return response;
	 }
}
