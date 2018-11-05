package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContact extends TestBase {

  @Test
  public void testDeleteContact() throws Exception {
     app.getNavigationHelper().goToHome();
     app.getNavigationHelper().selectData();
     app.getContactHelper().deleteContact();
     app.getNavigationHelper().goToHome();
  }

}
