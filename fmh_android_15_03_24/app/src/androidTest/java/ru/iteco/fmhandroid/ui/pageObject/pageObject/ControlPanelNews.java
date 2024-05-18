package ru.iteco.fmhandroid.ui.pageObject.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.pageObject.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ControlPanelNews {
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();
    FilterNews filterNews = new FilterNews();
    private final int buttonAddNews = R.id.add_news_image_view;
    private final int buttonEditNews = R.id.edit_news_item_image_view;
    private final int buttonDeleteNews = R.id.delete_news_item_image_view;
    private final ViewInteraction buttonOk = onView(withId(android.R.id.button1));

    public int getButtonAddNews() {
        return buttonAddNews;
    }

    @Step("Нажатие на кнопку 'Добавить' новость")
    public void addNews() {
        Allure.step("Нажатие на кнопку Добавить новость");
        // Ждем пока загрузиться элемент
        onView(withId(buttonAddNews)).check(matches(allOf(isDisplayed(), isClickable())));
        // Клик по элементу
        onView(withId(buttonAddNews)).perform(click());
        // Ждем, пока загрузится форма
        onView(isRoot()).perform(waitDisplayed(createNews.getButtonSave(), 6000));
    }

    @Step("Нажатие на кнопку 'Редактировать' новость")
    public void pressEditPanelNews() {
        Allure.step("Нажатие на кнопку Редактирование новостей");
        onView(withId(buttonEditNews)).check(matches(allOf(isDisplayed(), isClickable())));
        // Клик по элементу
        onView(withId(buttonEditNews)).perform(click());
        // Ждем, пока загрузится форма
        onView(isRoot()).perform(waitDisplayed(editNews.getButtonSave(), 6000));
    }

    @Step("Нажатие на кнопку 'Удалить' новость")
    public void deleteNews() {
        Allure.step("Нажатие на кнопку Удалить новость");
        onView(withId(buttonDeleteNews)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(buttonDeleteNews)).perform(click());
        buttonOk.check(matches(isDisplayed()));
        buttonOk.perform(click());
    }

    @Step("Поиск новости с заголовком {text} и проверка ее видимости")
    public void searchNewsAndCheckIsDisplayed(String text) {
        Allure.step("Поиск новости с заголовком " + text + " и проверка ее видимости");
        scrollTo();
        onView(isRoot()).check(matches(withText(text)));
        onView(withText(text)).check(matches(isDisplayed()));
    }

    @Step("Проверка отсутствия новости с заголовком {text}")
    public void checkDoesNotExistNews(String text) {
        Allure.step("Проверка отсутствия новости с заголовком {text}");
        onView(withText(text)).check(doesNotExist());
    }
}
