package com.management_system.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String displayErrorView(HttpServletRequest request) {
        Integer httpErrorCode = getErrorCode(request);

        if (httpErrorCode != null && httpErrorCode == 404) {
            return "404";
        } else {
            return "error";
        }
    }

    private Integer getErrorCode(HttpServletRequest request) {
        Object statusCode = request.getAttribute("jakarta.servlet.error.status_code");
        return (statusCode != null) ? (Integer) statusCode : null;
    }
}
