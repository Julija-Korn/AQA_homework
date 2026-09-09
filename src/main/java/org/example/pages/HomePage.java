package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    //Заголовок блока
    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение')]")
    private WebElement blockTitle;

    //Логотипы платёжных систем
    @FindBy(xpath = "//*[@id='pay-section']//img")
    private List<WebElement> paymentLogos;

    //Ссылка "Подробнее о сервисе"
    @FindBy(linkText = "Подробнее о сервисе")
    private WebElement detailsLink;

    //Кнопка "Принять" (cookie)
    @FindBy(xpath = "//button[contains(text(),'Принять')]")
    private WebElement cookieAcceptButton;

    //Крестик для закрытия баннера
    @FindBy(xpath = "//button[contains(@class, 'close')]")
    private WebElement closeBannerButton;

    //Пункты выбора услуги (услуги связи, домашний интернет, рассрочка, задолженность)
    @FindBy(xpath = "//*[contains(text(),'Услуги связи')]")
    private WebElement serviceCommunication;

    @FindBy(xpath = "//option[contains(text(),'Домашний интернет')]")
    private WebElement serviceHomeInternet;

    @FindBy(xpath = "//option[contains(text(),'Рассрочка')]")
    private WebElement serviceInstallment;

    @FindBy(xpath = "//option[contains(text(),'Задолженность')]")
    private WebElement serviceDebt;

    //Поля ввода (для услуг связи)
    @FindBy(xpath = "//input[contains(@placeholder, 'Номер')]")
    private WebElement phoneInput;

    @FindBy(id = "connection-sum")
    private WebElement amountInput;

    //Кнопка "Продолжить"
    @FindBy(xpath = "//*[@id='pay-connection']/button")
    private WebElement continueButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://www.mts.by/");
    }

    public void closePopups() {
        try {
            if (cookieAcceptButton.isDisplayed()) {
                cookieAcceptButton.click();
            }
        } catch (Exception ignored) {}

        try {
            if (closeBannerButton.isDisplayed()) {
                closeBannerButton.click();
            }
        } catch (Exception ignored) {}
    }

    public String getBlockTitle() {
        waitForVisibility(blockTitle);
        return blockTitle.getText()
                .replace("\n", " ")
                .replace("\r", " ")
                .trim()
                .replaceAll("\\s+", " ");
    }

    public List<WebElement> getPaymentLogos() {
        return paymentLogos;
    }

    public String getDetailsLinkHref() {
        waitForClickable(detailsLink);
        return detailsLink.getAttribute("href");
    }

    public void clickDetailsLink() {
        waitForClickable(detailsLink);
        detailsLink.click();
    }

    public void selectServiceCommunication() {
        scrollToElement(serviceCommunication);
        waitForClickable(serviceCommunication);
        clickWithJS(serviceCommunication);
        waitForVisibility(phoneInput);
    }

    public void selectServiceHomeInternet() {
        scrollToElement(serviceHomeInternet);
        waitForClickable(serviceHomeInternet);
        clickWithJS(serviceHomeInternet);
        waitForVisibility(phoneInput);
    }

    public void selectServiceInstallment() {
        scrollToElement(serviceInstallment);
        waitForClickable(serviceInstallment);
        clickWithJS(serviceInstallment);
        waitForVisibility(phoneInput);
    }

    public void selectServiceDebt() {
        scrollToElement(serviceDebt);
        waitForClickable(serviceDebt);
        clickWithJS(serviceDebt);
        waitForVisibility(phoneInput);
    }

    public void enterPhoneNumber(String phone) {
        scrollToElement(phoneInput);
        waitForVisibility(phoneInput);
        clickWithJS(phoneInput);
        setValueWithJS(phoneInput, phone);
    }

    public void enterAmount(String amount) {
        scrollToElement(amountInput);
        waitForVisibility(amountInput);
        clickWithJS(amountInput);
        setValueWithJS(amountInput, amount);
    }

    public void clickContinue() {
        scrollToElement(continueButton);
        waitForClickable(continueButton);
        continueButton.click();
        waitForPresenceOfElement(By.xpath("//iframe[contains(@src, 'bepaid') or contains(@src, 'pay')]"));
    }

    //Получение надписи (placeholder) у поля ввода для текущего выбранного сервиса
    public String getPhoneInputPlaceholder() {
        return phoneInput.getAttribute("placeholder");
    }

    public String getAmountInputPlaceholder() {
        return amountInput.getAttribute("placeholder");
    }

    // Проверка наличия iframe с оплатой
    public boolean isPaymentFramePresent() {
        try {
            driver.findElement(By.xpath("//iframe[contains(@src, 'bepaid') or contains(@src, 'pay')]"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
