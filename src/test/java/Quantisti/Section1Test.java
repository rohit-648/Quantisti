package Quantisti;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Section1Test {

	public void RemindMeLater(WebDriver driver) {

		try {
			driver.findElement(By.cssSelector(".web-push-btn.remind-btn")).click();
			System.out.println("Alert shown and handeled successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
	}

	@Test
	public void Login() {

		// Initialize Webdriver C:\Users\Rohit More\Downloads
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rohit More\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Step 1- Open Application - https://quantra.quantinsti.com
		driver.get("https://quantra.quantinsti.com/");
		RemindMeLater(driver);

		// Step 2- Click on the login button
		driver.findElement(By.cssSelector(".item.signup-link")).click();
		RemindMeLater(driver);
		// Step 3- Log in as username & password
		driver.findElement(By.name("email")).sendKeys("rohitmore648@gmail.com");
		driver.findElement(By.name("password")).sendKeys("dSTmjSALM@6HzQd");
		driver.findElement(By.cssSelector(".btn.btn-plain-border")).click();
		RemindMeLater(driver);

		// Step 4- Then click on the Browse Courses menu
		driver.findElement(By.cssSelector(".item.menu__link--toggle")).click();
		RemindMeLater(driver);
		// Step 5- And select the course - Sentiment Analysis in Trading
		driver.findElement(By.cssSelector("a[href='/learning-track/sentiment-analysis-trading']")).click();
		RemindMeLater(driver);

		// Step 6- Get the Course name & Price
		String CourseName = driver.findElement(By.cssSelector(".container.course-detail-inner-container div h1"))
				.getText();
		System.out.println(CourseName);

		String CoursePrice = driver.findElement(
				By.cssSelector(".course-detail__data-unit.price-data-unit div:nth-child(2) span:nth-child(2) span"))
				.getText();

		String actualCoursePrice = CoursePrice.substring(1);
		System.out.println("Course Price is Rs." + actualCoursePrice);
		RemindMeLater(driver);

		// Step 7-Then click on the Enroll now button
		driver.findElement(By.cssSelector("span.default-slot")).click();
		RemindMeLater(driver);

		/*
		 * Step 8- On the cart page, get the courses name & count the number of the
		 * courses present in the cart page and verify that the course count should
		 * match with the number displayed in the cart icon
		 */
		WebElement cartItemSection = driver.findElement(By.cssSelector(".cart-items-section"));
		List<WebElement> items = cartItemSection.findElements(By.cssSelector(".cart-item"));
		int courseCount = 0;

		for (WebElement item : items) {

			String CoursesinCart = item.findElement(By.cssSelector("h5.cart-item-title")).getText();
			System.out.println(CoursesinCart);
			courseCount = courseCount + 1;

		}
		System.out.println("Number of Courses Present in Cart is:" + courseCount);

		String countInCartIcon = driver.findElement(By.cssSelector(".cart-count")).getText();
		System.out.println("Count in cart icon is:" + countInCartIcon);

		String ActualcourseCount = Integer.toString(courseCount);
		if (ActualcourseCount.equals(countInCartIcon)) {
			System.out.println("Count Matches");

		}

		else {
			System.out.println("Count doesn't Match");
		}

		// Step 9- Capture the Base Amount & Amount Payable info
		String BaseAmount = driver.findElement(By.cssSelector(".cart-summary-item div:nth-child(2)")).getText();
		String ActualBaseAmount = BaseAmount.substring(1);
		System.out.println("Base Amount is : Rs." + ActualBaseAmount);

		String AmountPayable = driver
				.findElement(By.cssSelector(".cart-summary-item.amt-payable-wrap div:nth-child(2) h5 span")).getText();
		String ActualAmountPayable = AmountPayable.substring(1);
		System.out.println("AmountPayable is : Rs." + ActualAmountPayable);

		/*
		 * Step 10 - 10.Click on the View Details link of any course (a new browser tab
		 * gets open on clicking on the View Details link. After that close the tab and
		 * get back to the cart page )
		 */
		RemindMeLater(driver);
		driver.findElement(By.cssSelector("a[href='/course/python-for-trading\']")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();

		String ParentWindow = it.next();
		String ChildWindow = it.next();

		driver.switchTo().window(ChildWindow);
		driver.close();
		driver.switchTo().window(ParentWindow);

		// Step 11-After that remove any one course from the cart page by clicking
		// remove link
		// and get the alert message text
		WebElement remove = driver.findElement(By.xpath("//*[text()='Remove']"));

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Remove']")));
		remove.click();

		String ErrorMsg = driver.findElement(By.cssSelector(".toasted-container.top-center")).getText();
		System.out.println(ErrorMsg);

		// Step 12-Then click on the Apply coupon button and type ABC into the text box
		// and
		// click on the Apply button and get the error message.

		driver.findElement(By.cssSelector(".vue-ui-button.btn.secondary.ghost-button")).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(".form-control.text-uppercase.personal-coupon-input")));
		driver.findElement(By.cssSelector(".form-control.text-uppercase.personal-coupon-input")).sendKeys("ABC");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector(".vue-ui-button.btn.secondary.ghost-button")));
		WebElement Apply = driver.findElement(By.cssSelector(".vue-ui-button.btn.secondary.ghost-button"));

		// Actions A =new Actions(driver);
		// A.moveToElement(Apply).click().build().perform();
		driver.findElement(By.cssSelector(".modal-body form div:nth-child(3)")).click();
		String Alert = driver.findElement(By.cssSelector(".modal-body div div")).getText();
		System.out.println(Alert);

		// Step 13- 13.Then close the modal.
		driver.findElement(By.cssSelector(".modal-body button span")).click();

		// Step 14 - And Sign out from the application
		driver.findElement(By.cssSelector(".item.avatar.d-user-profile")).click();
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Logout']")));
		WebElement logOutButton = driver.findElement(By.xpath("//*[text()='Logout']"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", logOutButton);
		
		//driver.close();
	}
}
