package com.automation.helpers;

import configs.PropertyManager;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public String replaceParamWithVariable(String param){


        Pattern pattern = Pattern.compile("\\$(.+?)\\$");
        Matcher matcher = pattern.matcher(param);

        while (matcher.find()) {

            String match =  matcher.group();
            if (PropertyManager.getScenarioDataMap().containsKey(match)) {
                if (PropertyManager.getScenarioVariable(match, (String) null) != null)
                    param = param.replace(match, PropertyManager.getScenarioVariable(match).toString());
            } else if (PropertyManager.getScenarioDataMap().containsKey(match.replaceAll("\\$",""))) {
                if (PropertyManager.getScenarioVariable(match.replaceAll("\\$",""), (String) null) != null)
                    param = param.replace(match, PropertyManager.getScenarioVariable(match.replaceAll("\\$","")).toString());
            } else if (PropertyManager.getSystemProperty(match,null) != null){
                param = param.replace(match, PropertyManager.getSystemProperty(match));
            } else if (PropertyManager.getSystemProperty(match.replaceAll("\\$",""),null) != null){
                param = param.replace(match, PropertyManager.getSystemProperty(match.replaceAll("\\$", "")));
            } else if (PropertyManager.getEnvironmentVariablesMap() != null
                    && PropertyManager.getEnvironmentVariablesMap().containsKey(match.replaceAll("\\$",""))){
                param =  param.replace(match,PropertyManager.getEnvironmentVariable(match.replaceAll("\\$","")));
            } else if (PropertyManager.getFeatureDataMap().containsKey(match)) {
                if (PropertyManager.getFeatureFileDataVariable(match, null) != null)
                    param = param.replace(match, PropertyManager.getFeatureFileDataVariable(match).toString());
            } else if (PropertyManager.getAppPropsVariablesMap() != null
                    && PropertyManager.getAppPropsVariablesMap().containsKey(match.replaceAll("\\$","")))
                param =  param.replace(match,PropertyManager.getAppPropVariable(match.replaceAll("\\$","")));
        }

        pattern = Pattern.compile("</Random\\([0-9]+\\)>");
        matcher = pattern.matcher(param);
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
    protected Map<String, String> replaceParamWithVariable(Map<String, String> input) {
        Map<String, String> output = new LinkedHashMap<String, String>();
        for (String key : input.keySet()) {
            if(input.get(key) == null || input.get(key).isEmpty())
                output.put(replaceParamWithVariable(key), "");
            else
                output.put(replaceParamWithVariable(key), replaceParamWithVariable(input.get(key)));
        }
        return output;
    }

}
