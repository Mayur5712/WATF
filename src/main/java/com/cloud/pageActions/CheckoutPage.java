/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   22-05-2019
 *********************************************************/

package com.cloud.pageActions;

import com.cloud.ObjRepo.DeliveryAddressObj;
import com.cloud.core.Actions;
import com.cloud.core.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.security.cert.TrustAnchor;
import java.util.List;

public class CheckoutPage extends Actions {

    public CheckoutPage(WebDriver Driver) {
        super(Driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(Driver, Constants.DRIVER_WAIT), this);
    }

    @FindBy(xpath = "//div[contains(@id,'address-book-entry-')]")
    private List<WebElement> wAvailableSavedAddress;

    @FindBy(id = "enterAddressFullName")
    private WebElement wFullName;

    @FindBy(id = "enterAddressPhoneNumber")
    private WebElement wMobileNo;

    @FindBy(id = "enterAddressPostalCode")
    private WebElement wPinCode;

    @FindBy(id = "enterAddressAddressLine1")
    private WebElement wAddrLine1;

    @FindBy(id = "enterAddressAddressLine2")
    private WebElement wAddrLine2;

    @FindBy(id = "enterAddressCity")
    private WebElement wCity;

    @FindBy(xpath = "//input[@class='a-button-text submit-button-with-name']")
    private WebElement wDeliverButton;


    private void fillNewAddressForm(DeliveryAddressObj address){
        type(wFullName, address.FullName);

        type(wMobileNo, address.MobileNo);

        type(wAddrLine1, address.AddrLine1);

        type(wPinCode, address.PinCode);

        type(wCity, address.Town);

        Assert.assertTrue(isDisplayed(wDeliverButton));
        click(wDeliverButton);
    }


    public void selectAddressToCheckout(DeliveryAddressObj address){
        List<WebElement> savedAddress = wAvailableSavedAddress;
        boolean isAddrSaved = false;
        for(WebElement currentAddress: savedAddress){
            if(currentAddress.findElement(By.tagName("b")).getText().
                    equalsIgnoreCase(address.FullName)){
                currentAddress.findElement(By.tagName("a")).click();
                isAddrSaved = true;
                click(wDeliverButton);
            }
        }

        if(!isAddrSaved){
            fillNewAddressForm(address);
        }

    }

}
