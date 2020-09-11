package Selenium;


import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumWebDriver {


    public static void main(String[] args){

        // Vorbereitungen für ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Same\\Desktop\\ChromeDriver\\chromedriver.exe");
        ChromeDriver chromeDriver = new ChromeDriver();

        // Website aufrufen
        chromeDriver.get("localhost:8080");

        // Login und Passwort mit Daten eingeben und auf Login Button klicken
        WebElement username = chromeDriver.findElement(By.xpath("//input[@name='username']"));
        username.sendKeys("hans@carlook.de");

        WebElement password = chromeDriver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("hallo123");
        password.sendKeys(Keys.ENTER);

    }


}
