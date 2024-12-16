package pages;

import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StartPage {

    private final SelenideElement destinationField = $("[data-testid='destination-container'] input");
    private final SelenideElement searchButton = $("[type=\"submit\"]");
    private final SelenideElement cityButton = $("ul[role='group']>li:first-child");
    private final SelenideElement acceptButton = $("#onetrust-accept-btn-handler");

    @Step("Принять cookies")
    public StartPage acceptCookies(){
        acceptButton.hover().click();
        return this;
    }

    @Step("Ввести в поисковик '{word}' и нажать кнопку 'Найти'")
    public SearchPage search(String word){
        actions().scrollToElement(destinationField);
        destinationField.shouldBe(visible).val(word);
        sleep(1000);
        cityButton.shouldBe(visible).hover().click();
        searchButton.click();
        return page(SearchPage.class);
    }
}
