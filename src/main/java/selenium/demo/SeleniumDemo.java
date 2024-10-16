package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumDemo {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir")+ "/src/main/resources/Drivers/chrome/chromedriver129v.exe");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://demo.openmrs.org/openmrs/login.htm");

        // for single element
        WebElement element = driver.findElement(By.id("username"));
        element.sendKeys("Admin");

        //for more than  one element

        List<WebElement> webElements = driver.findElements(By.tagName("input"));

        System.out.println(webElements.size());

        driver.findElement(By.name("password")).sendKeys("Admin123");

        driver.findElement(By.id("Registration Desk")).click();

        driver.findElement(By.id("loginButton")).click();

//        driver.close();


    }
}