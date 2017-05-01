package com.warnerbros.flixstervideo;

import org.junit.*;
import businesscomponents.FunctionalComponents;
import pages.Fvhomepage;

public class Automation extends TestBase{
	Fvhomepage fvhomepage;
	String titleName = "One Tree Hill Season 8";
	String code = "";
	FunctionalComponents component;
	
	public Automation(String os, String version, String browser, String deviceName, String deviceOrientation) {
		super(os, version, browser, deviceName, deviceOrientation);
		// TODO Auto-generated constructor stub
		System.out.println("Constructor is being called");
	}
	
	@Test
	public void loginUvvU() throws Exception{
		component = new FunctionalComponents(this.driver);
		component.loginUvvU();
	}
//	@Test
//	public void newFV_NewUV_RedemptionFlow() throws Exception 
//	{
//		component = new FunctionalComponents(this.driver);
//		component.launchUrl();
//		component.redeemcode();
//		component.registrationredeem();
//		component.fvlogout();
//	}
	
}

