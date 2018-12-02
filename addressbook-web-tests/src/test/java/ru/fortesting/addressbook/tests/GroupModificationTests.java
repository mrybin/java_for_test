package ru.fortesting.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.GroupData;
import ru.fortesting.addressbook.model.Groups;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("qwe").withFooter("asd").withHeader("zxc"));
        }
    }

    @Test
    public void testGroupModification(){
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifyGroup.getId()).withName("qwe").withFooter("asd").withHeader("zxc");
        app.group().modify(group);
        assertThat(app.contact().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));
    }


}
