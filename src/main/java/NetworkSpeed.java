import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.network.Network;
import org.openqa.selenium.devtools.v106.network.model.ConnectionType;

public class NetworkSpeed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DevTools devsTool = driver.getDevTools();
		devsTool.createSession();
		devsTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devsTool.send(Network.emulateNetworkConditions(true, 3000, 20000, 100000, Optional.of( ConnectionType.CELLULAR4G)));
		// Fetching Network Loading Failed action 
		devsTool.addListener(Network.loadingFailed(), loadingFailed ->
		{
			System.out.println(loadingFailed.getErrorText());
			System.out.println(loadingFailed.getTimestamp());
		});
		long startTime=System.currentTimeMillis();
//		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
//		driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("udemy", Keys.ENTER);
		driver.findElements(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']")).get(0).click();
		String headTitle=driver.findElement(By.xpath("//div[@class='billboard-banner--content-box--2LhRB']")).getText();
		System.out.println(headTitle);
		long endTime=System.currentTimeMillis();
		System.out.println(endTime-startTime);

	}

}
