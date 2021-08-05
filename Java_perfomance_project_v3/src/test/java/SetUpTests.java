import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.ComputersPage;
import pages.DesktopsPage;
import pages.MainPage;
import util.Constants;
import util.WebDriverHolder;

import static util.Constants.*;
import static util.Constants.DESKTOPS_PAGE;


public class SetUpTests {

    private WebDriver driver;

    @BeforeClass
    protected void setUpBrowser(){
        WebDriverManager.chromedriver().setup();
        SCENARIO_NAME = this.getClass().getSimpleName();
        WebDriverHolder.setDriver(BROWSER_FACTORY.startBrowser(BROWSER_NAME, driver));
        WebDriverHolder.getDriver().get("https://demo.nopcommerce.com/");
    }

    @BeforeMethod
    protected void setUp(){
        MAIN_PAGE = new MainPage();
        COMPUTERS_PAGE = new ComputersPage();
        DESKTOPS_PAGE = new DesktopsPage();
    }

    @AfterClass
    protected void teardown(){
        if(WebDriverHolder.getDriver() != null){
            WebDriverHolder.getDriver().quit();
        }
    }
}
