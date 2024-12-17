package cloud.uwu.realestatebackend.mappers.user;

import cloud.uwu.realestatebackend.dtos.user.user.UserDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = {UserRoleMapper.class, UserFlagMapper.class, UserNotificationMapper.class})
public interface UserMapper {

    User userDTOToUser(UserDTO userDTO);

    @Mapping(source = "userFlag.id", target = "userFlagId")
    @Mapping(source = "userRole.id", target = "userRoleId")
    UserResponseDTO userToUserResponseDTO(User user);
}
