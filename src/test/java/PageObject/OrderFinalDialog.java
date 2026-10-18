package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderFinalDialog {
    private final WebDriver driver;

    public OrderFinalDialog(WebDriver driver){
        this.driver = driver;
    }

    private final By OrderFinalDialogButton = By.xpath("//button[text()='Посмотреть статус']");

    public void clickButton() {
        driver.findElement(OrderFinalDialogButton).click();
    }
}
