package tests.nopcommerce;

import driverfactory.webdriver.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.nopcommerce.SearchPage;
import pages.nopcommerce.homepage.HomePage;

public class ChangeCurrencyTest{

    public static ThreadLocal<WebDriver> driver;

    @BeforeClass(description = "Setup Driver")
    public void setUp(){
        driver = new ThreadLocal<>();
        driver.set(new WebDriver());
    }

    @Test(priority = 1, alwaysRun = true)
    public void UserCanChangeCurrency()
    {
        new HomePage(driver.get()).changeCurrency(1);
    }

    @Test(priority = 2, alwaysRun = true)
    public void UserCanSearchForProductWithAutoSuggest()
    {
        try {
            new SearchPage(driver.get())
                    .productSearch("Mac")
                    .openProductPage()
                    .checkCurrency("€");
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
