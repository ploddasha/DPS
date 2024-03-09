package ru.nsu.plodushcheva;

import java.util.*;

public class Person {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(String name) {
        this.spouse = new Person(name);
    }

    public void setSpouseGender(String gender) {
        this.spouse.setGender(gender);
    }

    public void addParent(String personId) {
        addParent(new Person(personId));
    }
    public void addParent(Person newParent) {
        if (parents.stream().noneMatch(parent ->
                Objects.equals(parent.getId(), newParent.getId()))
                && !Objects.equals(newParent.getId(), this.getId())) {
            parents.add(newParent);
            newParent.addChild(this);
        }
    }
    public List<Person> getParents() {
        return parents;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Set<Person> getChildren() {
        return children;
    }

    public Integer getSiblingsCount() {
        return siblingsCount;
    }

    public void setSiblingsCount(Integer siblingsCount) {
        this.siblingsCount = siblingsCount;
    }

    public Set<Person> getSiblings() {
        return siblings;
    }

    private String id;
    private String firstName;
    private String lastName;
    private String gender;

    private Person spouse;

    List<Person> parents = new ArrayList<>();

    private Integer childrenCount;
    private final Set<Person> children = new HashSet<>();

    private Integer siblingsCount;
    private final Set<Person> siblings = new HashSet<>();

    public Person() {
    }
    public Person(String id) {
        this.id = id;
    }

    //siblings
    public void addSibling(Person newSibling) {
        if (newSibling.siblingsCount == null) newSibling.siblingsCount = siblingsCount;
        if (siblingsCount == null) siblingsCount = newSibling.siblingsCount;

        if (siblings.stream().noneMatch(sibling ->
                Objects.equals(sibling.getId(), newSibling.getId()))
                && !Objects.equals(newSibling.getId(), this.getId())) {
            siblings.add(newSibling);
            newSibling.addSibling(this);
        }
    }

    public void addSibling(String id) {
        addSibling(new Person(id));
    }

    public void addSibling(String[] name, String gender) {
        Person sibling = new Person();
        sibling.setFirstName(name[0]);
        sibling.setLastName(name[1]);
        sibling.setGender(gender);
        addSibling(sibling);
    }

    public void addChild(Person newChild) {
        if (children.stream().noneMatch(child ->
                Objects.equals(child.getId(), newChild.getId()))
                && !Objects.equals(newChild.getId(), this.getId())) {
            children.add(newChild);
            newChild.addParent(this);
        }
    }

    public void addChild(String id, String gender) {
        Person child = new Person(id);
        child.setGender(gender);
        addChild(child);
    }


    public void addInfo(Person person) {
        if (person == this) return;

        if (person.getFirstName() != null)
            this.setFirstName(person.getFirstName());

        if (person.getLastName() != null)
            this.setLastName(person.getLastName());

        if (person.getGender() != null)
            this.setGender(person.getGender());

        if (person.getChildrenCount() != null)
            this.setChildrenCount(person.getChildrenCount());

        if (person.getSiblingsCount() != null)
            this.setChildrenCount(person.getSiblingsCount());

        for (Person sibling : person.getSiblings()) {
            sibling.getSiblings().remove(person);
            sibling.getSiblings().add(this);
            this.getSiblings().add(sibling);
        }

        if (person.getSpouse() != null) {
            this.spouse = person.getSpouse();
            this.spouse.spouse = this;
        }

        for (Person parent : person.parents) {
            parent.getChildren().remove(person);
            parent.getChildren().add(this);
        }

        for (Person child : person.getChildren()) {
            child.parents.remove(person);
            child.parents.add(this);
            this.getChildren().add(child);
        }

    }

    public boolean check() {
        if (this.spouse == this) {return false;}
        if (this.children.contains(this)) {
            return false;
        }
        if (this.parents.contains(this)) {
            return false;
        }
        if (this.siblings.contains(this)) {
            return false;
        }
        for (Person i : this.children) {
            if (this.parents.contains(i)) {
                return false;
            }
        }
        return true;
    }

}


