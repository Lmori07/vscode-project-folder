package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class action {

    public static void main(String[] args) {
        
        //Basic driver invocation
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.amazon.com/");

        WebElement mouseOver = driver.findElement(By.id("nav-link-accountList-nav-line-1")); 
        WebElement keyboardUpperCase = driver.findElement(By.id("twotabsearchtextbox"));

        Actions act = new Actions(driver);
        
        //Here will be the action from mouse and keyboard that i can setup on selenium
        act.moveToElement(mouseOver).build().perform();
        act.moveToElement(keyboardUpperCase).click().keyDown(Keys.SHIFT).sendKeys("hello").build().perform();
        driver.quit();
    }

}
