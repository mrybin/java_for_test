package ru.fortesting.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;
import ru.fortesting.addressbook.model.Contacts;
import ru.fortesting.addressbook.model.GroupData;
import ru.fortesting.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddToGroup extends TestBase{

   @BeforeMethod
    public void ensurePrecondition(){
        app.contact().goToHome();
        if (app.db().contacts().size() ==0){
            app.goTo().addNewPage();
            app.contact().create(new ContactData().withFirstname("tester1").withLastname("test23").withMobilePhone("96587321").withEmail("test@tes.com"));
        }
    }
    @Test
    public void testAddToGroup() {
        Contacts before = app.db().contacts();
        ContactData moveContact = before.iterator().next();
        Groups groups=app.db().groups();
        boolean k=false;
        Groups groupsBefore= moveContact.getGroups();
        for (GroupData group:groups){
            if (moveContact.getGroups().contains(group)==false){
                app.contact().addToGroup(moveContact, group);
                Contacts contacts = app.db().getSimpleContact(moveContact.getId());
                Groups afterMove=contacts.iterator().next().getGroups();
                assertThat(afterMove, equalTo(groupsBefore.withAdded(group)));
                k=true;
                break;
            }
        }
        if (k==false){
            GroupData newgroup=new GroupData().withName("EEE").withFooter("WWWW").withHeader("QQQQ");
            app.group().create(newgroup);
            app.contact().addToGroup(moveContact, newgroup);
            Contacts contacts = app.db().getSimpleContact(moveContact.getId());
            Groups afterMove=contacts.iterator().next().getGroups();
            assertThat(afterMove, equalTo(groupsBefore.
                    withAdded(newgroup.withId(afterMove.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
        }

    }


}
