/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   20-05-2019
 *********************************************************/

package testCases;

import com.cloud.core.Utils;
import com.cloud.pageActions.CartScreen;
import com.cloud.pageActions.ItemDetailsPage;
import com.cloud.pageActions.SearchItemsPage;
import org.testng.annotations.Test;

import javax.rmi.CORBA.Util;

public class TestAddToCartAndCheckout extends BaseTest {

    @Test
    public void test_process_of_placing_product_into_cart() throws InterruptedException {

        String TestCaseId = "TC_1";
        init_TestCase(TestCaseId);

        /* Search Item from Search bar*/
        SearchItemsPage searchItemsPage = new SearchItemsPage(getDriver());
        String itemToSarchDetailName = Utils.itemDetailsObj().ItemName+" "+
                                        Utils.itemDetailsObj().Memory+" "+
                                        Utils.itemDetailsObj().Color;
        searchItemsPage.searchItemFromSearchBar(itemToSarchDetailName);
        String itemDetailName = searchItemsPage.returnList(Utils.itemDetailsObj());

        /* Add item in cart */
        ItemDetailsPage itemDetailsPage = new ItemDetailsPage(getDriver());
        itemDetailsPage
                .addItemToCart()
                .navigateToCartScreen();

        /* Checkout item */
        CartScreen cartScreen = new CartScreen(getDriver());
        cartScreen.sendItemToCheckOut(itemDetailName, Integer.valueOf(Utils.itemDetailsObj().ItemQty));


    }
}
