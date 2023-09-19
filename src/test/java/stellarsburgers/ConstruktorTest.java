//тестируем переходы между разделами Булки, Соусы и Ингридиенты
package stellarsburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static stellarsburgers.Driver.WebDriverCreator.createChromeDriver;
import static stellarsburgers.Driver.WebDriverCreator.createYandexDriver;

public class ConstruktorTest {
    private final String expectedClassName = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    private WebDriver driver;

    @Before
    public void setUp() {

        driver = createChromeDriver();

    }

    //проверяем переход по ссылке Булки
    @Test
    @DisplayName("checkBunsLinkTest")
    public void checkBunsLinkTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        String actualResult = mainPage.clickBunsLink();
        Assert.assertEquals(expectedClassName, actualResult);
    }

    //проверяем переход по ссылке Соусы
    @Test
    @DisplayName("checkSousesLinkTest")
    public void checkSousesLinkTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        String actualResult = mainPage.clickSousesLink();
        Assert.assertEquals(expectedClassName, actualResult);
    }

    //проверяем переходи по ссылке Ингридиенты
    @Test
    @DisplayName("checkIngredientsLinkTest")
    public void checkIngredientsLinkTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        String actualResult = mainPage.clickIngredientsLink();
        Assert.assertEquals(expectedClassName, actualResult);
    }

    @After
    public void tearsDown() {
        driver.quit();
    }
}
