package ru.fortesting.addressbook.tests;

import org.testng.annotations.*;
import ru.fortesting.addressbook.model.GroupData;
import ru.fortesting.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
      app.goTo().groupPage();
      Groups before = app.group().all();
      GroupData group = new GroupData().withName("qwe1").withFooter("asd").withHeader("zxc");
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Set<GroupData> after = app.group().all();
      assertThat(after, equalTo(
              before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
    @Test
    public void testBadGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("qwe1'").withFooter("asd").withHeader("zxc");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Set<GroupData> after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
