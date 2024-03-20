package ru.nsu.plodushcheva.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PeopleWrapper {
    @XmlElement(name = "person")
    private List<PersonType> people;

    public List<PersonType> getPeople() {
        return people;
    }

    public void setPeople(List<PersonType> people) {
        this.people = people;
    }
}
