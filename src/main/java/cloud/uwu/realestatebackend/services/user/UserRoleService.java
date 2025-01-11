package cloud.uwu.realestatebackend.services.user;

import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRolePatchDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.UserFlag;
import cloud.uwu.realestatebackend.entities.user.UserRole;
import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.user.UserRoleMapper;
import cloud.uwu.realestatebackend.repositories.user.UserFlagRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;
    private final UserRepository userRepository;

    public UserRoleResponseDTO getUserRoleByUserId(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userRoleMapper.userRoleToUserRoleResponseDTO(user.getUserRole());
    }

    public UserRoleResponseDTO getUserRoleById(UUID id) {
        UserRole userRole = getUserRole(id);

        return UserRoleResponseDTO.builder()
                .id(userRole.getId())
                .role(userRole.getRole())
                .createdAt(userRole.getCreatedAt())
                .updatedAt(userRole.getUpdatedAt())
                .build();
    }

    public UserRole createUserRole() {
        return userRoleRepository.save(UserRole.builder()
                .role(Role.USER)
                .build());
    }

    public void updateUserRole(UUID id, UserRoleDTO userRoleDTO) {
        UserRole userRole = getUserRole(id);
        if (userRoleDTO.getRole() != null) {
            userRole.setRole(userRoleDTO.getRole());
        } else {
            throw new NullException("role is null");
        }
        userRoleRepository.save(userRole);
    }

    public void patchUserRole(UUID id, UserRolePatchDTO userRolePatchDTO) {
        UserRole userRole = getUserRole(id);
        if (userRolePatchDTO.getRole() != null && StringUtils
                .hasText(userRolePatchDTO.getRole().toString())) {
            userRole.setRole(userRolePatchDTO.getRole());
        }
        userRoleRepository.save(userRole);
    }

    public void deleteUserRole(UUID id) {
        getUserRole(id);
        userRoleRepository.deleteById(id);
    }

    //

    private UserRole getUserRole(UUID id) {
        return userRoleRepository.findById(id).orElseThrow(() ->
                new NotFoundException("userRole not found"));
    }
}
