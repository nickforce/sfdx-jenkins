package Test_Classes.Cucumber_Files.StepDefinitions;

import Page_Object_Classes.Base_Class;
import Reusable_Classes.Abstract_Class;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class test extends Abstract_Class {

    @Test
    public void testing() throws InterruptedException, IOException {

        driver.navigate().to("https://www.google.com");
        Thread.sleep(1000);
        driver.close();
    }





}
