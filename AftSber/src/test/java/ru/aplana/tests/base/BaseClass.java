package ru.aplana.tests.base;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void connectDriver() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 15, 2000);

//        Перейти на страницу http://www.sberbank.ru/ru/person
        driver.get("http://www.sberbank.ru/ru/person");
    }

    @After
    public void disconnectDriver() {
        driver.quit();
    }

    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void fillInputField(WebElement element, String value, String nameField) {
        if (!element.isDisplayed()) scrollToElementJs(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(value);
        Assert.assertEquals("Поле \"" + nameField + "\" было заполнено неправильно",
                value, element.getAttribute("value"));
    }

    protected void fillInputFieldForPhone(WebElement element, String value) {
        if (!element.isDisplayed()) scrollToElementJs(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.sendKeys(value);
        value = "+7 " + value;
        Assert.assertEquals("Поле \"Мобильный телефон\" было заполнено неправильно",
                value, element.getAttribute("value"));
    }
}
