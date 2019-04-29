package com.project.Controller;

import com.project.Exceptions.AlreadyLikedThisPost;
import com.project.Model.DTO.LikeResponseDTO;
import com.project.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            consumes="application/json",
            produces = "application/json")
    public ResponseEntity<LikeResponseDTO> like(@RequestHeader("userId") int userId, @RequestHeader("postId") int postId) throws AlreadyLikedThisPost
    {
        return new ResponseEntity<>(likeService.like(userId,postId), HttpStatus.CREATED);
    }
}
