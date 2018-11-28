package ru.fortesting.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    @Parameter(names = "-d", description = "Data format")
    public String format;

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
           if (format.equals("csv")) {
               saveAsCsv(contacts, new File(file));
           } else if (format.equals("json")) {
               saveAsJson(contacts, new File(file));
           } else {
               System.out.println("Unrecognized format" + format);
           }
       }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }


    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts){
            writer.write(String.format("%s;%s;%s;%s;%s\n",
                    contact.getLastname(),contact.getFirstname(),contact.getAddress(),contact.getEmail(),contact.getMobilePhone()));
        }
        writer.close();
    }

    private  List<ContactData> generatorContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i<count; i++ ){
            contacts.add(new ContactData().withLastname(String.format("Test %s",i))
                    .withFirstname(String.format("Tester %s",i)).withAddress(String.format("Moscow %s",i))
                    .withEmail(String.format("test%s@tester.ru",i)).withMobilePhone(String.format("3499%s",i)));
        }
        return contacts;
    }
}
