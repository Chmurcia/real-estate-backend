package cloud.uwu.realestatebackend.services.user;

import cloud.uwu.realestatebackend.dtos.user.user.UserDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserPatchDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.exceptions.AlreadyExistException;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.user.UserMapper;
import cloud.uwu.realestatebackend.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserResponseDTO getUserById(UUID id) {
        User user = getUser(id);

        return userMapper.userToUserResponseDTO(user);
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new NotFoundException("User not found"));

        return userMapper.userToUserResponseDTO(user);
    }

    public void updateUser(UUID id, UserDTO userDTO) {
        User user = getUser(id);

        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new AlreadyExistException("User with the email already exists");
        }

        if (userDTO.getEmail() != null && StringUtils.hasText(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        } else {
            throw new NullException("Email is null");
        }
        if (userDTO.getPassword() != null && StringUtils.hasText(userDTO.getPassword())) {
            user.setPassword(userDTO.getPassword());
        } else {
            throw new NullException("Password is null");
        }

        userRepository.save(user);
    }

    public void patchUser(UUID id, UserPatchDTO userPatchDTO) {
        User user = getUser(id);

        if (userPatchDTO.getEmail() != null && StringUtils.hasText(userPatchDTO.getEmail())) {
            user.setEmail(userPatchDTO.getEmail());
        }
        if (userPatchDTO.getPassword() != null && StringUtils.hasText(userPatchDTO.getPassword())) {
            user.setPassword(userPatchDTO.getPassword());
        }

        userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        getUser(id);
        userRepository.deleteById(id);
    }

    private User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));
    }
}
