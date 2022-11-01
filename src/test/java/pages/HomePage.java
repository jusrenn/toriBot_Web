package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class HomePage {

    @FindBy(id = "sp_message_iframe_710946")
    public WebElement iframe;

    @FindBy(xpath = "//button[@title=\"Hyväksy kaikki evästeet\"]")
    public WebElement acceptBtn;

    @FindBy(xpath = "//input[@id=\"frontSearch\"]")
    public WebElement searchBoxHomePage;

    @FindBy(xpath = "//input[@id=\"searchtext\"]")
    public WebElement searchBoxItemsPage;

    @FindBy(xpath = "//select[@id=\"searcharea_expanded\"]//option")
    public List<WebElement> areas;

    // Constructor
    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Accept Cookies
    public Boolean acceptCookies() {
        ReusableMethods.waitElement(iframe);
        if (iframe.isDisplayed()) {
            Driver.getDriver().switchTo().frame(iframe);
            acceptBtn.click();
            Driver.getDriver().switchTo().parentFrame();
            return true;
        } else {
            return false;
        }
    }

    // Select area
    public WebElement area(String areaName) {
        WebElement area = null;
        for (WebElement e : areas) {
            if (e.getText().equals(areaName)) {
                area = e;
                break;
            }
        }
        return area;
    }
}
