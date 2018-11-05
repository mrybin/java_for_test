package ru.fortesting.addressbook;

import org.testng.annotations.Test;

public class DeleteContact extends TestBase {

  @Test
  public void testDeleteContact() throws Exception {
     goToHome();
     selectData();
     deleteContact();
     goToHome();
  }

}
