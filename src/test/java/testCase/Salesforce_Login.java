package testCase;

import java.util.Hashtable;

import org.testng.annotations.Test;

import pages.LoginPage;
import testBase.BaseClass;

public class Salesforce_Login extends BaseClass {

	@Test(dataProvider = "Data_Collections")
	public static void login(Hashtable<String, String> Testdata) throws Exception {

		et = er.startTest("TC001_QSP_Salesforce_login_Testcase");

		LoginPage.homepage(Testdata);
	}
}
