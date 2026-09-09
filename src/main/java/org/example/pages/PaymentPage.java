package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaymentPage extends BasePage {
    //  Заголовок/сумма на странице оплаты
    @FindBy(xpath = "//div[contains(@class, 'total')]//span[contains(@class, 'amount')]")
    private WebElement totalAmount;

    // Номер телефона на странице оплаты
    @FindBy(xpath = "//div[contains(@class, 'order')]//span[contains(@class, 'phone')]")
    private WebElement phoneNumberDisplay;

    // Кнопка "Оплатить" (или "Продолжить")
    @FindBy(xpath = "//button[contains(text(), 'Оплатить') or contains(text(), 'Pay')]")
    private WebElement payButton;

    //  Поле для ввода номера карты (если есть на этой странице, до iframe)
    @FindBy(xpath = "//input[contains(@placeholder, 'Номер карты')]")
    private WebElement cardNumberInput;

    //  Поле для ввода срока действия
    @FindBy(xpath = "//input[contains(@placeholder, 'Срок')]")
    private WebElement expiryInput;

    //  Поле для ввода CVC
    @FindBy(xpath = "//input[contains(@placeholder, 'CVC') or contains(@placeholder, 'CVV')]")
    private WebElement cvcInput;

    //  Поле для ввода имени держателя
    @FindBy(xpath = "//input[contains(@placeholder, 'Имя') or contains(@placeholder, 'Name')]")
    private WebElement cardholderNameInput;

    //  Иконки платёжных систем на странице
    @FindBy(xpath = "//img[contains(@src, 'visa') or contains(@src, 'mastercard') or contains(@src, 'belkart')]")
    private List<WebElement> paymentIcons;

    // Крестик для закрытия страницы оплаты
    @FindBy(xpath = "//button[contains(@class, 'close')]")
    private WebElement closeButton;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    // Проверка отображения суммы
    public String getTotalAmount() {
        waitForVisibility(totalAmount);
        return totalAmount.getText();
    }

    //  Проверка отображения номера телефона
    public String getPhoneNumber() {
        waitForVisibility(phoneNumberDisplay);
        return phoneNumberDisplay.getText();
    }

    //  Получение текста на кнопке оплаты
    public String getPayButtonText() {
        waitForVisibility(payButton);
        return payButton.getText();
    }

    //  Получение placeholder'ов для полей ввода карты
    public String getCardNumberPlaceholder() {
        waitForVisibility(cardNumberInput);
        return cardNumberInput.getAttribute("placeholder");
    }

    public String getExpiryPlaceholder() {
        waitForVisibility(expiryInput);
        return expiryInput.getAttribute("placeholder");
    }

    public String getCvcPlaceholder() {
        waitForVisibility(cvcInput);
        return cvcInput.getAttribute("placeholder");
    }

    public String getCardholderPlaceholder() {
        waitForVisibility(cardholderNameInput);
        return cardholderNameInput.getAttribute("placeholder");
    }

    // Проверка наличия иконок платёжных систем
    public List<WebElement> getPaymentIcons() {
        return paymentIcons;
    }

    public boolean isPaymentIconPresent(String keyword) {
        for (WebElement img : paymentIcons) {
            String src = img.getAttribute("src");
            if (src != null && src.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // Закрыть страницу оплаты
    public void closePaymentPage() {
        try {
            if (closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (Exception ignored) {}
    }

    // Клик по кнопке "Оплатить"
    public void clickPayButton() {
        waitForClickable(payButton);
        payButton.click();
        waitForPresenceOfElement(By.xpath("//iframe[contains(@src, 'bepaid') or contains(@src, 'pay')]"));
    }
    }
