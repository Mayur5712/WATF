package com.cloud.managers;

import com.cloud.browser.Chrome;
import com.cloud.browser.IBrowser;
import com.cloud.core.Log;
import com.cloud.fileHandler.PropertyFileHandler;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> Driver=new ThreadLocal<>();

    private static final Logger LOGGER = Logger.getLogger(Class.class.getName());
    //private DesiredCapabilityBuilder desiredCapabilityBuilder;

    public WebDriverManager(){
        //desiredCapabilityBuilder = new DesiredCapabilityBuilder();
    }

    public static WebDriver getDriver()
    {
        return Driver.get();
    }

    protected static void setDriver(WebDriver driver){
        Driver.set(driver);
    }

    private WebDriver initialiseDriver(){
        WebDriver currentDriverSession = null;
        try {
            String Browser = PropertyFileHandler.getPropertyValue("Browser");
            LOGGER.info("Browser Selected, "+Browser);
            switch (Browser) {
                case "CHROME":
                    IBrowser GetBrowser = new Chrome();
                    currentDriverSession = GetBrowser.OpenBrowser();
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            Log.error(e.toString());
        }
        return currentDriverSession;
    }

    public void startWebDriverInstance(){
        WebDriver currentWebDriverSession = initialiseDriver();
        setDriver(currentWebDriverSession);
    }

    public static void switchTOAnotherWindow(WebDriver driver,int windowIndex){
        Set handles = driver.getWindowHandles();
        String[] individualHandle = new String[handles.size()];
        Iterator it = handles.iterator();
        int i =0;
        while(it.hasNext())
        {
            individualHandle[i] = (String) it.next();
            i++;
        }
        Log.info("Switching to Window "+windowIndex);
        driver.switchTo().window(individualHandle[windowIndex]);
    }
}
