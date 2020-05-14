import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;


public class lab3 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
         WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        String resultItems = "//div[@class='g']//h3";
        ArrayList<Object>searchLinks = new ArrayList<>();


        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("dinozaur");
        Thread.sleep(3000);
        executor.executeScript("arguments[0].click();", driver.findElements(By.xpath("//input[@name='btnK' ]")).get(1));
        Thread.sleep(3000);


        for (int i = 0; i < 10 ; i++) {
            searchLinks.add(driver.findElements(By.xpath("//div[@class='g']//cite")).get(i).getText());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  driver.findElements(By.xpath(resultItems)).get(i));
            executor.executeScript("arguments[0].click();", driver.findElements(By.xpath(resultItems)).get(i));
            Thread.sleep(3000);
            searchLinks.add(driver.getCurrentUrl());
            searchLinks.add(driver.findElements(By.xpath("//*[contains(text(),'dinozaur')]")).size());
            driver.navigate().back();
            Thread.sleep(3000);
        }


        driver.quit();
        System.out.println(searchLinks.toString());

    }
}
