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
import ru.iteco.fmhandroid.ui.utils.pageObject.OurMission;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.utils.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    AppBar appBar = new AppBar();
    OurMission ourMission = new OurMission();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarFragmentMain(), 5000));
        if (!mainPage.isDisplayedButtonProfile()) {
            authorizationPage.successfulAuthorization();
        }
    }

    @Description("Открытие страницы Главная")
    @Test
    public void openMainScreen() {
        appBar.pageOurMission();
        ourMission.textScreenCheckIsDisplayed();
        appBar.pageMain();
        mainPage.checkNews();
    }

}
