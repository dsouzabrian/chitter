package com.assignment.chitter.service;

import com.assignment.chitter.dto.TweetsDTO;
import com.assignment.chitter.dto.UsersDTO;
import com.assignment.chitter.model.Tweets;
import com.assignment.chitter.model.Users;
import com.assignment.chitter.repository.TweetsRepository;
import com.assignment.chitter.util.ConstantUtility;
import com.assignment.chitter.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetsService {
    @Autowired
    private TweetsRepository tweetsRepository;


    public Response addNewTweet(TweetsDTO tweet){
        try{
            tweet.setUserId(ConstantUtility.getUserId);
            int tweetLength = tweet.getContent().length();
            if(tweetLength>=151){
                new Response("Error ", "Please post characters less than 150");
            }
            Tweets tweets = tweet.toTweets();
            UsersDTO userDTO = new UsersDTO();
            Users user = userDTO.getUsers(ConstantUtility.getUserId);
            tweets.setUsers(user);
            tweetsRepository.save(tweets);
            return new Response("Success", new TweetsDTO().tweetsDTO(tweets),"Tweet added successfully");
        }catch(Exception ex) {
            if(ex instanceof DataIntegrityViolationException) {
                return new Response("Failed", null, ex.getMessage());
            } else {
                return new Response("Failed", null, ex.getMessage());
            }
        }
    }

    public Response getMyPreviousTweet(){
        try{
            List<Tweets> tweets = tweetsRepository.getMyTweets(ConstantUtility.getUserId);
            return new Response("SUCCESS",new TweetsDTO().tweetsDTOList(tweets) ,"Your tweets!");

        }catch(Exception ex) {
            if (ex.getMessage().contains("tweets")) {
                return new Response("OH!","You don't have any tweets posted");
            }else{
                return new Response("Something went wrong "+ex.getMessage());
            }
        }
    }

}
