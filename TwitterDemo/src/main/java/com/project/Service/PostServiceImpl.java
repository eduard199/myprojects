package com.project.Service;

import com.project.Dao.PostDAO;
import com.project.Exceptions.UserNotExisting;
import com.project.Model.DTO.PostCreationResponseDTO;
import com.project.Model.DTO.PostRequestDTO;
import com.project.Model.DTO.PostResponseDTO;
import com.project.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService
{
    private PostDAO postDAO;

    @Autowired
    public PostServiceImpl(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public PostCreationResponseDTO addPost(PostRequestDTO postRequestDTO, int userId)
    {
        Post post= postDAO.addPost(convertPostRequestDTOToPost(postRequestDTO),userId);
        return convertPostToPostCreationResponseDTO(post);
    }
    public List<PostResponseDTO> getOwnPosts(int userId, Timestamp timestamp) throws UserNotExisting
    {
        List<Post> posts=postDAO.getOwnPosts(userId,timestamp);
        List<PostResponseDTO> postsToReturn=new ArrayList<>();
        for(Post p:posts)
        {
            postsToReturn.add(convertPostToPostResponseDTO(p));
        }
        return postsToReturn;
    }

    @Override
    public List<PostResponseDTO> getFeed(int userId) throws UserNotExisting{
        List<Post> posts=postDAO.getFeed(userId);
        List<PostResponseDTO> postsToReturn=new ArrayList<>();
        for(Post p:posts)
        {
            postsToReturn.add(convertPostToPostResponseDTO(p));
        }
        return postsToReturn;
    }

    @Override
    public Post convertPostRequestDTOToPost(PostRequestDTO postRequestDTO) {
        return Post.builder()
                .withMessage(postRequestDTO.getMessage())
                .build();
    }

    @Override
    public PostResponseDTO convertPostToPostResponseDTO(Post post) {
        return PostResponseDTO.builder()
                .withId(post.getId())
                .withUserId(post.getUserId())
                .withMessage(post.getMessage())
                .withLikes(post.getLikes())
                .withReplies(post.getReplies())
                .build();
    }

    @Override
    public PostCreationResponseDTO convertPostToPostCreationResponseDTO(Post post) {
        return PostCreationResponseDTO.builder()
                .withId(post.getId())
                .withMessage(post.getMessage())
                .build();
    }
}
