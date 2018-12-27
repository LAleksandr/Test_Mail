package ru.mail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class FirstTest {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe"); //Для тестирования будет использоваться браузер Chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get("https://mail.ru/");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Mail.Ru: почта, поиск в интернете, новости, игры"));
    }

    @Test
    // Тест на ошибку "Введите имя ящика и пароль"
    public void noLogin() {
        // попытка войти в систему
        WebElement loginButton = driver.findElement(By.id("mailbox:submit"));
        loginButton.click();

        // проверка ошибки
        WebElement loginFailed = driver.findElement(By.id("mailbox:error"));
        String mailLoginFailed = loginFailed.getText();
        Assert.assertEquals("Введите имя ящика и пароль", mailLoginFailed);
    }

    @Test
    // Тест на ошибку "Введите пароль"
    public void noPassword() {
        //находим поле и вводим логин от почтового ящика
        WebElement loginField = driver.findElement(By.id("mailbox:login"));
        loginField.sendKeys("test_selenium00");

        // попытка войти в систему
        WebElement loginButton = driver.findElement(By.id("mailbox:submit"));
        loginButton.click();

        // проверка ошибки
        WebElement passwordFailed = driver.findElement(By.id("mailbox:error"));
        String mailPasswordFailed = passwordFailed.getText();
        Assert.assertEquals("Введите пароль", mailPasswordFailed);
    }

    @Test
    // Тест авторизация на почте и поиск письма
    public void userLogin() {
        //находим поле и вводим логин от почтового ящика
        WebElement loginField = driver.findElement(By.id("mailbox:login"));
        loginField.sendKeys("test_selenium00");

        //поиск поля и ввод пароля
        WebElement passwordField = driver.findElement(By.id("mailbox:password"));
        passwordField.sendKeys("qwe1234");

        // вход в систему
        WebElement loginButton = driver.findElement(By.id("mailbox:submit"));
        loginButton.click();

        // активизация поля поиска
        WebElement searchActivation = driver.findElement(By.id("portal-menu__search__form"));
        searchActivation.click();
        // ввод темы письма, которое необходимо найти и поиск его
        WebElement searchLetter = driver.findElement(By.name("blank"));
        searchLetter.sendKeys("Как воспользоваться почтой с мобильного?");
        WebElement seachButton = driver.findElement(By.name("search"));
        seachButton.click();

        //открытие найденного письма
        WebElement openingLetter = driver.findElement(By.className("search_tick"));
        openingLetter.click();

        // проверка времени, когда пришло письмо
        WebElement letterVerification = driver.findElement(By.className("b-letter__head__date"));
        String timeComing = letterVerification.getText();
        Assert.assertEquals("12 декабря, 19:50", timeComing);
    }

    @After
    public void close() {
        driver.quit();
    }
}
