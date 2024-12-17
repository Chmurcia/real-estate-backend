package cloud.uwu.realestatebackend.mappers.user;

import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagDTO;
import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagResponseDTO;
import cloud.uwu.realestatebackend.entities.user.UserFlag;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserFlagMapperTest {

    UserFlagMapper userFlagMapper = Mappers
            .getMapper(UserFlagMapper.class);

    @Test
    void userFlagToUserFlagDTO() {
        UserFlag userFlag = UserFlag.builder()
                .id(UUID.randomUUID())
                .isVerified(false)
                .isBanned(true)
                .isMuted(false)
                .build();

        UserFlagDTO userFlagDTO = UserFlagDTO.builder()
                .isVerified(false)
                .isBanned(true)
                .isMuted(false)
                .build();

        UserFlagDTO mappedUserFlag = userFlagMapper
                .userFlagToUserFlagDTO(userFlag);

        assertThat(mappedUserFlag).isNotNull();
        assertEquals(userFlagDTO.getIsVerified(), mappedUserFlag.getIsVerified());
        assertEquals(userFlagDTO.getIsBanned(), mappedUserFlag.getIsBanned());
        assertEquals(userFlagDTO.getIsMuted(), mappedUserFlag.getIsMuted());
    }

    @Test
    void userFlagDTOToUserFlag() {
        UserFlag userFlag = UserFlag.builder()
                .id(UUID.randomUUID())
                .isVerified(false)
                .isBanned(true)
                .isMuted(false)
                .build();

        UserFlagDTO userFlagDTO = UserFlagDTO.builder()
                .isVerified(false)
                .isBanned(true)
                .isMuted(false)
                .build();

        UserFlag mappedUserFlagDTO = userFlagMapper
                .userFlagDTOToUserFlag(userFlagDTO);

        assertThat(mappedUserFlagDTO).isNotNull();
        assertEquals(userFlag.getIsVerified(), mappedUserFlagDTO.getIsVerified());
        assertEquals(userFlag.getIsBanned(), mappedUserFlagDTO.getIsBanned());
        assertEquals(userFlag.getIsMuted(), mappedUserFlagDTO.getIsMuted());
    }

    @Test
    void userFlagToUserFlagResponseDTO() {
        UUID id = UUID.randomUUID();

        UserFlag userFlag = UserFlag.builder()
                .id(id)
                .isVerified(false)
                .isBanned(true)
                .isMuted(false)
                .build();

        UserFlagResponseDTO userFlagResponseDTO = UserFlagResponseDTO.builder()
                .id(id)
                .isVerified(false)
                .isBanned(true)
                .isMuted(false)
                .build();

        UserFlagResponseDTO mappedUserFlagResponse = userFlagMapper
                .userFlagToUserFlagResponseDTO(userFlag);

        assertThat(mappedUserFlagResponse).isNotNull();
        assertEquals(userFlagResponseDTO.getId(), mappedUserFlagResponse.getId());
        assertEquals(userFlagResponseDTO.getIsVerified(), mappedUserFlagResponse.getIsVerified());
        assertEquals(userFlagResponseDTO.getIsBanned(), mappedUserFlagResponse.getIsBanned());
        assertEquals(userFlagResponseDTO.getIsMuted(), mappedUserFlagResponse.getIsMuted());
    }
}