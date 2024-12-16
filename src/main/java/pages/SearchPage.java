package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class SearchPage {
    private final SelenideElement destinationField = $("[data-testid='destination-container'] input");
    private final ElementsCollection filterFiveStars = $$("div[data-filters-item='class:class=5']");
    private final SelenideElement closePopUp = $("[aria-label='Скрыть меню входа в аккаунт.']");

    private void closePopUpWithRetries() {
        for (int i = 0; i < 5; i++) { // Проверяем до 5 раз
            if (closePopUp.is(visible)) {
                closePopUp.click();
                closePopUp.should(disappear); // Ждем, пока попап исчезнет
                break;
            }
            sleep(500); // Пауза между проверками
        }
    }

    @Step("Проверка открытия страницы с поиском '{title}'")
    public SearchPage checkTitle(String title) {
        destinationField.shouldBe(visible).shouldHave(value(title));
        return this;
    }

    @Step("Выбрать в фильтрах '5 звезд'")
    public SearchPage selectFiveStars() {
        closePopUpWithRetries();
        filterFiveStars.get(0).shouldBe(visible).hover().click();
        return this;
    }

    @Step("проверить, что все отели с 5 звездами")
    public void checkFiveStars() {
        sleep(5000);
        int fiveStarsCount = 0;
        int loadedCardsCount = 0;

        while (true) {
            // Пересчитываем загруженные карточки
            ElementsCollection cards = $$("[data-testid='property-card']");

            if (cards.size() == loadedCardsCount) {
                // Если количество карточек перестало расти, выходим из цикла
                break;
            }

            // Обновляем количество загруженных карточек
            loadedCardsCount = cards.size();

            // Скроллим до последней карточки
            cards.last().scrollIntoView(true);

            // Считаем карточки с 5 звездами
            fiveStarsCount = (int) cards.stream()
                    .filter(card -> card.$("div[aria-label='5 из 5']").exists())
                    .count();
        }

        // Проверяем, что каждая карточка имеет 5 звезд
        assertEquals("Не все карточки имеют 5 звезд.", loadedCardsCount, fiveStarsCount);
    }

}
