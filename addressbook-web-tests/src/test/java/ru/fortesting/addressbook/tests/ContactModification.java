package ru.fortesting.addressbook.tests;

import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;

public class ContactModification extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHome();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillAddrNewData(new ContactData("test", "tes2", "99987321", "test2@tes.com"));
        app.getContactHelper().submitModification();
        app.getNavigationHelper().goToHome();
    }
}
