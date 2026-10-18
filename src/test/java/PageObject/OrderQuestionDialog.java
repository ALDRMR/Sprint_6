package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class OrderQuestionDialog {
    private final WebDriver driver;

    public OrderQuestionDialog(WebDriver driver){
        this.driver = driver;
    }

    private final By orderDialogYesButton = By.xpath("//button[contains(text(), 'Да')]");

    public void clickYesButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(orderDialogYesButton)).click();
    }
}
