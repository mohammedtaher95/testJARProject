package tests.nopcommerce;

import driverfactory.webdriver.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.nopcommerce.SearchPage;

public class SearchProductWithAutoSuggestTest{

    public static ThreadLocal<WebDriver> driver;
    String ProductName = "Apple MacBook Pro 13-inch";

    @BeforeClass(description = "Setup Driver")
    public void setUp(){
        driver = new ThreadLocal<>();
        driver.set(new WebDriver());
    }


    @Test
    public void UserCanSearchForProductWithAutoSuggest()
    {
        try {
            new SearchPage(driver.get())
                    .productSearchUsingAutoSuggest("Mac")
                    .checkThatProductPageShouldBeDisplayed(ProductName);
        }
        catch (Exception e)
        {
            System.out.println("Error Occurred " + e.getMessage());
        }

    }

    @AfterClass(description = "Tear down")
    public void tearDown(){
        driver.get().browser().deleteAllCookies();
        driver.get().quit();
        driver.remove();
    }
}
