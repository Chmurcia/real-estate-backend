package cloud.uwu.realestatebackend.services.user;

import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRolePatchDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.UserRole;
import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.user.UserRoleMapper;
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
class UserRoleServiceUnitTests {

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleMapper userRoleMapper;

    @InjectMocks
    private UserRoleService userRoleService;

    @Test
    void getUserRoleByUserId() {

        User user = User.builder()
                .id(UUID.randomUUID())
                .userRole(UserRole.builder().role(Role.ADMIN).build())
                .build();

        UserRoleResponseDTO userRoleResponseDTO = UserRoleResponseDTO
                .builder().role(Role.ADMIN).build();

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(userRoleMapper.userRoleToUserRoleResponseDTO(any(UserRole.class)))
                .thenReturn(userRoleResponseDTO);

        UserRoleResponseDTO foundUserRoleResponseDTO =
                userRoleService.getUserRoleByUserId(user.getId());

        assertThat(foundUserRoleResponseDTO).isNotNull();
        assertEquals(foundUserRoleResponseDTO.getRole(), Role.ADMIN);
    }

    @Test
    void getUserRoleByUserId_ShouldThrowNotFoundException() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                userRoleService.getUserRoleByUserId(UUID.randomUUID()));

    }

    @Test
    void getUserRoleById() {
        UUID id = UUID.randomUUID();

        UserRole userRole = UserRole.builder()
                .id(UUID.randomUUID())
                .role(Role.USER)
                .build();

        when(userRoleRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(userRole));

        UserRoleResponseDTO foundUserRole = userRoleService.getUserRoleById(id);

        verify(userRoleRepository).findById(id);

        assertThat(foundUserRole).isNotNull();
        assertEquals(foundUserRole.getRole(), Role.USER);
        assertNotEquals(foundUserRole.getRole(), Role.ADMIN);
    }

    @Test
    void getUserRoleById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        when(userRoleRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userRoleService.getUserRoleById(id));
    }

    @Test
    void updateUserRole() {
        UUID id = UUID.randomUUID();

        UserRoleDTO userRoleDTO = UserRoleDTO.builder()
                .role(Role.ADMIN)
                .build();

        UserRole userRole = UserRole.builder()
                .id(id)
                .role(Role.USER)
                .build();

        when(userRoleRepository.findById(id)).thenReturn(Optional.of(userRole));

        userRoleService.updateUserRole(id, userRoleDTO);

        verify(userRoleRepository).save(any(UserRole.class));
        verify(userRoleRepository).findById(id);
    }

    @Test
    void updateUserRole_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                userRoleService.updateUserRole(UUID.randomUUID(),
                        UserRoleDTO.builder().build()));
    }

    @Test
    void updateUserRole_ShouldThrowNullException() {
        UUID id = UUID.randomUUID();

        UserRoleDTO userRoleDTO = UserRoleDTO.builder()
                .role(Role.ADMIN)
                .build();

        when(userRoleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> userRoleService.updateUserRole(id, userRoleDTO));
    }

    @Test
    void updateUserRoleRole_ShouldThrowNullException() {
        UUID id = UUID.randomUUID();

        UserRoleDTO userRoleDTO = UserRoleDTO.builder()
                .role(null)
                .build();

        UserRole userRole = UserRole.builder()
                .id(id)
                .role(Role.USER)
                .build();

        when(userRoleRepository.findById(userRole.getId())).thenReturn(Optional.of(userRole));

        assertThrows(NullException.class, () ->
                userRoleService.updateUserRole(userRole.getId(), userRoleDTO));
    }

    @Test
    void patchUserRole() {
        UUID id = UUID.randomUUID();

        UserRolePatchDTO userRolePatchDTO = UserRolePatchDTO.builder()
                .role(Role.ADMIN)
                .build();

        UserRole userRole = UserRole.builder()
                .id(id)
                .role(Role.USER)
                .build();

        when(userRoleRepository.save(any(UserRole.class))).thenReturn(userRole);
        when(userRoleRepository.findById(id)).thenReturn(Optional.of(userRole));

        userRoleService.patchUserRole(id, userRolePatchDTO);

        verify(userRoleRepository).save(any(UserRole.class));
        verify(userRoleRepository).findById(id);
    }

    @Test
    void patchUserRoleUser_ShouldThrowNotFoundException() {

        assertThrows(NotFoundException.class,
                () -> userRoleService.patchUserRole(UUID.randomUUID(),
                        UserRolePatchDTO.builder().build()));
    }


    @Test
    void deleteUserRole() {
        UUID id = UUID.randomUUID();

        UserRole userRole = UserRole.builder()
                .id(id)
                .build();

        when(userRoleRepository.findById(any(UUID.class))).thenReturn(Optional.of(userRole));

        userRoleService.deleteUserRole(userRole.getId());

        verify(userRoleRepository).deleteById(userRole.getId());
    }

    @Test
    void deleteUserRole_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                userRoleService.deleteUserRole(UUID.randomUUID()));
    }

}