package commonMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import testBase.BaseClass;

public class Commonmethods extends BaseClass {

	public static void clickOnWebElement(String Xpath, String webelement) throws Exception {

		WebElement element = driver.findElement(By.xpath(or.getProperty(Xpath)));
		element.click();
		et.log(LogStatus.PASS, webelement + " has been clicked");
		Reporter.log(webelement + " has been clicked");
		screenShots();
	}

	public static void sendkeys(String Xpath, String data, String webelement) throws Exception {
		driver.findElement(By.xpath(or.getProperty(Xpath))).sendKeys(data);
		et.log(LogStatus.PASS, webelement + " has been Entered");
		Reporter.log(webelement + " has been Entered");
		screenShots();
	}

	public static boolean isDisplayed(String Xpath, String webelement) {
		try {
			driver.findElement(By.xpath(or.getProperty(Xpath))).isDisplayed();
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	// public static selectDropDownByValue(WebElement element, String value) throws
	// Exception {
	public static void selectDropDownByValue(WebElement element, String value) throws Exception {
		Select sel = new Select(element);
		sel.selectByValue(value);
		et.log(LogStatus.PASS, value + " has been Entered");
		screenShots();
		Reporter.log(value + " has been Entered");
	}

}
