package pageobjects;

import org.openqa.selenium.By;
import java.util.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends CustomerPage{

    private By pageTitle = By.xpath("//div[@class='header__platform-name']");
    private By startPublicationDate = By.xpath("//input[@name='ctl00$ctl00$BaseMainContent$MainContent$txtPublicationDate$txtDateFrom']");
    private By endPublicationDate = By.xpath("//input[@name='ctl00$ctl00$BaseMainContent$MainContent$txtPublicationDate$txtDateTo']");
    private By startPriceFrom = By.xpath("//input[@name='ctl00$ctl00$BaseMainContent$MainContent$txtStartPrice$txtRangeFrom']");
    private By chkPurchaseType_0 = By.xpath("//input[@id='BaseMainContent_MainContent_chkPurchaseType_0']");
    private By searchButton = By.xpath("//a[@id='BaseMainContent_MainContent_btnSearch']");
    private By canseledTab = By.xpath("//*[@id='lotStateTabs']//a[text()='Отменена']");
    private static SelenideElement pageLoader = $(By.id("load_BaseMainContent_MainContent_jqgTrade"));
    private static SelenideElement iconSeekNext = $(By.id("next_t_BaseMainContent_MainContent_jqgTrade_toppager"));

    public SearchPage open() {
        Selenide.open("/");
        return this;
    }

    public SearchPage typeStartPublicationDate(String date) {
        $(startPublicationDate).val(date);
        return this;
    }

    public SearchPage typeEndPublicationDate(String date) {
        $(endPublicationDate).val(date);
        return this;
    }

    public SearchPage typeStartPriceFrom(String date) {
        $(startPriceFrom).val(date);
        return this;
    }

    public SearchPage setchkPurchaseType_0(boolean value) {
        $(chkPurchaseType_0).setSelected(value);
        return this;
    }

    public SearchPage setchkPurchaseType_1(boolean value) {
        $(chkPurchaseType_0).setSelected(value);
        return this;
    }

    public  void waitUntilLoaderDisappears() {
        $(pageLoader).should(disappear);
    }

    public void clickSearchButton() {
        $(searchButton).click();
    }

    public Double getPriceFromCell(int rowNumber, int columnNumber) {
        List<ElementsCollection> rowsWithColumns = getRowsWithColumns();
        SelenideElement cell = rowsWithColumns.get(rowNumber - 1).get(columnNumber - 1);
        String str = (cell.getText());
        if (!str.contains("руб.")){
            return 0.00;
        }
        String price = str.substring(0, str.indexOf("руб."));
        price = price.replaceAll(" ", "");
        price = price.replaceAll(",", ".");
        return Double.parseDouble(price);
    }

    //    Суммировать цену
    private Double sum = 0.00;
    public Double sum() {
        Integer a = getRows().size();
        for (int i = 1; i < a+1; i++ ) {
            if (getValueFromCell(i,6).matches("\\d+")) {
                sum = sum + getPriceFromCell(i, 11);
            }
        }
        if(!$(iconSeekNext).has(cssClass("ui-state-disabled"))) {
            $(iconSeekNext).click();
            waitUntilLoaderDisappears();
            sum();
        }
        return sum;
    }
    private Double canseledSum = 0.00;
    public Double sumCanseled() {
        $(canseledTab).click();
        waitUntilLoaderDisappears();
        Integer a = getRows().size();
        for (int i = 1; i < a+1; i++ ) {
            if (getValueFromCell(i,6).matches("\\d+")) {
                canseledSum = canseledSum + getPriceFromCell(i, 11);
            }
        }
        if(!$(iconSeekNext).has(cssClass("ui-state-disabled"))) {
            $(iconSeekNext).click();
            waitUntilLoaderDisappears();
            sumCanseled();
        }
        return canseledSum;
    }
}