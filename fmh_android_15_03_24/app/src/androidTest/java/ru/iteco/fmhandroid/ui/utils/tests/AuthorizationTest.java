package ru.iteco.fmhandroid.ui.utils.tests;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.utils.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.utils.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.utils.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.utils.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    AppBar appBar = new AppBar();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() throws Exception {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarFragmentMain(), 5000));
        if (mainPage.isDisplayedButtonProfile()) {
            appBar.logOut();
        }
    }

    @Description("Успешная авторизация в приложении и выход из профиля")
    @Test
    public void successfulAuthorizationAndLogOut() {
        authorizationPage.inputInFieldLogin("login2");
        authorizationPage.inputInFieldPassword("password");
        authorizationPage.pressButton();
    }

    @Description("Авторизация невалидного пользователя при использовании регистра")
    @Test
    public void authorizationUsingRegister() {
        authorizationPage.visibilityElement();
        authorizationPage.inputInFieldLogin("LOGIN2");
        authorizationPage.inputInFieldPassword("PASSWORD2");
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация при введении в поле логин символов на кириллице")
    @Test
    public void authorizationUsingCyrillicInFailedLogin() {
        authorizationPage.visibilityElement();
        authorizationPage.inputInFieldLogin("логин2");
        authorizationPage.inputInFieldPassword("password2");
        authorizationPage.pressButton();
    }

    @Description("Авторизация при введении в поле логин спецсимволов")
    @Test
    public void authorizationUsingSpecialSymbolInFailedLogin() {
        authorizationPage.visibilityElement();
        authorizationPage.inputInFieldLogin("lOGin #%@`<|&?>*");
        authorizationPage.inputInFieldPassword("password2");
        authorizationPage.pressButton();
    }

    @Description("Авторизация при введении в поле пароль символов на кириллице")
    @Test
    public void authorizationUsingCyrillicInFailedPassword() {
        authorizationPage.visibilityElement();
        authorizationPage.inputInFieldLogin("login2");
        authorizationPage.inputInFieldPassword("пассворд2");
        authorizationPage.pressButton();
    }

    @Description("Авторизация при введении в поле пароль спецсимволов")
    @Test
    public void authorizationUsingSpecialSymbolFailedPassword() {
        authorizationPage.visibilityElement();
        authorizationPage.inputInFieldLogin("login2");
        authorizationPage.inputInFieldPassword("lOGin #%@`<|&?>*");
        authorizationPage.pressButton();
    }

    @Description("Авторизация с пустым логином")
    @Test
    public void authorizationWhenEmptyFailedLogin() {
        authorizationPage.visibilityElement();
        authorizationPage.inputInFieldLogin("password2");
        authorizationPage.pressButton();
    }

    @Description("Авторизация с пустым паролем")
    @Test
    public void authorizationWhenEmptyFailedPassword() {
        authorizationPage.visibilityElement();
        authorizationPage.inputInFieldLogin("login2");
        authorizationPage.pressButton();
    }

    @Description(" Авторизация с пустым логином и паролем")
    @Test
    public void authorizationWhenEmptyFailedLoginAndPassword() {
        authorizationPage.visibilityElement();
        authorizationPage.pressButton();

    }
}
