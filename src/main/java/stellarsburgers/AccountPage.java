//модель страницы личного кабинета
package stellarsburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static stellarsburgers.constant.SitePath.PROFILE_URI;

public class AccountPage {
    private WebDriver driver;
    //локатор для кнопки выхода из аккаунта
    private final By logoutButton = By.xpath(".//main[@class='App_componentContainer__2JC2W']//button[@type='button']");
    //локатор для кнопки Конструктор
    private final By constrButton = By.xpath(".//ul[@class='AppHeader_header__list__3oKJj']/li/a[@href='/']");
    //локатор для логотипа
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("gереход из личного кабинета в конструктор")
    public void constButtonClick() {
        driver.findElement(constrButton).click();
    }

    @Step("переход из личного кабинета на логотип")
    public void logoButtonClick() {
        driver.findElement(logoButton).click();
    }

    @Step("выход из аккаунта")
    public void logout() {
        driver.findElement(logoutButton).click();
    }

    @Step("проверка перехода на страницу авторизации")
    public String getLogoutButtonText() {
        return driver.findElement(logoutButton).getText();
    }
}
