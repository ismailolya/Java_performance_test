package util;

import pages.ComputersPage;
import pages.DesktopsPage;
import pages.MainPage;

public class Constants {

    public final static BrowserFactory BROWSER_FACTORY = new BrowserFactory();
    public final static String BROWSER_NAME = "Chrome";

    public static String SCENARIO_NAME;

    public static MainPage MAIN_PAGE;
    public static ComputersPage COMPUTERS_PAGE;
    public static DesktopsPage DESKTOPS_PAGE;

    public final static String DATABASE_URL = "http://localhost:8086";
    public final static String DATABASE_NAME = "clientSide";
    public final static String BUILD_ID = "1";
    public final static String PROJECT_NAME = "nopcommerce";
    public final static String ENV_NAME = "test";


}
