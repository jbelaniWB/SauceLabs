package pages;

import static org.junit.Assert.assertFalse;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Googlepage {

	public WebDriver driver;

	public Googlepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement common;
	public enum Googlepgelements{ 
		GmailID,Gmailpassword,next,Gmaillogin,fbokaybutton, Month, Day, Year;
	}
	String EmailAddress= "";
	String Password= "";
	public void Gpluslogin() throws InterruptedException
	{
		String windowtitle = "Google Accounts";
		String currentWindow = driver.getWindowHandle();
		Set<String> availableWindows = driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				String switchedWindowTitle=driver.switchTo().window(windowId).getTitle();
				if ((switchedWindowTitle.equals(windowtitle))||(switchedWindowTitle.contains(windowtitle)))
				{
					driver.manage().window().maximize();
					getElement(Googlepgelements.GmailID).sendKeys(EmailAddress);
					Thread.sleep(2000);
					getElement(Googlepgelements.next).click();
					Thread.sleep(2000);
					getElement(Googlepgelements.Gmailpassword).sendKeys(Password);
					Thread.sleep(2000);
					getElement(Googlepgelements.Gmaillogin).click();
					Thread.sleep(5000);				
				} else {
					driver.switchTo().window(currentWindow);
				}
			}
			driver.switchTo().window(currentWindow);
		}
	}

	public WebElement getElement(Googlepgelements element)
	{
		switch (element) {
		case GmailID:
			common=driver.findElement(By.id("Email"));
			break;
		case Gmailpassword:
			common=driver.findElement(By.id("Passwd"));
			break;
		case next:
			common=driver.findElement(By.id("next"));
			break;
		case Gmaillogin:
			common=driver.findElement(By.id("signIn"));
			break;
		default:
			break;
		}
		return common;
	}
}
