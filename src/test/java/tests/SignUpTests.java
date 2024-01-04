package tests;

import com.github.javafaker.Faker;
import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;



/** went SplashPage and after registration (from AuthenticationPage ) we can go to ContactListPage (positive registration) or stay in
 AuthenticationPage(negative registration) */
public class SignUpTests extends AppiumConfig {
    boolean flagIsUserLogin = false;
    boolean flagIsPopUpErrorDisplays = false;

    Faker faker = new Faker();
    // Generate a random email address
    String randomEmail = faker.internet().emailAddress();
    @Test
    public void positiveReg() {
        flagIsUserLogin = true;
        System.out.println("Random Email: " + randomEmail);

        Assert.assertTrue(new SplashPage(driver).goToAuthPage().
                registration(UserDTO.builder()
                        .email(randomEmail)
                        .password("123456Aa$")
                        .build())
                        .validateContactListOpened());

        }

    @AfterMethod
    public void afterMethod() {
        if(flagIsUserLogin) {
            flagIsUserLogin = false;
            new ContactListPage(driver).logout();
        } else if(flagIsPopUpErrorDisplays) {
            flagIsPopUpErrorDisplays = false;
            new AuthenticationPage(driver).clickOkBtnAlert();
        }
    }


    @Test()
    public void negativeRegistrationEmptyEmail() {
        flagIsPopUpErrorDisplays = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("123456Aa$")
                .clickRegBtnNegative().validateErrorTitleAlertCorrect());
    }

}
