package com.arnur.email_spammer.entity;
import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "groups_id")
    private Group group;
    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @Column(name = "time")
    private String date;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String time) {
        this.date = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "group=" + group +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", time='" + date + '\'' +
                '}';
    }
}
