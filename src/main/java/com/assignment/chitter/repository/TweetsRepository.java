package com.assignment.chitter.repository;

import com.assignment.chitter.model.Tweets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetsRepository extends JpaRepository<Tweets,Long> {
    @Query(value = "SELECT * FROM tweets where user_id = :userId and is_deleted ='false'", nativeQuery = true)
    public List<Tweets> getMyTweets(Long userId);
}
