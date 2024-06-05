package testsuite;
/**
 * 5. Write down the following test into ‘MenTest’
 *  class
 *  1. userShouldAddProductSuccessFullyTo
 *  ShoppinCart()
 *  * Mouse Hover on Men Menu
 *  * Mouse Hover on Bottoms
 *  * Click on Pants
 *  * Mouse Hover on product name
 *  ‘Cronus Yoga Pant’ and click on size
 *  32.
 *  * Mouse Hover on product name
 *  ‘Cronus Yoga Pant’ and click on colour
 *  Black.
 *  * Mouse Hover on product name
 *  ‘Cronus Yoga Pant’ and click on
 *  ‘Add To Cart’ Button.
 *  * Verify the text
 *  ‘You added Cronus Yoga Pant to your shopping cart.’
 *  * Click on ‘shopping cart’ Link into
 *  message
 *  * Verify the text ‘ShoppingCart.’
 *  * Verify the product name ‘CronusYogaPant’
 *  * Verify the product size ‘32’
 *  * Verify the product colour ‘Black’
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class MenTest extends Utility {

    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppinCart() {
        // Mouse Hover on Men Menu
        mouseHoverToElement(By.xpath("//span[normalize-space()='Men']"));
        //Mouse Hover on Bottoms
        mouseHoverToElement(By.cssSelector("a[id='ui-id-18'] span:nth-child(1)"));
        //Click on Pants
        mouseHoverToElementAndClick(By.cssSelector("a[id='ui-id-23'] span"));
        //Mouse Hover on product name
        mouseHoverToElement(By.xpath("//a[normalize-space()='Cronus Yoga Pant']"));
        //‘Cronus Yoga Pant’ and click on size 32.
        mouseHoverToElementAndClick(By.cssSelector("body > div:nth-child(5) > main:nth-child(4) > div:nth-child(4) > div:nth-child(1) > div:nth-child(4) > ol:nth-child(1) > li:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
        //Mouse Hover on product name
        mouseHoverToElement(By.xpath("//a[normalize-space()='Cronus Yoga Pant']"));
        //‘Cronus Yoga Pant’ and click on colour Black.
        mouseHoverToElementAndClick(By.xpath("(//div[@id='option-label-color-93-item-49'])[1]"));
//Mouse Hover on product name
        mouseHoverToElement(By.xpath("//a[normalize-space()='Cronus Yoga Pant']"));
//‘Cronus Yoga Pant’ and click on
//     * ‘Add To Cart’ Button.
        mouseHoverToElementAndClick(By.xpath("//body[1]/div[2]/main[1]/div[3]/div[1]/div[3]/ol[1]/li[1]/div[1]/div[1]/div[3]/div[1]/div[1]/form[1]/button[1]/span[1]"));
//Verify the text
//     * ‘You added Cronus Yoga Pant to your shopping cart.’
        String expected = "You added Cronus Yoga Pant to your shopping cart.";
        String actual = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
        Assert.assertEquals("Your item not added into shopping cart.", expected, actual);
//Click on ‘shopping cart’ Link into message
        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='shopping cart']"));
        //Verify the text ‘Shopping Cart.’
        String expectedText = "Shopping Cart";
        String actualText = driver.findElement(By.xpath("//span[@class='base']")).getText();
        Assert.assertEquals("shopping cart is empty", expectedText, actualText);
//Verify the product name ‘Cronus Yoga Pant’
        String expectedProduct = "Cronus Yoga Pant";
        String actualProduct = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/strong[1]/a[1]")).getText();
        Assert.assertEquals("wrong product in shopping cart", expectedProduct, actualProduct);
// Verify the product size ‘32’
        String expectedSize = "32";
        String actualsize = driver.findElement(By.xpath("//dd[contains(text(),'32')]")).getText();
        Assert.assertEquals("wrong product size in shopping cart", expectedSize, actualsize);
//Verify the product colour ‘Black’
        String expectedColor = "Black";
        String actualColor = driver.findElement(By.xpath("//dd[contains(text(),'Black')]")).getText();
        Assert.assertEquals("wrong color in shopping cart", expectedColor, actualColor);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
