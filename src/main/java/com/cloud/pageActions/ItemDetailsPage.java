/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   20-05-2019
 *********************************************************/

package com.cloud.pageActions;

import com.cloud.core.Actions;
import com.cloud.core.Constants;
import com.cloud.core.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ItemDetailsPage extends Actions {

    WebDriver driver;
    public ItemDetailsPage(WebDriver Driver) {
        super(Driver);
        this.driver = Driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(Driver, Constants.DRIVER_WAIT), this);
    }

    @FindBy(id="add-to-cart-button")
    private WebElement addToCartItem;

    public CartScreen addItemToCart(){
        CartScreen cartScreen = new CartScreen(driver);
        Log.info("Adding Item to Cart");
        click(addToCartItem);
        return  cartScreen;
    }
}
