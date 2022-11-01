package pages;

import com.google.gson.Gson;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultPage {

    private static final int maxItem = 30;

    // Personal items element
    @FindBy(xpath = "//li[@class=\"type\"][1]//a")
    public WebElement personalItems;

    // Items types
    @FindBy(xpath = "//div[@id=\"type_boxes\"]//label//input")
    public List<WebElement> itemsTypes;

    // Search Button
    @FindBy(xpath = "//button[@id=\"searchbutton\"]")
    public WebElement searchBtn;

    // Item name
    @FindBy(xpath = "//div[@class=\"li-title\"]")
    public List<WebElement> itemName;

    // Items links
    @FindBy(xpath = "//div[@class=\"list_mode_thumb\"]//a")
    public List<WebElement> itemLink;

    // Items image links
    @FindBy(xpath = "//img[@class=\"item_image\"]")
    public List<WebElement> itemImageLink;

    // Item times
    @FindBy(xpath = "//div[@class=\"date_image\"]")
    public List<WebElement> itemTime;

    // Item type
    @FindBy(xpath = "//div[@class=\"cat_geo clean_links\"]//p[2]")
    public List<WebElement> itemType;

    public ResultPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // List only free items
    public void onlyFree() {
        for (int i = 0; i < itemsTypes.size(); i++) {
            if (i != itemsTypes.size() - 1) {
                itemsTypes.get(i).click();
            }
        }
    }

    // Items to json
    public String toJson() {
        ArrayList<HashMap> items = new ArrayList<>();

        for (int i = 0; i < maxItem; i++) {

            HashMap<String, String> item = new HashMap<>();

            item.put("type", itemType.get(i).getText());
            item.put("name", itemName.get(i).getText());
            item.put("time", itemTime.get(i).getText());
            item.put("imgLink", itemImageLink.get(i).getAttribute("src"));
            item.put("link", itemLink.get(i).getAttribute("href"));

            items.add(item);
        }

        return new Gson().toJson(items);
    }
}
