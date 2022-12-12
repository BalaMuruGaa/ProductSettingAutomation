package TestAutomation;
import graphql.ThreadSafe;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

import static TestAutomation.ProductSetting.*;
import static org.testng.Assert.assertTrue;


public class L3CAutomation {


    public String baseUrl = "https://logs360cloud-test-s1.localzoho.com/";

    public  WebDriver driver;
    static String chromeDriverPath = "C:\\New folder (3)\\chromedriver.exe";
    static String fireboxDriverPath = "C:\\New folder (3)\\geckodriver.exe";
    static String edgeDriverPath = "C:\\New folder (3)\\msedgedriver.exe";
    static By l3cUserName= By.xpath("//input[@id='login_id']");
    static By l3cPassWord = By.xpath("//*[@id=\"password\" and @name='PASSWORD']");
    static By l3cNextButton = By.xpath("//button[@class='btn blue' and @id='nextbtn']");
    static By l3cSignIN =By.xpath("//*[@id=\"nextbtn\" and span[text()='Sign in']]");

    String browserName = "Chrome";

    @DataProvider
    public Object[][] Authentication() {
        Object[][] arrObj = getExcelData("D:\\Automation_TestData.xlsx","ProductSetting");
        return arrObj;
    }

    public Object[][] getExcelData(String fileName, String sheetName) {
        String[][] data = null;
        try
        {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRows-1][noOfCols];
            for(int i =1; i<noOfRows;i++){
                for(int j=0;j<noOfCols;j++){
                    row = sh.getRow(i);
                    cell= row.getCell(j);
                    data[i-1][j] = cell.getStringCellValue();
                }
            }
        }
        catch (Exception e) {
            System.out.println("The exception is: " +e.getMessage());
        }
        return data;
    }

    @BeforeTest
    public void LaunchUrl() throws InterruptedException {

        if (browserName.equalsIgnoreCase("Chrome")) {
            System.out.println("Opening the L3C Account in Chrome Browser");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
            driver.get(baseUrl);

        } else if (browserName.equalsIgnoreCase("Firebox")) {
            System.out.println("Opening the L3C Account in FireBox Browser");
            System.setProperty("webdriver.gecko.driver", fireboxDriverPath);
            driver = new FirefoxDriver();
            driver.get(baseUrl);
        } else {
            System.out.println("Opening the L3C Account in Edge Browser");
            System.setProperty("webdriver.edge.driver", edgeDriverPath);
            driver = new EdgeDriver();
            driver.get(baseUrl);
        }
        driver.manage().window().maximize();
        driver.findElement(l3cUserName).sendKeys("bala.muruganp+casbhf+bv4@zohotest.com");
        driver.findElement(l3cNextButton).click();
        Thread.sleep(3000);
        driver.findElement(l3cPassWord).sendKeys("Test1234@");
        driver.findElement(l3cSignIN).click();
    }

    @Test(dataProvider="Authentication")
    public void UpdateProductSetting(String report_Page, String reportExport_Limit, String topLeastN, String exportLimit, String timeZone, String emailLimit) throws InterruptedException {
        updateProductSetting(report_Page,reportExport_Limit,topLeastN,exportLimit,timeZone,emailLimit,driver);

    }
    /*@Test
    public void verifyTopLeastValue() throws InterruptedException {
        VerifyTopandLeastN(topLeastN,driver);
    }
    @Test
    public void verifyRecordsPage() throws InterruptedException {
        VerifyRecords_Page(recordPage,driver);
    }*/


}
