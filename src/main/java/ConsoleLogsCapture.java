import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ConsoleLogsCapture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Volumes/DATA1/Kuldeep_Automation/Grid/chromedriver");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Using Listeners concept to detect the test failed location. where the test has been failed
		//Listeners- OnTestfailure
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("(//a[@routerlink='/products'])[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Selenium')]")).click();
		driver.findElement(By.xpath("//button[@class='add-to-cart btn btn-default']")).click();
		driver.findElement(By.xpath("//a[@routerlink='/cart']")).click();
		WebElement qty=driver.findElement(By.xpath("//input[@id='exampleInputEmail1']"));
		qty.clear();
		qty.sendKeys("3");
		
		LogEntries logEntries=driver.manage().logs().get(LogType.BROWSER);//getting log entries  Object 
		List<LogEntry>logs=logEntries.getAll();//with LogEntries object >>getAll methods returns all logs in a list 
		for(LogEntry e: logs)// perform Iterator action through a list and getting desired action Logs 
		{
			
			System.out.println(e.getMessage());
		}
		
	}

}
