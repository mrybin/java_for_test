package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;
import ru.fortesting.addressbook.model.Contacts;
import ru.fortesting.addressbook.model.GroupData;
import ru.fortesting.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveFromGroup extends TestBase{

    @Test
    public void testRemoveFromGroup(){
        Contacts before = app.db().contacts();
        ContactData moveContact = before.iterator().next();
        Groups groups=app.db().groups();
        boolean k=false;
        Groups groupsBefore= moveContact.getGroups();
        for (GroupData group:groups){
            if (moveContact.getGroups().contains(group)==false){
                removeFromGroup(moveContact,group);
                k=true;
                assertThat(moveContact.getGroups(), equalTo(groupsBefore.without(group)));
                break;
            }
        }
        if (k==false){
            GroupData group=groups.iterator().next();
            addToGroup(moveContact, group);
            Groups groupsAfter =app.db().groups();
            removeFromGroup(moveContact,group);
            assertThat(moveContact.getGroups(), equalTo(groupsBefore));
        }
    }

}
