package ru.nsu.plodushcheva;

import java.io.*;
import java.util.*;

import javax.xml.stream.XMLStreamException;
import static ru.nsu.plodushcheva.FileWriter.writeFile;

public class Main {
    public static void main(String[] args){
        try {
            List<Person> people = PeopleParser.parse("people.xml");
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
            }
        }
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
        System.out.println("Failed checks " + failedCheck.size());

        return new ArrayList<>(ids.values());
    }
}