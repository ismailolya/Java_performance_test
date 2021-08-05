package pages;

import navigationtiming.PerfNavigationTiming;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static util.WebDriverHolder.getDriver;

public class BasePage {

    protected WebDriverWait wait;

    public BasePage(){
        wait = new WebDriverWait(getDriver(),100);
        PageFactory.initElements(getDriver(), this);
    }

    protected PerfNavigationTiming perfNavigationTiming = new PerfNavigationTiming();

    protected void waitUntilPageIsFullyLoaded(WebDriverWait wait){
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    //Locators
    @FindBy(xpath = "//ul[@class ='top-menu notmobile']//a[@href = '/computers']")
    protected WebElement computersTitle;

}
