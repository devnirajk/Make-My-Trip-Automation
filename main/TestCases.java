package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.makemytrip.com/");

        // Thread.sleep(3000);
        String currentURL = driver.getCurrentUrl();
        // Thread.sleep(3000);
        if (currentURL.contains("makemytrip")) {
            System.out.println("URL verification passed!");
        } else {
            System.out.println("URL verification failed!");
        }
        // Thread.sleep(3000);
        System.out.println("end Test case: testCase01");

    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");

        // Set system property to indicate the location of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Navigate to Make My Trip website
        driver.get("https://www.makemytrip.com/flights/");

        Thread.sleep(5000);

        // Select BLR(Bangalore) as the departure location
        WebElement fromInput = driver.findElement(By.id("fromCity"));
        fromInput.click();
        WebElement fromCity = driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromCity.sendKeys("blr");
        Thread.sleep(3000); // Wait for the dropdown to appear
        WebElement fromOption = driver.findElement(By.xpath("//p[text()='Bengaluru, India']"));
        fromOption.click();
        Thread.sleep(6000);

        // Select DEL(New Delhi) as the arrival location
        WebElement toInput = driver.findElement(By.id("toCity"));
        toInput.click();
        WebElement toCity = driver.findElement(By.xpath("//input[@placeholder='To']"));
        Thread.sleep(3000);
        toCity.sendKeys("del");
        Thread.sleep(3000); // Wait for the dropdown to appear
        WebElement toOption = driver.findElement(By.xpath("//p[text()='New Delhi, India']"));
        toOption.click();
        Thread.sleep(3000);
        WebElement date29 = driver.findElement(By.xpath("//p[text()=\"29\"]"));
        if(date29.getText().contains("29")){
            date29.click();
            System.out.println("Selected the 29 of this month");
        }

        // Click on the search button
        WebElement searchButton = driver
                .findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']"));
        searchButton.click();

        WebElement goContinue = driver
                .findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/div/div[3]/button"));
        if (goContinue.isDisplayed()) {
            goContinue.click();
            Thread.sleep(3000);
        } else {
            System.out.println("Continue button not displayed.");
        }

        // Store the flight price (per adult)
        List<WebElement> priceElements = driver
                .findElements(
                        By.xpath("//*[@id=\"listing-id\"]/div/div[2]/div/div[8]/div[1]/div[2]/div[2]/div/div/div"));
        if (priceElements.size() > 0) {
            String price = priceElements.get(0).getText();
            System.out.println("Price per adult: " + price);
        } else {
            System.out.println("No flight details found.");
        }
        System.out.println("End Test case: testCase03");

    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.makemytrip.com/railways/");
        Thread.sleep(10000);

        // Select YPR(Bangalore) as the departure location for the train.
        WebElement fromInput = driver.findElement(By.id("fromCity"));
        fromInput.click();
        WebElement fromCity = driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromCity.sendKeys("ypr");
        Thread.sleep(3000); // Wait for the dropdown to appear
        WebElement fromOption = driver
                .findElement(By.xpath("//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div/div/p[2]/span"));
        fromOption.click();
        Thread.sleep(6000);

        // // Select NDLS(New Delhi) as the arrival location for the train.
        WebElement toInput = driver.findElement(By.id("toCity"));
        // toInput.click();
        WebElement toCity = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toCity.sendKeys("ndls");
        Thread.sleep(3000); // Wait for the dropdown to appear
        WebElement toOption = driver
                .findElement(By.xpath("//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div/span"));
        toOption.click();

        // Select the correct date (29th of next Month) for the train.
        Thread.sleep(3000);
        WebElement date29 = driver.findElement(By.xpath(
                "//*[@id=\"top-banner\"]/div[2]/div/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[3]/div[5]/div[6]"));
        date29.click();
        // Select the class as 3AC
        Thread.sleep(2000);
        WebElement classOption = driver
                .findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[2]/div/div[4]/ul/li[3]"));
        classOption.click();

        // Click on the search button for the train.
        WebElement searchButton = driver
                .findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[2]/p/a"));
        searchButton.click();

        // Wait for the train details to load
        // Thread.sleep(8000); // Adjust the wait time as necessary
        // WebElement goContinue = driver
        //         .findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/div/div[3]/button"));
        // goContinue.click();
        Thread.sleep(3000);

        // Store the train price for 3AC.
        List<WebElement> priceElements = driver
                .findElements(By.xpath("//*[@id=\"train_options_29-03-2024_0\"]/div[1]/div[2]"));
        if (priceElements.size() > 0) {
            String price = priceElements.get(0).getText();
            System.out.println("Train price for 3AC: " + price);
        } else {
            System.out.println("No train details found.");
        }
        Thread.sleep(3000);
        System.out.println("End Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        driver.get("https://www.makemytrip.com/bus-tickets/");
        Thread.sleep(10000);

        // Select BLR(Bangalore) as the departure location
        WebElement fromInput = driver.findElement(By.id("fromCity"));
        fromInput.click();
        WebElement fromCity = driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromCity.sendKeys("bangl");
        Thread.sleep(3000); // Wait for the dropdown to appear
        WebElement fromOption = driver
                .findElement(By.xpath("//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div/p"));
        fromOption.click();
        Thread.sleep(6000);

        // Select Ranchi as the arrival location
        WebElement toInput = driver.findElement(By.id("toCity"));
        // toInput.click();
        WebElement toCity = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toCity.sendKeys("ran");
        Thread.sleep(3000); // Wait for the dropdown to appear
        WebElement toOption = driver
                .findElement(By.xpath("//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div/p/span"));
        toOption.click();

        // Select the correct date (20th of next Month) for the bus.
        Thread.sleep(3000);

        // Select the correct date (20th of next Month)
        Thread.sleep(3000); // Wait for the calendar to appear
        WebElement nextMonthArrow = driver.findElement(By.xpath(
                "//*[@id=\"top-banner\"]/div[2]/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[2]/div/div[1]/span[2]"));
        nextMonthArrow.click();
        Thread.sleep(1000);
        WebElement date20 = driver.findElement(By.xpath(
                "//*[@id=\"top-banner\"]/div[2]/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[3]/div[3]/div[7]"));
        date20.click();
        Thread.sleep(2000);

        // Click on the search button for buses
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"search_button\"]"));
        searchButton.click();

        // Wait for the search results to load
        Thread.sleep(5000); // Adjust the wait time as necessary
        // Verify that text displayed contains "No buses found"
        WebElement noBusesText = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div[1]/span[1]"));
        Thread.sleep(2000);
        if (noBusesText.getText().contains("No buses found")) {
            System.out.println("No buses found for the specified route and date.");
        } else {
            System.out.println("Buses found for the specified route and date.");
        }
        System.out.println("End Test case: testCase04");
    }
}

