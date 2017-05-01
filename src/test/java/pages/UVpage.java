package pages;

import java.util.List;
import static org.junit.Assert.assertFalse;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class UVpage {
	public WebDriver driver;
	public WebElement common;
	String UVEmailAddress= "";
	String UVPassword= "";
	String Titlename= "";

	public UVpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public enum UVhomepgElements {

		signinlink, cancel,UVloginbutton,UVUsername, UVPassword, Keepmeloggedcheck,
		viewlibrary,usersettings,Myaccnt,logout,unlink,savechanges, relink,changepassword,
		confirmNewpassword, unlinksavechanges, linkedServices, library, unlinkfromUVsite;
	}	
	public void unlinkLinkedServices() throws InterruptedException
	{
		if(getElement(UVhomepgElements.linkedServices).isEnabled())
		{
			getElement(UVhomepgElements.linkedServices).click();
			//CommonRepository.waitforPageLoad(driver);
			getElement(UVhomepgElements.unlinkfromUVsite).click();
			Thread.sleep(3000);
			//CommonRepository.waitforPageLoad(driver);
			if(getElement(UVhomepgElements.relink).isDisplayed())
			{
				System.out.println("Relink is Displayed");
				assertFalse("Verify UV unlink - UV unlink", false);
			}
			else
			{
				System.out.println("Relink is Either not Displayed or the Element not Visisble");
				assertFalse("Verify UV unlink - UV unlink", true);
			}
		}
	}
	public void switchtoFrame(String browser)
	{
		switch (browser) {
		case "Firefox":  
			//Switch frame
			driver.switchTo().frame(0);
			break;
		case "Chrome":
			//Switch frame
			driver.switchTo().frame(1); 
			break;
		}
	}
	public void uvSignin() throws InterruptedException
	{ 
		getElement(UVhomepgElements.UVUsername).sendKeys(UVEmailAddress);
		System.out.println(UVEmailAddress);
		Thread.sleep(1000);
		getElement(UVhomepgElements.UVPassword).sendKeys(UVPassword);
		Thread.sleep(1000);
		getElement(UVhomepgElements.Keepmeloggedcheck).click();
		Thread.sleep(1000);
		getElement(UVhomepgElements.UVloginbutton).click();
		Thread.sleep(15000);
		//CommonRepository.waitforPageLoad(driver);
	}
	public void verifyuvLibrary()
	{
		List<WebElement> titlelist=driver.findElements(By.cssSelector(".library-item-title"));
		String title=Titlename;
		boolean titleFound = false;
		System.out.println(titlelist.size());
		try{
			for(WebElement ele : titlelist) {
				String titledata = ele.getText().trim();
				System.out.println(titledata);
				if(titledata.contains(title)) {
					System.out.println(titledata);
					titleFound = true;
					System.out.println(titledata);
					break;
				}
			}
		}catch(Exception e) {
			List<WebElement> titlelist1=driver.findElements(By.cssSelector(".library-item-title"));
			for(WebElement ele : titlelist1) {
				String titledata = ele.getText().trim();
				if(titledata.contains(title)) {
					titleFound = true;
					break;
				}
			}
		}
		if(titleFound)
		{
			assertFalse("Verify Library - Verify the Colection for "+Titlename, false);	
		}
		else
		{
			assertFalse("Verify Library - Verify the Colection "+Titlename, true);	
		}
	}
	public void verifyLogin()
	{
		if(getElement(UVhomepgElements.Myaccnt).isDisplayed())
		{
			assertFalse("Verify UVLogin - Login Validation", false);	
		}
		else
		{
			assertFalse("Verify UVLogin - Login Validation", true);	
		}
	}
	public void uvLogout() throws InterruptedException
	{
		getElement(UVhomepgElements.Myaccnt).click();
		Thread.sleep(2000);
		WebElement logout=getElement(UVhomepgElements.logout);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", logout);
		//CommonRepository.waitforPageLoad(driver);
	}
	public void verifyuvLogout()
	{
		if(getElement(UVhomepgElements.signinlink).isDisplayed())
		{
			assertFalse("Verify UVlogout - Validation", false);
		}
		else
		{
			assertFalse("Verify UVlogout - Validation", true);
		}
	}
	public WebElement getElement(UVhomepgElements Webelement)
	{
		switch (Webelement) {
		case Keepmeloggedcheck:
			common=driver.findElement(By.id("kmsi"));
			break;
		case signinlink:
			common=driver.findElement(By.xpath(".//*[@id='block-ultraviolet-account-menu']/ul/li[2]/a"));
			break;
		case UVUsername:
			common=driver.findElement(By.id("username"));
			break;
		case UVPassword:
			common=driver.findElement(By.id("password"));
			break;
		case UVloginbutton:
			common=driver.findElement(By.id("login-btn"));
			break;
		case cancel:
			common=driver.findElement(By.id("cancel-btn"));
			break;
		case viewlibrary:
			common=driver.findElement(By.xpath(".//*[@id='block-ultraviolet-content']/div/div[1]/div[2]/div/div[3]/a"));
			break;
		case logout:
			common=driver.findElement(By.xpath(".//*[@id='block-ultraviolet-account-menu']/ul/li[2]/ul/li[3]/a"));
			break;
		case Myaccnt:
			common=driver.findElement(By.xpath(".//*[@id='block-ultraviolet-account-menu']/ul/li[2]/a"));
			break;
		case usersettings:
			common=driver.findElement(By.xpath(".//*[@id='block-ultraviolet-content']/div/div[1]/div[1]/div/div[3]/a"));
			break;
		case unlink:
			common=driver.findElement(By.xpath("//button[@class='link_service unlink']"));
			break;
		case unlinkfromUVsite:
			common=driver.findElement(By.xpath("//*[@id='edit-warnerbros']/div[1]/div[3]/a[1]"));
			break;
		case changepassword:
			common=driver.findElement(By.id("edit-password-pass1"));
			break;
		case confirmNewpassword:
			common=driver.findElement(By.id("edit-password-pass2"));
			break;
		case savechanges:
			common=driver.findElement(By.id("edit-save-changes"));
			break;
		case linkedServices:
			common=driver.findElement(By.xpath(".//*[@id='block-ultraviolet-local-tasks']/nav/ul/li[3]/a"));
			break;
		case relink:
			common=driver.findElement(By.cssSelector(".action.action--link.icon.icon-before.link"));
			break;
		case unlinksavechanges:
			common=driver.findElement(By.id("edit-submit"));
			break; 
		case library:
			common=driver.findElement(By.xpath(".//*[@id='block-ultraviolet-account-menu']/ul/li[2]/ul/li[1]/a"));
			break;	
		default:
			break;
		}
		return common;
	}
	
	public void setUVCreds(String uvEmail, String uvPass) throws Exception{
		this.UVEmailAddress = uvEmail;
		this.UVPassword = uvPass;
	}
}
