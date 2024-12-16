package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HotelPage {
    private final SelenideElement hotelName = $("div[class='hp__hotel-title pp-header'] h2");
    private final ElementsCollection ratingStars = $$("[data-testid='rating-stars'] > span");
    private final SelenideElement review = $x("//*[@id=\"js--hp-gallery-scorecard\"]/a/div/div/div/div[1]/div");
    private final SelenideElement countFeedback = $x("//*[@id=\"js--hp-gallery-scorecard\"]/a/div/div/div/div[2]/div[2]");
    private final SelenideElement price = $("tr[data-block-id]:first-child .prco-valign-middle-helper");

    @Step("Проверка")
    public HotelPage check() {
        hotelName.shouldHave(text(MapPage.hotelName));
        assertEquals("Количество звезд не совпадает", MapPage.countStars, ratingStars.stream().count());
        String ratingText = review.text().replace("Оценка", "").trim();
        double actualRating = Double.parseDouble(ratingText);
        assertEquals("Средняя оценка не совпадает", MapPage.averageRating, actualRating, 0.01);
        assertEquals("Количество отзывов не совпадает", MapPage.countFeedback.replace(" ", ""), countFeedback.text().replace(" ", ""));
        return this;
    }



}
