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

    public static void setSystemProperty(String systemPropVar, String stringValue)  {
        System.setProperty(systemPropVar,stringValue);
    }

    public static String getEnvironmentVariable(String environmentVar) {
        try {
            return environmentVariablesMap.get(environmentVar);
        } catch (NullPointerException ex) {
//            throw new VariableNotDefinedException(environmentVar);
        }
        return null;
    }


    }



