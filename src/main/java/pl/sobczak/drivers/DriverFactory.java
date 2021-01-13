package pl.sobczak.drivers;

public class DriverFactory {

    private static DriverManager driverManager;

    public static DriverManager createDriver(DriverType driverType) {
        switch (driverType) {
            case CHROME:
                driverManager = new ChromeManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxManager();
                break;
        }

        return driverManager;
    }
}
