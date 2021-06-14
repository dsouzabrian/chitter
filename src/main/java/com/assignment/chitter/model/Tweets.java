package com.assignment.chitter.model;

import javax.persistence.*;

@Entity
@Table(name="tweets")
public class Tweets extends RootModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "userId"))
    private Users users;

    @Column(name = "content", nullable = false, length = 150)
    private String content;

    public Tweets() {
    }

    public Tweets(Long id, Users users, String content) {
        this.id = id;
        this.users = users;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
