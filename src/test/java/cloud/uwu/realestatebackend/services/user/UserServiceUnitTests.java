package cloud.uwu.realestatebackend.services.user;

import cloud.uwu.realestatebackend.dtos.user.user.UserDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserPatchDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserResponseDTO;
import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagResponseDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.UserFlag;
import cloud.uwu.realestatebackend.entities.user.UserRole;
import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.user.UserMapper;
import cloud.uwu.realestatebackend.repositories.user.UserFlagRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceIntegrationTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserFlagService userFlagService;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private UserFlagRepository userFlagRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById() {
        UUID id = UUID.randomUUID();
        User user = User.builder()
                .id(id)
                .email("example@mail.com")
                .password("example123")
                .userFlag(UserFlag.builder().id(UUID.randomUUID()).build())
                .userRole(UserRole.builder().id(UUID.randomUUID()).build())
                .build();

        UserFlagResponseDTO userFlagResponseDTO = UserFlagResponseDTO.builder()
                .id(UUID.randomUUID())
                .isVerified(false)
                .isMuted(false)
                .isBanned(false)
                .build();

        UserRoleResponseDTO userRoleResponseDTO = UserRoleResponseDTO.builder()
                .id(UUID.randomUUID())
                .role(Role.USER)
                .build();


        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userFlagService.getUserFlagById(any(UUID.class)))
                .thenReturn(userFlagResponseDTO);
        when(userRoleService.getUserRoleById(any(UUID.class)))
                .thenReturn(userRoleResponseDTO);

        UserResponseDTO userResponseDTO = userService.getUserById(id);

        assertThat(userResponseDTO).isNotNull();
        assertEquals(user.getId(), userResponseDTO.getId());
        assertEquals(userResponseDTO.getUserRoleId(), userRoleResponseDTO.getId());
        assertEquals(userResponseDTO.getUserFlagId(), userFlagResponseDTO.getId());

    }

    @Test
    void getUserByEmail() {
        UUID id = UUID.randomUUID();
        User user = User.builder()
                .id(id)
                .email("example@mail.com")
                .password("example123")
                .userFlag(UserFlag.builder().id(UUID.randomUUID()).build())
                .userRole(UserRole.builder().id(UUID.randomUUID()).build())
                .build();

        UserFlagResponseDTO userFlagResponseDTO = UserFlagResponseDTO.builder()
                .id(UUID.randomUUID())
                .isVerified(false)
                .isMuted(false)
                .isBanned(false)
                .build();

        UserRoleResponseDTO userRoleResponseDTO = UserRoleResponseDTO.builder()
                .id(UUID.randomUUID())
                .role(Role.USER)
                .build();

        when(userRepository.findUserByEmail(any(String.class)))
                .thenReturn(Optional.of(user));
        when(userFlagService.getUserFlagById(any(UUID.class)))
                .thenReturn(userFlagResponseDTO);
        when(userRoleService.getUserRoleById(any(UUID.class)))
                .thenReturn(userRoleResponseDTO);

        UserResponseDTO userResponseDTO = userService.getUserByEmail(user.getEmail());

        assertThat(userResponseDTO).isNotNull();
        assertEquals(user.getEmail(), userResponseDTO.getEmail());
        assertEquals(userResponseDTO.getUserRoleId(), userRoleResponseDTO.getId());
        assertEquals(userResponseDTO.getUserFlagId(), userFlagResponseDTO.getId());
    }

    @Test
    void createUser() {
        UUID userFlagId = UUID.randomUUID();
        UUID userRoleId = UUID.randomUUID();

        UserFlag userFlag = UserFlag.builder()
                .id(userFlagId)
                .isVerified(false)
                .isMuted(false)
                .isBanned(false)
                .build();

        UserRole userRole = UserRole.builder()
                .id(userRoleId)
                .role(Role.USER)
                .build();

        User user = User.builder()
                .email("example@mail.com")
                .password("pass123")
                .build();

        UserDTO userDTO = UserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(UUID.randomUUID())
                .email(user.getEmail())
                .userFlagId(userFlagId)
                .userRoleId(userRoleId)
                .build();

        when(userMapper.userDTOToUser(any(UserDTO.class)))
                .thenReturn(user);

        when(userFlagRepository.saveAndFlush(any(UserFlag.class)))
                .thenReturn(userFlag);

        when(userRoleRepository.saveAndFlush(any(UserRole.class)))
                .thenReturn(userRole);

        when(userMapper.userToUserResponseDTO(any(User.class)))
                .thenReturn(userResponseDTO);

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserResponseDTO createdUser = userService
                .createUser(userDTO);

        verify(userFlagRepository).saveAndFlush(any(UserFlag.class));
        verify(userRoleRepository).saveAndFlush(any(UserRole.class));
        verify(userMapper).userToUserResponseDTO(any(User.class));
        verify(userRepository).save(any(User.class));

        assertThat(createdUser).isNotNull();
        assertEquals(createdUser.getEmail(), user.getEmail());
        assertEquals(createdUser.getUserFlagId(), userFlag.getId());
        assertEquals(createdUser.getUserRoleId(), userRole.getId());
    }

    @Test
    void updateUser() {
        User user = User.builder()
                .email("example@mail.com")
                .password("pass123")
                .build();

        UserDTO userDTO = UserDTO.builder()
                .email("example@mail.com")
                .password("pass123")
                .build();

        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(user));

        userService.updateUser(UUID.randomUUID(), userDTO);


        verify(userRepository).findById(any(UUID.class));
        verify(userRepository).save(user);
    }

    @Test
    void updateUserNotFound() {
        UserDTO userDTO = UserDTO.builder().build();

        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                userService.updateUser(UUID.randomUUID(), userDTO));

    }

    @Test
    void patchUser() {
        User user = User.builder()
                .email("example@mail.com")
                .password("pass123")
                .build();

        UserPatchDTO userPatchDTO = UserPatchDTO.builder()
                .email("example@mail.com")
                .password("pass123")
                .build();

        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(user));

        userService.patchUser(UUID.randomUUID(), userPatchDTO);


        verify(userRepository).findById(any(UUID.class));
        verify(userRepository).save(user);
    }

    @Test
    void patchUserNotFound() {
        UserPatchDTO userPatchDTO = UserPatchDTO.builder().build();

        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

       assertThrows(NotFoundException.class, () ->
               userService.patchUser(UUID.randomUUID(), userPatchDTO));
    }

    @Test
    void deleteUser() {
        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(User.builder().build()));

        userService.deleteUser(UUID.randomUUID());

        verify(userRepository).deleteById(any(UUID.class));

    }

    @Test
    void deleteUserNotFound() {
        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                userService.deleteUser(UUID.randomUUID()));
    }
}