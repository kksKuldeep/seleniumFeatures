import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v104.network.Network;

import com.google.common.collect.ImmutableList;

public class BlockNetworkRequest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DevTools devsTool = driver.getDevTools();
		devsTool.createSession();
		devsTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devsTool.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));
		long startTime=System.currentTimeMillis();
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("(//a[@routerlink='/products'])[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Selenium')]")).click();
		driver.findElement(By.xpath("//button[@class='add-to-cart btn btn-default']")).click();
		String cartAddMsg=driver.findElement(By.xpath("//p[@class='sp']")).getText();
		System.out.println(cartAddMsg);
		long endTime=System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}

}
