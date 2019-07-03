package steps;

import cucumber.api.java.ru.*;
import pageobjects.SearchPage;

public class StepDefs {
	SearchPage page = new SearchPage();
	@Дано("пользователь переходит на форму поиска")
	public void пользовательПереходитНаФормуПоиска() {
		page.open();
	}

	@Когда("пользователь выбирает дату начала публикации {string}")
	public void пользовательВыбираетДатуНачалаПубликации(String StartPublicationDate) {
		page.typeStartPublicationDate(StartPublicationDate);
	}

	@Когда("выбирает дату окончания публикации {string}")
	public void выбираетДатуОкончанияПубликации(String EndPublicationDate) {
		page.typeEndPublicationDate(EndPublicationDate);
	}

	@Когда("выбирает начальную стоимость закупки {string}")
	public void выбираетНачальнуюСтоимостьЗакупки(String string) {
		page.typeStartPriceFrom("0");
	}

	@Когда("отмечает \\\"Закупка в соответсвии с нормами два-два-три-ФЗ\\\"")
	public void отмечаетЗакупкаВСоответсвииСНормамиДваДваТриФЗ() {
		page.setchkPurchaseType_0(true);
	}

	@Когда("отмечает \\\"Коммерческая закупка\\\"")
	public void отмечает() {
		page.setchkPurchaseType_1(true);
	}

	@Когда("нажимает кнопку поиска")
	public void нажимаетКнопкуПоиска() {
		page.clickSearchButton();
		page.waitUntilLoaderDisappears();
	}

	@Тогда("сумма всех закупок равна")
	public void суммаВсехЗакупокРавна() {
		System.out.println(page.sum()+" test");
		System.out.println(page.sumCanseled()+" canseled");
		System.out.println(page.sum()-page.sumCanseled());
	}
}
