package ru.fortesting.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;

import java.util.List;

public class DeleteContact extends TestBase {

  @Test (enabled = false)
  public void testDeleteContact() throws Exception {
     app.getContactHelper().goToHome();
      if (! app.getContactHelper().isThereAContact()){
          app.getNavigationHelper().goToAddNewPage();
          app.getContactHelper().createContact(new ContactData("tester", "test", "96587321", "test@tes.com"));
      }
      List<ContactData> before = app.getContactHelper().getContactList();
     app.getContactHelper().selectContact(before.size() - 1);
     app.getContactHelper().deleteContact();
     app.getContactHelper().goToHome();
     List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() - 1);
      before.remove(before.size() - 1);
      Assert.assertEquals(after, before);
  }

}
