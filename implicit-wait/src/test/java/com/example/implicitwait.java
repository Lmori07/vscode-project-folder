package com.example;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class implicitwait {

    public static void main(String[] args) {

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        driver.manage().window().maximize();
        String[] itemsNeeded = {"Brocolli" , "Cucumber"};
        addItem(driver,itemsNeeded);
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
        //Here we are using our new implicit wait method to wait for the element to be visible.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
        driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");;
        driver.findElement(By.cssSelector("button.promoBtn")).click();
        String promoInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo"))).getText();
        //*****Assertion*****
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo"))).getText(), promoInfo);
        
        
    }

    public static void addItem(WebDriver driver, String[] itemsNeeded)
    {

    int counter = 0;

    //Vamos a generar un lista de varios elementos localizado usando CSS Selector "h4.product-name"
    List<WebElement> product =driver.findElements(By.cssSelector("h4.product-name"));

    for(int i=0; i<product.size(); i++)
        {
            //Aqui almacenamos cada product el testo en una variable para poder identificar la que realmente queremos
            //We need to format the name to properly get Array item name, right now it will get the test "Cucumber - 1kg" that is not matching
            String[] fName = product.get(i).getText().split("-");
            String formattedName = fName[0].trim();

            //Convert Array(this is original declared as array because is less request than Array List) into Array list(during run time for complex search)
            //Check any string from your ArrayList is present or not then we click it to add into the cart.
            List itemsNeededList = Arrays.asList(itemsNeeded);

            //He will search in the ArrayList just created for the value of our Array and add it to the cart.
            if(itemsNeededList.contains(formattedName))
                {
                    counter++;
                    //In order to save processing time we can create a count variable that will let us know how many time it clicks add cart
                    //If that matches with all our test scenario just then we can break because we know is really over.
                    //WARNING do not rely on text that change to find a locator it not a good practice this can impacted on the outcome of the script
                    driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                    //We need to remove break because we want to iterate for all the element of the array.
                    //break;

                    if(counter == itemsNeededList.size())
                        {
                            break;
                        }
                }
        }
    }
}
