package pl.sobczak.rebel.drivers;

import pl.sobczak.rebel.config.Configuration;
import pl.sobczak.rebel.drivers.managers.*;

public class DriverManagerFactory {

    private static DriverManager driverManager;

    public static DriverManager createManager() {
        switch (Configuration.getDriverType()) {
            case FIREFOX:
                driverManager = new FirefoxManager();
                break;
            case EDGE:
                driverManager = new EdgeManager();
                break;
            case OPERA:
                driverManager = new OperaManager();
                break;
            default:
                driverManager = new ChromeManager();
                break;
        }

        return driverManager;
    }
}
