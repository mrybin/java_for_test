package ru.fortesting.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.fortesting.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
      app.getNavigationHelper().gotoGroupPage();
      List<GroupData> before = app.getGroupHelper().getGroupList();
      GroupData group = new GroupData("qwe1", "asd", "zxc");
      app.getGroupHelper().createGroup(group);
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size() + 1);

    //  group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
      Comparator<? super GroupData> byId=(g1, g2)-> Integer.compare(g1.getId(),g2.getId());
      after.sort(byId);
      group.setId(after.get(after.size()-1).getId());
      before.add(group);
      before.sort(byId);
      Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after) );
  }

}
