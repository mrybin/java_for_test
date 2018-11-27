package ru.fortesting.addressbook.tests;

import org.testng.annotations.*;
import ru.fortesting.addressbook.model.GroupData;
import ru.fortesting.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withFooter(split[1]).withHeader(split[2])});
            line=reader.readLine();
        }
        return list.iterator();
    }
  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
      app.goTo().groupPage();
      Groups before = app.group().all();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.group().all();
      assertThat(after, equalTo(
              before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
    @Test(enabled = false)
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
