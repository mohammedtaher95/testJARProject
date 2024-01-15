package tests.nopcommerce;

import driverfactory.webdriver.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.nopcommerce.LoginPage;
import pages.nopcommerce.ProductDetailsPage;
import pages.nopcommerce.SearchPage;
import pages.nopcommerce.homepage.HomePage;
import tools.properties.Properties;
import utilities.UserFormData;


public class AddProductReviewTest{

    public static ThreadLocal<WebDriver> driver;
    String ProductName = "Apple MacBook Pro 13-inch";
    String SuccessMessage = "Product review is successfully added.";

    UserFormData newUser = new UserFormData();


    @BeforeClass(description = "Setup Driver")
    public synchronized void setUp(){
        Properties.web.set().baseURL("http://demo.nopcommerce.com");
        driver = new ThreadLocal<>();
        driver.set(new WebDriver());
    }

    @Test(priority = 1)
    public void UserCanRegisterSuccessfully()  {
        new HomePage(driver.get())
                .openRegistrationPage()
                .validateThatUserNavigatedToRegistrationPage()
                .fillUserRegistrationForm(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getOldPassword())
                .clickOnRegisterButton()
                .validateThatSuccessMessageShouldBeDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = {"UserCanRegisterSuccessfully"})
    public void RegisteredUserCanLogin()
    {
        new HomePage(driver.get())
                .openLoginPage()
                .userLogin(newUser.getEmail(), newUser.getOldPassword())
                .clickOnLoginButton()
                .checkThatLogoutButtonShouldBeDisplayed();
    }

    @Test(priority = 3, dependsOnMethods = {"UserCanRegisterSuccessfully", "RegisteredUserCanLogin"})
    public void UserCanSearchForProducts(){
        new SearchPage(driver.get())
                .productSearch(ProductName)
                .openProductPage()
                .checkThatProductPageShouldBeDisplayed(ProductName);
    }

    @Test(priority = 4, alwaysRun = true, dependsOnMethods = {"UserCanSearchForProducts"})
    public void RegisteredUserCanAddReviewForProduct() {
        new ProductDetailsPage(driver.get())
                .addReview()
                .fillReviewForm(newUser.getMessage(), newUser.getMessage())
                .clickOnSubmitButton()
                .verifyThatReviewShouldBeSubmittedSuccessfully(SuccessMessage, newUser.getMessage());
    }

    @Test(priority = 5, alwaysRun = true, dependsOnMethods = {"RegisteredUserCanAddReviewForProduct"})
    public void RegisteredUserCanLogout()
    {
        new LoginPage(driver.get()).clickOnLogoutButton();
    }

    @AfterClass(description = "Tear down")
    public void tearDown(){
        driver.get().browser().deleteAllCookies();
        driver.get().quit();
        driver.remove();
    }
}
