//тестируем переходы из личного кабинета и выход
package stellarsburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import stellarsburgers.User.UserClient;
import stellarsburgers.User.UserModel;

import java.util.concurrent.TimeUnit;

import static stellarsburgers.Driver.WebDriverCreator.createChromeDriver;
import static stellarsburgers.Utils.Utils.*;
import static stellarsburgers.constant.SitePath.BASE_URI;
import static stellarsburgers.constant.SitePath.PROFILE_URI;

public class UserAccountTest {

    private WebDriver driver;
    private UserClient userClient;
    private UserModel user;
    private String accessToken = "";

    @Before
    public void setUp() {
        driver = createChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //созадаем пользователя
        userClient = new UserClient();
        user = new UserModel(randomName(), randomEmail(), randomPassword());
        Response response = userClient.create(user);
        accessToken = response.path("accessToken");
    }

    //Проверяем переход в личный кабинет
    @Test
    @DisplayName("goToUserAccount")
    public void goToUserAccount() {
        //логинимся
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillInfo(user.getEmail(), user.getPassword());
        loginPage.clickButtonEnter();

        MainPage mainPage = new MainPage(driver);
        //кликаем по Личноему кабинету
        mainPage.toUserAccountClick();

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(PROFILE_URI, actualResult);
    }

    //проверяем переход из личного кабинета в конструктор
    @Test
    @DisplayName("goToMainPageViaConstrButton")
    public void goToMainPageViaConstrButton() {
        //логинимся
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillInfo(user.getEmail(), user.getPassword());
        loginPage.clickButtonEnter();

        MainPage mainPage = new MainPage(driver);
        //кликаем по Личноему кабинету
        mainPage.toUserAccountClick();

        AccountPage accountPage = new AccountPage(driver);
        //кликаем на ссылку конструктор и проверяем, что перешли на главную
        accountPage.constButtonClick();
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(BASE_URI, actualResult);
    }

    //проверяем переход из личного кабинета на главную страницу по логотипу
    @Test
    @DisplayName("goToMainPageViaLogo")
    public void goToMainPageViaLogo() {
        //логинимся
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillInfo(user.getEmail(), user.getPassword());
        loginPage.clickButtonEnter();

        MainPage mainPage = new MainPage(driver);
        //переходим в личный кабинет
        mainPage.toUserAccountClick();

        AccountPage accountPage = new AccountPage(driver);
        //кликаем на лого и проверяем, что перешли на главную
        accountPage.logoButtonClick();
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(BASE_URI, actualResult);
    }

    //проверяем выход из аккаунта
    @Test
    @DisplayName("logoutTest")
    public void logoutTest() {
        //логинимся
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillInfo(user.getEmail(), user.getPassword());
        loginPage.clickButtonEnter();

        MainPage mainPage = new MainPage(driver);
        //переходим в личный кабинет
        mainPage.toUserAccountClick();
        //проверяем, что перешли в ЛК
        Assert.assertEquals(PROFILE_URI, driver.getCurrentUrl());

        AccountPage accountPage = new AccountPage(driver);

        //кликаем на кнопку выйти в личном кабинете
        accountPage.logout();
        String actualResult = loginPage.getButtonEnterText();
        Assert.assertEquals("Войти", actualResult);
    }

    @After
    public void tearsDown() {
        driver.quit();
        userClient.delete(accessToken);
    }
}
