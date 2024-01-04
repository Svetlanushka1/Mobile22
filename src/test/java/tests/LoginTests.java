package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;


/** went SplashPage and after login (from AuthenticationPage ) we can go to ContactListPage (positive login) or stay in
 AuthenticationPage(negative login) */

public class LoginTests extends AppiumConfig {
    boolean flagIsUserLogin = false;
    boolean flagIsPopUpErrorDisplays = false;

    @Test()
    public void positiveLogin()  {
        flagIsUserLogin = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().
                login(UserDTO.builder()
                        .email("testqa20@gmail.com")
                        .password("123456Aa$")
                        .build())
                        .validateContactListOpened());
/*    goto splash page
    wait and goto Authentication page
    validate if ContactListPage is opened
 */
     }


    @Test()
    public void negativeLoginEmptyEmail() {
        flagIsPopUpErrorDisplays = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("123456Aa$")
                .clickLoginBtnNegative().validateErrorTitleAlertCorrect());
    }


     @AfterMethod
//
//        /*
//        if logged in => so we in ContactListPage and logout
//        if ErrorAlert => so we need to press AlertBtn
//         */
    public void afterMethod() {
        if(flagIsUserLogin) {
            flagIsUserLogin = false;
            new ContactListPage(driver).logout();
        } else if(flagIsPopUpErrorDisplays) {
            flagIsPopUpErrorDisplays = false;
            new AuthenticationPage(driver).clickOkBtnAlert();
        }
    }



}
