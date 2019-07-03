package pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class CustomerPage {

	//    Методы для работы с таблицами

	public ElementsCollection getRows(){
		ElementsCollection rows = $$("tr.jqgrow.ui-row-ltr");
		return rows;
	}

	public ElementsCollection getHeadings(){
         SelenideElement headingsRow = $(byXpath("//*[@id='gview_BaseMainContent_MainContent_jqgTrade']//thead/tr "));
         ElementsCollection headingColumns = headingsRow.findAll(By.xpath(".//th")).excludeWith(empty);
         return headingColumns;
     }

	public List<ElementsCollection> getRowsWithColumns(){
		ElementsCollection rows = getRows();

		List<ElementsCollection> rowsWithColumns = new ArrayList<ElementsCollection>();
		for (SelenideElement row : rows){
			ElementsCollection rowWithColumns = row.findAll(byXpath(".//td"));
			rowsWithColumns.add(rowWithColumns);
		}
		return rowsWithColumns;
	}

    public List<Map<String, SelenideElement>> getRowsWithColumnsByHeadings(){
            List<ElementsCollection> rowsWithColumns = getRowsWithColumns();
            List<Map<String, SelenideElement>> rowsWithColumnsByHeadings = new ArrayList<Map<String, SelenideElement>>();
            Map<String, SelenideElement> rowByHeadings;
            ElementsCollection headingColumns = getHeadings();

            for (ElementsCollection row : rowsWithColumns) {
                rowByHeadings = new HashMap<String, SelenideElement>();
                for (int i = 0; i < headingColumns.size(); i++){
                   String heading = headingColumns.get(i).getText();
                    SelenideElement cell = row.get(i);
                    rowByHeadings.put(heading, cell);
                }
           rowsWithColumnsByHeadings.add(rowByHeadings);
            }
        return rowsWithColumnsByHeadings;
    }

	public String getValueFromCell(int rowNumber, int columnNumber){
		List<ElementsCollection> rowsWithColumns = getRowsWithColumns();
		SelenideElement cell = rowsWithColumns.get(rowNumber - 1).get(columnNumber - 1);
		return cell.getText();
	}
}
