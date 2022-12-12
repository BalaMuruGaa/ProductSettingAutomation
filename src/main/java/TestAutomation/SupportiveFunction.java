package TestAutomation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class SupportiveFunction {


    //static WebDriver driver;
    static By l3cUserName= By.xpath("//input[@id='login_id']");
    static By l3cPassWord = By.xpath("//*[@id=\"password\" and @name='PASSWORD']");
    static By l3cNextButton = By.xpath("//button[@class='btn blue' and @id='nextbtn']");
    static By l3cSignIN =By.xpath("//*[@id=\"nextbtn\" and span[text()='Sign in']]");
    static By l3cClickSettingButton =By.xpath("//*[@class=\"ember-view loading\"][text()='Settings']");

    static By complianceNavigationPage= By.xpath("//a[text()='Compliance']");
    static By clickManageCompliancePage= By.xpath("//a[@policy_id='compliance']");
    static By CreateNewCompliance= By.xpath("//button[text()='Create New Compliance']");
    static By ManualComplianceName= By.xpath("//input[@id='compName']");
    static By clickSelectDevice = By.xpath("(//*[@id=\"basic-addon2\"]/i)[1]");
    static By selectWindowsGroupDevices = By.xpath("(//div[@data-name='WindowsGroup'])[1]");
    static By selectWindowsWorkstationDevices = By.xpath("(//div[@data-name='Windows Workstation'])[1]");
    static By addDeviceCompliance = By.xpath(" (//button[text()='Add'])[1]");
    static By SelectWindowLogonReports =By.xpath("//label[text()='Windows Logon Reports']");
    static By SelectWindowFailedReports= By.xpath("//label[text()='Windows Failed Logon Reports']");
    static By saveManualCompliance= By.xpath("//button[text()='Save']");
    static By clickSyslogDevices = By.xpath("//a[text()='Syslog Devices']");
    static By clickAddDevices = By.xpath("//button[text()='Add Device(s)']");
    static By inputDevices = By.xpath("//input[@id='myInput']");
    static By addDevices =By.xpath("//button[text()='Add']");
    static By clickSearch = By.xpath("//i[@data-original-title='Search']");
    static By searchInputDevice =By.xpath("//*[@class=\"table-search1 table-with-search open\"]//input");
    static By selectSyslogDevice =By.xpath("//td[@class='wdTableDisplayName']");
    static By clickEditDevice = By.xpath("//i[@data-original-title=\"Update\"]");
    static By selectDeviceType = By.xpath("(//select[@class=\"editSysLog ember-view selectpicker hiddenselect\"])[1]");
    static By updateDeviceType = By.xpath("//button[text()='Update']");

    public static void SignIn(String Username, String Password, WebDriver driver) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(l3cUserName).sendKeys(Username);
        driver.findElement(l3cNextButton).click();
        driver.findElement(l3cPassWord).sendKeys(Password);
        driver.findElement(l3cSignIN).click();
        WebElement setting = driver.findElement(l3cClickSettingButton);
        assertTrue(setting.isDisplayed());
        //

    }


    public static int CreateManualCompliance(WebDriver driver) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.findElement(complianceNavigationPage).click();
        Thread.sleep(2000);
        driver.findElement(clickManageCompliancePage).click();
        driver.findElement(CreateNewCompliance).click();
        driver.findElement(ManualComplianceName).sendKeys("ManualCompliance");
        driver.findElement(clickSelectDevice).click();
        driver.findElement(selectWindowsGroupDevices).click();
        driver.findElement(selectWindowsWorkstationDevices).click();
        driver.findElement(addDeviceCompliance).click();
        driver.findElement(SelectWindowLogonReports).click();
        driver.findElement(SelectWindowFailedReports).click();
        driver.findElement(saveManualCompliance).click();
        return 0;
    }

    public static void SyslogDeviceAdd(WebDriver driver) throws InterruptedException {

        String[] ipAddress=new String[] {"10.10.10.4", "10.10.10.7", "10.10.10.13","10.10.10.18","10.10.10.19"};
        String[] deviceName=new String[] {"Juniper Device", "CheckPoint Device", "Meraki Device","H3C Device","Arista Device"};
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[text()='Settings']")).click();
        driver.findElement(clickSyslogDevices).click();
        Duration ACQUIRE_TIMEOUT1 = Duration.ofMinutes(5);
        WebDriverWait wait = new WebDriverWait(driver, ACQUIRE_TIMEOUT1);
        for(int i=0;i<5;i++) {
            wait.until(ExpectedConditions.presenceOfElementLocated(clickAddDevices));
            driver.findElement(clickAddDevices).click();
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
                driver.findElement(inputDevices).sendKeys(ipAddress[i]);
                driver.findElement(addDevices).click();
            }
            Thread.sleep(50000);
            driver.findElement(clickSearch).click();
            wait.until(ExpectedConditions.elementToBeClickable(searchInputDevice));
            driver.findElement(searchInputDevice).clear();
            driver.findElement(searchInputDevice).sendKeys(ipAddress[i]);
            driver.findElement(searchInputDevice).sendKeys(Keys.ENTER);
            Thread.sleep(20000);
            Actions builder = new Actions(driver);
            builder.moveToElement(driver.findElement(selectSyslogDevice)).build().perform();
            builder.click();
            driver.findElement(clickEditDevice).click();
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
                wait.until(ExpectedConditions.presenceOfElementLocated(selectDeviceType));
                WebElement Select_RecordsPerPage = driver.findElement(selectDeviceType);
                Select FilterLogType = new Select(Select_RecordsPerPage);
                FilterLogType.selectByValue(deviceName[i]);
                wait.until(ExpectedConditions.elementToBeClickable(updateDeviceType));
                driver.findElement(updateDeviceType).click();
            }
        }



    }
}
