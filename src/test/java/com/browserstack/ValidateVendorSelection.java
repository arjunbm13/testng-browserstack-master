package com.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidateVendorSelection extends SeleniumTest {

    @Test
    public void VendorSelection() throws Exception {
        // navigate to bstackdemo
        driver.get("https://www.testathon.live");

        // Check the title
        Assert.assertTrue(driver.getTitle().matches("StackDemo"));
        //Then i verify All top menus
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"offers\"]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(" //*[@id=\"orders\"]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"favourites\"]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=\"Sign In\"]")).isDisplayed());

        //Then i verify All vendors are listed
        // Extract vendor names using XPath
        List<WebElement> spanElements = driver.findElements(By.xpath("//h4/..//span"));
        Set<String> foundVendors = new HashSet<>();
        for (WebElement span : spanElements) {
            String text = span.getText().trim();
            if (!text.isEmpty()) {
                foundVendors.add(text);
            }
            span.click();
            System.out.println(span.getText().trim() + " is tapped");
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()=\" Product(s) found.\"]")).isDisplayed());

        }

        // Expected vendors
        List<String> expectedVendors = Arrays.asList("Apple", "Samsung", "Google", "OnePlus");

        // Compare and report
        for (String vendor : expectedVendors) {
            if (foundVendors.contains(vendor)) {
                System.out.println(vendor + " is listed ✅");
            } else {
                System.out.println(vendor + " is missing ❌");
            }
        }

    }
}
