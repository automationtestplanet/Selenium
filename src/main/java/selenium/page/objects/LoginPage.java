package selenium.page.objects;

import io.cucumber.java.be.I;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

@Getter
public class LoginPage extends BaseClass {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement userNameElement;

    @FindBy(name = "password")
    private WebElement passwordElement;

    @FindBy(css = "input[value='Log In']")
    private WebElement loginButtonElement;

    public void launchOpenMrsApplication(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void setUserName(String userName) {
        getUserNameElement().sendKeys(userName);
    }

    public void setPassword(String password) {
        getPasswordElement().sendKeys(password);
    }

    public WebElement getModuleElement(String moduleName) {
        return driver.findElement(By.id(moduleName));
    }

    public void clickModule(String moduleName) {
        getModuleElement(moduleName).click();
    }

    public void clickLoginButton() {
        getLoginButtonElement().click();
    }

    public void loginToOpenMrs(String userName, String password, String moduleName) {
//        driver.findElement(By.id("username")).sendKeys(userName);
//        driver.findElement(By.name("password")).sendKeys(password);
//        driver.findElement(By.id(moduleName)).click();
//        driver.findElement(By.cssSelector("input[value='Log In']")).click();

//        userNameElement.sendKeys(userName);
//        passwordElement.sendKeys(password);
//        driver.findElement(By.id(moduleName)).click();
//        loginButtonElement.click();
        setUserName(userName);
        setPassword(password);
        clickModule(moduleName);
        clickLoginButton();
    }
}
