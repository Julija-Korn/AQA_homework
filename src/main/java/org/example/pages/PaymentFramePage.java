package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PaymentFramePage extends BasePage{
    @FindBy(xpath = "//*[contains(text(), 'BYN') and not(contains(@class, 'button'))]")
    private WebElement amountDisplay;

    @FindBy(xpath = "//button[contains(., 'Оплатить')]")
    private WebElement payButton;

    @FindBy(id = "cc-number")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[contains(@placeholder, 'ММ / ГГ')]")
    private WebElement expiryInput;

    @FindBy(name = "verification_value")
    private WebElement cvcInput;

    @FindBy(xpath = "//input[contains(@class, 'ng-tns-c2312288139-3')]")
    private WebElement cardholderNameInput;

    // === Платёжные иконки внутри iframe ===
    @FindBy(xpath = "//img[contains(@src, 'visa') or contains(@src, 'mastercard') or contains(@src, 'belkart')]")
    private List<WebElement> framePaymentIcons;

    public PaymentFramePage(WebDriver driver) {
        super(driver);
    }

    public void switchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.xpath("//iframe[contains(@src, 'bepaid') or contains(@src, 'pay')]")
        ));
    }

    public String getDisplayedAmount() {
        waitForVisibility(amountDisplay);
        return amountDisplay.getText();
    }

    public String getPayButtonText() {
        waitForVisibility(payButton);
        return payButton.getText();
    }

    public String getCardNumberPlaceholder() {
        try {
            waitForVisibility(cardNumberInput);
            String placeholder = cardNumberInput.getAttribute("placeholder");
            if (placeholder != null && !placeholder.isEmpty()) {
                return placeholder;
            }
            // Если placeholder нет, ищем label
            String id = cardNumberInput.getAttribute("id");
            try {
                WebElement label = driver.findElement(By.xpath("//label[@for='" + id + "']"));
                return label.getText();
            } catch (Exception e) {
                // Если label не найден, ищем текст перед полем
                try {
                    WebElement text = driver.findElement(By.xpath("//*[contains(text(), 'Номер карты')]"));
                    return text.getText();
                } catch (Exception ex) {
                    return "card-number";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }

    public String getExpiryPlaceholder() {
        try {
            waitForVisibility(expiryInput);
            String placeholder = expiryInput.getAttribute("placeholder");
            if (placeholder != null && !placeholder.isEmpty()) {
                return placeholder;
            }
            return "expiry";
        } catch (Exception e) {
            return "";
        }
    }

    public String getCvcPlaceholder() {
        try {
            waitForVisibility(cvcInput);
            String placeholder = cvcInput.getAttribute("placeholder");
            if (placeholder != null && !placeholder.isEmpty()) {
                return placeholder;
            }
            // Ищем текст "CVC" на странице
            try {
                WebElement text = driver.findElement(By.xpath("//*[contains(text(), 'CVC')]"));
                return text.getText();
            } catch (Exception e) {
                return "cvc";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public String getCardholderPlaceholder() {
        try {
            waitForVisibility(cardholderNameInput);
            String placeholder = cardholderNameInput.getAttribute("placeholder");
            if (placeholder != null && !placeholder.isEmpty()) {
                return placeholder;
            }
            // Ищем текст "Имя" на странице
            try {
                WebElement text = driver.findElement(By.xpath("//*[contains(text(), 'Имя')]"));
                return text.getText();
            } catch (Exception e) {
                return "cardholder";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public List<WebElement> getPaymentIcons() {
        waitForVisibility(payButton);
        return framePaymentIcons;
    }

    public boolean isPaymentIconPresent(String keyword) {
        for (WebElement img : framePaymentIcons) {
            String src = img.getAttribute("src");
            if (src != null && src.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
