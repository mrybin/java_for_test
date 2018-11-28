package ru.fortesting.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;
import ru.fortesting.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModification extends TestBase{

    @BeforeMethod
    public void ensurePrecondition(){
        app.contact().goToHome();
        if (app.contact().all().size() ==0){
            app.goTo().addNewPage();
            app.contact().create(new ContactData().withFirstname("tester1").withLastname("test23").withMobilePhone("96587321").withEmail("test@tes.com"));
        }
    }
    @Test
    public void testContactModification() {

        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstname("test").withLastname("tes2").withMobilePhone("99987321").withEmail("test2@tes.com");
        app.contact().modify(contact);
        assertThat(app.group().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }


}
