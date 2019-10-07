package pages;

import java.util.Hashtable;

import org.testng.Assert;

import commonMethods.Commonmethods;
import testBase.BaseClass;

public class LoginPage extends BaseClass{
	
	public static void homepage(Hashtable<String, String> Testdata) throws Exception {
		
	String Actual_title = driver.getTitle();

	Assert.assertEquals(Actual_title, "CRM Software: Cloud Computing Solutions For Every Business - Salesforce");
	
	passLogStatus("User has been redirected to the " + Actual_title + " Application.");
	
	Commonmethods.clickOnWebElement("xlogin", "Login Button should be clicked");
//	Commonmethods.clickOnWebElement("xlogin_id2", "Login2 Button should be clicked");
	Commonmethods.sendkeys("xuser_id", Testdata.get("Login_id"), "Login ID");
	Commonmethods.sendkeys("xpasswd", Testdata.get("Password"), "Password entered");
	Commonmethods.clickOnWebElement("xsubmit", "Submit Button should be clicked");
	
}

}
