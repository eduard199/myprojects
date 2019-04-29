package com.project.Service;

import com.project.Dao.LikeDAO;
import com.project.Exceptions.AlreadyLikedThisPost;
import com.project.Model.DTO.LikeResponseDTO;
import com.project.Model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService
{
    private LikeDAO likeDAO;

    @Autowired
    public LikeServiceImpl(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    @Override
    public LikeResponseDTO like(int userId, int postId) throws AlreadyLikedThisPost {
        Like like=likeDAO.like(userId,postId);
        return convertLikeToLikeResponseDTO(like);
    }

    @Override
    public LikeResponseDTO convertLikeToLikeResponseDTO(Like like) {
        return LikeResponseDTO.builder()
                .withUserId(like.getUserId())
                .withPostId(like.getPostId())
                .build();
    }
}
