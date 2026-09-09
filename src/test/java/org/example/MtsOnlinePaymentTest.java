package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;


public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://www.mts.by/");

        closePopups();
    }

    private void closePopups() {
        try {
            WebElement cookieButton = driver.findElement(
                    By.xpath("//button[contains(text(),'Принять')]")
            );
            if (cookieButton.isDisplayed()) {
                cookieButton.click();
            }
        } catch (Exception ignored) {
        }

        try {
            WebElement closeBanner = driver.findElement(
                    By.xpath("//button[contains(@class, 'close')]")
            );
            if (closeBanner.isDisplayed()) {
                closeBanner.click();
            }
        } catch (Exception ignored) {
        }
    }


    @Test
    public void testBlockTitle() {
        WebElement titleElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/h2")
                )
        );
        String actualTitle = titleElement.getText()
                .replace("\n", " ")
                .replace("\r", " ")
                .trim()
                .replaceAll("\\s+", " ");

        String expectedTitle = "Онлайн пополнение без комиссии";

        assertThat(actualTitle).isEqualToIgnoringCase(expectedTitle);
    }

    @Test
    public void testPaymentLogosExist() {
        List<WebElement> logos = driver.findElements(
                By.xpath("//*[@id='pay-section']//img")
        );

        assertThat(logos).isNotEmpty();

        boolean hasVisa = logos.stream()
                .anyMatch(img -> {
                    String alt = img.getAttribute("alt");
                    String src = img.getAttribute("src");
                    return (alt != null && alt.toLowerCase().contains("visa")) ||
                            (src != null && src.toLowerCase().contains("visa"));
                });

        boolean hasVerifiedByVisa = logos.stream()
                .anyMatch(img -> {
                    String alt = img.getAttribute("alt");
                    String src = img.getAttribute("src");
                    return (alt != null && alt.toLowerCase().contains("verified by visa")) ||
                            (src != null && src.toLowerCase().contains("verified"));
                });

        boolean hasMasterCard = logos.stream()
                .anyMatch(img -> {
                    String alt = img.getAttribute("alt");
                    String src = img.getAttribute("src");
                    return (alt != null && alt.toLowerCase().contains("mastercard")) ||
                            (src != null && src.toLowerCase().contains("mastercard"));
                });

        boolean hasMasterCardSecureCode = logos.stream()
                .anyMatch(img -> {
                    String alt = img.getAttribute("alt");
                    String src = img.getAttribute("src");
                    return (alt != null && alt.toLowerCase().contains("secure code")) ||
                            (alt != null && alt.toLowerCase().contains("securecode")) ||
                            (src != null && src.toLowerCase().contains("securecode"));
                });

        boolean hasBelkart = logos.stream()
                .anyMatch(img -> {
                    String alt = img.getAttribute("alt");
                    String src = img.getAttribute("src");
                    return (alt != null && alt.toLowerCase().contains("белкарт")) ||
                            (src != null && src.toLowerCase().contains("belkart"));
                });

        System.out.println("hasVisa: " + hasVisa);
        System.out.println("hasVerifiedByVisa: " + hasVerifiedByVisa);
        System.out.println("hasMasterCard: " + hasMasterCard);
        System.out.println("hasMasterCardSecureCode: " + hasMasterCardSecureCode);
        System.out.println("hasBelkart: " + hasBelkart);

        assertThat(hasVisa)
                .as("Проверка наличия логотипа Visa")
                .isTrue();
        assertThat(hasVerifiedByVisa)
                .as("Проверка наличия логотипа Verified by Visa")
                .isTrue();
        assertThat(hasMasterCard)
                .as("Проверка наличия логотипа MasterCard")
                .isTrue();
        assertThat(hasMasterCardSecureCode)
                .as("Проверка наличия логотипа MasterCard SecureCode")
                .isTrue();
        assertThat(hasBelkart)
                .as("Проверка наличия логотипа Белкарт")
                .isTrue();
    }

    @Test
    public void testDetailsLink() {
        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Подробнее о сервисе")
                )
        );

        String href = link.getAttribute("href");
        assertThat(href).contains("mts.by");

        link.click();

        assertThat(driver.getCurrentUrl()).isNotEqualTo("https://www.mts.by/");
    }

    @Test
    public void testContinueButton() {

        WebElement block = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@id='pay-section']")
                )
        );
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                block
        );

        WebElement serviceOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[contains(text(),'Услуги связи')]")
                )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", serviceOption);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement phoneInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[contains(@placeholder, 'Номер')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", phoneInput);
        wait.until(ExpectedConditions.visibilityOf(phoneInput));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", phoneInput);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = '297777777';",
                phoneInput
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                phoneInput
        );

        WebElement amountInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@id=\"connection-sum\"]")
                )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", amountInput);

        wait.until(ExpectedConditions.visibilityOf(amountInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", amountInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '1.00';", amountInput);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                amountInput
        );

        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"pay-connection\"]/button")
                )
        );
        continueButton.click();

        // 5. Проверяем, что кнопка больше не кликабельна или исчезла
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        boolean hasPaymentFrame = false;

        for (WebElement frame : frames) {
            String src = frame.getAttribute("src");
            System.out.println("Найден iframe: " + src);
            if (src != null && (src.contains("bepaid") || src.contains("pay") || src.contains("checkout"))) {
                hasPaymentFrame = true;
                System.out.println("✅ Найден iframe с оплатой: " + src);
                break;
            }
        }

        // Если iframe не найден, пробуем найти через XPath
        if (!hasPaymentFrame) {
            try {
                WebElement frame = driver.findElement(
                        By.xpath("//iframe[contains(@src, 'bepaid') or contains(@src, 'pay')]")
                );
                hasPaymentFrame = true;
                System.out.println("✅ Найден iframe через XPath: " + frame.getAttribute("src"));
            } catch (Exception e) {
                System.out.println("iframe не найден через XPath");
            }
        }

        assertThat(hasPaymentFrame)
                .as("После нажатия 'Продолжить' должен появиться iframe с оплатой")
                .isTrue();
    }


    @AfterEach
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
