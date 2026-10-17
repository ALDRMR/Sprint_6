package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class OrderQuestionDialog {
    private final WebDriver driver;

    public OrderQuestionDialog(WebDriver driver){
        this.driver = driver;
    }

    private final By OrderDialogModalHeader = By.xpath("//*[contains(@class, 'Order_ModalHeader')]");
    private final By orderDialogYesButton = By.xpath("//button[contains(text(), 'Да')]");

    public String modelText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(OrderDialogModalHeader));
        return element.getText();
    }

    public void clickYesButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(orderDialogYesButton)).click();
    }
}
