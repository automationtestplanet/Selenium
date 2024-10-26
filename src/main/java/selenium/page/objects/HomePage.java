package selenium.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HomePage extends BaseClass {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(partialLinkText = "Logout")
    private WebElement logoutElement;

    @FindBy(xpath = "//button[@class='navbar-toggler']")
    private WebElement logoutMenuButton;

    public boolean verifyTileTile(String tileName) {
        return driver.findElement(By.partialLinkText(tileName)).isDisplayed();
    }

    public void clickTile(String tileName) {
        driver.findElement(By.partialLinkText(tileName)).click();
    }

    public WebElement logoutElement() {
        return getLogoutElement();
    }

    public void clickLogout(){
        getLogoutElement().click();
    }

    public void clickLogoutMenuButton(){
        if(!getLogoutMenuButton().getAttribute("aria-expanded").equalsIgnoreCase("true")){
            getLogoutMenuButton().click();
        }
    }
}
