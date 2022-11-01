package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class ReusableMethods {

    private static WebDriverWait wait;

    public static void takeScreenShot(String fileName) {
        try {
            File file = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(ConfigReader.getProperty("screenshotPath") + fileName + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void waitElement(WebElement e) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public static void createFile() throws IOException {
        File file = new File("src/jsonResult/result.json");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void writeToFile(String value) throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter("src/jsonResult/result.json", true));
        file.newLine();
        file.write(value);
        file.close();
    }

    // Write to file
    public static void createReport(String json) {
        try {
            ReusableMethods.createFile();
            ReusableMethods.writeToFile(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
