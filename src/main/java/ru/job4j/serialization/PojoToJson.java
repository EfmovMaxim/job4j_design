package ru.job4j.serialization;


import org.json.JSONObject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class PojoToJson {
    public static void main(String[] args) {
        Person person = new Person("Ivan", "Ivanovich", 23);
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11",
                new String[]{"1@mail.ru", "2@yandex.ru"}, person);

        //JSONObject jsonPerson = new JSONObject(person);
        //System.out.println(jsonPerson.toString());

        JSONObject jsonObject = new JSONObject(contact);

        System.out.println(jsonObject.toString());
    }
}
