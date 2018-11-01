package ru.fortesting.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("qwe", "asd", "zxc"));
    submitGroupCreation();
    returnToGroupPage();

  }

}
