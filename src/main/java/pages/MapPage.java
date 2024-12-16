package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MapPage {

    private final SelenideElement firstHotel = $("li:first-child > [aria-label]");
    private final SelenideElement searchBoxMarker = $("[aria-label='searchBoxResultMarker marker']");
    private final SelenideElement inputName = $("input[name='field']");
    private final SelenideElement firstSearchResult = $("[data-testid='searchbox-results-item']:first-child");
    protected static String hotelName;
    protected static int countStars;
    protected static double averageRating;
    protected static String countFeedback;
    protected static String price;

    @Step("Навести курсор на первый отель")
    public MapPage hoverFirstHotel(){
        firstHotel.shouldBe(visible).hover();
        hotelName = firstHotel.$("h2").text();
        countStars = (int) firstHotel.$$("[data-testid='rating-stars'] > span, [data-testid='rating-squares'] > span").stream().count();
        averageRating =  Double.parseDouble($x("//li[1]/*/div[2]/div[2]/div/div[1]/div").text().replace(",", ".").replace("Оценка ", ""));
        countFeedback = $x("//li[1]/*/div[2]/div[2]/div/div[2]/div[2]").text();
        price = $x("//li[1]/*/div[2]/div[5]/div[2]/span[2]").text();

        System.out.println(hotelName);
        System.out.println(countStars);
        System.out.println(averageRating);
        System.out.println(countFeedback);
        System.out.println(price);

        return this;
    }

    @Step("Нажать на соответствующий маркер на карте")
    public HotelPage clickMapMarker() {
        inputName.shouldBe(visible).val(hotelName);
        sleep(2000);
        firstSearchResult.shouldBe(visible).hover().click();
        sleep(2000);
        searchBoxMarker.shouldBe(visible).hover().click();
        switchTo().window(1);
        return page(HotelPage.class);
    }



}
