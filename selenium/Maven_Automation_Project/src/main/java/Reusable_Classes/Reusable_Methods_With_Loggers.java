package Reusable_Classes;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Reusable_Methods_With_Loggers {

     static int timeout = 9;


     //method to re use chrome driver and chrome options
    public static WebDriver setDriver() throws InterruptedException, IOException {
        //kill all chrome driver instance
        Thread.sleep(1800);
        // Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        Thread.sleep(1800);
        //set the chrome path
        System.setProperty("webdriver.chrome.driver","src//main//resources//chromedriver");
        //set some pre conditions using ChromeOptions
        ChromeOptions options = new ChromeOptions();
        //set the arguments you want for the driver
        options.addArguments("start-maximized","incognito");
        //now simply define your chrome driver
        WebDriver driver = new ChromeDriver(options);

        return driver;
    }

    //method to compare expected with actual title
    public static void verifyTitle(WebDriver driver,String expectedTitle,ExtentTest logger){
        String actualTitle = driver.getTitle();
        if(actualTitle.equals(expectedTitle)){
            System.out.println("Expected title matches with Actual " + expectedTitle);
            logger.log(LogStatus.PASS,"Expected title matches with Actual " + expectedTitle);
        } else {
            System.out.println("Expected doesn't match actual title. Actual title is " + actualTitle);
            logger.log(LogStatus.FAIL,"Expected doesn't match actual title. Actual title is " + actualTitle);
            getScreenShot(driver,logger,"Title Verification");
        }
    }//end of verify title method


    //method to select a drop down value by visible text
    public static void dropdownByText(WebDriver driver,String locator, String userInput, String elementName){
              WebDriverWait wait = new WebDriverWait(driver,timeout);
              try{
                  System.out.println("Selecting a value on element " + elementName);
                  WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
                  Select dDown = new Select(element);
                  dDown.selectByVisibleText(userInput);
              } catch (Exception e) {
                System.out.println("Unable to select element " + elementName + " " + e);
             }
    }//end of drop down by text method

    //method to enter user input on send keys
    public static void userKeys(WebDriver driver,WebElement locator, String userInput, String elementName,ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Entering a value on element " + elementName);
            logger.log(LogStatus.INFO,"Entering a value " + userInput + " on " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            element.clear();
            Thread.sleep(900);
            element.sendKeys(userInput);
        } catch (Exception e) {
            System.out.println("Unable to enter element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to enter value on " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
        }
    }//end of sendkeys method


    //entering value using javascriptExecutor
    public static void userValueByJs(WebDriver driver,WebElement locator,String userInput, String elementName,ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try{
            System.out.println("Entering a value on element " + elementName);
            logger.log(LogStatus.INFO,"Entering a value " + userInput + " on " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            element.clear();
            Thread.sleep(1000);
            js.executeScript("arguments[0].value='"+userInput+"';", element);
        } catch (Exception e) {
            System.out.println("Unable to enter element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to enter value on " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
        }
    }//end of entering value by JS

    //method to enter user credentials on send keys
    public static void userCredential(WebDriver driver,WebElement locator, String userInput, String elementName,ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try{
            System.out.println("Entering a value on element " + elementName);
            logger.log(LogStatus.INFO,"Entering credential on " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            element.clear();
            //Thread.sleep(900);
            js.executeScript("arguments[0].value='"+userInput+"';", element);
        } catch (Exception e) {
            System.out.println("Unable to enter element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to enter credential " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
        }
    }//end of sendkeys method

    //method to enter user input on send keys
    public static void keyEnter(WebDriver driver,String locator, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Hitting enter on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            element.clear();
            Thread.sleep(1800);
            element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Unable to hit enter on element " + elementName + " " + e);
        }
    }//end of keyEnter method

    //method to enter user input on send keys
    public static void userTypeAndHitEnter(WebDriver driver,WebElement locator, String userInput, String elementName,ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Entering a value on element " + elementName);
            logger.log(LogStatus.INFO,"Entering a value " + userInput + " on " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            element.clear();
            element.sendKeys(userInput);
            element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Unable to enter element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to enter value on " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
        }
    }//end of userTypeAndHitEnter method

    //method to click on an element
    public static void click(WebDriver driver, WebElement locator, String elementName, ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Clicking a value on element " + elementName);
            logger.log(LogStatus.INFO,"Clicking on " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            element.click();
        } catch (Exception e) {
            System.out.println("Unable to click element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to click on " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
        }
    }//end of click method

    public static void clickByJs(WebDriver driver, WebElement locator, String elementName, ExtentTest logger) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Thread.sleep(1500);
        try{
            System.out.println("Clicking a value on element " + elementName);
            logger.log(LogStatus.INFO,"Clicking on " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Unable to click element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to click on " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
        }
    }//end of click by js method

    public static void clickByAsynJs(WebDriver driver, WebElement locator, String elementName, ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try{
            System.out.println("Clicking a value on element " + elementName);
            logger.log(LogStatus.INFO,"Clicking a value on " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            js.executeAsyncScript("arguments[0].click();", element);
        } catch (Exception e) {
            if(e.getMessage().contains("script timeout")) {
            } else {
                System.out.println("Unable to click element " + elementName + " " + e);
                logger.log(LogStatus.FAIL, "Unable to click on " + elementName + " " + e);
                getScreenShot(driver, logger, elementName);
            }//end of if else
        }//end of try catch
    }//end of click by js method

    //method to click on an element
    public static void mouseClick(WebDriver driver,String locator,String elementName,ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        Actions actions = new Actions(driver);
        try{
            System.out.println("Mouse clicking a value on element " + elementName);
            logger.log(LogStatus.INFO,"Mouse clicking a value on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            actions.moveToElement(element).click().perform();
        } catch (Exception e) {
            System.out.println("Unable to mouse click element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to mouse click element " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
        }
    }//end of click method
    //method to click by index on an element
    public static void clickByIndex(WebDriver driver,String locator,int index, String elementName,ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Clicking a value by index " + index + " on element " + elementName);
            logger.log(LogStatus.INFO,"Clicking a value by index " + index + " on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.click();
        } catch (Exception e) {
            System.out.println("Unable to click by index " + index +  " on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to click by index " + index +  " on element " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
        }
    }//end of click method

    //method to submit on an element
    public static void submit(WebDriver driver,WebElement locator,String elementName, ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Submitting a value on element " + elementName);
            logger.log(LogStatus.INFO,"Submitting a value on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            element.submit();
        } catch (Exception e) {
            logger.log(LogStatus.FAIL,"Unable to submit element " + elementName + " " + e);
            getScreenShot(driver,logger,elementName);
            System.out.println("Unable to submit element " + elementName + " " + e);
        }
    }//end of click method

    //method to return text from an element
    public static String captureText(WebDriver driver,WebElement locator,String elementName,ExtentTest logger) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        String result = null;
        try{
            System.out.println("Capturing a text from element " + elementName);
            logger.log(LogStatus.INFO,"Capturing a text from element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            result = element.getText();
            System.out.println("My Text result is " + result);
        } catch (Exception e) {
            System.out.println("Unable to capture text on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to capture text on element " + elementName + " " + e);
            Thread.sleep(1000);
            getScreenShot(driver,logger,elementName);
        }

        return result;
    }//end of captureText method

    //method to Mouse Hover on a element
    public static void Hover(WebDriver driver,String locator,String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        Actions action = new Actions(driver);
        try {
            System.out.println("Hovering over an element" + elementName);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            action.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("unable to Hover the mouse" + elementName + " " + e);
        }//end of try and catch
    }//end of Mouse Hover method

    //method to perform MouseClick
    public static void MouseClick(WebDriver driver,String locator, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        Actions action = new Actions(driver);
        try {
            System.out.println("hover and click " +elementName);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            action.click(element).perform();
        }catch (Exception e) {
            System.out.println("unable to hover and click " + elementName + " " + e);
        }//end of try catch

    }//end of MouseClick method




    //method to capture screenshot when logger fails
    public static void getScreenShot(WebDriver wDriver,ExtentTest logger,String imageName) {
        try {
            String fileName = imageName + ".png";
            String directory = "src//main//java//HTML_Report//Screenshots//";
            File sourceFile = ((TakesScreenshot) wDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(directory + fileName));
            //String imgPath = directory + fileName;
            String image = logger.addScreenCapture("Screenshots//" + fileName);
            logger.log(LogStatus.FAIL, "", image);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Error Occured while taking SCREENSHOT!!!");
            e.printStackTrace();
        }
    }//end of screenshot method


    public static String getDateInFormat(int days){
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        Calendar c = Calendar.getInstance();
        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, days);
        //Date after adding the days to the given date
        String newDate = sdf.format(c.getTime());
        return newDate;
    }//end of get date

    public String generateID(int num) {
        Random rnd = new Random();
        char [] digits = new char[num];
        digits[0] = (char) (rnd.nextInt(9) + '1');
        for(int i=1; i<digits.length; i++) {
            digits[i] = (char) (rnd.nextInt(10) + '0');
        }
        Long val = Long.parseLong(new String(digits));
        return val.toString();
    }//end of get up to 19 digits id

}//end of java class
