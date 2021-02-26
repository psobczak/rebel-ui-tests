package pl.sobczak.rebel.config;

import lombok.Getter;
import pl.sobczak.rebel.drivers.DriverType;

@Getter
public class Configuration {

    private static final String BASE_URL = System.getProperty("baseUrl", "https://www.rebel.pl/");
    private static final DriverType DRIVER_TYPE = getConfigurationDriver();

    private static DriverType getConfigurationDriver() {
        DriverType driverType;
        String driver = System.getProperty("driver", "chrome");
        switch (driver.toLowerCase()) {
            case "firefox":
                driverType = DriverType.FIREFOX;
                break;
            case "edge":
                driverType = DriverType.EDGE;
                break;
            case "opera":
                driverType = DriverType.OPERA;
                break;
            default:
                driverType = DriverType.CHROME;
                break;
        }

        return driverType;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static DriverType getDriverType() {
        return DRIVER_TYPE;
    }
}
