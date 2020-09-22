package io.masumbhuiyan.ppmtool.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionResponseHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdentifierException(ProjectIdentifierException projectIdentifierException, WebRequest webRequest) {
        ProjectIdentifierExceptionResponse projectIdentifierExceptionResponse = new ProjectIdentifierExceptionResponse(projectIdentifierException.getMessage());
        return new ResponseEntity(projectIdentifierExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
