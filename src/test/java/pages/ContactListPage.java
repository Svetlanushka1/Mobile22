package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ContactListPage extends BasePage{

    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> allPhoneNumbers;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;

    @FindBy(xpath = "//*[@text='Contact list']")
    MobileElement textTitle;


    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    MobileElement menuMoreOptions;


    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement btnLogout;


    @FindBy(xpath = "//*[@class='android.widget.ImageButton']")
    MobileElement btnAddNewContact;

    public boolean validateContactListOpened()  {
        pause(5000);
        return isTextEqual(textTitle, "Contact list");
    }

    public AuthenticationPage logout() {
        clickBase(menuMoreOptions);
        clickBase(btnLogout);
        return new AuthenticationPage(driver);
    }

    public AddNewContactPage clickBtnAddNewContact() {
        pause(15000);
        clickBase(btnAddNewContact);
        return new AddNewContactPage(driver);
    }

    public boolean isPhoneNumberOnThePage(String phoneNumber) {
        boolean flag = false;
        while(!flag) {
            flag = isContainsText(allPhoneNumbers, phoneNumber);
            if (flag == false) isEndOfList();
        }
        return flag;
    }

      public boolean isEndOfList(){
        String beforeScroll =
                allPhoneNumbers
                        .get(allPhoneNumbers.size() - 1)
                        .getText() + " " +
                        allPhoneNumbers
                                .get(allPhoneNumbers.size() - 1)
                                .getText();
        scrollingList();
        String afterScroll =
                allPhoneNumbers
                        .get(allPhoneNumbers.size() - 1)
                        .getText() + " " +
                        allPhoneNumbers
                                .get(allPhoneNumbers.size() - 1)
                                .getText();
        if(beforeScroll.equals(afterScroll)) return true;
        return false;
    }

        public ContactListPage scrollingList(){
        pause(5000);

        MobileElement contact = contacts.get(contacts.size() - 1);

        Rectangle rect = contact.getRect();
        int xRow = rect.getX() + rect.getWidth()/2;
        int yRow = rect.getY() + rect.getHeight()/2;

        TouchAction<?> action = new TouchAction<>(driver);
        action
                .longPress(PointOption.point(xRow, yRow))
                .moveTo(PointOption.point(xRow, 0))
                .release()
                .perform();

        return this;
    }



    public boolean isContainsText(List<MobileElement> list, String text){
        for(MobileElement element : list){
            if(element.getText().contains(text)){
                return true;
            }
        }
        return false;
    }


}
