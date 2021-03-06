package ru.fortesting.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;
import ru.fortesting.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteContact extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        app.contact().goToHome();
        if (app.db().contacts().size() ==0){
            app.goTo().addNewPage();
            app.contact().create(new ContactData().withFirstname("tester1").withLastname("test23").withMobilePhone("96587321").withEmail("test@tes.com"));
        }
    }
    @Test
    public void testDeleteContact() throws Exception {
      Contacts before = app.db().contacts();
      ContactData deletedContact = before.iterator().next();
      app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.without(deletedContact)));
  }

}
