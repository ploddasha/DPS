package ru.nsu.plodushcheva;

import java.io.*;
import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.plodushcheva.schema.PeopleWrapper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.bind.*;

public class Main {
    public static void main(String[] args){
        try {
            List<Person> people = PeopleParser.parse("people.xml");
            System.out.println(people.size());
            List<Person> normalized = normalize(people);
            writeFile(normalized);
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Person> normalize(List<Person> data) {
        Map<String, Person> ids = new HashMap<>();
        List<Person> temp = new ArrayList<>();

        for (Person person : data) {
            if (person.getId() != null) {
                if (ids.containsKey(person.getId())) {
                    ids.get(person.getId()).addInfo(person);
                } else {
                    ids.put(person.getId(), person);
                }
            } else {
                temp.add(person);
            }
        }
        System.out.println(ids.size());

        //add info from people without id to people with id
        List<Person> withoutIds = new ArrayList<>();
        for (Person i : temp) {
            List<Person> list = ids.values().parallelStream().
                    filter(x ->
                            Objects.equals(x.getFirstName(), i.getFirstName()) &&
                                    Objects.equals(x.getLastName(), i.getLastName())).toList();
            if (list.size() == 1) {
                ids.get(list.get(0).getId()).addInfo(i);
            } else if (list.size() < 1) {
                withoutIds.add(i);
            } // else тезки
        }
        System.out.println("withoutIds" + withoutIds.size());

        for (Person person : ids.values()) {
            if (person.getParents().size() > 1) {
                person.cleanParents();
            }
        }

        List<Person> failedCheck = new ArrayList<>();
        for (Person person : ids.values()) {
            if (!person.check()) {
                failedCheck.add(person);
                System.out.println(person);
            }
        }
        System.out.println("failed check" + failedCheck.size());

        return new ArrayList<>(ids.values());
    }


    /*
    private static void writeFile(List<Person> people) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PeopleWrapper wrapper = new PeopleWrapper();
            wrapper.setPeople(people);

            marshaller.marshal(wrapper, new File("peopleNew2.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    } */



    private static void writeFile(List<Person> people) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        Document doc = docBuilder.newDocument();
        Element peopleElement = doc.createElement("people");
        peopleElement.setAttribute("count", String.valueOf(people.size()));

        doc.appendChild(peopleElement);

        for (Person person : people) {
            Element personElement = createPersonElement(doc, person);
            peopleElement.appendChild(personElement);
        }

        try (FileOutputStream output =
                     new FileOutputStream("peopleNew.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeXml(Document doc, OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    private static Element createPersonElement(Document doc, Person person) {
        Element personElement = doc.createElement("person");

        // Add id element
        Element idElement = doc.createElement("id");
        idElement.appendChild(doc.createTextNode(person.getId()));
        personElement.appendChild(idElement);

        // Add gender element
        Element genderElement = doc.createElement("gender");
        genderElement.appendChild(doc.createTextNode(person.getGender() != null ? person.getGender() : ""));
        personElement.appendChild(genderElement);

        // Add first name element
        Element firstNameElement = doc.createElement("first");
        firstNameElement.appendChild(doc.createTextNode(person.getFirstName()));
        personElement.appendChild(firstNameElement);

        // Add last name element
        Element lastNameElement = doc.createElement("last");
        lastNameElement.appendChild(doc.createTextNode(person.getLastName()));
        personElement.appendChild(lastNameElement);

        // Add spouse element
        String tag = "spouse";
        String id = "";
        if (person.getSpouse() != null) {
            if (Objects.equals(person.getSpouse().getGender(), "female")) {
                tag = "wife";
            } else if (Objects.equals(person.getSpouse().getGender(), "male")){
                tag = "husband";
            }
            id = person.getSpouse().getId();
        }
        Element spouseElement = doc.createElement(tag);
        spouseElement.appendChild(doc.createTextNode(id));
        personElement.appendChild(spouseElement);


        // Add parents element
        Element parentsElement = doc.createElement("parents");
        parentsElement.setAttribute("count", String.valueOf(person.getParents().size()));
        for (Person parent : person.getParents()) {
            String parentTag = "parent";
            if (Objects.equals(parent.getGender(), "female")) {
                parentTag = "mother";
            } else if (Objects.equals(parent.getGender(), "male")) {
                parentTag = "father";
            }
            Element parentElement = doc.createElement(parentTag);
            parentElement.appendChild(doc.createTextNode(parent.getId()));
            parentsElement.appendChild(parentElement);
        }
        personElement.appendChild(parentsElement);

        // Add children element
        Element childrenElement = doc.createElement("children");
        childrenElement.setAttribute("count", String.valueOf(person.getChildren().size()));
        for (Person child : person.getChildren()) {
            String childTag = "child";
            if (Objects.equals(child.getGender(), "female")) {
                childTag = "daughter";
            } else if (Objects.equals(child.getGender(), "male")){
                childTag = "son";
            }
            Element childElement = doc.createElement(childTag);
            childElement.appendChild(doc.createTextNode(child.getId()));
            childrenElement.appendChild(childElement);
        }
        personElement.appendChild(childrenElement);

        // Add siblings element
        Element siblingsElement = doc.createElement("siblings");
        siblingsElement.setAttribute("count", String.valueOf(person.getSiblings().size()));
        for (Person sibling : person.getSiblings()) {
            String siblingTag = "sibling";
            if (Objects.equals(sibling.getGender(), "female")) {
                siblingTag = "sister";
            } else if (Objects.equals(sibling.getGender(), "male")) {
                siblingTag = "brother";
            }
            Element siblingElement = doc.createElement(siblingTag);
            siblingElement.appendChild(doc.createTextNode(sibling.getId()));
            siblingsElement.appendChild(siblingElement);
        }
        personElement.appendChild(siblingsElement);

        return personElement;
    }



}