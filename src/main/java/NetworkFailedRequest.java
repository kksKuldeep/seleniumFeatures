import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.fetch.Fetch;
import org.openqa.selenium.devtools.v106.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v106.network.model.ErrorReason;

public class NetworkFailedRequest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DevTools devsTool = driver.getDevTools();
		devsTool.createSession();
		// request and Fetch requestpattern data and convert then into list
	Optional<List<RequestPattern>> patterns=Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));
		devsTool.send(Fetch.enable(patterns, Optional.empty()));
		//add Listners to perform request
		devsTool.addListener(Fetch.requestPaused(), request->
		
				{
					devsTool.send(Fetch.failRequest(request.getRequestId(), ErrorReason.CONNECTIONFAILED));
				});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
		//devsTool.send(Fetch.failRequest(fetch, null));
		
	}

}
