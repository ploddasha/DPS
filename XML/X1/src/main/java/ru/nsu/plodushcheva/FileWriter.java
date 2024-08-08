package ru.nsu.plodushcheva;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.plodushcheva.schema.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FileWriter {


    static void writeFile(List<Person> people) {
        PeopleType peopleObj = new PeopleType();
        for (Person person : people) {
            PersonType personType = convertToPersonType(person);
            peopleObj.getPerson().add(personType);
        }
        try {
            JAXBContext context = JAXBContext.newInstance(PeopleType.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(peopleObj, new File("peopleNew.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static PersonType convertToPersonType(Person person) {
        PersonType personType = new PersonType();

        personType.setId(person.getId());
        personType.setGender(person.getGender() != null ? person.getGender() : "");
        personType.setFirst(person.getFirstName());
        personType.setLast(person.getLastName());

        if (person.getSpouse() != null) {
            if (Objects.equals(person.getSpouse().getGender(), "female")) {
                personType.setWife(person.getSpouse().getId());
            } else if (Objects.equals(person.getSpouse().getGender(), "male")) {
                personType.setHusband(person.getSpouse().getId());
            } else {
                personType.setSpouse(person.getSpouse().getId());
            }
        }

        personType.setParents(convertToParentsType(person.getParents()));
        personType.setChildren(convertToChildrenType(person.getChildren()));
        personType.setSiblings(convertToSiblingsType(person.getSiblings()));

        return personType;
    }

    private static ParentsType convertToParentsType(List<Person> parents) {
        ParentsType parentsType = new ParentsType();
        parentsType.setCount(String.valueOf(parents.size()));
        for (Person parent : parents) {
            JAXBElement<String> parentElement;
            if (Objects.equals(parent.getGender(), "female")) {
                parentElement = new JAXBElement<>(new QName("mother"), String.class, parent.getId());
            } else {
                parentElement = new JAXBElement<>(new QName("father"), String.class, parent.getId());
            }
            parentsType.getContent().add(parentElement);
        }
        return parentsType;
    }

    private static ChildrenType convertToChildrenType(Set<Person> children) {
        ChildrenType childrenType = new ChildrenType();
        childrenType.setCount(String.valueOf(children.size()));
        for (Person child : children) {
            JAXBElement<String> childElement;
            if (Objects.equals(child.getGender(), "male")) {
                childElement = new JAXBElement<>(new QName("son"), String.class, child.getId());
            } else if (Objects.equals(child.getGender(), "female")) {
                childElement = new JAXBElement<>(new QName("daughter"), String.class, child.getId());
            } else {
                childElement = new JAXBElement<>(new QName("child"), String.class, child.getId());
            }
            childrenType.getContent().add(childElement);
        }
        return childrenType;
    }

    private static SiblingsType convertToSiblingsType(Set<Person> siblings) {
        SiblingsType siblingsType = new SiblingsType();
        siblingsType.setCount(String.valueOf(siblings.size()));
        for (Person sibling : siblings) {
            JAXBElement<String> siblingElement;
            if (Objects.equals(sibling.getGender(), "male")) {
                siblingElement = new JAXBElement<>(new QName("brother"), String.class, sibling.getId());
            } else if (Objects.equals(sibling.getGender(), "female")) {
                siblingElement = new JAXBElement<>(new QName("sister"), String.class, sibling.getId());
            } else {
                siblingElement = new JAXBElement<>(new QName("sibling"), String.class, sibling.getId());
            }
            siblingsType.getContent().add(siblingElement);
        }
        return siblingsType;
    }

    /*
    static void writeFile(List<Person> people) {
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

    */


}
