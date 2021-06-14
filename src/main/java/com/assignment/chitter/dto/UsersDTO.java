package com.assignment.chitter.dto;


import com.assignment.chitter.model.Users;
import com.assignment.chitter.util.AuthTokenUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersDTO {

    public Long id;

    private String username;

    private String emailId;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users toUser() {
        AuthTokenUtil auth = new AuthTokenUtil();
        Users users = new Users();
        users.setEmailId(getEmailId());
        users.setUsername(getUsername());
        users.setPassword(auth.encrypt(getPassword()));
        users.setDeleted(false);
        return users;
    }

    public Users getUsers(Long id){
        Users users = new Users();
        users.setId(id);
        return users;
    }
    public UsersDTO toUserDTO(Users users){
        UsersDTO dto = new UsersDTO();
        dto.setId(users.getId());
        dto.setEmailId(users.getEmailId());
        dto.setUsername(users.getEmailId());
        dto.setPassword(users.getPassword());
        return dto;
    }

    public List<UsersDTO> getListOfUsers(List<Users> users){
        List<UsersDTO> usersDTOS = new ArrayList<>();
        for(Users user : users){
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setId(user.getId());
            usersDTO.setUsername(user.getUsername());
            usersDTOS.add(usersDTO);

        }
        return usersDTOS;
    }
}
