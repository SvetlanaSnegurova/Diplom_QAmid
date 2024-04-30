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
import ru.iteco.fmhandroid.ui.utils.pageObject.AboutAppPage;
import ru.iteco.fmhandroid.ui.utils.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.utils.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.utils.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.utils.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutAppPageTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();

    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();

    AboutAppPage aboutAppPage = new AboutAppPage();
    String urlPrivacyPolicy = "https://vhospice.org/#/privacy-policy";
    String urlTermsOfUse = "https://vhospice.org/#/terms-of-use";

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

    @Description("Просмотр политики конфиденциальности")
    @Test
    public void OpenPrivacyPolicy() {
        appBar.AboutApp();
        aboutAppPage.intentPrivatePolicy(urlPrivacyPolicy);
        aboutAppPage.back();
    }

    @Description("Просмотр политики конфиденциальности")
    @Test
    public void OpenTermsOfUse() {
        appBar.AboutApp();
        aboutAppPage.intentTermOfUse(urlTermsOfUse);
        aboutAppPage.back();

    }
}
