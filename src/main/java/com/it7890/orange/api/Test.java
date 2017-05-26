package com.it7890.orange.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by wyq on 2017/5/23.
 */
public class Test {
    private static Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        String s = "http://www.esquire.tw/wp-content/uploads/2017/05/201705171205-1024x132";

        Pattern patt = Pattern.compile(".*?\\d*x\\d*$");
        Matcher matcher = patt.matcher(s);
        logger.info("result: {}", matcher.find());
    }
}
