package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;

public class ContactModification extends TestBase{

    @Test
    public void testContactModification() {
        app.getContactHelper().goToHome();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToAddNewPage();
            app.getContactHelper().createContact(new ContactData("tester", "test", "96587321", "test@tes.com"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillAddrNewData(new ContactData("test", "tes2", "99987321", "test2@tes.com"));
        app.getContactHelper().submitModification();
        app.getContactHelper().goToHome();
    }
}
