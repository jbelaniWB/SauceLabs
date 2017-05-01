package pages;

import static org.junit.Assert.assertFalse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class UVsignuppage{

	public WebDriver driver;

	public UVsignuppage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement common;
	String UVEmailAddress= "";
	String UVPassword= "";

	public enum UVsignuppgelements {
		password, confirmPassword, firstName, lastName,ConfirmEmail,TermsofUse,EmailAddress, ConfirmEmailAddress, 
		ultravioletUsername, createLibrary, ultravioletUsername1;
	}

	public void UVSignupForm() throws InterruptedException
	{
		getElement(UVsignuppage.UVsignuppgelements.firstName).sendKeys("QAoffshore");
		Thread.sleep(2000);
		getElement(UVsignuppage.UVsignuppgelements.lastName).sendKeys("Test");
		Thread.sleep(2000);
		getElement(UVsignuppgelements.ultravioletUsername).clear();
		String UVEmail= "";
		getElement(UVsignuppgelements.ultravioletUsername).sendKeys(UVEmail);
		Thread.sleep(2000);
		getElement(pages.UVsignuppage.UVsignuppgelements.password).sendKeys(UVPassword);
		Thread.sleep(2000);
		getElement(UVsignuppage.UVsignuppgelements.confirmPassword).sendKeys(UVPassword);
		Thread.sleep(2000);
		getElement(UVsignuppage.UVsignuppgelements.TermsofUse).click();
		Thread.sleep(2000);
	}
	public void verifyAccountCreation()
	{
		WebElement myaccnt=driver.findElement(By.xpath(".//*[@id='block-ultraviolet-account-menu']/ul/li[2]/a"));
		if(myaccnt.isDisplayed())
		{
			assertFalse("Verify UVAccountCreation - Validate the UVAccount Creation ", false);
		}
		else
		{
			assertFalse("Verify UVAccountCreation - Validate the UVAccount Creation ", true);
		}
	}
	public WebElement getElement(UVsignuppgelements Webelement)
	{
		switch (Webelement) {
		case EmailAddress:
			common=driver.findElement(By.id("edit-email"));
			break;
		case ConfirmEmailAddress:
			common=driver.findElement(By.id("edit-email-confirm"));
			break;
		case ultravioletUsername:
			common=driver.findElement(By.id("edit-username"));
			break;
		case password:
			common=driver.findElement(By.id("edit-password-pass1"));
			break;
		case confirmPassword:
			common=driver.findElement(By.id("edit-password-pass2"));
			break;
		case firstName:
			common=driver.findElement(By.id("edit-first-name"));
			break;
		case lastName:
			common=driver.findElement(By.id("edit-last-name"));
			break;
		case TermsofUse:
			common=driver.findElement(By.id("edit-terms-of-use"));
			break;
		case createLibrary:
			common=driver.findElement(By.id("edit-complete"));
			break;
		default:
			break;
		}
		return common;
	}
}
