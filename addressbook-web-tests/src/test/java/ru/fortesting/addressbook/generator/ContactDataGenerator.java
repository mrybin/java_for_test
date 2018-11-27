package ru.fortesting.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.fortesting.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcommander = new JCommander(generator);
        try {
            jcommander.parse(args);
        } catch (ParameterException ex) {
            jcommander.usage();
            return;
        }
        generator.run();

    }


    private void run() throws IOException {
        List<ContactData> contacts = generatorContacts(count);
        save(contacts,new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts){
            writer.write(String.format("%s;%s;%s;%s;%s\n",
                    contact.getLastname(),contact.getFirstname(),contact.getAddress(),contact.getEmail(),contact.getHomePhone()));
        }
        writer.close();
    }

    private  List<ContactData> generatorContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i<count; i++ ){
            contacts.add(new ContactData().withLastname(String.format("Test %s",i))
                    .withFirstname(String.format("Tester %s",i)).withAddress(String.format("Moscow %s",i))
                    .withEmail(String.format("test%s@tester.ru",i)).withHomePhone(String.format("3499%s",i)));
        }
        return contacts;
    }
}
