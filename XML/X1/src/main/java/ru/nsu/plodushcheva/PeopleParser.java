package ru.nsu.plodushcheva;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PeopleParser {

    public static List<Person> parse(String path) throws XMLStreamException, FileNotFoundException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(path);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(inputStream);

        List<Person> personsData = new ArrayList<>();
        Person person = null;

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "people":
                        Attribute count = startElement.getAttributeByName(new QName("count"));
                        if (count != null) {
                            System.out.println("Amount of people: " + count.getValue());
                        }
                        break;
                    case "person":
                        person = new Person();
                        Attribute name = startElement.getAttributeByName(new QName("name"));
                        if (name != null) {
                            String[] fullName = name.getValue().trim().split("\\s+");
                            person.setFirstName(fullName[0]);
                            person.setLastName(fullName[1]);
                        }

                        Attribute id = startElement.getAttributeByName(new QName("id"));
                        if (id != null && !Objects.equals(id.getValue(), "UNKNOWN")) {
                            person.setId(id.getValue());
                        }

                        break;
                    case "id":
                        Attribute value = startElement.getAttributeByName(new QName("value"));
                        if (value != null && !Objects.equals(value.getValue(), "UNKNOWN")
                                && person != null) {
                            person.setId(value.getValue());
                        }
                        break;
                    case "fullname":
                    case "children":
                        break;
                    case "first", "firstname":
                        Attribute firstNameVal = startElement.getAttributeByName(new QName("value"));
                        if (person != null) {
                            if (firstNameVal != null) {
                                person.setFirstName(firstNameVal.getValue());
                            } else {
                                event = reader.nextEvent();
                                String firstName = event.asCharacters().getData();
                                person.setFirstName(firstName);
                            }
                        }
                        break;
                    case "family", "family-name":
                        event = reader.nextEvent();
                        String lastName = event.asCharacters().getData();
                        if (person != null) {
                            person.setLastName(lastName);
                        }
                        break;
                    case "surname":
                        Attribute valueSurname = startElement.getAttributeByName(new QName("value"));
                        if (valueSurname != null && person != null) {
                            person.setLastName(valueSurname.getValue());
                        }
                        break;
                    case "gender":
                        Attribute genderValue = startElement.getAttributeByName(new QName("value"));
                        if (person != null) {
                            if (genderValue != null) {
                                person.setGender(genderValue.getValue());
                            } else {
                                event = reader.nextEvent(); //? or just event
                                String gender = event.asCharacters().getData().trim();
                                if (Objects.equals(gender, "M")) {
                                    person.setGender("male");
                                } else if (Objects.equals(gender, "F")) {
                                    person.setGender("female");
                                } else {
                                    System.out.println("Unknown gender:" + gender);
                                }
                            }
                        }
                        break;
                    case "wife":
                        Attribute wifeValue = startElement.getAttributeByName(new QName("value"));
                        if (wifeValue != null && person != null) {
                            person.setSpouse(wifeValue.getValue());
                            person.setSpouseGender("female");
                        }
                        break;
                    case "husband":
                        Attribute husbandValue = startElement.getAttributeByName(new QName("value"));
                        if (husbandValue != null && person != null) {
                            person.setSpouse(husbandValue.getValue());
                            person.setSpouseGender("male");
                        }
                        break;
                    case "parent":
                        Attribute parentValue = startElement.getAttributeByName(new QName("value"));
                        if (parentValue != null && person != null
                                && !Objects.equals(parentValue.getValue(), "UNKNOWN")) {
                            person.addParent(parentValue.getValue());
                        }
                        break;
                    case "mother":
                        event = reader.nextEvent();
                        String[] mother = event.asCharacters().getData().trim().split("\\s+");
                        if (person != null) {
                            person.addParent(mother, "female");
                        }
                        break;
                    case "father":
                        event = reader.nextEvent();
                        String[] father = event.asCharacters().getData().trim().split("\\s+");
                        if (person != null) {
                            person.addParent(father, "male");
                        }
                        break;
                    case "children-number":
                        Attribute childrenValue = startElement.getAttributeByName(new QName("value"));
                        if (childrenValue != null && person != null) {
                            person.setChildrenCount(Integer.getInteger(childrenValue.getValue()));
                        }
                        break;
                    case "son":
                        Attribute sonId = startElement.getAttributeByName(new QName("id"));
                        if (sonId != null && person != null) {
                            person.addChild(sonId.getValue(), "male");
                        }
                        break;
                    case "daughter":
                        Attribute daughterId = startElement.getAttributeByName(new QName("id"));
                        if (daughterId != null && person != null) {
                            person.addChild(daughterId.getValue(), "female");
                        }
                        break;
                    case "siblings":
                        Attribute siblingsId = startElement.getAttributeByName(new QName("val"));
                        if (siblingsId != null && person != null) {
                            String[] ids = siblingsId.getValue().trim().split("\\s+");
                            for (String s : ids) {
                                person.addSibling(s);
                            }
                        }
                        break;
                    case "siblings-number":
                        Attribute siblingsCount = startElement.getAttributeByName(new QName("val"));
                        if (siblingsCount != null && person != null) {
                            person.setSiblingsCount(Integer.getInteger(siblingsCount.getValue()));
                        }
                        break;
                    case "sister":
                        event = reader.nextEvent();
                        String[] sister = event.asCharacters().getData().trim().split("\\s+");
                        if (person != null) {
                            person.addSibling(sister, "female");
                        }
                        break;
                    case "brother":
                        event = reader.nextEvent();
                        String[] brother = event.asCharacters().getData().trim().split("\\s+");
                        if (person != null) {
                            person.addSibling(brother, "male");
                        }
                        break;
                }
                if (event.isStartElement()) {
                    System.out.println( "tag: " + event.asStartElement().getName());
                }

            }
            else if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("person") && person != null) {
                    personsData.add(person);
                    if (person.getSpouse() != null) {
                        personsData.add(person.getSpouse());
                    }
                    personsData.addAll(person.getChildren());
                    personsData.addAll(person.getSiblings());
                    personsData.addAll(person.getParents());
                    person = null;
                    if (event.isEndElement()) {
                        System.out.println( "tagE: " + event.asEndElement().getName());
                    }
                }
                if (endElement.getName().getLocalPart().equals("people")) {
                    System.out.println("read xml file");
                    return personsData;
                }
            }
        }
        return personsData;
    }
}
