package task1;

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
                .search(searchWord)
                .checkTitle("searchWord")
                .selectFiveStars()
                .checkFiveStars();
    }

    @Test
    @DisplayName("Проверка корректной работы карты")
    public void test2(){
        open(baseUrl, StartPage.class)
                .acceptCookies()
                .search(searchWord)
                .checkTitle(searchWord)
                .selectFiveStars()
                .checkFiveStars();
    }
}
