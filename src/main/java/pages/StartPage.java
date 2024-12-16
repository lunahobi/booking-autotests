package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StartPage {

    private final SelenideElement destinationField = $("[data-testid='destination-container'] input");
    private final SelenideElement searchButton = $("[type=\"submit\"]");
    private final SelenideElement cityButton = $("ul[role='group']>li:first-child");
    private final SelenideElement acceptButton = $("#onetrust-accept-btn-handler");
    private final SelenideElement startDateButton = $("td[role='gridcell'] span[data-date='2024-12-16']");
    private final SelenideElement endDateButton = $("td[role='gridcell'] span[data-date='2025-01-23']");

    @Step("Принять cookies")
    public StartPage acceptCookies(){
        acceptButton.hover().click();
        return this;
    }

    @Step("Ввести в поисковик '{word}'")
    public StartPage inputDestination(String word){
        actions().scrollToElement(destinationField);
        destinationField.shouldBe(visible).val(word);
        cityButton.shouldBe(visible).hover().click();
        return this;
    }

    @Step("Выбрать случайные даты")
    public StartPage chooseDate(){
        startDateButton.hover().click();
        endDateButton.hover().click();
        return this;
    }

    @Step("Нажать кнопку 'Найти'")
    public SearchPage clickSearch() {
        sleep(1000);
        searchButton.hover().click();
        return page(SearchPage.class);
    }


}
