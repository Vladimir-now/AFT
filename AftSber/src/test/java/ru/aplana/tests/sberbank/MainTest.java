package ru.aplana.tests.sberbank;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.aplana.tests.base.BaseClass;
import ru.aplana.tests.sberbank.form.Form;
import ru.aplana.tests.sberbank.form.XPathFormSber;


public class MainTest extends BaseClass {

    @Test
    public void scenarioSber() {
//        Нажать на меню – Карты
        WebElement cards = driver.findElement(By.xpath("//label[@for = \"kitt-top-menu-2\"]"));
        cards.click();

//        Выбрать подменю – «Дебетовые карты»
        WebElement debitCard = driver.findElement(By.xpath("//a[contains(@data-cga_click_top_menu, \"Карты_Дебетовые\")]"));
        wait.until(ExpectedConditions.visibilityOf(debitCard)).click();

//        Проверить наличие на странице заголовка – «Дебетовые карты»
        WebElement findHeaderDC = driver.findElement(By.xpath("//div[@class = \"kit-row\" ]/h2[contains(@class, \"kit-col_xs\")]"));
        Assert.assertEquals("Не найден заголовок: \"Дебетовые карты\"", "Дебетовые карты", findHeaderDC.getText());

//        Под заголовком из представленных кнопок кликнуть – «Молодежная»
        WebElement youthCard = driver.findElement(By.xpath("//div[@role = \"radiogroup\" ]/label[@for = \"dcl-filter-4\"]"));
        wait.until(ExpectedConditions.visibilityOf(youthCard)).click();

//        Выбрать карту «Молодёжная карта» - кликнуть на кнопку «Заказать онлайн»
        WebElement orderOnline = driver.findElement(By.xpath("//a[contains(@href, \"youth-card#order\")]"));
        scrollToElementJs(orderOnline);
        wait.until(ExpectedConditions.elementToBeClickable(orderOnline)).click();


//        Проверить наличие на странице заголовка – «Молодёжная карта»
        WebElement findHeaderYouthCard = driver.findElement(By.xpath("//h1[@data-test-id = \"PageTeaserDict_header\"]"));
        Assert.assertEquals("Не найден заголовок: \" Молодёжная карта\"", "Молодёжная карта", findHeaderYouthCard.getText());

//        Нажать «Оформить онлайн»
        WebElement clickOrderOnline = driver.findElement(By.xpath("//div[contains(@class, \"bli-widget\")]/a"));// Button внизу страницы
        scrollToElementJs(clickOrderOnline);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(clickOrderOnline)).click();

//        В представленной форме заполнить поля:
//            * Фамилию, Имя, Отчетво, Имя и фамилия на карте, Дату рождения, E-mail, Мобильный телефон
//            * Основной документ - не заполняем
//        Проверить, что все поля заполнены правильно

        Form form = new Form("Петров", "Иван", "Петрович", "Ivan Petrov",
                 "12.01.1995", "petrov_ivan@mail.ru", "(999) 999-99-99");
        XPathFormSber xPath = new XPathFormSber();
        fillInputField(driver.findElement(By.xpath(xPath.getLastNameXPath())), form.getLastName(), "Фамилия");
        fillInputField(driver.findElement(By.xpath(xPath.getFirstNameXPath())), form.getFirstName(), "Имя");
        fillInputField(driver.findElement(By.xpath(xPath.getMiddleNameXPath())), form.getMiddleName(), "Отчество");

        //Проверка для поля "Имя и фамилия на карте"
        WebElement checkAttributeCardName = driver.findElement(By.xpath(xPath.getNameCardXPath()));
        Assert.assertEquals("Поле \"Имя и фамилия на карте\" было заполнено неправильно",
                form.getNameCard().toUpperCase(), checkAttributeCardName.getAttribute("value"));

        fillInputField(driver.findElement(By.xpath(xPath.getDateBirthdayXPath())), form.getDateBirthday(), "Дата рождения");
        fillInputField(driver.findElement(By.xpath(xPath.getEmailXPath())), form.getEmail(), "E-mail");
        fillInputFieldForPhone(driver.findElement(By.xpath(xPath.getPhoneNumberXPath())), form.getPhoneNumber());

//        Нажать «Далее»
        WebElement nextButton = driver.findElement(By.xpath("//button[@class=\"odcui-button odcui-button_color_black odc-footer__step-button odc-footer__step-button_type_next -visor-no-click\"]"));
        scrollToElementJs(nextButton);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

//        Проверить, что появилось сообщение именно у незаполненных полях – «Обязательное поле»

        Assert.assertEquals("Сообщение \"Обязательное поле\" в поле \"Серия\" отсутствует","Обязательное поле", driver.findElement(By.xpath(xPath.getSeriesPassportMessageFieldXPath())).getText());
        Assert.assertEquals("Сообщение \"Обязательное поле\" в поле \"Номер\" отсутствует","Обязательное поле", driver.findElement(By.xpath(xPath.getNumberPassportMessageFieldXPath())).getText());
        Assert.assertEquals("Сообщение \"Обязательное поле\" в поле \"Дата выпуска\" отсутствует","Обязательное поле", driver.findElement(By.xpath(xPath.getDateIssuePassportMessageFieldXPath())).getText());
        Assert.assertEquals("Сообщение \"Обязательное поле\" в поле \"Соглашение на обработку персональных данных\" отсутствует",
                "Обязательное поле", driver.findElement(By.xpath(xPath.getTickIAgreeMessageFieldXPath())).getText());

    }
}
