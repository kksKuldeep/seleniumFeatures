import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;

public class cdpCommandsTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		Map deviceMatrix = new HashMap();
		deviceMatrix.put("widht", 600);
		deviceMatrix.put("height", 1000);
		deviceMatrix.put("deviceScaleFactor", 50);
		deviceMatrix.put("mobile", true);

		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMatrix);
		

		driver.findElement(By.xpath("//button[@class='navbar-toggler collapsed']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@routerlink='/library']")).click();

		driver.close();

	}

}
