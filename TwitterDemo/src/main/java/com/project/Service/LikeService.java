package com.project.Service;

import com.project.Exceptions.AlreadyLikedThisPost;
import com.project.Model.DTO.LikeResponseDTO;
import com.project.Model.Like;

public interface LikeService
{
    LikeResponseDTO like(int userId, int postId) throws AlreadyLikedThisPost;
    LikeResponseDTO convertLikeToLikeResponseDTO(Like like);
}
