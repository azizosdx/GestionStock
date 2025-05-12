package meddeb.aziz.itbs.mapper;

import meddeb.aziz.itbs.dto.UserDTO;
import meddeb.aziz.itbs.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface UserMapper {


    UserDTO userToUserDTO(User user);

    User userDTOToEntity(UserDTO userDTO);

    List<UserDTO> usersToUsersDTO(List<User> users);

    List<User> usersDTOToUsersEntity(List<UserDTO> usersDTO);

}
