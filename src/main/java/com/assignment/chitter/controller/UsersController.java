package com.assignment.chitter.controller;


import com.assignment.chitter.dto.UsersDTO;
import com.assignment.chitter.service.UsersService;
import com.assignment.chitter.util.ConstantUtility;
import com.assignment.chitter.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired(required=true)
    private UsersService usersService;

    @PostMapping("/signup")
    public Response signUp(@RequestBody UsersDTO usersDTO)throws Exception{
        return usersService.register(usersDTO);

    }

    @GetMapping("/signin")
    public String signIn(@RequestParam(value = "email_id") String emailId, @RequestParam(value = "password") String password) throws Exception{
        return usersService.login(emailId,password);
    }

    @GetMapping("/search")
    public Response searchUsers(@RequestParam(value = "users") String users)throws Exception {
        if (ConstantUtility.username.isEmpty()) {
            return new Response("Kindly Login Before searching any user");
        } else {
            return usersService.searchUsers(users);
        }
    }

}
