package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class ComputersPage extends BasePage{
    public ComputersPage(){super();}

    @FindBy(xpath = "//div[@class='item-grid']")
    private WebElement subCategoryBox;

    public ComputersPage openComputersPage(){
        computersTitle.click();
        wait.until(ExpectedConditions.elementToBeClickable(subCategoryBox));
        waitUntilPageIsFullyLoaded(wait);
        perfNavigationTiming.writeToInflux("ComputersPage");
//        perfNavigationTiming.getAllTiming();
//        System.out.println("NAV START: "+ perfNavigationTiming.getNavigationStart());
        System.out.println("Latancy: " + perfNavigationTiming.getLatency());
        System.out.println("getBackEndResponse: "+ perfNavigationTiming.getBackEndResponse());
        System.out.println("getTimeToInteract: "+ perfNavigationTiming.getTimeToInteract());
        System.out.println("getTimeToLoad: "+ perfNavigationTiming.getTimeToLoad());
        System.out.println("getTotalTime: "+ perfNavigationTiming.getTotalTime());
        System.out.println("Computers page is loaded");
        return this;
    }
}
