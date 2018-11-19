package ru.fortesting.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.fortesting.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;

public class ContactModification extends TestBase{

    @Test (enabled = false)
    public void testContactModification() {
        app.getContactHelper().goToHome();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToAddNewPage();
            app.getContactHelper().createContact(new ContactData("tester1", "test23", "96587321", "test@tes.com"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size()- 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"test", "tes2", "99987321", "test2@tes.com");
        app.getContactHelper().fillAddrNewData(contact);
        app.getContactHelper().submitModification();
        app.getContactHelper().goToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() );
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId=(c1, c2)-> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
