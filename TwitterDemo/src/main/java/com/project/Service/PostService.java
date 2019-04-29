package com.project.Service;

import com.project.Exceptions.UserNotExisting;
import com.project.Model.DTO.PostCreationResponseDTO;
import com.project.Model.DTO.PostRequestDTO;
import com.project.Model.DTO.PostResponseDTO;
import com.project.Model.Post;

import java.sql.Timestamp;
import java.util.List;

public interface PostService {

    PostCreationResponseDTO addPost(PostRequestDTO postRequestDTO, int userId);
    List<PostResponseDTO> getOwnPosts(int userId, Timestamp timestamp) throws UserNotExisting;
    List<PostResponseDTO> getFeed(int userId) throws UserNotExisting;
    Post convertPostRequestDTOToPost(PostRequestDTO postRequestDTO);
    PostResponseDTO convertPostToPostResponseDTO(Post post);
    PostCreationResponseDTO convertPostToPostCreationResponseDTO(Post post);
}
