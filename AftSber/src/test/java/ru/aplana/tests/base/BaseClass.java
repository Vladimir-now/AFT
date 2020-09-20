package ru.aplana.tests.base;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new String[][]{
                {"Васильев", "Василий", "Васильевич", "VASILII VASILEV","12.02.1998","firstperson@mail.ru","(999) 555-77-22"},
                {"Петров", "Петр", "Дунаевич", "PETR PETROV","11.07.2001","seconperson@mail.ru","(999) 333-44-55"},
                {"Сидоров", "Игорь", "Игоревич", "IGOR SIDOROV", "01.11.1999","nextperson@mail.ru","(991) 322-45-35"}
        });
    }
    @Parameterized.Parameter(0)
    public String lastName;

    @Parameterized.Parameter(1)
    public String firstName;

    @Parameterized.Parameter(2)
    public String middleName;

    @Parameterized.Parameter(3)
    public String cardName;

    @Parameterized.Parameter(4)
    public String birthDate;

    @Parameterized.Parameter(5)
    public String eMail;

    @Parameterized.Parameter(6)
    public String phoneNumber;

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
