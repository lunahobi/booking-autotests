import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.open;

public class BookingStarsTest extends BaseTest{

    private final static String baseUrl = "https://booking.com/";
    private final static String searchWord = "Анталья";

    /**
     * Зайти на сайт https://booking.com/
     * ввести в поиске «Анталья»
     * нажать на кнопку «Найти»
     * проверить, что в поиске отображается «Анталья»
     * выбрать «5 звёзд» в списке «Все фильтры» слева
     * убедиться, что все отели на данной странице имеют 5 звёзд
     */
    @Test
    @DisplayName("Проверка корректного отображения звезд и применения фильтра")
    public void test1(){
        open(baseUrl, StartPage.class)
                .acceptCookies()
                .inputDestination(searchWord)
                .clickSearch()
                .checkTitle(searchWord)
                .selectFiveStars()
                .checkFiveStars();
    }

    /**
     * Зайти на сайт https://www.booking.com/
     * ввести в поиске любой город(заграничный)
     * выбрать случайные даты
     * нажать на кнопку «Найти»
     * нажать на кнопку «Показать на карте»
     * навести курсор на первый отель(карточка слева)
     * сохранить(в переменные) название отеля, количество звезд, среднюю оценку, количество отзывов, стоимость
     * нажать на соответствующий маркер на карте
     * на открывшейся странице отеля проверить название отеля, количество звезд, среднюю оценку, количество отзывов и стоимость
     */
    @Test
    @DisplayName("Проверка корректной работы карты")
    public void test2(){
        open(baseUrl, StartPage.class)
                .acceptCookies()
                .inputDestination("Венеция")
                .chooseDate()
                .clickSearch()
                .showOnMap()
                .hoverFirstHotel()
                .clickMapMarker()
                .check();
    }
}
