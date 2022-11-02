package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;

public class Tori {

    HomePage homePage = new HomePage();
    ResultPage resultPage = new ResultPage();

    @BeforeClass
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("UrlTori"));
    }

    // Accept cookies and search items
    @Test(priority = 1)
    public void searchOrder() {

        // Accept Cookies
        homePage.acceptCookies();

        // Waiting element
        ReusableMethods.waitElement(homePage.searchBoxHomePage);

        // Setting area
        homePage.area(ConfigReader.getProperty("Area")).click();

        // Searching items
        homePage.searchBoxHomePage.sendKeys(ConfigReader.getProperty("SearchText") + Keys.ENTER);

        // Result checking
        Assert.assertEquals(homePage.searchBoxItemsPage.getAttribute("value"), ConfigReader.getProperty("SearchText"));
    }

    @Test(priority = 2)
    public void searchingResult() {

        // Selecting personal items
        resultPage.personalItems.click();

        // Listing only free items
        resultPage.onlyFree();

        // Click to search button
        resultPage.searchBtn.click();
    }

    @Test(priority = 3)
    public void report() throws IOException {
        //System.out.println(resultPage.toJson());
        ReusableMethods.jsonToFile(resultPage.toJson());
    }

    // Close Driver and browser
    @AfterClass
    public void closeDriver() throws InterruptedException {
        Thread.sleep(1000);
        Driver.getDriver().close();
    }
}
