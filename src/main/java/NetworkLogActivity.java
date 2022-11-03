import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.network.Network;
import org.openqa.selenium.devtools.v106.network.model.Request;
import org.openqa.selenium.devtools.v106.network.model.Response;




public class NetworkLogActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DevTools devsTool=driver.getDevTools();
		devsTool.createSession();
		devsTool.send(Network.enable(Optional.<Integer>empty(), Optional.<Integer>empty(), Optional.<Integer>empty()));
		
		devsTool.addListener(Network.requestWillBeSent(), request ->
		{
			Request resqt=request.getRequest();
			//System.out.println(resqt.getUrl());
			//System.out.println(resqt.getHeaders());
			
		}
				);
		//event Get Fired>> Fired when HTTP response is available.
		devsTool.addListener(Network.responseReceived(), response ->
		{
			Response resp=response.getResponse();
			//System.out.println(resp.getUrl());
			//System.out.println(resp.getStatus());
			//response.getResponse().getUrl();
			//response.getResponse().getStatus();
			if(resp.getStatus().toString().startsWith("4"))
			{
				System.out.println(resp.getUrl()+"is getting failed with status code" +resp.getStatus());
			}
		});
				
			driver.get("https://rahulshettyacademy.com/angularAppdemo/");	
			driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
				
	}

}
