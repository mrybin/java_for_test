package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;

public class ContactPhoneTest extends TestBase{

    @Test
    public void testContactPhones() {
        app.contact().goToHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
    }
}
