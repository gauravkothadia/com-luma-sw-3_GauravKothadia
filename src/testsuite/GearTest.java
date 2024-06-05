package testsuite;
/**
 * 6.Write down the following test into ‘GearTest’ class
 *  1. userShouldAddProductSuccessFullyTo
 *  ShoppinCart()
 *  * Mouse Hover on Gear Menu
 *  * Click on Bags
 *  * Click on Product Name ‘Overnight Duffle’
 *  * Verify the text ‘Overnight Duffle’
 *  * Change Qty 3
 *  * Click on ‘Add to Cart’ Button.
 *  * Verify the text
 * ‘You added Overnight Duffle to your shopping cart.’
 *  * Click on ‘shopping cart’ Link into
 *  message
 *  * Verify the product name ‘CronusYogaPant’
 *  * Verify the Qty is ‘3’
 *  * Verify the product price ‘$135.00’
 *  * Change Qty to ‘5’
 *  * Click on ‘Update Shopping Cart’ button
 *  * Verify the product price ‘$225.00
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class GearTest extends Utility {

    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppinCart() {
        //Mouse Hover on Gear Menu
        mouseHoverToElement(By.id("ui-id-6"));
        //Click on Bags
        clickOnElement(By.xpath("//span[normalize-space()='Bags']"));
        //Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));
        //Verify the text ‘Overnight Duffle’
        Assert.assertEquals("Overnight Duffle", getTextFromElement(By.xpath("//span[@class='base']")));
        //Change Qty 3
        driver.findElement(By.id("qty")).clear();
        sendTextToElement(By.id("qty"), "3");
        //Click on ‘Add to Cart’ Button.
        clickOnElement(By.id("product-addtocart-button"));
        //Verify the text 'You added Overnight Duffle to your shopping cart.’
        Assert.assertEquals("You added Overnight Duffle to your shopping cart.", getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
        //Click on ‘shopping cart’ Link into message
        clickOnElement(By.linkText("shopping cart"));
        //Verify the product name ‘Overnight Duffle’
        Assert.assertEquals("Overnight Duffle", getTextFromElement(By.partialLinkText("Overnight Duf")));
        //Verify the Qty is ‘3’
        Assert.assertEquals("3", driver.findElement(By.xpath("//input[@class='input-text qty']")).getAttribute("value"));
        //Verify the product price ‘$135.00’
        Assert.assertEquals("$135.00", getTextFromElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$135.00']")));
        //Change Qty to ‘5’
        driver.findElement(By.xpath("//input[@class='input-text qty']")).clear();
        sendTextToElement(By.xpath("//input[@class='input-text qty']"), "5");
        //Click on ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//span[normalize-space()='Update Shopping Cart']"));
        //Verify the product price ‘$225.00
        Assert.assertEquals("$225.00", getTextFromElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$225.00']")));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
