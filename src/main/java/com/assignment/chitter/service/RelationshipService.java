package com.assignment.chitter.service;

import com.assignment.chitter.dto.TweetsDTO;
import com.assignment.chitter.dto.UsersDTO;
import com.assignment.chitter.model.RelationshipsModel;
import com.assignment.chitter.model.Tweets;
import com.assignment.chitter.model.Users;
import com.assignment.chitter.repository.RelationshipRepository;
import com.assignment.chitter.repository.TweetsRepository;
import com.assignment.chitter.util.ConstantUtility;
import com.assignment.chitter.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationshipService {

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Autowired
    private  TweetsRepository tweetsRepository;

    public Response addFollowers(List<UsersDTO> getListOfUsers){
        try{

            if(getListOfUsers.isEmpty()){
                return new Response("Error","Please add the users you want to follow");
            }
            UsersDTO userDTOS = new UsersDTO();
            Users userFollowing = userDTOS.getUsers(ConstantUtility.getUserId);
            for(UsersDTO id : getListOfUsers){
                RelationshipsModel relationshipsModel = new RelationshipsModel();
                UsersDTO userDTO = new UsersDTO();
                Users userFollowed = userDTO.getUsers(id.getId());
                relationshipsModel.setFollowerId(userFollowing);
                relationshipsModel.setFollowedId(userFollowed);
                relationshipsModel.setDeleted(false);
                relationshipRepository.save(relationshipsModel);

            }
            return new Response("Success",getListOfUsers,"You have succesfully added the above users in your follower's list");


        }catch(Exception ex){
            return new Response("Something went wrong : "+ex.getMessage());
        }
    }

    public Response findTweets(String tweet){
        try{
            if(tweet.isEmpty()){
                new Response("Sorry!","Please enter your input");
            }
            List<RelationshipsModel> relationshipsModelList = relationshipRepository.getFollowedId(ConstantUtility.getUserId);
            List<List<TweetsDTO>> tweetsList = new ArrayList<>();
            for(RelationshipsModel relationshipsModel : relationshipsModelList){
                List<Tweets> tweets = tweetsRepository.getMyTweets(relationshipsModel.getFollowedId().getId());
                if(tweets.isEmpty())
                    continue;

                tweetsList.add(new TweetsDTO().tweetsDTOList(tweets));
            }
            if(tweetsList.isEmpty()) {
                return new Response("Error!", "No tweets available");
            }else{
                return new Response("Success!", tweetsList,"Tweets posted");
            }
        }catch(Exception ex){
            return new Response("Error!","Something went wrong");
        }
    }

}
