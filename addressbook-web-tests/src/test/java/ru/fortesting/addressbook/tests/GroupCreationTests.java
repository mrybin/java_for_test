package ru.fortesting.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.fortesting.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
      app.getNavigationHelper().gotoGroupPage();
      List<GroupData> before = app.getGroupHelper().getGroupList();
      GroupData group = new GroupData("qwe", "asd", "zxc");
      app.getGroupHelper().createGroup(group);
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size() + 1);

      int max=0;
      for (GroupData g: after) {
          if (g.getId()>max)
              max = g.getId();
      }
      group.setId(max);
      before.add(group);
  }

}
