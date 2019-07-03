
package utilities;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import static com.codeborne.selenide.Configuration.*;

public class Hook {

	@Before
	public void beforeScenario(){
		System.out.println("This will run before the Scenario");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Documents\\GitHub\\RTSSearchForm\\drivers\\chromedriver.exe");
		browserSize = "1920x1080";
		baseUrl = "https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx";
		browser = "chrome";
	}

	@After
	public void afterScenario() {
		System.out.println("This will run after the Scenario");
	}
}