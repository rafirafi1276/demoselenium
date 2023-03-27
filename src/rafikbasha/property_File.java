package rafikbasha;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hwpf.usermodel.CharacterProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class property_File {
WebDriver driver ;	
Properties con;	
@BeforeTest
public void setup()throws Throwable
{
con= new Properties();
con.load(new FileInputStream("OR.properties"));
driver = new ChromeDriver();	
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
driver.get(con.getProperty("url"));

	}
@Test
public void verifylogin()
{
	driver.findElement(By.xpath(con.getProperty("objReset"))).click();
	driver.findElement(By.xpath(con.getProperty("objuser"))).sendKeys("admin");
	driver.findElement(By.xpath(con.getProperty("objpass"))).sendKeys("master");
	driver.findElement(By.xpath(con.getProperty("objloginbtn"))).click();
	String Expected_Title="Dashboard « Stock Accounting";
	String Actual_Title=driver.getTitle();		
	if(Expected_Title.equalsIgnoreCase(Actual_Title))
	{
		Reporter.log("login sucess"+Expected_Title+"     "+Actual_Title,true);
	}else
	{
		Reporter.log("login fail"+Expected_Title+"     "+Actual_Title,true);
	}
	
}
	
@AfterTest
public void teardown()
{
driver.close();	
}

}


