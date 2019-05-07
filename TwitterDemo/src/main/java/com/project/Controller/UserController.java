package com.project.Controller;


import com.project.Exceptions.DuplicateIdAndFollowindUserId;
import com.project.Exceptions.DuplicateUsername;
import com.project.Exceptions.SameUserIdAndFollowingUserId;
import com.project.Exceptions.UsernameNotFound;
import com.project.Model.DTO.FollowResponseDTO;
import com.project.Model.DTO.UserRequestDTO;
import com.project.Model.DTO.UserResponseDTO;
import com.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(
            value="/",
            method= RequestMethod.POST,
            consumes="application/json",
            produces = "application/json")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO user) throws DuplicateUsername
    {
        return new ResponseEntity<>(this.userService.register(user),HttpStatus.CREATED);
    }


    @RequestMapping(
        value="/{userName}",
        method=RequestMethod.GET,
        consumes = "application/json",
        produces = "application/json")
    public ResponseEntity<List<UserResponseDTO>> findByUserName(@PathVariable("userName") String userName) throws UsernameNotFound
    {
        return new ResponseEntity<>(this.userService.searchUser(userName),HttpStatus.OK);
    }
    @RequestMapping(
            value = "/{followingUserId}/follow",
            method = RequestMethod.GET,
            consumes="application/json",
            produces = "application/json")
    public ResponseEntity<FollowResponseDTO> follow(@RequestHeader("userId") int userId, @PathVariable("followingUserId") int followingUserId) throws SameUserIdAndFollowingUserId, DuplicateIdAndFollowindUserId
    {
        return new ResponseEntity<>(this.userService.follow(userId,followingUserId), HttpStatus.CREATED);
    }

    @RequestMapping(
            value="/{unfollowingUserId}",
            method = RequestMethod.DELETE,
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<String> unfollow(@RequestHeader("userId") int userId,@PathVariable("unfollowingUserId") int unfollowingUserId)
    {
        return new ResponseEntity<>(this.userService.unfollow(userId,unfollowingUserId),HttpStatus.OK);
    }
    @RequestMapping(
            value="/{userName}/unregister",
            method=RequestMethod.DELETE,
            consumes="application/json",
            produces="application/json")
    public ResponseEntity<String> unregister(@PathVariable("userName") String userName)  throws UsernameNotFound
    {
        return new ResponseEntity<>(this.userService.unregister(userName),HttpStatus.OK);
    }
}
