package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactListPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.SplashPage;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void beforeClass() {
        new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                        .email("testqa20@gmail.com")
                        .password("123456Aa$")
                        .build());
    }

    @Test
    public void positiveAddNewContact(){

        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("oleg")
                        .lastName("sher")
                        .email("aaaa@sher.biz")
                        .phone("46567658567773")
                        .address("jsdhfgkfdjhgdkf")
                        .description("ererteryrty")
                        .build())
                .isPhoneNumberOnThePage("14456567567"));
    }

    @Test
    public void negativeTestEmptyPhone(){}

}
