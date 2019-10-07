package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.AutomationWorldByRahul.SeleniumTraining.DataCollection;
import com.AutomationWorldByRahul.SeleniumTraining.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties or;
	public static Properties config;
	public static ExcelReader e1 = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\Salesforce_login.xlsx");
	public static String testCaseName;
	public static ExtentReports er;
	public static ExtentTest et;
	public static Hashtable<String, String> testCaseRunMode = new Hashtable<String, String>();

	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Calendar.getInstance().getTime());

	@BeforeSuite
	public void testsite() throws IOException {

		er = new ExtentReports(System.getProperty("user.dir") + "\\src\\test\\resources\\extentReports\\ExtentReport_"
				+ timeStamp + ".html");

		config = new Properties();
		FileInputStream s1 = new FileInputStream(
				"C:\\Users\\Nehesh Sakpal\\eclipse-workspace\\com.salesforce_build\\src\\test\\resources\\properties\\config.properties");
		config.load(s1);

		or = new Properties();
		FileInputStream s2 = new FileInputStream(
				"C:\\Users\\Nehesh Sakpal\\eclipse-workspace\\com.salesforce_build\\src\\test\\resources\\properties\\or.properties");
		or.load(s2);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Calendar.getInstance().getTime());
		er = new ExtentReports(System.getProperty("user.dir") + "\\src\\test\\resources\\extentReports\\ExtentReport___"
				+ timeStamp + ".html");
		loadHashTable(testCaseRunMode, "Test_Cases", "TestCaseName", "Run_Mode");
	}

	@BeforeMethod
	public static void launchBrowser() {

		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Nehesh Sakpal\\eclipse-workspace\\com.salesforce_build\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.get(config.getProperty("link_url"));
		System.out.println("salesforce login page should be open ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}

	@AfterMethod
	public void close() {
		driver.close();
		et.log(LogStatus.INFO, "Excution has been completed for Test case:-" + testCaseName);
	}

	@AfterSuite(alwaysRun = true)
	public static void endTest() {

		er.endTest(et);
		er.flush();
	}

	@DataProvider
	public static Object[][] Data_Collections() {
		DataCollection d1 = new DataCollection(e1, "NEHESH_Salesforce", "TC001_QSP_Salesforce_login_Testcase");
		return d1.dataArray();
	}

	public static void screenShots() throws Exception {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Calendar.getInstance().getTime());

		String ReportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
				+ "\\src\\test\\resources\\screenShots\\ScreenShot____" + timeStamp + ".png";

		File destFile = new File(ReportDirectory);

		FileHandler.copy(scrFile, destFile);

		et.log(LogStatus.PASS, et.addScreenCapture(ReportDirectory));
	}

	public static void loadHashTable(Hashtable<String, String> testCaseRunMode, String SheetName, String KeyCol,
			String valueCol) {

		int row = e1.getRowCount(SheetName);

		for (int i = 1; i <= row; i++) {

			String key = e1.getCellData(SheetName, KeyCol, i);

			String val = e1.getCellData(SheetName, valueCol, i);

			testCaseRunMode.put(key, val);
		}

		System.out.println(testCaseRunMode);
	}

	/*
	 * public static boolean isExecutable(String TC_name) { testCaseName = TC_name;
	 * if (testCaseRunMode.get(testCaseName).equalsIgnoreCase("Y")) { skip = "No";
	 * return true; } else { skip = "Yes"; return false; }}
	 */

	public static void passLogStatus(String message) throws Exception {

		et.log(LogStatus.PASS, message);
		screenShots();
	}

}
