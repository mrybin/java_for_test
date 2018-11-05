package ru.fortesting.addressbook;

import org.testng.annotations.Test;

public class Addrnew  extends  TestBase{

  @Test
  public void testAddrnew() throws Exception {
    goToAddNewPage();
    fillAddrNewData(new ContactData("tester", "test", "96587321", "test@tes.com"));
    submitAddrNew();
    goToHomePage();
  }

}
