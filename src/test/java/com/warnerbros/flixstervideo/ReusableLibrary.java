package com.warnerbros.flixstervideo;

import org.openqa.selenium.WebDriver;

public abstract class ReusableLibrary {
	/**
	 * The {@link WebDriver} object
	 */
	protected WebDriver driver;

	protected TestBase testBase;

	public ReusableLibrary(TestBase testBase) {
		this.testBase = testBase;

		this.driver = testBase.getDriver();
	}
}
