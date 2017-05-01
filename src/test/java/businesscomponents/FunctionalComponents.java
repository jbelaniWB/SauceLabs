package businesscomponents;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertFalse;


import com.warnerbros.flixstervideo.TestBase;

import pages.CommonRepository;
import pages.FVmyaccountpage;
import pages.FVsignuppage;
import pages.Fvhomepage;
import pages.Redeempage;
import pages.UVpage;
import pages.UVpage.UVhomepgElements;
import pages.UVsignuppage;
import pages.FVsignuppage.Fvsignuppgelements;
import pages.Fvhomepage.Fvhomepgelements;

public class FunctionalComponents {
	public WebDriver driver;
	Fvhomepage fvhomepage;
	Redeempage redeempage;
	FVsignuppage fvsignuppage;
	FVmyaccountpage fvmyaccountpage;
	UVsignuppage uvsignuppage;
	FunctionalComponents component;
	UVpage uvpage;
	
	String timeStamp = "",
			uvURL="https://www.myuv.com/",
			UVsignupURL="https://www.myuv.com/register/create-library";
	
	String titleName = "One Tree Hill Season 8";
	String code = "KQW9QC9VT947";

	public FunctionalComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//component = new FunctionalComponents(this.driver);
		fvhomepage = new Fvhomepage(this.driver);
		redeempage = new Redeempage(this.driver);
		fvsignuppage = new FVsignuppage(this.driver);
		fvmyaccountpage = new FVmyaccountpage(this.driver);
		uvsignuppage = new UVsignuppage(this.driver);
		uvpage = new UVpage(this.driver);
	}

	public void launchUrl() throws Exception {
		fvhomepage.visitPage();
		fvhomepage.verifyHomepage();
	}

	public void redeemcode() throws Exception {
		fvhomepage.getElement(Fvhomepage.Fvhomepgelements.redeem).click();
		waitforPageLoad(driver);
		fvhomepage.getElement(Fvhomepage.Fvhomepgelements.searchtextbox).sendKeys(titleName);
		Thread.sleep(4000);
		WebElement common = fvhomepage.getElement(Fvhomepage.Fvhomepgelements.searchbtn);
		clickElementJscript(driver, common);
		// fvhomepage.getElement(Fvhomepage.Fvhomepgelements.searchbtn).click();
		waitforPageLoad(driver);
		common = fvhomepage.getElement(Fvhomepage.Fvhomepgelements.searchresult);
		Thread.sleep(5000);
		clickElementJscript(driver, common);
		waitforPageLoad(driver);
		fvhomepage.getElement(Fvhomepage.Fvhomepgelements.enterredeemcode).sendKeys(code);
		Thread.sleep(3000);
		redeempage.resetToken(code);
		fvhomepage.getElement(Fvhomepage.Fvhomepgelements.redeemcontinue).click();

		Thread.sleep(7000);
		redeempage.validRedeemcode();
	}

	public void registrationredeem() throws Exception {
		fvsignuppage.getElement(FVsignuppage.Fvsignuppgelements.clickhere).click();
		waitforPageLoad(driver);
		Thread.sleep(2000);
		String timeStamp = getTimeStamp();
		String FVEmailAddress = "test_FV_" + timeStamp + "@mailinator.com";
		fvsignuppage.getElement(Fvsignuppgelements.EmailAddress).sendKeys(FVEmailAddress);
		Thread.sleep(3000);
		fvsignuppage.FVSignupForm();
		scrollDown(2);
		fvsignuppage.getElement(FVsignuppage.Fvsignuppgelements.SameUVoption).click();
		Thread.sleep(2000);
		fvsignuppage.getElement(FVsignuppage.Fvsignuppgelements.continuebutton).click();
		Thread.sleep(20000);
		waitforPageLoad(driver);
		redeempage.checkOptin();
		Thread.sleep(6000);
		redeempage.getElement(Redeempage.Redeempgelements.completeredemption).click();
		waitforPageLoad(driver);
		Thread.sleep(4000);
		redeempage.validateTitleRedemption();
		redeempage.validateRegistratrionredemption();
	}

	public void fvlogout() throws Exception {
		Thread.sleep(2000);
		driver.navigate().refresh();
		fvmyaccountpage.getElement(FVmyaccountpage.FVmyaccntpgelements.myprofile).click();
		Thread.sleep(2000);
		fvmyaccountpage.getElement(FVmyaccountpage.FVmyaccntpgelements.fvlogout).click();
		Thread.sleep(3000);
		WebElement login = fvhomepage.getElement(Fvhomepgelements.fvlogin);
		if (login.isDisplayed()) {
			assertFalse("Verify Logout - Logout successful", false);
		} else {
			assertFalse("Verify Logout - Verify Logout ", true);
		}
	}
	
	public void createUvvU() throws Exception {
//		timeStamp = getTimeStamp();
//		String UVEmailAddress ="test_UV_" + timeStamp +"@mailinator.com";
//		driver.get(UVsignupURL);
//		driver.manage().window().maximize();
//		waitforPageLoad(driver);
//		//String UVEmail= dataTable.getData("UV_Data","UVEmailAddress");
//		Thread.sleep(2000);
//		uvsignuppage.getElement(UVsignuppage.UVsignuppgelements.EmailAddress).sendKeys(UVEmail);
//		Thread.sleep(2000);
//		uvsignuppage.getElement(UVsignuppage.UVsignuppgelements.ConfirmEmailAddress).sendKeys(UVEmail);
//		Thread.sleep(3000);
//		uvsignuppage.UVSignupForm();
//		uvsignuppage.getElement(UVsignuppage.UVsignuppgelements.createLibrary).click();
//		waitforPageLoad(driver);
//		uvsignuppage.verifyAccountCreation();
	}
	
	public void loginUvvU() throws Exception {
		uvpage.setUVCreds("test_FV_2015_143850@mailinator.com", "pass12");
		driver.get(uvURL);
		driver.manage().window().maximize();
		CommonRepository.waitforPageLoad(driver);
		uvpage.getElement(UVhomepgElements.signinlink).click();
		CommonRepository.getFrames(driver);
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.id("ui-id-1")));
		//driver.switchTo().frame(driver.findElement(By.className("cboxIframe")));
		Thread.sleep(3000);
		uvpage.uvSignin();
		uvpage.verifyLogin();
		Thread.sleep(5000);
		uvpage.getElement(UVhomepgElements.Myaccnt).click();
		Thread.sleep(2000);
		WebElement library=uvpage.getElement(UVhomepgElements.library);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()",library);
		Thread.sleep(5000);
		CommonRepository.waitforPageLoad(driver);
		assertFalse("Verify UV Library - UV Library Navigation", false);
		//uvpage.getElement(UVhomepgElements.viewlibrary).click();
		//Thread.sleep(12000);
		CommonRepository.waitforPageLoad(driver);
		//uvpage.verifyuvLibrary();
		Thread.sleep(4000);
		uvpage.uvLogout();
	}

	// Common script
	public static void clickElementJscript(WebDriver driver, WebElement common) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", common);
	}

	public static void waitforPageLoad(WebDriver driver) {
		/**
		 * This loop will rotate for 100 times to check If page Is ready after
		 * every 1 second given one sec wait time for every iteration. loop
		 * executes until the document load status is complete.
		 * 
		 * @author 416467
		 */
		for (int i = 0; i < 500; i++) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("Error page not loaded properly");
			}
			// Check for page ready state.
			if (((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete")) {
				break;
			}
		}
	}

	public String getTimeStamp() throws InterruptedException {
		String timeStamp = new SimpleDateFormat("MMdd_HHmmss").format(Calendar.getInstance().getTime());
		Thread.sleep(2000);
		return timeStamp;
	}

	public void scrollDown(int n) {
		try {
			for (int i = 0;; i++) {
				if (i >= n) {
					break;
				}
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)", "");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
