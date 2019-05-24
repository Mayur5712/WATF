/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   20-05-2019
 *********************************************************/

package com.cloud.pageActions;

import com.cloud.ObjRepo.ItemDetailsObj;
import com.cloud.core.Actions;
import com.cloud.core.Constants;
import com.cloud.core.Log;
import com.cloud.managers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class SearchItemsPage extends Actions {

    WebDriver driver;
    public SearchItemsPage(WebDriver driverInstance){
        super(driverInstance);
        this.driver = driverInstance;
        PageFactory.initElements(new AjaxElementLocatorFactory(driverInstance, Constants.DRIVER_WAIT), this);
    }

    @FindBy(xpath = "//a[@class='a-link-normal a-text-normal']")
    private List<WebElement> searchList;


    @FindBy(id = "priceblock_ourprice")
    private WebElement itemPrice;


    public String returnList(ItemDetailsObj itemDetailsObj){

        List<WebElement> receivedList = searchList;
        String winHandleBefore = driver.getWindowHandle();
        String itemName = null;

        for (WebElement item: receivedList
             ) {
            Log.info(item.getText());
            if(item.getText().contains(itemDetailsObj.ItemName) &&
                    item.getText().contains(itemDetailsObj.Memory) &&
                    item.getText().contains(itemDetailsObj.Color)){{
            item.click();
            itemName = item.getText();
            break;
            }
            }

        }

        WebDriverManager.switchTOAnotherWindow(driver, 1);
        Log.info("-------------------------------------");
        Log.info(itemPrice.getText());
        Log.info("-------------------------------------");
        return itemName;

    }


}
