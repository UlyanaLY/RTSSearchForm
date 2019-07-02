import org.junit.*;
import static com.codeborne.selenide.Configuration.*;

public class SearchTest {
    private SearchPage page;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Documents\\GitHub\\RTSSearchForm\\drivers\\chromedriver.exe");
        browserSize = "1920x1080";
        baseUrl = "https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx";
        browser = "chrome";
    }

    @Test
    public void verifyStartPublicationDate() {
        page = new SearchPage();
        page.open();
        page.typeStartPublicationDate("26.04.2019");
        page.typeEndPublicationDate("26.04.2019");
        page.getTitle().click();
        page.typeStartPriceFrom("0");
        page.setchkPurchaseType_0(true);
        page.setchkPurchaseType_1(true);
        page.clickSearchButton();
        page.waitUntilLoaderDisappears();
        page.getRows();
        System.out.println(page.sum()+" test");
        System.out.println(page.sumCanseled()+" canseled");
        System.out.println(page.sum()-page.sumCanseled());
    }
}