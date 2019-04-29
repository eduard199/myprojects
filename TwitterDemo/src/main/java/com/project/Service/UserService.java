package com.project.Service;

import com.project.Exceptions.DuplicateIdAndFollowindUserId;
import com.project.Exceptions.DuplicateUsername;
import com.project.Exceptions.SameUserIdAndFollowingUserId;
import com.project.Exceptions.UsernameNotFound;
import com.project.Model.DTO.FollowResponseDTO;
import com.project.Model.DTO.UserRequestDTO;
import com.project.Model.DTO.UserResponseDTO;
import com.project.Model.User;

import java.util.List;

public interface UserService
{
    UserResponseDTO register(UserRequestDTO user) throws DuplicateUsername;
    List<UserResponseDTO> searchUser(String userName) throws UsernameNotFound;
    FollowResponseDTO follow(int userId, int followingUserId) throws SameUserIdAndFollowingUserId, DuplicateIdAndFollowindUserId;
    User convertUserRequestDTOToUser(UserRequestDTO user);
    UserResponseDTO convertUserToUserResponseDTO(User user);
}
