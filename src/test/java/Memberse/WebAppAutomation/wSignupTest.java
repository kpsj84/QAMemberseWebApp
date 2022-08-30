package Memberse.WebAppAutomation;

import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import WebPageObjects.SignupPage;

public class wSignupTest extends WBase {
	
	@Test
	public void wSignupTestCase() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Signup']")).click(); 
		//ExplorePage ep = new ExplorePage(driver);
		//ep.SignupButton();
		
		String email = getSaltString();
		System.out.println(email);
		String password = "123456";
		
		SignupPage sp = new SignupPage(driver);
		sp.Email().sendKeys(email);
		sp.Password().sendKeys(password);
		sp.confirmPassword().sendKeys(password);
		sp.SignupButton().click();
		
		Thread.sleep(5000);
		
		String abc = driver.findElement(By.xpath("//p[text()='Your account has been successfully created.']")).getText(); 
		System.out.println(abc);
		Assert.assertEquals(abc, "Your account has been successfully created.");
		System.out.println("Test Case Completed");
	}
	
	public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString() + "@yopmail.com";
        return saltStr;
    }

}
