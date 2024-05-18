package ru.iteco.fmhandroid.ui.pageObject.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.Utils;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.ControlPanelNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.CreateNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.EditNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.NewsPage;

public class ControlPanelNewsTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    NewsPage newsPage = new NewsPage();
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();

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

    @Description("ТК.58 Нажатие на кнопку сортировка новостей по возрастанию/убыванию")
    @Test
    public void sortingNews() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        newsPage.buttonSortingNews();
    }

    @Description("ТК.59 Открытие формы 'Cоздания новости'")
    @Test
    public void openFormCreateNews() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
    }

    @Description("ТК.60 Редактирование новости")
    @Test
    public void shouldEditTheNewsAfterEditing() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        String title = "Создание";
        createNews.createNews("Объявление", title, Utils.currentDate(), "10:00", "Описание новости");
        controlPanelNews.searchNewsAndCheckIsDisplayed(title);
        controlPanelNews.pressEditPanelNews();
        editNews.editCategory("Зарплата");
        String editTitle = "Редактирование";
        editNews.editTitle(editTitle);
        editNews.editDate(Utils.dateMore1Month());
        editNews.editTime("12:00");
        editNews.editDescription("Новое описание новости");
        editNews.pressSave();
        controlPanelNews.searchNewsAndCheckIsDisplayed(editTitle);
    }
    @Description("ТК.62 Удаление новости")
    @Test
    public void shouldNewsBeDeletedAfterDelete() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        String title = "Создание новости";
        createNews.createNews("Some Category", title, Utils.currentDate(), "10:00", "Описание новости для удаления");
        controlPanelNews.searchNewsAndCheckIsDisplayed(title);
        controlPanelNews.deleteNews();
        controlPanelNews.checkDoesNotExistNews(title);
    }
}

