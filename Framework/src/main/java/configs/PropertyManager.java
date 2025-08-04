package configs;

import utils.YmlHandler;
import java.util.HashMap;
import java.util.Map;

public class PropertyManager {

    private static Map<String, Object> scenarioDataMap = new HashMap<>();
    protected static Map<String, Object> featureFileData = new HashMap<>();

    private static final ThreadLocal<Map<String, String>> environmentVariablesMap =
            ThreadLocal.withInitial(HashMap::new);

    private static final ThreadLocal<Map<String, String>> appPropsVariablesMap =
            ThreadLocal.withInitial(HashMap::new);

    public static void loadTestPropertiesFromYML(String env) throws Exception {
        PropertyManager.setSystemProperty("env", env);

        try {
            Map<String, Map<String, String>> resultMap;

            try {
                resultMap = YmlHandler.readYMLData(CoreParams.RUNTIME_RESOURCES_DIR + "/environments.yml");
            } catch (Throwable t) {
                throw new Exception("Error Reading YML File", t);
            }

            Map<String, String> envData = resultMap.get(env);
            if (envData == null) {
                throw new Exception("Environment '" + env + "' not found in environments.yml. Available keys: " + resultMap.keySet());
            }

            // ✅ Store values in the thread-local map
            environmentVariablesMap.get().clear();
            environmentVariablesMap.get().putAll(envData);

        } catch (Throwable ex) {
            throw new Exception("Could not load test environment properties for environment: " + env, ex);
        }
    }

    public static String getSystemProperty(String systemPropVar) {
        return System.getProperty(systemPropVar);
    }

    public static String getSystemProperty(String systemPropVar, String defaultValue) {
        return System.getProperty(systemPropVar, defaultValue);
    }

    public static void setSystemProperty(String systemPropVar, String stringValue) {
        System.setProperty(systemPropVar, stringValue);
    }

    public static void setScenarioVariable(String scenarioVariable, Object dataObject) {
        if (!getScenarioDataMap().containsKey(scenarioVariable)) {
            getScenarioDataMap().put(scenarioVariable, dataObject);
        }
    }

    public static Map<String, Object> getScenarioDataMap() {
        if (scenarioDataMap == null) {
            scenarioDataMap = new HashMap<>();
        }
        return scenarioDataMap;
    }

    public static String getEnvironmentVariable(String environmentVar) {
        return environmentVariablesMap.get().get(environmentVar);
    }

    public static String getEnvironmentVariable(String environmentVar, String defaultValue) {
        return environmentVariablesMap.get().getOrDefault(environmentVar, defaultValue);
    }

    public static Object getScenarioVariable(String scenarioVariable) {
        return getScenarioDataMap().get(scenarioVariable);
    }

    public static Object getScenarioVariable(String scenarioVariable, String defaultValue) {
        return getScenarioDataMap().getOrDefault(scenarioVariable, defaultValue);
    }

    public static <T> T getScenarioVariable(String scenarioVariable, Class<T> className) {
        if (getScenarioDataMap().containsKey(scenarioVariable)) {
            return className.cast(getScenarioDataMap().get(scenarioVariable));
        }
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

    public static Object getFeatureFileDataVariable(String featureFileDataVariable, String defaultValue) {
        return featureFileData.getOrDefault(featureFileDataVariable, defaultValue);
    }

    public static String getAppPropVariable(String appPropVar) {
        return appPropsVariablesMap.get().get(appPropVar);
    }

    public static void clearThreadLocals() {
        environmentVariablesMap.remove();
        appPropsVariablesMap.remove();
    }

    public static Map<String, String> getEnvironmentVariablesMap() {
        return environmentVariablesMap.get();
    }

    public static Map<String, String> getAppPropsVariablesMap() {
        return environmentVariablesMap.get();
    }

}
