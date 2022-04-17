package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String s = "error";
        int i = 9;
        double d = 2.3;
        float f = 5f;
        byte b = 5;
        char ch = 'N';


        LOG.debug("s - {} i - {} d - {} f - {} b - {} ch - {}", s, i, d, f, b, ch);
    }
}