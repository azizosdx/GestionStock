package meddeb.aziz.itbs.service;

import meddeb.aziz.itbs.dto.UserDTO;

import java.util.List;

public interface IUserService {

    public void deleteUser(Long id) throws Exception;

    public UserDTO findUserById(Long id) throws Exception;

    public UserDTO findUserByEmail(String email) throws Exception;

    public UserDTO updateUser(Long id, UserDTO userDTO) throws Exception;

    public List<UserDTO> getAllUsers() throws Exception;

    public UserDTO createUser(UserDTO userDTO) throws Exception;

}
