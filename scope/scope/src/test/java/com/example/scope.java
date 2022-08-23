package com.example;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class scope {

    public static void main(String[] args) {
        //browser initialization
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://qaclickacademy.com/practice.php");

        /*1 - interview question get me the number of link present on a webpage. If we see here understanding that all links will come with
         * tag "a", we can easily run a findelements in plural to get the numbers of links present.
        */
        System.out.println(driver.findElements(By.tagName("a")).size());
        
        /*2 - interview question: get me the number of link present on the footer section only. so we know now that locator finder, we now need to
        set our scope only for the footer section.
        */
        //Here we are setting the scope for the footer section only.
        WebElement footerDriver = driver.findElement(By.id("gf-BIG"));
        //In here we are only looking for the tag present on the footer section only.
        System.out.println(footerDriver.findElements(By.tagName("a")).size());

        /*3 - interview question: get the number of line present on the first column, again we can scope limiting our code to only handle that 
        column we are a looking for.
        */
        //In here we are landing expecifically on the first column.
        WebElement columnDriver = footerDriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        System.out.println(columnDriver.findElements(By.tagName("a")).size());

        /*4 - interview question: open each link and validate it work correctly.
        */
        for(int i=1; i<columnDriver.findElements(By.tagName("a")).size(); i++)
        {
            /*Here we are running an iteration starting from position 1 to size of array of element. "i" will determiante witch links
            is going to be open*/
            //This keyboard event will help us open each lin on a separate tab. This was store into a variable for better use.
            String keyboardEventHandler = Keys.chord(Keys.CONTROL,Keys.ENTER);
            //Since we are using a keyboard event sendKeys is used for this.
            columnDriver.findElements(By.tagName("a")).get(i).sendKeys(keyboardEventHandler);
        }
        
        /*Bonus printing title for each website opened*/
        Set<String> windowHadler = driver.getWindowHandles();
        Iterator<String> handlerIterator = windowHadler.iterator();
        while(handlerIterator.hasNext())
        {
            driver.switchTo().window(handlerIterator.next());
            System.out.println(driver.getTitle());
        }
    
    }
}
