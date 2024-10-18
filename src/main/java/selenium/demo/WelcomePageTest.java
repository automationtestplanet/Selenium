package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WelcomePageTest {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(System.getProperty("user.dir") + "/src/main/resources/HTML_Pages/Welcome.html");

        driver.findElement(By.id("userName")).sendKeys("Raju Chelle");
        driver.findElement(By.name("Password")).sendKeys("Test1234");
        driver.findElement(By.className("checkbox")).click();

        driver.findElement(By.cssSelector("[value='Register']")).click();

        driver.findElement(By.linkText("Terms & Conditions")).click();
//        driver.findElement(By.partialLinkText("Terms")).click();
//        driver.findElement(By.partialLinkText("Conditions")).click();

        driver.findElement(By.xpath("html/body/div[@id='div7']/input[2]")).sendKeys("Raju");


    }
}
