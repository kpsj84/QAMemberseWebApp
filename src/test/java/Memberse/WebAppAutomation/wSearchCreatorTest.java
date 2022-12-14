package Memberse.WebAppAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import WebPageObjects.ExplorePage;
import WebPageObjects.HomePage;
import WebPageObjects.LoginPage;
import WebPageObjects.WelcomePage;

public class wSearchCreatorTest extends WBase {
	
	@Test
	public void wSearchCreatorTestCase()throws InterruptedException{
		WUtilities u = new  WUtilities(driver);
		u.timeDelayToLoadWebsite();
		
		WelcomePage wp = new WelcomePage(driver);
		wp.LoginButton().click();
		Thread.sleep(2000);
		
		LoginPage lp = new LoginPage(driver);
		lp.Email().sendKeys("kqatestc7@yopmail.com");
		lp.Password().sendKeys("kqatestc7");
		lp.Login().click();
		Thread.sleep(7000);
		 
		HomePage hp = new HomePage(driver);
		hp.ExploreMenu().click();
		Thread.sleep(2000);
		
		ExplorePage ep = new ExplorePage(driver);
		ep.SearchField().sendKeys("kqatest");
		Thread.sleep(13000); //earlier 10sec                     
	    String Text= driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div/div[3]/main/div/div/div[2]/div/div/div/div[1]/a/div/div[2]/div/div/p[1]")).getText();
	    System.out.println(Text);
	    Assert.assertEquals(Text,"kqatestc4's Community");    
	   
	    String Text1=driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div/div[3]/main/div/div/div[2]/div/div/div/div[1]/a/div/div[2]/div/div/p[2]")).getText();
	    System.out.println(Text1);
	    Assert.assertEquals(Text1,"@kqatestc4");
	     
	    int verifyResult = driver.findElements(By.xpath("//*[@class='space-y-2']/div")).size();
        System.out.println(verifyResult);
     
        List<WebElement> verifyResult2 = driver.findElements(By.xpath("//*[@class='space-y-2']/div"));
        int testresult2 = verifyResult2.size();
        System.out.println(verifyResult2.size());
     
        if(testresult2 == 0)
        {
        	String noResult = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div/div[3]/main/div/div/div[2]/div/div[2]/p")).getText();
        	System.out.println(noResult);
        	Assert.assertEquals(noResult, "No results available.");
        }
        else
        {
        	System.out.println("Got Results from the Search field");
        }
	}

}
