import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DevTools devTools=driver.getDevTools();
		devTools.createSession();
		Map<String,Object> locationMatrix=new HashMap<String,Object>();
		
		locationMatrix.put("latitude", 40);
		locationMatrix.put("longitude", 3);
		locationMatrix.put("accuracy", 1);
		driver.executeCdpCommand("Emulation.setGeolocationOverride", locationMatrix);
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("udemy", Keys.ENTER);
		driver.findElements(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']")).get(0).click();
		String headTitle=driver.findElement(By.xpath("//div[@class='billboard-banner--content-box--2LhRB']")).getText();
		System.out.println(headTitle);

	}

}
