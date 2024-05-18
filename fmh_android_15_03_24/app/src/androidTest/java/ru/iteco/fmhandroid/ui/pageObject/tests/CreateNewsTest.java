package ru.iteco.fmhandroid.ui.pageObject.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.pageObject.Utils.waitDisplayed;

import android.os.SystemClock;

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

public class CreateNewsTest {
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();
    NewsPage newsPage = new NewsPage();

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

    @Description("ТК.93 Успешное создание новости")
    @Test
    public void successfulNewsCreation() {
        // Переход на страницу новостей
        appBar.switchToNews();
        // Переход на панель управления новостями
        newsPage.switchControlPanelNews();
        // Добавление новой новости
        controlPanelNews.addNews();
        // Добавление категории
        createNews.addCategory("Some Category");
        // Добавление заголовка новости
        createNews.addTitle("Создание новости");
        // Добавление даты
        createNews.addDate(Utils.currentDate());
        // Добавление времени
        createNews.addTime("20:00");
        // Добавление описания
        createNews.addDescription("Описание новости");
        // Нажатие на кнопку сохранения
        createNews.pressSave();
    }

    @Description("ТК.100 Создание новости с прошедшей датой публикации ")
    @Test
    public void shouldStayOnNewsCreationScreenWhenCreatingNewsInPast() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.addCategory("Some Category");
        String text = "Создание новости в прошлом";
        createNews.addTitle(text);
        String pastDate = Utils.dateInPast();
        createNews.addDate(pastDate);
        createNews.addTime("10:00");
        createNews.addDescription("Описание новости в прошлом");
        createNews.pressSave();
        // Проверка отображения ошибки
        createNews.checkErrorDisplay("Неверная дата публикации");
    }

    @Description("ТК.94 Создание новости с пустыми полями")
    @Test
    public void shouldStayOnNewsCreationScreenWhenCreatingNewsWithEmptyFields() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        onView(isRoot()).perform(waitDisplayed(createNews.getButtonSave(), 5000));
        createNews.pressSave();
        SystemClock.sleep(10000);
        createNews.checkErrorDisplay("Заполните пустые поля");
    }
}
