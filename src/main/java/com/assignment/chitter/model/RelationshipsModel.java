package com.assignment.chitter.model;

import javax.persistence.*;

@Entity
@Table(name="relationships")
public class RelationshipsModel extends RootModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", foreignKey = @ForeignKey(name = "followerId"))
    private Users followerId;

    @ManyToOne
    @JoinColumn(name = "followed_id", foreignKey = @ForeignKey(name = "followedId"))
    private Users followedId;

    public RelationshipsModel() {
    }

    public RelationshipsModel(Long id, Users followerId, Users followedId) {
        this.id = id;
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Users followerId) {
        this.followerId = followerId;
    }

    public Users getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Users followedId) {
        this.followedId = followedId;
    }
}
