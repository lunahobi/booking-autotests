package task1.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;
import task1.tests.base.BaseTest;

import static com.codeborne.selenide.Selenide.open;

public class BookingStarsTest{

    /**
     * Зайти на сайт https://booking.com/
     * ввести в поиске «Анталья»
     * нажать на кнопку «Найти»
     * проверить, что в поиске отображается «Анталья»
     * выбрать «5 звёзд» в списке «Все фильтры» слева
     * убедиться, что все отели на данной странице имеют 5 звёзд
     */
    @Test
    @DisplayName("Тестирование страницы расписания на сайте Мосполитеха")
    public void test(){
        open("https://booking.com/", StartPage.class)
                .search("Анталья")
                .checkTitle("Анталья")
                .select5stars()
                .check5stars();
    }
}
