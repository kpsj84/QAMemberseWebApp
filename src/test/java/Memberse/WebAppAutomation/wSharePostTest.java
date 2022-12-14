package Memberse.WebAppAutomation;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import WebPageObjects.HomePage;
import WebPageObjects.LoginPage;
import WebPageObjects.WelcomePage;

public class wSharePostTest extends WBase {
	
	@Test
	public void wSharePostTestCase()throws InterruptedException{
		WUtilities u = new  WUtilities(driver);
		u.timeDelayToLoadWebsite();
		
		WelcomePage wp = new WelcomePage(driver);
		wp.LoginButton().click();
		Thread.sleep(2000);
		LoginPage lp = new LoginPage(driver);
		lp.Email().sendKeys("kqatestc5@yopmail.com");
		lp.Password().sendKeys("kqatestc5");
		lp.ShowPassword().click();
		lp.Login().click();
		Thread.sleep(10000);
	    
	    HomePage hp= new HomePage(driver);
	    hp.HomeMenu().click();
	    Thread.sleep(6000);
        hp.ShareButton().click(); //failure
	    Thread.sleep(2000);
	    String Text = driver.findElement(By.xpath("//*[text()='Link copied to clipboard.']")).getText();
		System.out.println(Text);
	    Thread.sleep(1000);
	    Assert.assertEquals(Text, "Link copied to clipboard.");
	    Thread.sleep(2000);
	    
	    hp.createPost().click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id=\"news-item-form\"]/div/div[2]/div[1]/div/textarea")).sendKeys(Keys.chord(Keys.META, "v"));
        Thread.sleep(2000);
		WebElement verifysharelink = driver.findElement(By.cssSelector("#news-item-form > div > div.space-y-4 > div:nth-child(1) > div > textarea"));
		String chk = verifysharelink.getAttribute("value");
		System.out.println(chk);
		Thread.sleep(1000); 
	}

}
