package com.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class scrolldown {
    public static void main(String[] args) throws InterruptedException {
        //browser initialization
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        
        //Here we are initialazing a script.
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //Here we are scrolling downn the page at that specific position.
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);
        //We are focusing the test here on the tableFixHead and the executing scroll.
        js.executeScript("document.querySelector('.tableFixHead').scrollTop=500");

        //Here we are selecting only element of the for column of the table.
        List<WebElement> values = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        int sum = 0;

        //This loop will help us sum all the elements of that column.
        for(int i=0; i<values.size(); i++) {
            sum += Integer.parseInt(values.get(i).getText());
        }
        //This assert will help us validate that sum is correct.
        Assert.assertEquals(sum, Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim()));
        //Printing the number.
        System.out.println("La suma es: " + sum);
        
    }
}
