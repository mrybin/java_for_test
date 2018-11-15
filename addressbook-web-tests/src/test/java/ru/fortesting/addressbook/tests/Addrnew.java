package ru.fortesting.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Addrnew  extends  TestBase{

  @Test
  public void testAddrnew() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToAddNewPage();
    ContactData contact = new ContactData("tester", "test", "96587321", "test@tes.com");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    Comparator<? super ContactData> byId=(c1, c2)-> Integer.compare(c1.getId(),c2.getId());
    after.sort(byId);
    contact.setId(after.get(after.size() - 1).getId());
    before.add(contact);
    before.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after) );
  }

}
