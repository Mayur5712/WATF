/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   20-05-2019
 *********************************************************/

package com.cloud.pageActions;

import com.cloud.core.Actions;
import com.cloud.core.Constants;
import com.cloud.core.Log;
import com.cloud.core.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.List;


public class CartScreen extends Actions {

    WebDriver driver;
    public CartScreen(WebDriver Driver) {
        super(Driver);
        this.driver = Driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(Driver, Constants.DRIVER_WAIT), this);
    }

    @FindBy(id = "nav-cart")
    private WebElement wCartButton;

    @FindBy(xpath = "//div[@class='sc-list-body sc-java-remote-feature']")
    private List<WebElement> wItemsInCart;

    @FindBy(id = "sc-buy-box-ptc-button")
    private WebElement proceedToBuyBtn;

    public void navigateToCartScreen(){
        Log.info("Navigating to Cart Screen");
        click(wCartButton);
    }


    public void sendItemToCheckOut(String itemToCheckOut, int quantity){
        WebElement getCheckoutItemObject = getValidCartItemObject(itemToCheckOut);
        Assert.assertNotNull(getCheckoutItemObject);
        setQuantityOfItem(getCheckoutItemObject, quantity);
        setItemAsGift(getCheckoutItemObject);
        navigateToCheckOut().selectAddressToCheckout(Utils.fillDeliveryAddObj());
    }


    /**
     *
     * @param itemName
     * @return
     */
    public WebElement getValidCartItemObject(String itemName){
        List<WebElement> availableItemsInCart = wItemsInCart;
        WebElement itemToBuy = null;
        for(int i = 0; i < availableItemsInCart.size() ; i++) {
            try{
            WebElement tempItem = availableItemsInCart.get(i);
            List<WebElement> avalailableEle = tempItem.findElements(By.tagName("li"));
            for (WebElement currentItemName:avalailableEle) {
                if(currentItemName.getText().equalsIgnoreCase(itemName)){
                    itemToBuy = availableItemsInCart.get(i);
                    return itemToBuy;
                }
            }
            }
            catch(Exception ex){
                Log.error("Error ");
            }
        }
        return itemToBuy;
    }


    public void setQuantityOfItem(WebElement itemInCart, int orderQty){
        WebElement cartQtyDropDown = itemInCart.findElement(By
                .xpath("//select[@class='a-native-dropdown a-button-span8']"));

        select_DropDown_Using_Value(cartQtyDropDown, String.valueOf(orderQty));
    }

    public void setItemAsGift(WebElement itemInCart){
        click(itemInCart.findElement(By.tagName("input")));
    }

    public CheckoutPage navigateToCheckOut(){
        CheckoutPage checkoutPage =new CheckoutPage(driver);
        click(proceedToBuyBtn);
        return checkoutPage;
    }
}

