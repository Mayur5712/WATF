/**
 * 
 */
package com.cloud.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class Actions {
	private static WebDriver driverInstance;
	public WebDriverWait wait;
	WebDriver driver;

	public Actions(WebDriver Driver) {
		this.driverInstance = Driver;
		wait = new WebDriverWait( driverInstance, Constants.DRIVER_WAIT );


	}

	@FindBy(id="twotabsearchtextbox")
	private WebElement wSearchBar;

	@FindBy(id = "nav-search-submit-text")
	private WebElement wSearchButton;

	@FindBy(id = "nav-link-yourAccount")
	public WebElement wSignInButton;

	protected void click(WebElement ElementToClick) {
		try {
			if (ElementToClick != null) {
				wait.until( elementToBeClickable( ElementToClick ) );
				ElementToClick.click();
				Log.info( "Click on element" );
			} else {
				Log.error( "Element could not found" );
			}

		} catch (Exception e) {
			Log.error( "Error while clicking the element" + e.toString() );
		}
	}

	public void type(WebElement ElementToType, String StringToType) {
		try {
			if (ElementToType != null) {
				wait.until( visibilityOf( ElementToType ) );
				ElementToType.clear();
				ElementToType.sendKeys( StringToType );
				Log.info( "Enter the text" );
			} else {
				Log.error( "Element could not found" );
			}

		} catch (Exception e) {
			Log.error( "Error while writting the element" + e.toString() );
		}
	}

	public void submit(WebElement ElementToSubmit) {
		try {
			if (ElementToSubmit != null) {
				wait.until( visibilityOf( ElementToSubmit ) );
				ElementToSubmit.submit();
				Log.info( "Submit the webelement" );
			} else {
				Log.error( "Element could not found" );
			}

		} catch (Exception e) {
			Log.error( "Error while submitting the element" + e.toString() );
		}
	}

	public void select_DropDown_Using_Value(WebElement DropDownEle, String String_To_Select) {
		try {
			wait.until( visibilityOf( DropDownEle ) );
			new Select( DropDownEle ).selectByVisibleText( String_To_Select );
			Log.info( String_To_Select + " is selected" );
		} catch (Exception e) {
			Log.error( e.toString() );
		}
	}

	public void select_DropDown_Using_Index(WebElement DropDownEle, int Index) {
		try {
			wait.until( visibilityOf( DropDownEle ) );
			new Select( DropDownEle ).selectByIndex( Index );

		} catch (Exception e) {
			Log.error( e.toString() );
		}
	}

	/**
	 * This function will return whether the element given is displayed or not
	 *
	 * @param ElementToCheck
	 * @return
	 */
	public boolean isDisplayed(WebElement ElementToCheck) {
		WebDriverWait wait = new WebDriverWait( driverInstance, Constants.DRIVER_WAIT );
		try {
			wait.until( visibilityOf( ElementToCheck ) );
			return ElementToCheck.isDisplayed();
		} catch (Exception E) {
			Log.error( "Element is not getting displayed" );
			return false;
		}
	}


	public static String[][] ReadDataForDP() {
		String[][] Temp = null;
		return Temp;
	}


	public boolean IsPresentInTable(WebElement TableID, String EntryToSearch, int ColIdx)
			throws InterruptedException {


		List<WebElement> GetRowsInTable = TableID.findElements( By.tagName( "tr" ) );

		for (WebElement CurrRow : GetRowsInTable) {
			List<WebElement> GetColInTable = CurrRow.findElements( By.tagName( "th" ) );
			if (GetColInTable.size() > 0) {
				continue;
			} else {
				GetColInTable = CurrRow.findElements( By.tagName( "td" ) );
				System.out.println( "Entry found : " + GetColInTable.get( ColIdx ).getText() );

				if (GetColInTable.get( ColIdx ).getText().equalsIgnoreCase( EntryToSearch )) {
					System.out.println( "Entry present in table : " + GetColInTable.get( ColIdx ).getText() );

					return true;

				}

			}
		}

		return false;
	}


	public void searchItemFromSearchBar(String itemToSearch){
		Log.info("Searching "+itemToSearch +" from search bar");
		type(wSearchBar, itemToSearch);
		wSearchButton.submit();
	}



}





