package TestAutomation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductSetting {

    static By l3cClickSettingButton =By.xpath("//*[text()='Settings']");

    static By l3cAdminSetting =By.xpath("//a[text()='Admin']");
    static By l3cProductCustomization =By.xpath("//a[text()='Product Customization']");
    static By l3cProductSetting =By.xpath("//a[text()='Product Settings']");
    static By l3cTopLeastN =By.xpath("//input[@id=\"ROWS_TOP_N\"]");
    static By l3cSaveProductSetting =By.xpath("//*[@id=\"saveButton\"]");
    static By l3cNotificationElement =By.xpath("//td[@class='fw-text-break']");
    static By l3cReportPage =By.xpath("//a[text()='Reports']");
    static By l3cClickTopLeastValueOfEventId =By.xpath("//span[text()='EventId']");
    static By l3cTopValue_ReportPage =By.xpath("//*[@id=\"topAndLeastModal\"]/div[1]//tbody//tr");
    static By l3cLeastValue_ReportPage =By.xpath("//*[@id=\"topAndLeastModal\"]/div[2]//tbody//tr");
    static By closingFrame=By.xpath("//*[@class='pull-right']//button[@class='close']");

    static By l3cSearchNavigationPage =By.xpath("//a[text()='Search']");
    static By l3cSearchInputText = By.xpath("//*[@class='bs-searchbox']//input");
    static By l3cSelectDeviceType =By.xpath("//a[@tabindex='0'] //label[text()='Windows']");
    static By l3cClickSearch =By.xpath("//btn[text()='Search']");
    static By l3cSelectingLogType =By.xpath("//button[@data-id='multiSelectLogtype']");
    static By l3cEventId =By.xpath(" //span[text()='EventId']");
    static By l3cReportsPage = By.xpath("//select[@class='generalselect ember-view selectpicker hiddenselect']");
    static By l3cExportLimit =By.xpath("//input[@id='EXPORT_LIMIT']");
    static By l3cExportTimeout =By.xpath("//input[@id='TIME_OUT']");
    static By l3cTimeZone =By.xpath("//select[@id=\"ZONE_FORMAT\"]");
    static By l3cDailyEmailLimit =By.xpath("//input[@id='MAIL_LIMIT']");
    static By l3cRecords_reportPage = By.cssSelector("#tableTopToolBar > ul.right-flow > li:nth-child(2) > div > button");

    static By l3cRecordsPage =By.xpath("//button[@class='btn dropdown-toggle']");
    static By l3cCorrelationPage = By.xpath("//a[text()='Correlation']");
    static By l3cComplianceManageRule =By.xpath("//a[text()='Manage Rules']");
    static By l3cAlertPage = By.xpath("//a[text()='Alerts']");
    static By l3cIncident = By.xpath("(//a[text()='Incident'])");
    static By l3cAlertSetting = By.xpath("//*[@class='dropdown-toggle'] //i[@class='fw-icon fw-icn-menu-settings']");
    static By l3cAlertManageProfile = By.xpath("//a[text()='Manage Profiles']");
    static By l3cAlertViewAlerts = By.xpath("//a[text()='View Alerts']");
    static By l3CorrelationAlertProfile = By.xpath("//a[text()='Correlation Alert Profiles']");

    static SoftAssert softAssert = new SoftAssert();


    static By clickManualCompliance = By.xpath("//a[text()='ManualCompliance']");
    static By ClickWindowsLogonReport = By.xpath("//a[text()='User Logons']");








    public static void updateProductSetting(String report_Page, String reportExport_Limit, String topLeastN, String exportLimit, String timeZone, String emailLimit, WebDriver driver) throws InterruptedException {


        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(l3cClickSettingButton).click();
        driver.findElement(l3cAdminSetting).click();
        driver.findElement(l3cProductCustomization).click();
        driver.findElement(l3cProductSetting).click();
        WebElement Select_RecordsPerPage = driver.findElement(l3cReportsPage);
        Select FilterLogType = new Select(Select_RecordsPerPage);
        FilterLogType.selectByValue(report_Page);
        driver.findElement(l3cExportLimit).clear();
        driver.findElement(l3cExportLimit).sendKeys(reportExport_Limit);
        driver.findElement(l3cTopLeastN).clear();
        driver.findElement(l3cTopLeastN).sendKeys(topLeastN);
        driver.findElement(l3cExportTimeout).clear();
        driver.findElement(l3cExportTimeout).sendKeys(exportLimit);
        WebElement Select_TimeZone = driver.findElement(l3cTimeZone);
        Select FilterLogType_TimeZone = new Select(Select_TimeZone);
        FilterLogType_TimeZone.selectByValue(timeZone);
        driver.findElement(l3cDailyEmailLimit).clear();
        driver.findElement(l3cDailyEmailLimit).sendKeys(emailLimit);
        driver.findElement(l3cSaveProductSetting).click();
        Duration ACQUIRE_TIMEOUT = Duration.ofMinutes(2);
        WebDriverWait wait = new WebDriverWait(driver, ACQUIRE_TIMEOUT);
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(l3cNotificationElement));
            System.out.println(element.getText());
            Assert.assertEquals("Product Settings have been saved successfully",element.getText());
            driver.findElement(l3cProductCustomization).click();

        }
        catch(Exception e){
                if(Integer.parseInt(reportExport_Limit)<1000)
                    softAssert.assertEquals("* Direct Export Report Limit cannot be less than 1000.",driver.findElement(By.xpath("(//span[@class='empty-error error-text fw-b-m15'])[1]")).getText());
                else if(Integer.parseInt(reportExport_Limit)>100000)
                    softAssert.assertEquals("* Direct Export Report Limit cannot exceed 100000.",driver.findElement(By.xpath("(//span[@class='empty-error error-text fw-b-m15'])[1]")).getText());
                if(Integer.parseInt(topLeastN)<10)
                    softAssert.assertEquals("* Rows in Top & Least Reports cannot be less than 10.",driver.findElement(By.xpath("(//span[@class='empty-error error-text fw-b-m15'])[2]")).getText());
                else if(Integer.parseInt(topLeastN)>1000)
                    softAssert.assertEquals("* Rows in Top & Least Reports cannot exceed 10",driver.findElement(By.xpath("(//span[@class='empty-error error-text fw-b-m15'])[2]")).getText());
                if(Integer.parseInt(exportLimit)<5)
                    softAssert.assertEquals("* Export Time Out cannot be less than 5 minutes.",driver.findElement(By.xpath("(//span[@class='empty-error error-text fw-b-m15'])[3]")).getText());
                else if(Integer.parseInt(exportLimit)>60)
                    softAssert.assertEquals("* Export Time Out cannot exceed 60 minut",driver.findElement(By.xpath("(//span[@class='empty-error error-text fw-b-m15'])[3]")).getText());
                if(Integer.parseInt(emailLimit)<1)
                    softAssert.assertEquals("* Daily Mail Limit cannot be less than 1.",driver.findElement(By.xpath("(//span[@class='empty-error error-text fw-b-m15'])[4]")).getText());
                else if(Integer.parseInt(emailLimit)>5000)
                    softAssert.assertEquals("* Daily Mail Limit cannot exceed 5000.",driver.findElement(By.xpath("(//span[@class='empty-error error-text fw-b-m15'])[4]")).getText());
                driver.findElement(l3cProductCustomization).click();
                softAssert.assertAll();
        }
    }
    public static void VerifyTopandLeastN(int topLeastN,WebDriver driver) throws InterruptedException {

        Duration ACQUIRE_TIMEOUT = Duration.ofMinutes(2);
        WebDriverWait w = new WebDriverWait(driver,ACQUIRE_TIMEOUT);

        // Verifying Top and Least value changes in Report Page

        driver.findElement(l3cReportPage).click();
        driver.findElement(l3cClickTopLeastValueOfEventId).click();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
            List<WebElement> topValues_ReportPage = driver.findElements(l3cTopValue_ReportPage);
            List<WebElement> leastValues_ReportPage = driver.findElements(l3cLeastValue_ReportPage);
            if(topValues_ReportPage.size()<=topLeastN && leastValues_ReportPage.size()<=topLeastN) {
                System.out.println("Top and Least value changes reflected in Reports Page ");
            }
            else{
                System.out.println("Top and Least value changes not  reflected in Reports Page ");
            }
           driver.findElement(closingFrame).click();
        }
        // Verify the Top and Least value in Search page

        driver.findElement(l3cSearchNavigationPage).click();
        driver.findElement(l3cSelectingLogType).click();
        driver.findElement(l3cSearchInputText).sendKeys("Windows");
        driver.findElement(l3cSelectDeviceType).click();
        Thread.sleep(2000);
        driver.findElement(l3cClickSearch).click();
        driver.findElement(l3cEventId).click();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
            List<WebElement> topValues_SearchPage = driver.findElements(l3cTopValue_ReportPage);
            List<WebElement> leastValues_SearchPage = driver.findElements(l3cLeastValue_ReportPage);
            if(topValues_SearchPage.size()<=topLeastN && leastValues_SearchPage.size()<=topLeastN) {
                System.out.println("Top and Least value changes reflected in Search Page ");
            }
            else{
                System.out.println("Top and Least value changes not  reflected in Search Page ");
            }
            driver.findElement(By.xpath("(//button[text()='×'])[3]")).click();
        }
    }
    public static void VerifyRecords_Page(int records_Page, WebDriver driver) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);

        //Verify the Records Per Page in Report Page
        driver.findElement(l3cReportPage).click();
       String Text=recordperPage(driver);
        if(Integer.parseInt(Text)==records_Page)
            System.out.println("Records Per page Reflected properly in Reports Page");
        else
            System.out.println("Records Per page not Reflected properly in Reports Page");

        // Verify the Records per in Compliance Page

        driver.findElement(SupportiveFunction.complianceNavigationPage).click();
        Thread.sleep(2000);

        driver.findElement(SupportiveFunction.clickManageCompliancePage).click();
        WebElement Element_L3C_CompliancePage=driver.findElement(l3cRecords_reportPage);
        String selected_Option1 = Element_L3C_CompliancePage.getText();
        if(Integer.parseInt(selected_Option1)==records_Page)
            System.out.println("Records Per page Reflected properly in Compliance Page");
        else
            System.out.println("Records Per page not Reflected properly in Compliance Page");

        // Verify the Records per Page value in Search Page

        driver.findElement(l3cSearchNavigationPage).click();
        driver.findElement(l3cClickSearch).click();
        WebElement elementSearchPage=driver.findElement(l3cRecordsPage);
        String getText2 = elementSearchPage.getText();
        if(Integer.parseInt(getText2)==records_Page)
            System.out.println("Records Per page Reflected properly in Search Page");
        else
            System.out.println("Records Per page not Reflected properly in Search Page");

        // Verify the Records per page value in Correlation Page

        driver.findElement(l3cCorrelationPage).click();
        Thread.sleep(2000);
        driver.findElement(l3cComplianceManageRule).click();
        WebElement elementCorrelationPage=driver.findElement(l3cRecords_reportPage);
        String getText3 = elementCorrelationPage.getText();
        if(Integer.parseInt(getText3)==records_Page)
            System.out.println("Records Per page Reflected properly in Correlation Page");
        else
            System.out.println("Records Per page not Reflected properly in Correlation Page");

        // Verify the Records per page in Alert Page
        driver.findElement(l3cAlertPage).click();
        Thread.sleep(2000);
        driver.findElement(l3cAlertSetting).click();
        driver.findElement(l3cAlertManageProfile).click();
        WebElement manageProfile_Alert=driver.findElement(l3cRecordsPage);
        String selected_Option4 = manageProfile_Alert.getText();
        if(Integer.parseInt(selected_Option4)==records_Page)
            System.out.println("Records Per page Reflected properly in  alert Tabular column in Alert Manage Profile");
        else
            System.out.println("Records Per page not Reflected properly in alert Tabular column in Alert Manage Profile");

        Thread.sleep(2000);
        driver.findElement(l3CorrelationAlertProfile).click();
        WebElement manageProfile_Correlation=driver.findElement(l3cRecordsPage);
        String getText4 = manageProfile_Correlation.getText();
        if(Integer.parseInt(getText4)==records_Page)
            System.out.println("Records Per page Reflected properly in Correlation alert Tabular column in Alert Manage Profile");
        else
            System.out.println("Records Per page not Reflected properly in Correlation alert Tabular column in Alert Manage Profile");
        Thread.sleep(2000);
        driver.findElement(l3cAlertViewAlerts).click();
        WebElement recordsPageAlert=driver.findElement(l3cRecordsPage);
        String getText5 = recordsPageAlert.getText();
        if(Integer.parseInt(getText5)==records_Page)
            System.out.println("Records Per page Reflected properly Alert Tabular");
        else
            System.out.println("Records Per page not Reflected properly in Alert Tabular");
        Thread.sleep(2000);
        driver.findElement(l3cIncident).click();
        WebElement recordsPageIncident=driver.findElement(l3cRecordsPage);
        String getText6 = recordsPageIncident.getText();
        if(Integer.parseInt(getText6)==records_Page)
            System.out.println("Records Per page Reflected properly Incident Tabular Column");
        else
            System.out.println("Records Per page not Reflected properly in Alert Tabular column");

        //Verify the Records Per Page in Setting Page

        driver.findElement(l3cClickSettingButton).click();
        // Verify Records in Device Management Tab
        driver.findElement(l3cAlertViewAlerts).click();
        WebElement records_WindowsDevice=driver.findElement(l3cRecordsPage);
        String getText7 = records_WindowsDevice.getText();
        if(Integer.parseInt(getText7)==records_Page)
            System.out.println("Records Per page Reflected properly window Device Management Page");
        else
            System.out.println("Records Per page not Reflected properly window Device Management Page");

    }

    public static String  recordperPage(WebDriver driver) {
        WebElement elementReportPage=driver.findElement(l3cRecords_reportPage);
        String getText = elementReportPage.getText();
        return getText;
    }



}
