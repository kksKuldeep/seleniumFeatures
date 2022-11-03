import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.emulation.Emulation;
import org.openqa.selenium.devtools.v106.emulation.model.DisplayFeature;
import org.openqa.selenium.devtools.v106.emulation.model.ScreenOrientation;
import org.openqa.selenium.devtools.v106.page.model.Viewport;

import io.github.bonigarcia.wdm.WebDriverManager;

public class mobileEmulatorTest {
	
	public static void main(String[] args) throws InterruptedException
	{
		
		//WebDriverManager.chromedriver();
		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//initialize chrome dev Tools
		DevTools devTools=driver.getDevTools();
		//create devTools session
		devTools.createSession();
		//send commands to CDP Methods> CDP method invoke and get access to chrome dev tools
		
		devTools.send(Emulation.setDeviceMetricsOverride
				(600, 1000, 50, false, Optional.<Number> empty(),
			            Optional.<Integer> empty(), Optional.<Integer> empty(), Optional.<Integer> empty(), 
			            Optional.<Integer> empty(), Optional.<Boolean> empty(), Optional.<ScreenOrientation> empty(), 
			            Optional.<Viewport> empty(), Optional.<DisplayFeature> empty()));
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		
		driver.findElement(By.xpath("//button[@class='navbar-toggler']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@routerlink='/library']")).click();
		
		
		
		driver.close();
			
	}

}
