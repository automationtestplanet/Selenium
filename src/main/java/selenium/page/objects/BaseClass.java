package selenium.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {
    public WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyPageTitle(String title) {
        return driver.getTitle().trim().equalsIgnoreCase(title);
    }

    public boolean verifyPageName(String pageName) {
        return driver.findElement(By.xpath("//h2[contains(text(),'" + pageName + "')]")).isDisplayed();
    }

    public boolean verifyElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }
}
