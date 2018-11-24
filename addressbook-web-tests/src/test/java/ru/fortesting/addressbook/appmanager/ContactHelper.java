package ru.fortesting.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.fortesting.addressbook.model.ContactData;
import ru.fortesting.addressbook.model.Contacts;


import java.util.List;


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
      type(By.name("mobile"),contactData.getMobilePhone());
      type(By.name("email"),contactData.getEmail());
    }


    public void initContactModificationById(int i) {
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
    private Contacts contactsCache=null;
    public void create(ContactData contact) {
        fillAddrNewData(contact);
        submitAddrNew();
        contactsCache=null;
        goToHome();
    }

    public Contacts all() {
        if (contactsCache!=null){
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath( "//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath("./td[2]")).getText();
            String firstname = element.findElement(By.xpath("./td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String allemails = element.findElement(By.xpath("./td[5]")).getText();
            String allphones = element.findElement(By.xpath("./td[6]")).getText();
            contactsCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allphones).withAddress(address).withAllEmails(allemails));
        }
        return new Contacts(contactsCache);
    }


    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }
    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillAddrNewData(contact);
        submitModification();
        contactsCache=null;
        goToHome();
    }


    public void delete(ContactData contact) {
        selectById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        contactsCache=null;
        goToHome();
    }
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFormEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname= wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname= wd.findElement(By.name("lastname")).getAttribute("value");
        String home= wd.findElement(By.name("home")).getAttribute("value");
        String mobile= wd.findElement(By.name("mobile")).getAttribute("value");
        String work= wd.findElement(By.name("work")).getAttribute("value");
        String address=wd.findElement(By.name("address")).getAttribute("value");
        String email=wd.findElement(By.name("email")).getAttribute("value");
        String email2=wd.findElement(By.name("email2")).getAttribute("value");
        String email3=wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId()).withLastname(lastname).withFirstname(firstname).withHomePhone(home)
                .withMobile(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
