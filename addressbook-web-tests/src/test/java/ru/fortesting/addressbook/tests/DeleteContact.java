package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContact extends TestBase {

  @Test
  public void testDeleteContact() throws Exception {
     app.goToHome();
     app.selectData();
     app.deleteContact();
     app.goToHome();
  }

}
