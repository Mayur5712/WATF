package testCases;

import com.cloud.core.Constants;
import com.cloud.managers.ExtentManager;
import com.cloud.managers.WebDriverManager;
import com.cloud.pageActions.Login;
import org.apache.log4j.xml.DOMConfigurator;
import com.cloud.fileHandler.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * @author mayur.waghmare
 *
 */
public class BaseTest extends Excel_Reader
{
	private WebDriverManager webDriverManager;
    private Excel_Reader loadTestCaseRepo;
    private LinkedHashMap<String, String[]> testCaseRepo;
    private WebDriver driver;
    private Login login;
    private PropertyFileHandler props;


    BaseTest() {
        webDriverManager = new WebDriverManager();
        loadTestCaseRepo = new Excel_Reader();
        props = new PropertyFileHandler();
    }

    WebDriver getDriver(){
        driver = WebDriverManager.getDriver();
        return driver;
    }


	@BeforeSuite
	public void initTestSuite() throws IOException {

		/*Initializing Test Cases Module*/
        testCaseRepo = loadTestCaseRepo.getTestCases(Constants.TEST_CASE_REPO,"Test Cases");

        /*Initializing Logger Module*/
        DOMConfigurator.configure("log4j.xml");

        /*Initializing Reporting Module */
        ExtentManager.extent_Report = ExtentManager.getInstance();

		/*Initializing Browser Module*/
		webDriverManager.startWebDriverInstance();

		/* Login into Application*/
        login = new Login(getDriver());
        login.loginInApp(props.getPropertyValue("USERNAME"),
                props.getPropertyValue("PASSWORD"));

	}

    @AfterTest
    public void afterTest()
    {
        ExtentManager.extent_Report.flush();
        ExtentManager.getInstance().flush();
    }


    @AfterSuite
    public void afterSuite() throws IOException {
        login.logout();
        getDriver().quit();
    }


    protected void init_TestCase(String TestCaseID)
    {
        ExtentManager.markup = null;
        String testCat = testCaseRepo.get(TestCaseID)[0];
        String testCaseName = testCaseRepo.get(TestCaseID)[1];
        String testDesc = testCaseRepo.get(TestCaseID)[2];
        testDesc = testDesc.replace("\n", "<br/>");
        ExtentManager.extent_Logger = ExtentManager.extent_Report.createTest(TestCaseID + " : " + testCaseName, testDesc);
        ExtentManager.extent_Logger.assignCategory(testCat);
        ExtentManager.extent_Logger.info("Test case initialize");
    }
}

