package com.smartgrow.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Facade {

    @Value("${spring.application.version}")
    String version;

    @Value("${spring.application.description}")
    String description;

    @Value("${spring.application.name}")
    String name;

    @Value("${message.default:not-found}")
    String defaultMessage;

    @Value("${message.dev:not-found}")
    String devMessage;

    @Value("${message.prod:not-found}")
    String prodMessage;

    @Value("${message.application:not-found}")
    String applicationMessage;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "name: " + name + "</br> version: " + version + "</br> description: " + description + "</br> defaultMessage: " + defaultMessage + "</br> devMessage: " + devMessage + "</br> prodMessage: " + prodMessage + "</br> applicationMessage: " + applicationMessage;
    }
}
