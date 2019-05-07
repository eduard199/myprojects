package com.project.Service;

import com.project.Dao.UserDAO;
import com.project.Exceptions.DuplicateIdAndFollowindUserId;
import com.project.Exceptions.DuplicateUsername;
import com.project.Exceptions.SameUserIdAndFollowingUserId;
import com.project.Exceptions.UsernameNotFound;
import com.project.Model.DTO.FollowResponseDTO;
import com.project.Model.DTO.UserRequestDTO;
import com.project.Model.DTO.UserResponseDTO;
import com.project.Model.Follow;
import com.project.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDbAdapter) {
        this.userDAO = userDbAdapter;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) throws DuplicateUsername
    {
        User user= userDAO.register(convertUserRequestDTOToUser(userRequestDTO));
        return convertUserToUserResponseDTO(user);
    }
    public List<UserResponseDTO> searchUser(String userName) throws UsernameNotFound
    {
        List<User> users=userDAO.searchUser(userName);
        List<UserResponseDTO> usersToReturn=new ArrayList<>();
        for(User u:users)
        {
            usersToReturn.add(convertUserToUserResponseDTO(u));
        }
        return usersToReturn;
    }

    @Override
    public FollowResponseDTO follow(int userId, int followingUserId) throws SameUserIdAndFollowingUserId, DuplicateIdAndFollowindUserId {
        Follow follow=userDAO.follow(userId,followingUserId);
        return convertFollowToFollowResponseDTO(follow);
    }

    @Override
    public String unfollow(int userId,int unfollowingUserId)
    {
        return userDAO.unfollow(userId,unfollowingUserId);
    }

    @Override
    public String unregister(String userName)  throws UsernameNotFound
    {
        return userDAO.unregister(userName);
    }

    @Override
    public User convertUserRequestDTOToUser(UserRequestDTO user)
    {
        return User.builder()
                .withUserName(user.getUserName())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .build();
    }

    @Override
    public UserResponseDTO convertUserToUserResponseDTO(User user)
    {
        return UserResponseDTO.builder()
                .withUserId(user.getUserId())
                .withUserName(user.getUserName())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withEmail(user.getEmail())
                .build();
    }
    public FollowResponseDTO convertFollowToFollowResponseDTO(Follow follow)
    {
        return FollowResponseDTO.builder()
                .withUserId(follow.getUserId())
                .withFollowingUserId(follow.getFollowingUserId())
                .build();
    }
}
