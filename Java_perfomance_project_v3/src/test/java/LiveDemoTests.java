import org.testng.annotations.Test;
import util.Constants;

public class LiveDemoTests extends SetUpTests{

    @Test(priority = 1)
    public void openMainPage(){
        Constants.MAIN_PAGE.openMainPage();
    }

    @Test(priority = 2)
    public void openComputersPage(){
        Constants.COMPUTERS_PAGE.openComputersPage();
    }

    @Test(priority = 3)
    public void openDesktopsPage(){
        Constants.DESKTOPS_PAGE.openDesktopsPage()
                .openFirstDesktop();
    }
}
