package Memberse.WebAppAutomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import WebPageObjects.HomePage;
import WebPageObjects.LoginPage;
import WebPageObjects.PlayListPage;
import WebPageObjects.WelcomePage;

public class wCreateAndDeletePlaylistTest extends WBase {
	
	@Test
	public void wCreateAndDeletePlaylistTestCase()throws InterruptedException, AWTException {
		WUtilities u = new  WUtilities(driver);
		u.timeDelayToLoadWebsite();
		
		WelcomePage wp = new WelcomePage(driver);
		wp.LoginButton().click();
		Thread.sleep(1000);
		
		LoginPage lp = new LoginPage(driver);
		lp.Email().sendKeys("kqatestc3@yopmail.com");
		lp.Password().sendKeys("kqatestc3");
		Thread.sleep(1000);
	    lp.ShowPassword().click();
		lp.Login().click();
	    Thread.sleep(10000);  
	    
	    HomePage hp = new HomePage(driver);
	    hp.CreatorMenu().click();
		Thread.sleep(2000);
    	
		PlayListPage plp = new PlayListPage(driver);
        plp.PlayListSubMenu().click();
        Thread.sleep(5000);
        
        plp.createPlayList().click();
        Thread.sleep(3000);
        String autotext = getSaltString();
		System.out.println(autotext);
        plp.PlayListTitleTextbox().sendKeys("Autolist-" + autotext);
        plp.DescriptionTextbox().sendKeys("This is an Automated QA Description");
        plp.UploadImageButton().click();
        Thread.sleep(5000);
        
        File fl = new File(System.getProperty("user.dir") + "/src/samples/Pic1.jpg");
        StringSelection str = new StringSelection(fl.getAbsolutePath());
        //StringSelection str = new StringSelection("C:\\Users\\QA\\Desktop\\MusicFile.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Thread.sleep(5000);
        
        Robot rb = new Robot();
        //This required as in Automation, Focus loose from open file upload window
        rb.keyPress(KeyEvent.VK_META);
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_META);
        rb.keyRelease(KeyEvent.VK_TAB);
        rb.delay(500);
        
        //Open Goto window
        rb.keyPress(KeyEvent.VK_META);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_G);
        rb.keyRelease(KeyEvent.VK_META);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyRelease(KeyEvent.VK_G);
        rb.delay(500);
        
        //Paste the clipboard value
        rb.keyPress(KeyEvent.VK_META);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_META);
        rb.keyRelease(KeyEvent.VK_V);
        
        //Close both the Windows
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        rb.delay(500);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(6000);
        plp.PlayListSaveButton().click();
        Thread.sleep(6000);
        
        WebDriverWait w = new WebDriverWait(driver,60);
        w.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#__next > div.rnc__base > div.rnc__notification-container--bottom-right > div > div > div > div.rnc__notification-message")));
        String text= driver.findElement(By.cssSelector("#__next > div.rnc__base > div.rnc__notification-container--bottom-right > div > div > div > div.rnc__notification-message")).getText();
		System.out.println(text);
		Assert.assertEquals(text,"Operation succesfully completed.");
		System.out.println("PlayList Created with Name as :- " + "Autolist-" + autotext);
		
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		plp.playListDeleteButton().click();
		Thread.sleep(5000);
		plp.deleteConfirmButton().click();
		Thread.sleep(5000);
		System.out.println("PlayList with Name as :- " + "Autolist-" + autotext + "deleted too.");
       }

    	public String getSaltString() {
		String SALTCHARS="369852147";
		StringBuilder salt= new StringBuilder();
		   Random rnd =new Random();
		   while(salt.length() < 4) {//length of the random string.
			   int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			   salt.append(SALTCHARS.charAt(index));
		   }
		   String saltStr= salt.toString();
		    return saltStr;    
	}

}
