package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{


    @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getNavigationHelper().selectData();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
