package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactListPage extends BasePage{

    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@text='Contact list']")
    MobileElement textTitle;

    public boolean validateContactListOpened() throws InterruptedException {
        Thread.sleep(5000);
        return isTextEqual(textTitle, "Contact list");
    }
}
