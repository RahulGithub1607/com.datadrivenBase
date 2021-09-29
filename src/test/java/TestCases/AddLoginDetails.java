package TestCases;

import TestBase.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddLoginDetails extends TestBase {

    // Read data from the excel
    // SheetName is className which is same in excel sheet

    @Test(dataProvider = "getData")
//    Add columns Name as parameter method
    public void addCustomer(String emailAdd, String passWord) {

        driver.findElement(By.xpath(OR.getProperty("email_textbox"))).sendKeys(emailAdd);
        driver.findElement(By.xpath(OR.getProperty("password_textbox"))).sendKeys(passWord);
        driver.findElement(By.xpath(OR.getProperty("Login_button"))).click();
    }

    @DataProvider
    public Object[][] getData() {
        // SheetName is className which is same in excel sheet

        String sheetName = "AddLoginDetails";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows - 1][cols - 1];
        for (int rowNum = 2; rowNum <= rows; rowNum++) {
            for (int colNum = 0; colNum < cols; colNum++) {
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
            }
        }
        return  data;
    }


}
