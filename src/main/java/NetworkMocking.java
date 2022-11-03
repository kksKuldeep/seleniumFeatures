import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.fetch.Fetch;

public class NetworkMocking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DevTools devsTool = driver.getDevTools();
		devsTool.createSession();
		devsTool.send(Fetch.enable(Optional.empty(), Optional.empty()));
		devsTool.addListener(Fetch.requestPaused(), request -> {
			if (request.getRequest().getUrl().contains("shetty")) {
				String newMockedUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
				System.out.println(newMockedUrl);
				devsTool.send(Fetch.continueRequest(request.getRequestId(), Optional.of(newMockedUrl),
						Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty(),
						Optional.empty()));
			} else {
				devsTool.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()),
						Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty(),
						Optional.empty()));

			}
		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
		String notifyValidater=driver.findElement(By.tagName("p")).getText();
		System.out.println(notifyValidater);
	}

}
