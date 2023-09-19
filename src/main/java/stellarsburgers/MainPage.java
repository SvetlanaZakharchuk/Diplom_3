//модель главной страницы
package stellarsburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static stellarsburgers.constant.SitePath.BASE_URI;

public class MainPage {
    private WebDriver driver;
    //локатор для проверки перехода в личный кабинет
    private final By userAccountButton = By.xpath(".//div/header/nav/a[@href='/account']");
    //локатор для кнопки "Войти в аккаунт"
    private final By enterButton = By.xpath(".//main[@class='App_componentContainer__2JC2W']//button");
    //локаторы для раздела "Конструктор" для проверки переходов Булки, соусы, начинки
    //Buns
    private final By bunsLink = By.xpath(".//div/span[text()='Булки']/parent::div");
    //souses
    private final By sousesLink = By.xpath(".//div/span[text()='Соусы']/parent::div");
    //ingredients
    private final By ingredientsLink = By.xpath(".//div/span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URI);
    }
    @Step("переходим в раздел Булки")
    public String clickBunsLink() {
        driver.findElement(sousesLink).click();
        driver.findElement(bunsLink).click();

        return driver.findElement(bunsLink).getAttribute("class");
    }
    @Step("переходим в раздел Соусы")
    public String clickSousesLink() {
        driver.findElement(sousesLink).click();
        return driver.findElement(sousesLink).getAttribute("class");
    }
    @Step("переходим в раздел начинки")
    public String clickIngredientsLink() {
        driver.findElement(ingredientsLink).click();
        return driver.findElement(ingredientsLink).getAttribute("class");
    }

    @Step("клик по кнопке Вход")
    public void buttonEnterClick() {
        driver.findElement(enterButton).click();
    }

    @Step("переход в личный кабинет")
    public void toUserAccountClick() {
        driver.findElement(userAccountButton).click();
    }
}
