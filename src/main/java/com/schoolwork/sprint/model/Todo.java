package com.schoolwork.sprint.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long todoid;

    @Column(nullable = false)
    private String description;
    private String datetime;
    private Boolean completed = false;

    @ManyToOne //id
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public Todo() {
    }

    public Todo(String description, String datetime, Boolean completed, User user) {
        this.description = description;
        this.datetime = datetime;
        this.completed = completed;
        this.user = user;
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
