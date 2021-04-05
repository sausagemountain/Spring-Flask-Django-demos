package com.example.demo.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return MessageFormat.format("Hello {0}! Demonstration Successful!", name);
    }

    @RequestMapping("/one/{path}")
    public Object jsonTest(@PathVariable String path, @RequestParam String input, HttpServletRequest request) {
        return new OneResponse(input, path, request.getMethod());
    }
}

class OneResponse {
    @JsonProperty("input_param")
    String inputParam;

    @JsonProperty("path_param")
    String pathParam;

    @JsonProperty("request_method")
    String requestMethod;

    public OneResponse(String inputParam, String pathParam, String requestMethod) {
        this.inputParam = inputParam;
        this.pathParam = pathParam;
        this.requestMethod = requestMethod;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public String getPathParam() {
        return pathParam;
    }

    public void setPathParam(String pathParam) {
        this.pathParam = pathParam;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
