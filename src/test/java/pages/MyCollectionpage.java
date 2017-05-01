package pages;

import static org.junit.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MyCollectionpage{

	public WebDriver driver;

	public MyCollectionpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public enum MyCollectionpgelements {

		mycollection,getstarted,linkaccnts,clickhere, continuerelinkuv,yeslinkmyaccnt, continuelinkuv,
		watchnow,download,downloadpghdrtext, facebook, Google, zeroCollectiontext,uvmailtext;

	}
	public WebElement common;
	String UVEmailAddress= "";
	
	public void detectexistUV() throws InterruptedException
	{
		Thread.sleep(2000);
		String uvmailtext=getElement(MyCollectionpgelements.uvmailtext).getText();
		Thread.sleep(1000);
		if(uvmailtext.contains(UVEmailAddress))
		{
			assertFalse("Verify the UVemail detection - Detect Existing UVmail", false);
		}
		else
		{
			assertFalse("Verify the UVemail detection - Detect Existing UVmail", true);
		}
	}
	public void goCollectionandGetStarted() throws InterruptedException
	{
		getElement(MyCollectionpgelements.mycollection).click();
		Thread.sleep(3000);
		getElement(MyCollectionpgelements.getstarted).click();
		Thread.sleep(3000);
	}
	public void verifyCollectionSkipUV() throws InterruptedException
	{
		getElement(MyCollectionpgelements.mycollection).click();
		Thread.sleep(10000);
		WebElement getstarted=getElement(MyCollectionpgelements.getstarted);
		Thread.sleep(1000);
		if(getstarted.isDisplayed())
		{
			assertFalse("Verify the Mycollection - Verify MyCollection FV with no UV linked", false);
		}
		else
		{
			assertFalse("Verify the Mycollection - Verify MyCollection FV with no UV linked", true);
		}
	}
	public void verifyEmptyCollection() throws InterruptedException
	{
		getElement(MyCollectionpgelements.mycollection).click();
		CommonRepository.waitforPageLoad(driver);
		Thread.sleep(6000);
		String text=getElement(MyCollectionpgelements.zeroCollectiontext).getText();
		System.out.println(text);
		String test="Your Collection is empty";
		Thread.sleep(2000);
		if(text.equalsIgnoreCase(test))
		{
			assertFalse("Verify the Collection - Verify EmptyCollection", false);
		}
		else
		{
			assertFalse("Verify the collection - Verify EmptyCollection", true);
		}
	}
	public WebElement getElement(MyCollectionpgelements webelement)
	{
		switch (webelement) 
		{
		case mycollection:
			common=driver.findElement(By.linkText("My Collection"));
			break;
		case zeroCollectiontext:
			common=driver.findElement(By.xpath("//*[@id='title-results']/h3"));
			break;	
		case getstarted:
			common=driver.findElement(By.cssSelector(".btn.btn-lg.btn-uv"));
			break;
		case continuerelinkuv:
			common=driver.findElement(By.cssSelector(".btn.btn-primary.btn-lg.submit"));
			break;
		case linkaccnts:
			common=driver.findElement(By.xpath("//*[@id='collection-modal']/div/div/div[3]/a"));
			break;
		case clickhere:
			common=driver.findElement(By.linkText("Click here"));
			break;
		case uvmailtext:
			common=driver.findElement(By.xpath("//div[@class='uv-email-found ']/div[1]/label"));
			break;
		case yeslinkmyaccnt:
			common=driver.findElement(By.xpath("//div[@class='uv-email-found ']/div[1]/label"));
			break;
		case continuelinkuv:
			common=driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg submit']"));
			break;
		case watchnow:
			common=driver.findElement(By.cssSelector(".btn.btn-primary.btn-lg.watch"));
			break;
		case download:
			common=driver.findElement(By.cssSelector(".btn.btn-action.btn-lg.download"));
			break;
		case downloadpghdrtext:
			common=driver.findElement(By.xpath(".//*[@id='title-download']/div/h3"));
			break;
		case facebook:
			common=driver.findElement(By.xpath("//span[text()='Facebook']"));
			break;
		case Google:
			common=driver.findElement(By.xpath("//span[text()='Google']"));
			break;			
		}
		return common;	
	}
}