package ru.fortesting.addressbook.tests;

import org.testng.annotations.*;
import ru.fortesting.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("qwe", "asd", "zxc"));
    app.submitGroupCreation();
    app.returnToGroupPage();

  }

}
