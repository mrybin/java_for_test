package ru.fortesting.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.fortesting.addressbook.model.ContactData;
import ru.fortesting.addressbook.model.Contacts;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactHelper extends HelperBase{



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


    public void initContactModification(int i) {
        click(By.xpath("//a[@href='edit.php?id="+i+"']"));
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

    public void create(ContactData contact) {
        fillAddrNewData(contact);
        submitAddrNew();
        goToHome();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath( "//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath("./td[2]")).getText();
            String firstname = element.findElement(By.xpath("./td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }


    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }
    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillAddrNewData(contact);
        submitModification();
        goToHome();
    }


    public void delete(ContactData contact) {
        selectById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        goToHome();
    }
}
