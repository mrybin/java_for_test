package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTest  extends TestBase{
    @Test
    public void testContactEmails() {
        app.contact().goToHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFormEditForm)));

    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s)->! s.equals("")).collect(Collectors.joining("\n"));
    }

}
