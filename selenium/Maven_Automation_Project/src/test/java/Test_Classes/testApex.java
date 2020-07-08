package Test_Classes;

import Page_Object_Classes.Base_Class;
import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

public class testApex extends Abstract_Class {

    @Test
    public void testAPex() throws InterruptedException {

        driver.navigate().to("https://betr--dev1.my.salesforce.com/");

        Thread.sleep(2000);

        Base_Class.login_page().login();

        Thread.sleep(2000);

        driver.navigate().to("https://betr--dev1.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");

        Thread.sleep(2000);

        Reusable_Methods.click(driver,"//*[@id='debugMenuEntry-btnInnerEl']","Debug Link");
        Reusable_Methods.click(driver,"//div[text()='Open Execute Anonymous Window']","Execute");

        Thread.sleep(3000);
        Actions actions = new Actions(driver);
        By linesBy = By.xpath("//*[@class='CodeMirror-code']//pre/span");
        List<WebElement> lines = driver.findElements(linesBy);
        while (lines.size() > 0) {//deleting old Apex code
            lines.get(0).click();
            actions.sendKeys(lines.get(0), Keys.chord(Keys.CONTROL, "A")).click().perform();
            actions.sendKeys(Keys.BACK_SPACE).perform();
            lines = driver.findElements((linesBy));
        }
        actions.sendKeys("Database.executeBatch(new ACHOutBatch('BetrLink Master Bank'));\n").perform();
        //actions.sendKeys("System.debug('World');\n").perform();
        WebElement buttonExecute = driver.findElement(By.xpath("//button//span[text()='Execute']"));
        buttonExecute.click();
    }




}//end of test
