//тестируем регистрацию
package stellarsburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static stellarsburgers.Driver.WebDriverCreator.createChromeDriver;
import static stellarsburgers.Utils.Utils.*;

@RunWith(Parameterized.class)
public class UserRegisterTest {

    private static final String REGISTER_MSG = "Войти";
    private static final String WRONG_PASSWORD = "Некорректный пароль";
    private final String name;
    private final String email;
    private final String password;
    private WebDriver driver;

    public UserRegisterTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[] param() {
        return new Object[][]{
                {randomName(), randomEmail(), randomPassword()},
                //   { randomName(), randomEmail(), randomPassword()}
        };
    }

    @Before
    public void setUp() {
        driver = createChromeDriver();
    }

    //проверяем регистрацию нового пользователя
    @Test
    @DisplayName("checkUserRegisterTest")
    public void checkUserRegisterTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        UserInfo userInfo = new UserInfo(driver);
        userInfo.fillUserInfo(name, email, password);

        registerPage.clickButtonRegister();

        String actualResult = registerPage.isRegister();
        Assert.assertEquals(REGISTER_MSG, actualResult);
    }

    //проверяем регистрацию с некорректным паролем
    @Test
    @DisplayName("checkUserRegisterWithWrongPassword")
    public void checkUserRegisterWithWrongPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        UserInfo userInfo = new UserInfo(driver);
        userInfo.fillUserInfo(name, email, "123");
        registerPage.clickButtonRegister();

        String actualResult = registerPage.passwordMsg();
        Assert.assertEquals(WRONG_PASSWORD, actualResult);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
