import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class L4ex6_2 {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/mbpro/Desktop/JavAppiumAutomation/JavaAppiumAuto/JavaAppiumAuto/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub/"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void l3_Ex4()
    {
        //Пропустим онбординг кликнув Skip
        waitForElementForClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Not find button Skip",
                15
        );

        //Тапнем в поле поиска
        waitForElementForClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find input id 'org.wikipedia:id/search_container'",
                5
        );


        //Тап в поле ввода и ввод текста
        waitForElementSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "JAVA",
                "Cannot search input",
                15
        );

        //Ищем нашу статью и тапаем по ней
        waitForElementForClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Not find статья",
                15
        );

        //Ищем стать и кликаем по ней
        String title_for_equals = "Java (programming language)";


        String title_find = assertElementPresent(
                By.xpath("//android.view.View[@text='Java (programming language)']"),
                "text",
                "Cannot find title article",
                0
        );

        Assert.assertEquals(
                "Article title not found",
                title_for_equals,
                title_find
        );


    }


    //___Тут мы пишем методы/функции______________________________________________

    //Пишем метод на ожидание элемента и его клик
    private WebElement waitForElementForClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 15);
        element.click();
        return element;
    }

    //Пишем метод на клик по элементу и ввод текста
    private WebElement waitForElementSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 15);
        element.sendKeys(value);
        return element;
    }

    //Пишем метод на ожидание элемента на странице
    private WebElement waitForElementPresent (By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    private String assertElementPresent (By by, String attribute, String error_message,long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


}
