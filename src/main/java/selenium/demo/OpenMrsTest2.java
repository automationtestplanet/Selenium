package selenium.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.objects.HomePage;
import selenium.page.objects.LoginPage;

public class OpenMrsTest2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");
        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.launchOpenMrsApplication("https://demo.openmrs.org/openmrs/login.htm");
        if (loginPage.verifyPageTitle("Login")) {
            loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
            if (homePage.verifyElementDisplayed(homePage.getLogoutElement()) && homePage.verifyPageTitle("Home")) {
                System.out.println("Login Successful");
                // Register a Patient
                if (homePage.verifyTileTile("Register a patient")) {
                    homePage.clickTile("Register a patient");
                }else{
                    System.out.println("Register a patient tile is not available");
                }
            }else{
                System.out.println("Login Failed");
            }
        } else {
            System.out.println("Login Page is not available");
        }
    }
}
