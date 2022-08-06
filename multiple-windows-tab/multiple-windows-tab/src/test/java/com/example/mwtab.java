package com.example;

import java.util.Iterator;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class mwtab{

    public static void main(String[] args) {
        
        //Basic driver initialization
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.findElement(By.cssSelector(".blinkingText")).click();

        /*Handle windows tab
        Here we are creating an array of windows id that selenium open when click a new link, this will create
        as many id as window tab are open*/
        Set<String> windows = driver.getWindowHandles();//[parentId,childId,sub-childId]
        /*Here we are using this iterator to move to next windows Id from windowhandles, since we are starting from empty or null
        the first iteration will land on the parentId*/
        Iterator<String> iteration = windows.iterator();
        //Here we are at parentId so we should save that iteration into a variable to always know the parent id 
        String parentId = iteration.next();
        String childId = iteration.next();

        //Switch window tab to child
        driver.switchTo().window(childId);
        /*Here i extracted from the text the email that i will use on the username text box 
        on parentId window tab*/
        String email = driver.findElement(By.cssSelector(".im-para.red")).getText().split("at")[1].trim().split(" ")[0];
        //Switch window back to parent
        driver.switchTo().window(parentId);
        //Insert the email on the textbox on parent tabs
        driver.findElement(By.id("username")).sendKeys(email);
        
    }
     
}
