package com.assignment.chitter.controller;

import com.assignment.chitter.dto.UsersDTO;
import com.assignment.chitter.repository.RelationshipRepository;
import com.assignment.chitter.service.RelationshipService;
import com.assignment.chitter.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class RelationshipController {

    @Autowired
    private RelationshipService relationshipService;

    @PostMapping("/followUsers")
    public Response addUsers(@RequestBody List<UsersDTO> usersDTOList ) throws Exception{
        return relationshipService.addFollowers(usersDTOList);
    }

    @GetMapping("/findTweets")
    public Response findFollowedTweets(@RequestParam(value = "tweet") String tweet)throws Exception{
        return relationshipService.findTweets(tweet);
    }
}
