package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.pages.HomePage;
import org.example.pages.PaymentFramePage;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Тестирование сайта MTS.BY")
@Feature("Онлайн пополнение без комиссии")

public class MtsPaymentPageObjectTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        homePage = new HomePage(driver);
        homePage.open();
        homePage.closePopups();
    }

    // 1. ПРОВЕРКА ЗАГОЛОВКА БЛОКА
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка заголовка блока 'Онлайн пополнение без комиссии'")
    @Story("Проверка заголовка")
    public void testBlockTitle() {
        String actualTitle = homePage.getBlockTitle();
        String expectedTitle = "Онлайн пополнение без комиссии";
        assertThat(actualTitle).isEqualToIgnoringCase(expectedTitle);
    }

    // 2. ПРОВЕРКА НАЛИЧИЯ ВСЕХ ЛОГОТИПОВ
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка наличия логотипов платежных систем: Visa, MasterCard, Belkart, Verified by Visa")
    @Story("Проверка логотипов")
    public void testPaymentLogosExist() {
        List<WebElement> logos = homePage.getPaymentLogos();
        assertThat(logos).isNotEmpty();

        boolean hasVisa = logos.stream().anyMatch(img -> {
            String src = img.getAttribute("src");
            return src != null && src.toLowerCase().contains("visa");
        });

        boolean hasMasterCard = logos.stream().anyMatch(img -> {
            String src = img.getAttribute("src");
            return src != null && src.toLowerCase().contains("mastercard");
        });

        boolean hasBelkart = logos.stream().anyMatch(img -> {
            String src = img.getAttribute("src");
            return src != null && src.toLowerCase().contains("belkart");
        });

        boolean hasVerifiedByVisa = logos.stream().anyMatch(img -> {
            String src = img.getAttribute("src");
            return src != null && src.toLowerCase().contains("verified");
        });

        assertThat(hasVisa).as("Visa logo").isTrue();
        assertThat(hasMasterCard).as("MasterCard logo").isTrue();
        assertThat(hasBelkart).as("Belkart logo").isTrue();
        assertThat(hasVerifiedByVisa).as("Verified by Visa").isTrue();
    }

    // 3. ПРОВЕРКА ССЫЛКИ "ПОДРОБНЕЕ О СЕРВИСЕ"
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка ссылки 'Подробнее о сервисе'")
    @Story("Проверка ссылки")
    public void testDetailsLink() {
        String href = homePage.getDetailsLinkHref();
        assertThat(href).contains("mts.by");

        homePage.clickDetailsLink();
        assertThat(driver.getCurrentUrl()).isNotEqualTo("https://www.mts.by/");
    }

    // 4. ПРОВЕРКА НАДПИСЕЙ В НЕЗАПОЛНЕННЫХ ПОЛЯХ (все 4 варианта)
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка надписей (placeholder) в незаполненных полях для всех вариантов оплаты")
    @Story("Проверка плейсхолдеров")
    public void testPlaceholdersForAllServices() {

        // Услуги связи
        homePage.selectServiceCommunication();
        assertThat(homePage.getPhoneInputPlaceholder()).as("Услуги связи - номер").isNotEmpty();
        assertThat(homePage.getAmountInputPlaceholder()).as("Услуги связи - сумма").isNotEmpty();

        // Домашний интернет
        homePage.selectServiceHomeInternet();
        assertThat(homePage.getPhoneInputPlaceholder()).as("Домашний интернет - номер").isNotEmpty();
        assertThat(homePage.getAmountInputPlaceholder()).as("Домашний интернет - сумма").isNotEmpty();

        // Рассрочка
        homePage.selectServiceInstallment();
        assertThat(homePage.getPhoneInputPlaceholder()).as("Рассрочка - номер").isNotEmpty();
        assertThat(homePage.getAmountInputPlaceholder()).as("Рассрочка - сумма").isNotEmpty();

        // Задолженность
        homePage.selectServiceDebt();
        assertThat(homePage.getPhoneInputPlaceholder()).as("Задолженность - номер").isNotEmpty();
        assertThat(homePage.getAmountInputPlaceholder()).as("Задолженность - сумма").isNotEmpty();
    }

    // 5. ПОЛНЫЙ СЦЕНАРИЙ ДЛЯ "УСЛУГИ СВЯЗИ" С ПРОВЕРКОЙ В IFARME
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Полный сценарий оплаты услуги связи с проверкой платежного фрейма")
    @Story("Полный сценарий оплаты")
    public void testCommunicationServiceFullFlow() {
        // 1. Выбираем "Услуги связи"
        homePage.selectServiceCommunication();

        // 2. Заполняем поля
        homePage.enterPhoneNumber("297777777");
        homePage.enterAmount("1.00");

        // 3. Нажимаем "Продолжить"
        homePage.clickContinue();

        // 4. Проверяем, что появился iframe
        assertThat(homePage.isPaymentFramePresent())
                .as("After clicking 'Continue', payment iframe should appear")
                .isTrue();

        // 5. Переключаемся в iframe
        PaymentFramePage framePage = new PaymentFramePage(driver);
        framePage.switchToFrame();

        // 6. Проверяем сумму (на экране и на кнопке)
        String displayedAmount = framePage.getDisplayedAmount();
        assertThat(displayedAmount).as("Displayed amount in iframe").contains("1.00");

        String payButtonText = framePage.getPayButtonText();
        assertThat(payButtonText).as("Pay button text").isNotEmpty();

        // 7. Проверяем надписи в незаполненных полях ввода карты
        assertThat(framePage.getCardNumberPlaceholder()).as("Card number placeholder").isNotEmpty();
        assertThat(framePage.getExpiryPlaceholder()).as("Expiry placeholder").isNotEmpty();
        assertThat(framePage.getCvcPlaceholder()).as("CVC placeholder").isNotEmpty();
        assertThat(framePage.getCardholderPlaceholder()).as("Cardholder name placeholder").isNotEmpty();

        // 8. Проверяем наличие иконок платёжных систем в iframe
        assertThat(framePage.isPaymentIconPresent("visa"))
                .as("Visa icon in payment frame").isTrue();
        assertThat(framePage.isPaymentIconPresent("mastercard"))
                .as("MasterCard icon in payment frame").isTrue();
        assertThat(framePage.isPaymentIconPresent("belkart"))
                .as("Belkart icon in payment frame").isTrue();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}