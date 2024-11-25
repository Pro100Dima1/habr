package org.example.habr;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get("https://www.habr.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void endingText() {
        System.out.println("Тесты завершены");
    }

    @Test
    @DisplayName("Тест по поиску иконки настройки языка в футере")
    public void searchIcon() {

        WebElement searchButton = driver.findElement(By.xpath("//nav/*[contains(text(),'Маркетинг')]"));
        searchButton.click();
        assertTrue(driver.findElement(By.cssSelector(".tm-section-name")).isDisplayed(), "Иконка не найдена");
    }

    @Test
    @DisplayName("Тест по поиску Ответы на все вопросы об IT в dropdown")
    public void qAndA() {

        WebElement dropDownList = driver.findElement(By.xpath("//button[contains(@class,'tm-header__icon_dropdown')]"));
        dropDownList.click();
        assertTrue(driver.findElement(By.xpath("//a//*[contains(text(), 'Ответы на любые вопросы об IT')]")).isDisplayed(),
                "Ответов нет");
    }


}
