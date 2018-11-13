package ru.fortesting.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{


    @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("qwe", "asd", "zxc"));
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(0);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
      int after = app.getGroupHelper().getGroupCount();
      Assert.assertEquals(after, before - 1);
  }

}
