package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project-3- ProjectName : com-luma-sw-3
 * BaseUrl = https://magento.softwaretestingboard.com/
 * 1. Create the package ‘browserfactory’ and create the
 * class with the name ‘BaseTest’ inside the
 * ‘browserfactory’ package. And write the browser setup
 * code inside the class ‘Base Test’.
 * 2. Create the package utilities and create the class
 * with the name ‘Utility’ inside the ‘utilities’
 * package. And write the all the utility methods in
 * it’.
 * 3. Create the package ‘testsuite’ and create the
 * following classes inside the ‘testsuite’ package.
 * 1. WomenTest
 * 2. MenTest
 * 3. GearTest
 * 4. Write down the following test into WomenTestclass
 * 1. verifyTheSortByProductNameFilter
 * * Mouse Hover on Women Menu
 * * Mouse Hover on Tops
 * * Click on Jackets
 * * Select Sort By filter “Product Name”
 * * Verify the products name display in
 * alphabetical order
 * 2. verifyTheSortByPriceFilter
 * * Mouse Hover on Women Menu
 * * Mouse Hover on Tops
 * * Click on Jackets
 * * Select Sort By filter “Price”
 * * Verify the products price display in
 * Low to High
 * 5. Write down the following test into ‘MenTest’
 * class
 * 1. userShouldAddProductSuccessFullyTo
 * ShoppinCart()
 * * Mouse Hover on Men Menu
 * * Mouse Hover on Bottoms
 * * Click on Pants
 * * Mouse Hover on product name
 * ‘Cronus Yoga Pant’ and click on size
 * 32.
 * * Mouse Hover on product name
 * ‘Cronus Yoga Pant’ and click on colour
 * Black.
 * * Mouse Hover on product name
 * ‘Cronus Yoga Pant’ and click on
 * ‘Add To Cart’ Button.
 * * Verify the text
 * ‘You added Cronus Yoga Pant to your shopping cart.’
 * * Click on ‘shopping cart’ Link into
 * message
 * * Verify the text ‘ShoppingCart.’
 * * Verify the product name ‘CronusYogaPant’
 * * Verify the product size ‘32’
 * * Verify the product colour ‘Black’
 * 6.Write down the following test into ‘GearTest’ class
 * 1. userShouldAddProductSuccessFullyTo
 * ShoppinCart()
 * * Mouse Hover on Gear Menu
 * * Click on Bags
 * * Click on Product Name ‘Overnight Duffle’
 * * Verify the text ‘Overnight Duffle’
 * * Change Qty 3
 * * Click on ‘Add to Cart’ Button.
 * * Verify the text
 * ‘You added Overnight Duffle to your shopping cart.’
 * * Click on ‘shopping cart’ Link into
 * message
 * * Verify the product name ‘CronusYogaPant’
 * * Verify the Qty is ‘3’
 * * Verify the product price ‘$135.00’
 * * Change Qty to ‘5’
 * * Click on ‘Update Shopping Cart’ button
 * * Verify the product price ‘$225.00’
 */

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    // 1. verifyTheSortByProductNameFilter
    @Test
    public void verifyTheSortByProductNameFilter() {
        // * Mouse Hover on Women Menu
        // * Mouse Hover on Tops
        // * Click on Jackets
        Actions act = new Actions(driver);
        act.moveToElement(findSingleElement(By.xpath("//nav[@class='navigation']//span[contains(text(),'Women')]/parent::a")))
                .moveToElement(findSingleElement(By.xpath("//li[@class='level1 nav-2-1 category-item first parent ui-menu-item']//span[contains(text(),'Tops')]/parent::a")))
                .moveToElement(findSingleElement(By.xpath("//li[@class='level2 nav-2-1-1 category-item first ui-menu-item']//span[contains(text(),'Jackets')]/parent::a"))).click().build().perform();
        // * Select Sort By filter “Product Name”
        selectByVisibleTextFromDropDown(By.id("sorter"), getTextFromElement(By.xpath("(//option[@value='name'][normalize-space()='Product Name'])[1]")));
        // * Verify the products name display in alphabetical order
        List<WebElement> listOfProducts = findMultipleElements(By.cssSelector(".product-item-info a.product-item-link"));
        List<String> productNames = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (WebElement ele : listOfProducts) {
            if (!ele.isDisplayed()) {
                js.executeScript("arguments[0].scrollIntoView();", ele);
            }
            productNames.add(ele.getText());
        }
        //Creating replica for comparison
        List<String> sortedNames = productNames;
        Collections.sort(sortedNames);
        System.out.println(productNames);
        System.out.println(sortedNames);
        Assert.assertEquals(sortedNames, productNames);
    }

    // 2. verifyTheSortByPriceFilter
    @Test
    public void verifyTheSortByPriceFilter() {
        // * Mouse Hover on Women Menu
        // * Mouse Hover on Tops
        // * Click on Jackets
        Actions act = new Actions(driver);
        act.moveToElement(findSingleElement(By.xpath("//nav[@class='navigation']//span[contains(text(),'Women')]/parent::a")))
                .moveToElement(findSingleElement(By.xpath("//li[@class='level1 nav-2-1 category-item first parent ui-menu-item']//span[contains(text(),'Tops')]/parent::a")))
                .moveToElement(findSingleElement(By.xpath("//li[@class='level2 nav-2-1-1 category-item first ui-menu-item']//span[contains(text(),'Jackets')]/parent::a"))).click().build().perform();
        // * Select Sort By filter “Price”
        selectByVisibleTextFromDropDown(By.id("sorter"), "Price");
        // * Verify the products price display in Low to High
        List<WebElement> listOfProductPrice = findMultipleElements(By.xpath("//span[@class='normal-price']//span[@class='price']"));
        List<Double> priceList = new ArrayList<>();
        for (WebElement ele : listOfProductPrice) {
            priceList.add(Double.parseDouble(ele.getText().trim().substring(1)));
        }
        //Creating replica for comparison
        List<Double> sortedNames = priceList;
        Collections.sort(sortedNames);
        System.out.println(priceList);
        System.out.println(sortedNames);
        Assert.assertEquals(sortedNames, priceList);
    }


    @After
    public void tearDown() {
        closeBrowser();
    }
}
