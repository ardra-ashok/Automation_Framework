package configs;


import lombok.Getter;
import lombok.Setter;
import utils.YmlHandler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;



public class PropertyManager {

    private static Map<String, Object> scenarioDataMap = new HashMap<>();
    protected static Map<String, Object> featureFileData = new HashMap<>();

    @Getter
    @Setter
    protected static Map<String, String> appPropsVariablesMap = new HashMap<String, String>();

    @Getter
    @Setter
    protected static Map<String, String> environmentVariablesMap = new HashMap<String, String>();

    protected static Properties props = new Properties();

    public static void loadTestPropertiesFromYML(String env) throws Exception {
        String fileName = "../Framework/src/main/resources/runtimeResources/core.properties";

        InputStream inputStream = new FileInputStream(fileName);

        props.load(inputStream);
        String RUNTIME_RESOURCES_DIR = props.getProperty("runtime.resources.dir", "../Framework/src/main/resources/runtimeResources");

        PropertyManager.setSystemProperty("env", env);
        try {
            Map<String, Map<String,String>> resultMap = new LinkedHashMap<>();

            try {
                resultMap = YmlHandler.readYMLData(RUNTIME_RESOURCES_DIR + "/environments.yml");
            }catch (Throwable t) {
                throw new Exception("Error Reading Yml File");
            }
            environmentVariablesMap = resultMap.get(env);
            if(environmentVariablesMap == null) {
                throw new Exception("Environment '" + env + "' not found in environments.yml. Available keys: " + resultMap.keySet());
            }
        } catch (Throwable ex) {

            throw new Exception("Could not load test environment properties for environment: " + env,ex);
        }
    }

    public static String getSystemProperty(String systemPropVar ) {
        if (System.getProperty(systemPropVar) != null)
            return System.getProperty(systemPropVar);
        return null;
    }

    public static String getSystemProperty(String systemPropVar, String defaultValue) {
        if (System.getProperty(systemPropVar) != null) {
            return System.getProperty(systemPropVar);
        } else
            return defaultValue;

    }

    public static void setSystemProperty(String systemPropVar, String stringValue)  {
        System.setProperty(systemPropVar,stringValue);
    }


    public static void setScenarioVariable(String scenarioVariable, Object dataObject){
        if (!getScenarioDataMap().containsKey(scenarioVariable))
                getScenarioDataMap().put(scenarioVariable,dataObject );
    }


    public static Map<String, Object> getScenarioDataMap() {
        if (scenarioDataMap == null) {
            scenarioDataMap = new HashMap<>();
        }
        return scenarioDataMap;
    }

    public static String getEnvironmentVariable(String environmentVar) {
            return environmentVariablesMap.get(environmentVar);
    }
    public static String getEnvironmentVariable(String environmentVar, String defaultValue)  {
        return environmentVariablesMap.getOrDefault(environmentVar, defaultValue);
    }


    public static Object getScenarioVariable(String scenarioVariable) {
        if (getScenarioDataMap().containsKey(scenarioVariable))
            return getScenarioDataMap().get(scenarioVariable);
        return null;
    }

    public static Object getScenarioVariable(String scenarioVariable,String defaultValue )  {
        return getScenarioDataMap().getOrDefault(scenarioVariable, defaultValue);
    }

    public static <T> T getScenarioVariable(String scenarioVariable, Class<T> className ) {
        if (getScenarioDataMap().containsKey(scenarioVariable))
            return className.cast( getScenarioDataMap().get(scenarioVariable));
        return null;

    }

    public static Map<String, Object> getFeatureDataMap() {
        if (featureFileData == null) {
            featureFileData = new HashMap<>();
        }
        return featureFileData;
    }

    public static Object getFeatureFileDataVariable(String featureFileDataVariable) {
        return featureFileData.get(featureFileDataVariable);
    }

    public static Object getFeatureFileDataVariable(String featureFileDataVariable,String defaultValue )  {
        return featureFileData.getOrDefault(featureFileDataVariable, defaultValue);
    }

    public static String getAppPropVariable(String appPropVar) {
        return appPropsVariablesMap.get(appPropVar);
    }


}



