package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;

public class DeleteContact extends TestBase {

  @Test
  public void testDeleteContact() throws Exception {
     app.getContactHelper().goToHome();
      if (! app.getContactHelper().isThereAContact()){
          app.getNavigationHelper().goToAddNewPage();
          app.getContactHelper().createContact(new ContactData("tester", "test", "96587321", "test@tes.com"));
      }
   //  app.getGroupHelper().selectGroup();
     app.getContactHelper().deleteContact();
     app.getContactHelper().goToHome();
  }

}
