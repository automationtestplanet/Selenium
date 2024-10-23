package selenium.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class BaseClass {
    public WebDriver driver;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".icon-home.small")
    private WebElement homeIconElement;

    public boolean verifyPageTitle(String title) {
        return driver.getTitle().trim().equalsIgnoreCase(title);
    }

    public boolean verifyPageName(String pageName) {
        return driver.findElement(By.xpath("//h2[contains(text(),'" + pageName + "')]")).isDisplayed();
    }

    public boolean verifyElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public void clickHomeIcon() {
        getHomeIconElement().click();
    }

    public void waitForVisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForStalenessOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.stalenessOf(element));
    }
}
