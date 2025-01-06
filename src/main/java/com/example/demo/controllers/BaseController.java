package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void logInfo(String message) {
        logger.info(message);
    }

    protected void logError(String message) {
        logger.error(message);
    }

}
