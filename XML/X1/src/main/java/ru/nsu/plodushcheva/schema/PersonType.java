package ru.nsu.plodushcheva.schema;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonType {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public String getHusband() {
        return husband;
    }

    public void setHusband(String husband) {
        this.husband = husband;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }

    public List<String> getSons() {
        return sons;
    }

    public void setSons(List<String> sons) {
        this.sons = sons;
    }

    public List<String> getDaughters() {
        return daughters;
    }

    public void setDaughters(List<String> daughters) {
        this.daughters = daughters;
    }

    public List<String> getSisters() {
        return sisters;
    }

    public void setSisters(List<String> sisters) {
        this.sisters = sisters;
    }

    public List<String> getBrothers() {
        return brothers;
    }

    public void setBrothers(List<String> brothers) {
        this.brothers = brothers;
    }

    @XmlElement
    private String id;
    @XmlElement
    private String gender;
    @XmlElement
    private String first;
    @XmlElement
    private String last;
    @XmlElement
    private String wife;
    @XmlElement
    private String husband;
    @XmlElement
    private String spouse;
    @XmlElementWrapper(name = "parents")
    @XmlElement(name = "parent")
    private List<String> parents;
    @XmlElementWrapper(name = "children")
    @XmlElement(name = "son")
    private List<String> sons;
    @XmlElementWrapper(name = "children")
    @XmlElement(name = "daughter")
    private List<String> daughters;
    @XmlElementWrapper(name = "siblings")
    @XmlElement(name = "sister")
    private List<String> sisters;
    @XmlElementWrapper(name = "siblings")
    @XmlElement(name = "brother")
    private List<String> brothers;

}

