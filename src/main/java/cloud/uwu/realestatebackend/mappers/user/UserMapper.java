package cloud.uwu.realestatebackend.mappers.user;

import cloud.uwu.realestatebackend.dtos.user.user.UserDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = {UserRoleMapper.class, UserFlagMapper.class, UserNotificationMapper.class})
public interface UserMapper {

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

    @Mapping(source = "userFlag", target = "userFlag")
    @Mapping(source = "userRole", target = "userRole")
    UserResponseDTO userToUserResponseDTO(User user);
}
