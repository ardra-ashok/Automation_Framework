package com.automation.helpers;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public String replaceParamWithVariable(String param){


        Pattern pattern = Pattern.compile("</Random\\([0-9]+\\)>");
        Matcher matcher = pattern.matcher(param);
        while (matcher.find()) {
            String match =  matcher.group();
            int length = Integer.parseInt(match.replaceAll("</Random\\(", "").replaceAll("\\)>",""));
            String key = match.replaceAll("</Random\\([0-9]+\\)>", getRandomeString(length));
            param = param.replace(match, key);
        }
        return param;
    }

    public static String getRandomeString(int length) {
        return RandomStringUtils.random(length, true, true);
    }

}
