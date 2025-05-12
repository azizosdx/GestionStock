package meddeb.aziz.itbs.service.Implements;


import meddeb.aziz.itbs.dto.UserDTO;
import meddeb.aziz.itbs.entity.User;
import meddeb.aziz.itbs.mapper.UserMapper;
import meddeb.aziz.itbs.repository.UserRepository;
import meddeb.aziz.itbs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    public void deleteUser(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("There is no such user");
        }
        userRepository.deleteById(id);
    }

    public UserDTO findUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("There is no such user");
        }
        return userMapper.userToUserDTO(user.get());
    }

   public UserDTO findUserByEmail(String email) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new Exception("There is no such user");
        }
        return userMapper.userToUserDTO(user.get());
    }



    public UserDTO updateUser(Long id, UserDTO userDTO) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new Exception("There is no such user");
        }
        User updatedUser = userMapper.userDTOToEntity(userDTO);
        updatedUser.setId(id);
        updatedUser = userRepository.save(updatedUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    public List<UserDTO> getAllUsers() throws Exception {
        List<User> userList = userRepository.findAll();

        List<UserDTO> responseList = new ArrayList<>();
        userList.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userMapper.userToUserDTO(user);
            responseList.add(userDTO);
        });
        return responseList;
    }

    public UserDTO createUser(UserDTO userDTO) throws Exception {
        if (userDTO.getEmail() == null) {
            throw new Exception("Email is null in entity!");
        }
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("User already exists");
        }
        User user = userMapper.userDTOToEntity(userDTO);
        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }

}
