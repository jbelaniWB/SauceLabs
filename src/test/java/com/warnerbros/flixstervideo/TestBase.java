package com.warnerbros.flixstervideo;

import com.saucelabs.common.SauceOnDemandAuthentication;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

import java.net.URL;
import java.util.LinkedList;

import com.saucelabs.common.SauceOnDemandSessionIdProvider;

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs
 * using multiple browsers in parallel.
 * <p/>
 * The test also includes the {@link SauceOnDemandTestWatcher} which will invoke
 * the Sauce REST API to mark the test as passed or failed.
 *
 * @author Neil Manvar
 */
@Ignore
@RunWith(ConcurrentParameterized.class)
public class TestBase implements SauceOnDemandSessionIdProvider {

	public static String username = "jbelaniApex82";// System.getenv("SAUCE_USERNAME");
	public static String accesskey = "cb9e0c54-1544-4c95-bb09-ce018399bb9f";// System.getenv("SAUCE_ACCESS_KEY");
	public static String seleniumURI;
	public static String buildTag;
	public static final String USERNAME = "jbelaniApex82";
	public static final String ACCESS_KEY = "cb9e0c54-1544-4c95-bb09-ce018399bb9f";
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	/**
	 * Constructs a {@link SauceOnDemandAuthentication} instance using the
	 * supplied user name/access key. To use the authentication supplied by
	 * environment variables or from an external file, use the no-arg
	 * {@link SauceOnDemandAuthentication} constructor.
	 */
	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accesskey);

	/**
	 * JUnit Rule which will mark the Sauce Job as passed/failed when the test
	 * succeeds or fails.
	 */
	@Rule
	public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

	@Rule
	public TestName name = new TestName() {
		public String getMethodName() {
			return String.format("%s", super.getMethodName());
		}
	};

	protected String browser;
	protected String os;
	protected String version;
	protected String deviceName;
	protected String deviceOrientation;
	protected String sessionId;
	protected WebDriver driver;

	/**
	 * Constructs a new instance of the test. The constructor requires three
	 * string parameters, which represent the operating system, version and
	 * browser to be used when launching a Sauce VM. The order of the parameters
	 * should be the same as that of the elements within the
	 * {@link #browsersStrings()} method.
	 * 
	 * @param os
	 * @param version
	 * @param browser
	 * @param deviceName
	 * @param deviceOrientation
	 */

	public TestBase(String os, String version, String browser, String deviceName, String deviceOrientation) {
		super();
		this.os = os;
		this.version = version;
		this.browser = browser;
		this.deviceName = deviceName;
		this.deviceOrientation = deviceOrientation;
	}

	/**
	 * @return a LinkedList containing String arrays representing the browser
	 *         combinations the test should be run against. The values in the
	 *         String array are used as part of the invocation of the test
	 *         constructor
	 */
	@ConcurrentParameterized.Parameters
	public static LinkedList browsersStrings() {
		LinkedList browsers = new LinkedList();

		browsers.add(new String[] { "Windows 10", "14.14393", "MicrosoftEdge", null, null });
//		 browsers.add(new String[]{"Windows 10", "49.0", "firefox", null, null});
//		 browsers.add(new String[]{"Windows 7", "11.0", "internet explorer", null, null});
//		 browsers.add(new String[]{"OS X 10.11", "10.0", "safari", null, null});
//		 browsers.add(new String[]{"OS X 10.10", "54.0", "chrome", null, null});
		 
		return browsers;
	}

	/**
	 * Constructs a new {@link RemoteWebDriver} instance which is configured to
	 * use the capabilities defined by the {@link #browser}, {@link #version}
	 * and {@link #os} instance variables, and which is configured to run
	 * against ondemand.saucelabs.com, using the username and access key
	 * populated by the {@link #authentication} instance.
	 *
	 * @throws Exception
	 *             if an error occurs during the creation of the
	 *             {@link RemoteWebDriver} instance.
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("https://" + username + ":" + accesskey + seleniumURI + "/wd/hub");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(CapabilityType.BROWSER_NAME, browser);
		caps.setCapability(CapabilityType.VERSION, version);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("device-orientation", deviceOrientation);
		caps.setCapability(CapabilityType.PLATFORM, os);

		String methodName = name.getMethodName();
		caps.setCapability("name", methodName);

		driver = new RemoteWebDriver(new URL(URL), caps);
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	/**
	 * @return the value of the Sauce Job id.
	 */
	@Override
	public String getSessionId() {
		return sessionId;
	}

	@BeforeClass
	public static void setupClass() {
		// get the uri to send the commands to.
		seleniumURI = "@ondemand.saucelabs.com:80";
		// If available add build tag. When running under Jenkins BUILD_TAG is
		// automatically set.
		// You can set this manually on manual runs.
		buildTag = System.getenv("BUILD_TAG");
	}
	
	/**
	 * Function to get the {@link WebDriver} object
	 * @return The {@link WebDriver} object
	 */
	public WebDriver getDriver()
	{
		return driver;
	}
}
