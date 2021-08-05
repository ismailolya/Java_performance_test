package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage{

    public MainPage(){super();}

    public MainPage openMainPage(){
        wait.until(ExpectedConditions.elementToBeClickable(computersTitle));
        perfNavigationTiming.writeToInflux("MainPage");
        return this;
    }
}
