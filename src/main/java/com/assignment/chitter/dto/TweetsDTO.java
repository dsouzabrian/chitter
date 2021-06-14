package com.assignment.chitter.dto;

import com.assignment.chitter.model.Tweets;
import com.assignment.chitter.model.Users;
import com.assignment.chitter.repository.UsersRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetsDTO {

    private Long id;

    private Long userId;

    private String content;

    private Timestamp updatedTime;

    private String username;

    public TweetsDTO(){

    }

    public TweetsDTO(Long id, Long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Tweets toTweets(){

        Tweets tweets = new Tweets();
        tweets.setContent(getContent());
        tweets.setDeleted(false);
        return tweets;
    }

    public TweetsDTO tweetsDTO(Tweets tweets){
        TweetsDTO tweetsDTO = new TweetsDTO();
        tweetsDTO.setContent(tweets.getContent());
        return tweetsDTO;
    }
    public List<TweetsDTO> tweetsDTOList(List<Tweets> tweets){
        List<TweetsDTO> tweetsDTOS = new ArrayList<TweetsDTO>();
        for(Tweets tweet : tweets) {
            TweetsDTO tweetsDTO = new TweetsDTO();
            tweetsDTO.setUsername(tweet.getUsers().getUsername());
            tweetsDTO.setContent(tweet.getContent());
            tweetsDTO.setUpdatedTime(tweet.getUpdatedAt());
            tweetsDTOS.add(tweetsDTO);
        }
        return tweetsDTOS;
    }
}
