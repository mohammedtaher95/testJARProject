package tests;

import driverFactory.Webdriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static browserActions.BrowserActions.*;
import static elementActions.ElementActions.*;

public class TestClass {

    Webdriver driver;
    By registerLink = By.linkText("Register");

    @BeforeClass
    public void setUp() throws IOException {
        driver = new Webdriver();
    }

    @Test
    public void testMethod(){

        navigateToURL("http://demo.nopcommerce.com");
        clickButton(registerLink);
        Assert.assertTrue(getCurrentURL().contains("register"));

    }

    @AfterClass
    public void tearDown() throws IOException {
        driver.quit();
    }
}
