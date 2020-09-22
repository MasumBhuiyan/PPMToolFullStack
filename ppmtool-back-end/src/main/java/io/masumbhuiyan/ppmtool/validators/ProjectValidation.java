package io.masumbhuiyan.ppmtool.validators;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectValidation {
    public ResponseEntity<?> getProjectValidationErrors(BindingResult bindingResult) {
        if( bindingResult.hasErrors() ) {
            Map<String, String> projectValidationErrors = new HashMap<>();
            for(FieldError fieldError: bindingResult.getFieldErrors()) {
                projectValidationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(projectValidationErrors, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
