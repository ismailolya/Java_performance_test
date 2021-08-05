package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class DesktopsPage extends BasePage{
    public DesktopsPage(){super();}

    @FindBy(xpath = "//div[@class='picture']//a[@title='Show products in category Desktops']")
    public WebElement desktopsPageButton;

    @FindBy(xpath = "//div[@class='item-grid']//div[@class='picture']/a")
    public List<WebElement> desktopsList;

    @FindBy(xpath = "//button[@id='add-to-cart-button-1']")
    public WebElement addToCartButtonOnDesktopPage;

    @FindBy(xpath = "//div[@class='product-name']/h1")
    public WebElement openedDesktopTitle;

    public DesktopsPage openDesktopsPage(){
        wait.until(ExpectedConditions.elementToBeClickable(desktopsPageButton));
        desktopsPageButton.click();
        System.out.println("Cliked on desktops page");
        wait.until(ExpectedConditions.elementToBeClickable(desktopsList.get(0)));
        waitUntilPageIsFullyLoaded(wait);
        perfNavigationTiming.writeToInflux("DesktopsPage");
        System.out.println("Desktop list is appeared");
        return this;
    }

    public DesktopsPage openFirstDesktop(){
        String firstDesktopInTheListName = desktopsList.get(0).getText();
        System.out.println("Let's grab first desktop" + firstDesktopInTheListName);
        desktopsList.get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonOnDesktopPage));
        Assert.assertTrue(openedDesktopTitle.getText().contains(firstDesktopInTheListName));
        waitUntilPageIsFullyLoaded(wait);
        //perfNavigationTiming.writeToInflux("FirstDesktopPage");
        System.out.println("Asserted that desktop we wanted to open and actually opened are the same");
        return this;
    }

}
