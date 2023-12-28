package tests;

import config.AppiumConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;



/** went SplashPage and after registration (from AuthenticationPage ) we can go to ContactListPage (positive registration) or stay in
 AuthenticationPage(negative registration) */
public class SignUpTests extends AppiumConfig {
    @Test
    public void positiveReg() {

        }

    @Test
    public void negativeRegEmptyEmail() {
        }
    @AfterMethod
    public void afterMethod() {
        /*
        if logged in => so we in ContactListPage and logout
        if ErrorAlert => so we need to press AlertBtn
         */

        }
}
