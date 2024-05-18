package ru.iteco.fmhandroid.ui.pageObject.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.Utils;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.ControlPanelNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.FilterNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.NewsPage;

public class FilterNewsTest {
    AppBar appBar = new AppBar();
    FilterNews filterNews = new FilterNews();
    NewsPage newsPage = new NewsPage();
    MainPage mainPage = new MainPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();

    ControlPanelNews controlPanelNews = new ControlPanelNews();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        // Подождать 5 секунд для завершения загрузки страницы авторизации
        Thread.sleep(5000);
        if (!mainPage.isDisplayedButtonProfile()) {
            authorizationPage.successfulAuthorization();
        }
    }

    @Description("ТК.40 Выбор каждой категории из выпадающего списка")
    @Test
    public void inputCategoriesNewsInCyrillic() {
        //Переход на старницу новостей
        appBar.switchToNews();
        //Нажатие на кнопку фильтровать(Открытие формы фильтрации)
        newsPage.openFormFilterNews();
        // Список категорий
        List<String> categories = Arrays.asList(
                "День рождения",
                "Объявление",
                "Зарпалата",
                "Профсоюз",
                "Праздник",
                "Массаж",
                "Благодарность",
                "Нужна помощь"
        );
        // Ввод всех категорий поочередно
        for (String category : categories) {
            filterNews.addCategoryFilter(category);
        }
    }

    @Description("ТК.43 Фильтрация новостей, используя незаполненную форму ")
    @Test
    public void filterNewsEmptyForm() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("");
        filterNews.setDateFromFilter("");
        filterNews.setDateToFilter("");
        filterNews.confirmFilter();
    }

    @Description("ТК.43 Фильтрация новостей, по корректным категории и периоду дат")
    @Test
    public void filterNewsValidDate() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("День рождения");
        filterNews.setDateFromFilter(Utils.currentDate());
        filterNews.setDateToFilter(Utils.currentDate());
        filterNews.confirmFilter();
    }

    @Description("ТК.45 Фильтрация новостей, только по категории")
    @Test
    public void filterNewsOnlyCategory() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("Зарплата");
        filterNews.confirmFilter();
    }

    @Description("ТК.46 Фильтрация новостей, по категории и начальной дате")
    @Test
    public void filterNewsByCategoryAndStartingDate() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("День рождения");
        filterNews.setDateFromFilter(Utils.currentDate());
        filterNews.confirmFilter();
    }

    @Description("ТК.47 Фильтрация новостей, по корректной категории и конечной дате")
    @Test
    public void filterNewsByCategoryAndFinishDate() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("День рождения");
        filterNews.setDateToFilter(Utils.currentDate());
        filterNews.confirmFilter();
    }

    @Description("ТК.48 Фильтрация новостей, только по периоду дат")
    @Test
    public void filterNewsByDatePeriod() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.setDateFromFilter(Utils.currentDate());
        filterNews.setDateToFilter(Utils.currentDate());
        filterNews.confirmFilter();
    }

    @Description("ТК.49 Фильтрация новостей, используя первой датой прошедшую дату, второй датой текущую дату")
    @Test
    public void filterNews() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("День рождения");
        filterNews.setDateFromFilter(Utils.dateMore1Years());
        filterNews.setDateToFilter(Utils.currentDate());
        filterNews.confirmFilter();
    }

    @Description("ТК.50 Фильтрация новостей, используя будущий период дат(1 день вперед)")
    @Test
    public void filterNewsDateInPast() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("День рождения");
        filterNews.setDateFromFilter(Utils.dateInPast());
        filterNews.setDateToFilter(Utils.dateInPast());
        filterNews.confirmFilter();
    }

    @Description("ТК.51 Фильтрация новостей, используя первой датой будущую дату, второй датой прошедшую дату")
    @Test
    public void filterNewsDateFutureDateFirstAndPastDateSecondDate() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("День рождения");
        filterNews.setDateFromFilter(Utils.dateInPast());
        filterNews.setDateToFilter(Utils.dateMore1Month());
        filterNews.confirmFilter();
    }

    @Description("ТК.54 Отмена фильтрации после заполнения формы с помощью кнопки 'Отмена'")
    @Test
    public void filterNewsCancel() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("День рождения");
        filterNews.setDateFromFilter(Utils.currentDate());
        filterNews.setDateToFilter(Utils.currentDate());
        filterNews.cancelFilter();
    }
}


