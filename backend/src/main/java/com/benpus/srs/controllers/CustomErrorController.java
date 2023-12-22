package com.benpus.srs.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {
    private final ErrorAttributes errorAttributes;
    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "The path you are looking for does not exist in this application!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "Bad request. Make sure request JSON does not contain errors!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
            } else if (exception  !=  null && exception.toString().contains("a foreign key constraint fails")) {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "Could not delete this entry because of foreign key constraint. Make sure to delete all entries referencing current one with foreign key!");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
            } else {
                System.out.println("SRs klaida: "+ exception.toString());
            }
        }
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Unknown error.");
        return ResponseEntity.internalServerError().body(responseBody);
    }

    public String getErrorPath() {
        return "/error";
    }
}
