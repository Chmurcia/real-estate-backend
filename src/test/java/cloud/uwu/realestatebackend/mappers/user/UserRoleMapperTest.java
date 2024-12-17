package cloud.uwu.realestatebackend.mappers.user;

import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleResponseDTO;
import cloud.uwu.realestatebackend.entities.user.UserRole;
import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRoleMapperTest {

    UserRoleMapper userRoleMapper = Mappers
            .getMapper(UserRoleMapper.class);

    @Test
    void userRoleToUserRoleDTO() {
        UserRole userRole = UserRole.builder()
                .id(UUID.randomUUID())
                .role(Role.USER)
                .build();

        UserRoleDTO userRoleDTO = UserRoleDTO.builder()
                .role(Role.USER)
                .build();

        UserRoleDTO mappedUserRole = userRoleMapper
                .userRoleToUserRoleDTO(userRole);

        assertThat(mappedUserRole).isNotNull();
        assertEquals(userRoleDTO.getRole(), mappedUserRole.getRole());
    }

    @Test
    void userRoleDTOToUserRole() {
        UserRole userRole = UserRole.builder()
                .id(UUID.randomUUID())
                .role(Role.USER)
                .build();

        UserRoleDTO userRoleDTO = UserRoleDTO.builder()
                .role(Role.USER)
                .build();

        UserRole mappedUserRoleDTO = userRoleMapper
                .userRoleDTOToUserRole(userRoleDTO);

        assertThat(mappedUserRoleDTO).isNotNull();
        assertEquals(userRole.getRole(), mappedUserRoleDTO.getRole());
    }

    @Test
    void userRoleToUserRoleResponseDTO() {
        UUID id = UUID.randomUUID();

        UserRole userRole = UserRole.builder()
                .id(id)
                .role(Role.USER)
                .build();

        UserRoleResponseDTO userRoleResponseDTO = UserRoleResponseDTO.builder()
                .id(id)
                .role(Role.USER)
                .build();

        UserRoleResponseDTO mappedUserRoleResponse = userRoleMapper
                .userRoleToUserRoleResponseDTO(userRole);

        assertThat(mappedUserRoleResponse).isNotNull();
        assertEquals(userRoleResponseDTO.getRole(), mappedUserRoleResponse.getRole());
    }
}