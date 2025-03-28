package meddeb.aziz.itbs.mapper;

import meddeb.aziz.itbs.dto.UserDTO;
import meddeb.aziz.itbs.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    List<UserDTO> usersToUsersDTO(List<User> users);

    List<User> usersDTOToUsers(List<UserDTO> usersDTO);

}
