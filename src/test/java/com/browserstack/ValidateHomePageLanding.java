package com.browserstack;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateHomePageLanding  extends SeleniumTest {

    @Test
    public void homeValidation() throws Exception {
        // navigate to bstackdemo
        driver.get("https://www.testathon.live");

        // Check the title
        Assert.assertTrue(driver.getTitle().matches("StackDemo"));

        // Save the text of the product for later verify
        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();
        // Click on add to cart button
        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();


        //*[@id="offers"]
        //*[@id="orders"]
        //*[@id="Favourites"]
        //*[@id="signin"]

        // See if the cart is opened or not
        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        // Check the product inside the cart is same as of the main page
        String productOnCartText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")).getText();
        Assert.assertEquals(productOnScreenText, productOnCartText);
    }
}
