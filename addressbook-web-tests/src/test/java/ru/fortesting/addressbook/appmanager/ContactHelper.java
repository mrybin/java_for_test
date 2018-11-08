package ru.fortesting.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.fortesting.addressbook.model.ContactData;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase{
    public boolean acceptNextAlert = true;


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitAddrNew() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillAddrNewData(ContactData contactData) {
      type(By.name("firstname"),contactData.getFirstname());
      type(By.name("lastname"),contactData.getLastname());
      type(By.name("mobile"),contactData.getMobile());
      type(By.name("email"),contactData.getEmail());
    }

    public void deleteContact() {
      click(By.xpath("//input[@value='Delete']"));
      wd.switchTo().alert().accept();
      //assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

 /*   public String closeAlertAndGetItsText() {
      try {
        Alert alert = wd.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
          alert.accept();
        } else {
          alert.dismiss();
        }
        return alertText;
      } finally {
        acceptNextAlert = true;
      }
    }
*/

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public void goToHome() {
        click(By.linkText("home"));
    }
    public void submitModification() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        fillAddrNewData(contact);
        submitAddrNew();
        goToHome();
    }
}
