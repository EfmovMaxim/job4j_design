package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.nio.file.Files;

@XmlRootElement(name = "contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    private int zipCode;
    @XmlAttribute
    private String phone;
    @XmlElementWrapper
    @XmlElement(name = "email")
    private String[] emails;
    private Person person;

    public Contact() {
        this(0, "", new String[]{}, new Person());
    }

    public Contact(int zipCode, String phone, String[] emails, Person person) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.emails = emails;
        this.person = person;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public String[] getEmails() {
        return emails;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11",
                new String[]{"1@mail.ru", "2@yandex.ru"}, new Person("Ivan", "Ivanovich", 23));
        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }


        Gson gson = new GsonBuilder().create();
        System.out.println("JSON:" + gson.toJson(contact));


        JAXBContext context = JAXBContext.newInstance(Contact.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(contact, writer);
            xml = writer.getBuffer().toString();
            System.out.println("XML:\n" + xml);
        }
    }
}