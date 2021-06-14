package com.assignment.chitter.service;

import com.assignment.chitter.controller.TweetsController;
import com.assignment.chitter.dto.UsersDTO;
import com.assignment.chitter.model.Users;
import com.assignment.chitter.repository.UsersRepository;
import com.assignment.chitter.util.AuthTokenUtil;
import com.assignment.chitter.util.ConstantUtility;
import com.assignment.chitter.util.Response;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Response register(UsersDTO usersDTO){
        try{
            if(usersDTO.getUsername().isEmpty() || usersDTO.getUsername()== null || usersDTO.getUsername()=="" ){
                return new Response("ERROR", null, "Username is required!");
            }

            if(usersDTO.getEmailId().isEmpty() || usersDTO.getEmailId()== null || usersDTO.getEmailId()=="" ){
                return new Response("ERROR", null, "Email id is required!");
            }

            if(usersDTO.getPassword().isEmpty() || usersDTO.getPassword()== null || usersDTO.getPassword()=="" ){
                return new Response("ERROR", null, "Password is required!");
            }

            Users users = usersDTO.toUser();
            usersRepository.save(users);
            return new Response("SUCCESS","Welcome "+ usersDTO.getUsername() +" to Chitter App");
        }catch (Exception ex){
            if (ex instanceof DataIntegrityViolationException) {
                if (ex.getMessage().contains("uk_users_username")) {
                    return new Response("FAILED", null, "Username has already been used");
                } else if (ex.getMessage().contains("uk_users_email_id")) {
                    return new Response("FAILED", null, "Email Id has already been used");
                } else {
                    return new Response("FAILED", null, ex.getMessage());
                }
            } else {
                return new Response("FAILED", null, ex.getMessage());
            }
        }
    }

    public String login(String emaildId, String password) {
        String username = "Something went wrong";
        try {
            if (emaildId.isEmpty() || emaildId == null || emaildId == "") {
                return ("ERROR, Email id is required!");
            }

            if (password.isEmpty() || password == null || password == "") {
                return ("ERROR, Password is required!");
            }
            AuthTokenUtil auth = new AuthTokenUtil();
            String getEncryptedPassword = auth.encrypt(password);
            Users user = usersRepository.checkValidUser(emaildId, getEncryptedPassword);
            ConstantUtility.username = user.getUsername();
            ConstantUtility.getUserId = user.getId();
            username = user.getUsername();
            return ("Hi, "+username+"!");
        } catch (Exception ex) {
            return ("Email id or Password is incorrect");
        }

        }
    public Response searchUsers(String user){
        try{
            if(user.isEmpty()){
                return new Response("Please enter your input");
            }
            List<Users> users = usersRepository.checkListOfUsers(user);
            return new Response("Success",new UsersDTO().getListOfUsers(users)," Hurrah!");
        }catch(Exception ex){
            return new Response("Sorry!"," No, User found");
        }
    }
}
