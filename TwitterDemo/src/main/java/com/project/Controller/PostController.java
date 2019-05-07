package com.project.Controller;

import com.project.Exceptions.UserNotExisting;
import com.project.Model.DTO.PostCreationResponseDTO;
import com.project.Model.DTO.PostRequestDTO;
import com.project.Model.DTO.PostResponseDTO;
import com.project.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            consumes="application/json",
            produces = "application/json")
    public ResponseEntity<PostCreationResponseDTO> post(@RequestBody PostRequestDTO postRequestDTO, @RequestHeader("userId") int userId)
    {
        return new ResponseEntity<>(postService.addPost(postRequestDTO,userId), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/newer/{timestamp}",
            method = RequestMethod.GET,
            consumes="application/json",
            produces = "application/json")
    public List<PostResponseDTO> getOwnPost(@RequestHeader("userId") int userId, @PathVariable("timestamp")Timestamp timestamp) throws UserNotExisting {
        return postService.getOwnPosts(userId,timestamp);
    }

    @RequestMapping(
            value = "/feed",
            method = RequestMethod.GET,
            consumes="application/json",
            produces = "application/json"
    )
    public List<PostResponseDTO> getFeed(@RequestHeader("userId") int userId) throws UserNotExisting
    {
        return postService.getFeed(userId);
    }

    @RequestMapping()
}
