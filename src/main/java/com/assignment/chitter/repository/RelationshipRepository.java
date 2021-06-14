package com.assignment.chitter.repository;

import com.assignment.chitter.model.RelationshipsModel;
import com.assignment.chitter.model.Tweets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends JpaRepository<RelationshipsModel,Long> {
    @Query(value = "SELECT * FROM relationships where follower_id = :followerId and is_deleted ='false'", nativeQuery = true)
    public List<RelationshipsModel> getFollowedId(Long followerId);
}
