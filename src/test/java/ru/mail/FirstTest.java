package ru.mail;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstTest {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.ru/");
    }

    @Test
    public void firstTest1() {
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Mail.Ru: почта, поиск в интернете, новости, игры"));
    }




    @After
    public void close() {
        driver.quit();
    }
}
