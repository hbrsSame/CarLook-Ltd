package Selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWebDriver {

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Same\\Desktop\\ChromeDriver\\chromedriver.exe");
        ChromeDriver chromeDriver = new ChromeDriver();



        chromeDriver.get("localhost:8080");
        WebElement element = chromeDriver.findElement(By.xpath("//input[@name='username']"));
        element.sendKeys("hans.peter@carlook.de");

        WebElement button = chromeDriver.findElement(By.xpath("//input[@name='Login']"));
        button.click();


    }


}
