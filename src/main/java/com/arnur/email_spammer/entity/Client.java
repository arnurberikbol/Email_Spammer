package com.arnur.email_spammer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "clients_to_groups"
            , joinColumns = @JoinColumn(name = "clients_id")
            , inverseJoinColumns = @JoinColumn(name = "groups_id")
    )
//    private List<Group> groups;
    private List<Group> groups;

//    public void addClientToGroup(Group group) {
//        if (groups == null)
//            groups = new ArrayList<Group>();
//        groups.add(group);
//    }

    public void addClientToGroup(Group group) {
        if (groups == null)
            groups = new ArrayList<Group>();
        groups.add(group);
    }

    public void removeClientFromGroup(Group group) {
        groups.remove(group);
    }

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", groups=" + groups +
                '}';
    }
}
