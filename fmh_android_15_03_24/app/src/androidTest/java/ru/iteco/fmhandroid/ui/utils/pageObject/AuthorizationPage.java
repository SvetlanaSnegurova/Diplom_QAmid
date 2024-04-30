package ru.iteco.fmhandroid.ui.utils.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class AuthorizationPage {
    MainPage mainPage = new MainPage();
    AppBar appBar = new AppBar();

    @Step("Ввод в поле Login")
    public void inputInFieldLogin(String login) {
        Allure.step("Ввод в поле Login");
        ViewInteraction inputInFieldLogin = onView(withId(R.id.login_text_input_layout));
        inputInFieldLogin.check(matches(isDisplayed())).perform(typeText(login), closeSoftKeyboard());
        pressBack();
    }

    @Step("Ввод в поле Password")
    public void inputInFieldPassword(String password) {
        Allure.step("Ввод в поле Password");
        ViewInteraction inputInFieldPassword = onView(withId(R.id.password_text_input_layout));
        inputInFieldPassword.check(matches(isDisplayed())).perform(typeText(password), closeSoftKeyboard());
        pressBack();
    }

    @Step("Нажатие на кнопку Выйти")
    public void pressButton() {
        Allure.step("Нажатие на кнопку Выйти");
        ViewInteraction buttonSingIn = onView(withId(R.id.enter_button));
        buttonSingIn.check(matches(isDisplayed()));
        buttonSingIn.perform(click());
    }

    @Step("Проверка видимости элемента с текстом Авторизация")
    public void visibilityElement() {
        Allure.step("Проверка видимости элемента с текстом Авторизация");
        ViewInteraction textViewAuth = onView(withText("Authorization"));
        textViewAuth.check(matches(isDisplayed()));
        textViewAuth.check(matches(withText("Authorization")));
    }

    @Step("Успешная авторизация пользователя")
    public void successfulAuthorization() {
        Allure.step("Успешная авторизация пользователя");
        inputInFieldLogin("login2");
        inputInFieldPassword("password2");
        pressButton();
        onView(isRoot()).perform(waitDisplayed(appBar.getPressProfile(), 5000));
        mainPage.checkNews();
    }
}
