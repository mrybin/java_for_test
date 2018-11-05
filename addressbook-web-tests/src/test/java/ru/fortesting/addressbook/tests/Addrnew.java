package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;

public class Addrnew  extends  TestBase{

  @Test
  public void testAddrnew() throws Exception {
    app.goToAddNewPage();
    app.fillAddrNewData(new ContactData("tester", "test", "96587321", "test@tes.com"));
    app.submitAddrNew();
    app.goToHomePage();
  }

}
