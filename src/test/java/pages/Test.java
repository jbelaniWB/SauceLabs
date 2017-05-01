/*package pages;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;

public class CommonFunctions extends ReusableLibrary {
	
	public CommonFunctions(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	public WebDriverWait objWait; 
	public WebElement objElement;
	
	*//**
	 * This method is to wait for any element explicitly and click it
	 * @author Gopi
	 * @param strLocatorType,strObjProperty,strObjName,intWaitTime
	 * @return None
	*//*
	
	public void waitAndClickAnyElement(String strLocatorType, String strObjProperty, String strObjName, int intWaitTime) {
		objWait = new WebDriverWait(driver,intWaitTime);
		objElement = objWait.until(ExpectedConditions.elementToBeClickable(getElementByProperty(strObjProperty,strLocatorType)));
		try {
			objElement.click();
		}
		catch(Exception e) {
			System.out.println("Element " + strObjName + " is not found, due to - " + e);
		}
	}
	
	*//**
	 * This method will wait for any element to load for maximum of 10 seconds
	 * @author Gopi
	 * @param strObjectProperty,strFindElementType
	 * @return Boolean
	*//*
	
	public Boolean waitForElementToLoad(String strObjectProperty,String strFindElementType) throws InterruptedException {
		boolean blnFlag = false;
		int intWaitTime = 2;
		do {
			Thread.sleep(2*1000);
			if(verifyElementExistence(strObjectProperty,strFindElementType)) {
				blnFlag = true;
				break;
			}
		} while(intWaitTime*1000<=10000);
		return blnFlag;
	}
	
	*//**
	 * This method will wait for any element to load for maximum of 20 seconds
	 * @author Gopi
	 * @param WebElement
	 * @return Void
	*//*
	
	public void waitForElementToLoad(WebElement objTestEle) throws InterruptedException {
		
		objWait = new WebDriverWait(driver,20);
		objElement = objWait.until(ExpectedConditions.elementToBeClickable(objTestEle));
		try {
			objElement.getSize();
		}
		catch(Exception e) {
			System.out.println("Element is not found, due to - " + e);
		}
	}
	
	*//**
	 * This method is to verify the elements' existence
	 * @author Gopi
	 * @param strObjectProperty,strFindElementType
	 * @return Boolean
	*//*
	
	public boolean verifyElementExistence(String strObjectProperty,String strFindElementType) {
		try {
			getElementByProperty(strObjectProperty,strFindElementType).getSize();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	*//**
	 * This method is to identify the web element
	 * @author Gopi
	 * @param strObjectProperty,strFindElementType
	 * @return WebElement
	*//*
	
	public WebElement getElementByProperty(String strObjectProperty,String strFindElementType) {
		try {
			if (strFindElementType.equalsIgnoreCase("CSS"))
				return driver.findElement(By.cssSelector(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("XPATH"))
				return driver.findElement(By.xpath(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("ID"))
				return driver.findElement(By.id(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("NAME"))
				return driver.findElement(By.name(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("LINKTEXT"))
				return driver.findElement(By.linkText(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("PARTIALLINK"))
				return driver.findElement(By.partialLinkText(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("TAG"))
				return driver.findElement(By.tagName(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("CLASS"))
				return driver.findElement(By.className(strObjectProperty));
			else
				return null;
		} catch (org.openqa.selenium.NoSuchElementException nsee) {
			System.out.println(nsee);
			return null;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	*//**
	 * This method is to switch to any frame
	 * @author Gopi
	 * @param strObjectProperty,strFindElementType
	 * @return Void
	*//*
	
	public void switchToAnyFrame(String strObjectProperty,String strFindElementType) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(getElementByProperty(strObjectProperty,strFindElementType));
		}
		catch (org.openqa.selenium.NoSuchElementException nsee) {
			System.out.println(nsee);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	*//**
	 * This method is to select the last encounter record
	 * @author Gopi
	 * @param None
	 * @return Void
	*//*
	public void selectEncounter() throws NoValueFoundException {
		boolean blnFlag = false;;
		try {
			String strEntTemp = "";
			String strExpectEnCnt = strEncounter;
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.name("mainwindow")));
			List<WebElement> objEncntRows = driver.findElements(By.xpath("(//table[@class='newui zebra'])[1]/tbody/tr"));
			for(int intInd=1;intInd<=objEncntRows.size();intInd++) {
				strEntTemp = driver.findElement(By.xpath("(//table[@class='newui zebra'])[1]/tbody/tr[" + intInd + "]/td[1]/a")).getText();
				if(strEntTemp.trim().equals(strExpectEnCnt.trim())) {
					driver.findElement(By.xpath("(//table[@class='newui zebra'])[1]/tbody/tr[" + intInd + "]/td[11]/a")).click();
					blnFlag = true;
					break;
				}
			}
		}
		catch (org.openqa.selenium.NoSuchElementException nsee) {
			System.out.println(nsee);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		if(!blnFlag) {
			throw new NoValueFoundException();
		}
	}
	
	*//**
	 * This method is to navigate to specified menu
	 * @author Gopi
	 * @param None
	 * @return strTestMenu
	*//*
	
	public void navigateToTestMenu(String strTestMenu) {
		try {
			switchToAnyFrame("mainwindow","name");
			driver.findElement(By.xpath("//*[@id='wc_menuicon']")).click();
			Thread.sleep(3000);
			Actions objAct = new Actions(driver);
			WebElement objMenu = driver.findElement(By.linkText(strTestMenu));
			objAct.moveToElement(objMenu).click().build().perform();
		}
		catch (org.openqa.selenium.NoSuchElementException nsee) {
			System.out.println(nsee);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	*//**
	 * This method is to read the encounter number
	 * @author Gopi
	 * @param None
	 * @return String
	 * @throws NoValueFoundExecption 
	*//*
	
	public String readEncounterNo() {
		String strEncounterNoTemp = "";
		String strEncounterNo = "";
		//boolean blnFlag = false;

		List<WebElement> objEntNos = driver.findElements(By.cssSelector("div#wc_enc_bar > div > span"));
		for(WebElement objEnt:objEntNos) {
			strEncounterNoTemp = objEnt.getText();
			strEncounterNo = strEncounterNoTemp.split(" ")[2];
			char chrEncounterLastDig = strEncounterNo.charAt(strEncounterNo.length()-1);
			try {
				int intVal = (int) chrEncounterLastDig;
				//blnFlag = true;
				break;
			}
			catch(Exception e) {
				//do nothing
			}
		}
		return strEncounterNo;
	}
	
	*//**
	 * This method is to choose the change the patient status
	 * @author Gopi
	 * @param None
	 * @return Void
	 * @throws InterruptedException  
	 * @throws NoValueFoundException 
	*//*
	
	public void selectPatientStatus() throws InterruptedException, NoValueFoundException {

		try {
			Select objDropDown = new Select(driver.findElement(By.id("station_id")));
			List<WebElement> lstOptions = objDropDown.getOptions();
			//String strPtStatus = "";
			boolean blnFlag = false;
			for(WebElement lstObj:lstOptions) {
				strPtStatus = lstObj.getText().trim();
				if(strPtStatus.contains("Exam B-2") || strPtStatus.contains("Exam B-1") || strPtStatus.contains("Exam B-4") || strPtStatus.contains("Exam B-5") || strPtStatus.contains("Exam B-6") || strPtStatus.contains("Exam B-3")) {
					blnFlag = true;
					new Select(driver.findElement(By.id("station_id"))).selectByVisibleText(strPtStatus);
					System.out.println("Patient status " + strPtStatus + "- is selected");
					Thread.sleep(2000);
					
					Actions objAct = new Actions(driver);
					WebElement objMenu = driver.findElement(By.xpath("//input[@value='Save']"));
					objAct.moveToElement(objMenu).click().build().perform();				
					//
				}
			}
			
			if(!blnFlag) {
				throw new NoValueFoundException();
			}
			

		}
		catch (ElementNotVisibleException e) {
			System.out.println("Error in selectPatientStatus, due to - " + e);
		}
	}

	*//**
	 * This method is to wait for an alert and accept/dismiss it
	 * @author Gopi
	 * @param strAction, intWaitTime
	 * @return Void
	*//*
	
	public void handleAlert(String strAction, int intWaitTime) {
		try {
			objWait = new WebDriverWait(driver,intWaitTime);
			objWait.until(ExpectedConditions.alertIsPresent());
			Alert objAlt = driver.switchTo().alert();
			if(strAction.equalsIgnoreCase("accept")) {
				objAlt.accept();
			}
			else {
				objAlt.dismiss();
			}
		}
		catch(Exception e) {
			System.out.println("Unable to recognize the alert, due to - " + e);
		}
	}
}
*/