package cloud.uwu.realestatebackend.mappers.user;

import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleResponseDTO;
import cloud.uwu.realestatebackend.entities.user.UserRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDTO userRoleToUserRoleDTO(UserRole userRole);
    UserRole userRoleDTOToUserRole(UserRoleDTO userRoleDTO);

    UserRoleResponseDTO userRoleToUserRoleResponseDTO(UserRole userRole);
}
