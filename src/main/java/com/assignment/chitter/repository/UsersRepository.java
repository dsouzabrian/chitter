package com.assignment.chitter.repository;

import com.assignment.chitter.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT username FROM users where username= :username and is_deleted ='false'", nativeQuery = true)
    public boolean getUsername(String username);

    @Query(value = "SELECT email_id FROM users where email_id= :emailId and is_deleted ='false'", nativeQuery = true)
    public boolean getEmailId(String emailId);

    @Query(value = "SELECT * FROM users where email_id= :emaildId and password = :password and is_deleted ='false'", nativeQuery = true)
    public Users checkValidUser(String emaildId, String password);

    @Query(value = "SELECT * FROM users where username like %:user% and is_deleted ='false'", nativeQuery = true)
    public List<Users> checkListOfUsers(String user);

}
