package com.assignment.chitter.controller;

import com.assignment.chitter.dto.TweetsDTO;
import com.assignment.chitter.dto.UsersDTO;
import com.assignment.chitter.service.TweetsService;
import com.assignment.chitter.util.ConstantUtility;
import com.assignment.chitter.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class TweetsController {

    @Autowired(required=true)
    private TweetsService tweetsService;

    public static String username = "";

    @PostMapping("/postMyTweet")
    public Response addPost(@RequestBody TweetsDTO tweet)throws Exception{
        if(ConstantUtility.username.isEmpty()){
             return new Response("Kindly Login Before Posting Tweet");
        }else {

            return tweetsService.addNewTweet(tweet);
        }
    }

    @GetMapping("/getMyTweet")
    public Response myTweet()throws  Exception {
        if (ConstantUtility.username.isEmpty()) {
            return new Response("Kindly Login Before Posting Tweet");
        } else {
            return tweetsService.getMyPreviousTweet();
        }
    }
}
