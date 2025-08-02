package configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CoreParams {

    private static String coreRuntimeParamsfile="../framework/src/main/resources/runtimeResources/core.properties";
    public static String SCREENSHOTS_DIR;
    public static String RUNTIME_RESOURCES_DIR;
    public static String DRIVERS_DIR;


    public static void loadCoreParams(String fileName) throws IOException {
        if (fileName.equals(""))
            fileName = coreRuntimeParamsfile;

        InputStream inputStream = new FileInputStream(fileName);

        Properties props = new Properties();
        props.load(inputStream);

        RUNTIME_RESOURCES_DIR = props.getProperty("runtime.resources.dir", "../framework/src/main/resources/runtimeResources");
        SCREENSHOTS_DIR = props.getProperty("screenshot.dir", "target/HTML Reports/screenshots/");
        DRIVERS_DIR = props.getProperty("drivers.dir", "../supportData/drivers");

    }
}
