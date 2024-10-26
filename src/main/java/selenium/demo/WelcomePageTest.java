package selenium.demo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.objects.HomePage;
import selenium.page.objects.LoginPage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WelcomePageTest {

    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        driver.get(System.getProperty("user.dir") + "/src/main/resources/HTML_Pages/Welcome.html");

        /*

        driver.findElement(By.id("userName")).sendKeys("Raju Chelle");
        driver.findElement(By.name("Password")).sendKeys("Test1234");
        driver.findElement(By.className("checkbox")).click();

        driver.findElement(By.cssSelector("[value='Register']")).click();

        driver.findElement(By.linkText("Terms & Conditions")).click();
//        driver.findElement(By.partialLinkText("Terms")).click();
//        driver.findElement(By.partialLinkText("Conditions")).click();

        driver.findElement(By.xpath("html/body/div[@id='div7']/input[2]")).sendKeys("Raju");

        */


        //Same window
//        driver.findElement(By.linkText("OpenMRS in Same Window")).click();
//        loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
//        homePage.clickLogout();

        JavascriptExecutor je = (JavascriptExecutor) driver; // down-casting

//        je.executeScript("window.scroll(0,document.body.scrollHeight)");

        String mainWindowId = driver.getWindowHandle();
        System.out.println(mainWindowId);

        // Separate tab
        driver.findElement(By.linkText("OpenMRS in New Tab")).click();
        Set<String> allWindowIds = driver.getWindowHandles();
        System.out.println(allWindowIds);

        for (String eachWindowId : allWindowIds) {
            if (!eachWindowId.equals(mainWindowId)) {
                driver.switchTo().window(eachWindowId);
                Thread.sleep(10000);
                je.executeScript("window.alert('Hello........Click OK to Continue...!!!!!!!!!!')");
                Thread.sleep(10000);

                Alert alert1 = driver.switchTo().alert();
                alert1.accept();
//                alert1.dismiss();

                loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
                homePage.clickLogout();
                driver.close();
                driver.switchTo().window(mainWindowId);
            }
        }

        // Separate Window
        driver.findElement(By.linkText("OpenMRS in Seperate Window")).click();
        allWindowIds = driver.getWindowHandles();
        System.out.println(allWindowIds);

        for (String eachWindowId : allWindowIds) {
            if (!eachWindowId.equals(mainWindowId)) {
                driver.switchTo().window(eachWindowId);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
                homePage.clickLogout();
                driver.close();
                driver.switchTo().window(mainWindowId);
            }
        }

        // In frame
        driver.switchTo().frame("frame1");
        Thread.sleep(10000);

//        loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
        loginPage.getUserNameElement().sendKeys("Admin");
        loginPage.getPasswordElement().sendKeys("Admin123");
        WebElement module = loginPage.getModuleElement("Registration Desk");
        je.executeScript("arguments[0].scrollIntoView(true)", module);
        je.executeScript("arguments[0].click()", module);
        je.executeScript("arguments[0].click()", loginPage.getLoginButtonElement());
        je.executeScript("arguments[0].scrollIntoView(true)", homePage.getLogoutMenuButton());
        je.executeScript("arguments[0].click()", homePage.getLogoutMenuButton());
        je.executeScript("arguments[0].click()", homePage.getLogoutElement());
        driver.switchTo().defaultContent();

        je.executeScript("window.scroll(0,document.body.scrollTop)");
        je.executeScript("window.scroll(0,document.body.scrollLeft)");







    }
}
