package cloud.uwu.realestatebackend.mappers.user;

import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagDTO;
import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagResponseDTO;
import cloud.uwu.realestatebackend.entities.user.UserFlag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserFlagMapper {
    UserFlagDTO userFlagToUserFlagDTO(UserFlag userFlag);
    UserFlag userFlagDTOToUserFlag(UserFlagDTO userFlagDTO);

    UserFlagResponseDTO userFlagToUserFlagResponseDTO(UserFlag userFlag);
}
