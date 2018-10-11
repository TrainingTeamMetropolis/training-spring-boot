package com.luvina.controller;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class CatchErrorsController {

    @Autowired
    private Environment environment;

    @ExceptionHandler({SQLGrammarException.class, SQLTimeoutException.class})
    public ModelAndView handleError(HttpServletRequest request, Exception e)   {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system_error");
        modelAndView.addObject("msg", environment.getProperty("ErrorConnectDatabase"));
        return modelAndView;
    }
}
