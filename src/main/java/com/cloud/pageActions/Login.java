/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   22-05-2019
 *********************************************************/

package com.cloud.pageActions;

import com.cloud.core.Actions;
import com.cloud.core.Constants;
import com.cloud.fileHandler.PropertyFileHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

public class Login extends Actions {
    WebDriver driver;
    public Login(WebDriver Driver) {
        super(Driver);
        this.driver = Driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(Driver, Constants.DRIVER_WAIT), this);
    }


    @FindBy(id = "ap_email")
    private WebElement wUserName;

    @FindBy(id = "continue")
    private WebElement wContinue;

    @FindBy(id="ap_password")
    private WebElement wPassword;

    @FindBy(id="signInSubmit")
    private WebElement wLoginBtn;

    @FindBy(id="nav-item-signout")
    private WebElement wLogout;

    /**
     * Sign in Application
     * @param userName
     * @param password
     */
    public void loginInApp(String userName, String password){

        click(wSignInButton);
        type(wUserName, userName);
        click(wContinue);
        type(wPassword, password);
        click(wLoginBtn);

    }

    /**
     * Sign out from Application
     * @throws IOException
     */
    public void logout() throws IOException {
        driver.navigate().to(PropertyFileHandler.getPropertyValue("URL"));
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);

        actions
                .moveToElement(wSignInButton)
                .build()
                .perform();
        click(wLogout);
    }

}

