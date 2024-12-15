package cloud.uwu.realestatebackend.services.user;

import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagDTO;
import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagPatchDTO;
import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.UserFlag;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.user.UserFlagMapper;
import cloud.uwu.realestatebackend.repositories.user.UserFlagRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRepository;
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
class UserFlagServiceIntegrationTest {

    @Mock
    private UserFlagRepository userFlagRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserFlagMapper userFlagMapper;

    @InjectMocks
    private UserFlagService userFlagService;

    @Test
    void getUserFlagByUserId() {
        UserFlag userFlag = UserFlag.builder()
                .isVerified(false)
                .isBanned(false)
                .isMuted(false)
                .build();

        User user = User.builder()
                .id(UUID.randomUUID())
                .userFlag(userFlag)
                .build();

        UserFlagResponseDTO userFlagResponseDTO =UserFlagResponseDTO.builder()
                .id(user.getUserFlag().getId())
                .isVerified(user.getUserFlag().getIsVerified())
                .isBanned(user.getUserFlag().getIsBanned())
                .isMuted(user.getUserFlag().getIsMuted())
                .createdAt(user.getUserFlag().getCreatedAt())
                .updatedAt(user.getUserFlag().getUpdatedAt())
                .build();

        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(user));

        when(userFlagMapper.userFlagToUserFlagResponseDTO(any(UserFlag.class)))
                .thenReturn(userFlagResponseDTO);

        UserFlagResponseDTO foundUserFlagResponseDTO = userFlagService
                .getUserFlagByUserId(user.getId());

        assertThat(foundUserFlagResponseDTO).isNotNull();
    }

    @Test
    void getUserFlagByUserIdNotFound() {
        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                userFlagService.getUserFlagByUserId(UUID.randomUUID()));
    }

    @Test
    void getUserFlagById() {
        UUID id = UUID.randomUUID();
        UserFlag userFlag = UserFlag.builder()
                .id(UUID.randomUUID()).build();

        UserFlagResponseDTO userFlagResponseDTO = UserFlagResponseDTO.builder()
                .id(userFlag.getId())
                .isVerified(userFlag.getIsVerified())
                .isBanned(userFlag.getIsBanned())
                .isMuted(userFlag.getIsMuted())
                .createdAt(userFlag.getCreatedAt())
                .updatedAt(userFlag.getUpdatedAt())
                .build();

        when(userFlagRepository.findById(id))
                .thenReturn(Optional.of(userFlag));

        when(userFlagMapper.userFlagToUserFlagResponseDTO(any(UserFlag.class)))
                .thenReturn(userFlagResponseDTO);

        UserFlagResponseDTO foundUserFlagResponseDTO = userFlagService
                .getUserFlagById(id);

        assertThat(foundUserFlagResponseDTO).isNotNull();
        assertNotEquals(userFlag.getId(), UUID.randomUUID());
        assertEquals(userFlag.getId(), userFlagResponseDTO.getId());
    }

    @Test
    void getUserFlagByIdNotFound() {
        when(userFlagRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,
                () -> userFlagService.getUserFlagById(UUID.randomUUID()));
    }

    @Test
    void createUserFlag() {
        UUID id = UUID.randomUUID();
        UserFlagDTO userFlagDTO = UserFlagDTO.builder().build();
        UserFlag userFlag = UserFlag.builder()
                .id(id)
                .isVerified(true)
                .isMuted(false)
                .isBanned(false)
                .build();

        UserFlagResponseDTO userFlagResponseDTO = UserFlagResponseDTO.builder()
                .id(id)
                .isVerified(userFlag.getIsVerified())
                .isBanned(userFlag.getIsBanned())
                .isMuted(userFlag.getIsMuted())
                .build();

        when(userFlagMapper.userFlagDTOToUserFlag(any(UserFlagDTO.class)))
                .thenReturn(userFlag);
        when(userFlagRepository.save(any(UserFlag.class)))
                .thenReturn(userFlag);
        when(userFlagMapper.userFlagToUserFlagResponseDTO(any(UserFlag.class)))
                .thenReturn(userFlagResponseDTO);

         UserFlagResponseDTO createdUserFlag = userFlagService
                 .createUserFlag(userFlagDTO);

         verify(userFlagRepository).save(any(UserFlag.class));

         assertThat(createdUserFlag).isNotNull();
         assertEquals(userFlag.getId(), createdUserFlag.getId());
         assertNotEquals(userFlag.getId(), UUID.randomUUID());
    }

    @Test
    void updateUserFlag() {
        UUID id = UUID.randomUUID();

        UserFlagDTO userFlagDTO = UserFlagDTO.builder()
                .isVerified(true)
                .isMuted(false)
                .isBanned(true)
                .build();

        UserFlag userFlag = UserFlag.builder()
                .id(id)
                .isVerified(true)
                .isMuted(false)
                .isBanned(false)
                .build();

        when(userFlagRepository.findById(userFlag.getId()))
                .thenReturn(Optional.of(userFlag));

        userFlagService.updateUserFlag(userFlag.getId(), userFlagDTO);

        verify(userFlagRepository).findById(id);
        verify(userFlagRepository).save(any(UserFlag.class));
    }

    @Test
    void updateUserFlagNotFound() {
        UUID id = UUID.randomUUID();

        UserFlagDTO userFlagDTO = UserFlagDTO.builder().build();

        when(userFlagRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userFlagService
                .updateUserFlag(id, userFlagDTO));
    }

    @Test
    void updateUserFlagVerifiedNull() {
        UUID id = UUID.randomUUID();

        UserFlagDTO userFlagDTO = UserFlagDTO.builder()
                .isMuted(false)
                .isBanned(true)
                .build();

        UserFlag userFlag = UserFlag.builder().build();

        when(userFlagRepository.findById(id))
                .thenReturn(Optional.of(userFlag));

        assertThrows(NullException.class, () -> userFlagService
                .updateUserFlag(id, userFlagDTO));
    }

    @Test
    void updateUserFlagBannedNull() {
        UUID id = UUID.randomUUID();

        UserFlagDTO userFlagDTO = UserFlagDTO.builder()
                .isVerified(true)
                .isMuted(false)
                .build();

        UserFlag userFlag = UserFlag.builder().build();

        when(userFlagRepository.findById(id))
                .thenReturn(Optional.of(userFlag));

        assertThrows(NullException.class, () -> userFlagService
                .updateUserFlag(id, userFlagDTO));
    }

    @Test
    void updateUserFlagMutedNull() {
        UUID id = UUID.randomUUID();

        UserFlagDTO userFlagDTO = UserFlagDTO.builder()
                .isVerified(true)
                .isBanned(true)
                .build();

        UserFlag userFlag = UserFlag.builder().build();

        when(userFlagRepository.findById(id))
                .thenReturn(Optional.of(userFlag));

        assertThrows(NullException.class, () -> userFlagService
                .updateUserFlag(id, userFlagDTO));
    }

    @Test
    void patchUserFlag() {
        UUID id = UUID.randomUUID();

        UserFlagPatchDTO userFlagPatchDTO = UserFlagPatchDTO.builder()
                .isVerified(true)
                .isBanned(true)
                .build();

        UserFlag userFlag = UserFlag.builder()
                .id(id)
                .isVerified(true)
                .isMuted(false)
                .isBanned(false)
                .build();

        when(userFlagRepository.findById(userFlag.getId()))
                .thenReturn(Optional.of(userFlag));

        userFlagService.patchUserFlag(userFlag.getId(), userFlagPatchDTO);

        verify(userFlagRepository).findById(id);
        verify(userFlagRepository).save(any(UserFlag.class));
    }

    @Test
    void patchUserFlagNotFound() {
        UUID id = UUID.randomUUID();

        UserFlagPatchDTO userFlagPatchDTO = UserFlagPatchDTO.builder().build();

        when(userFlagRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userFlagService
                .patchUserFlag(id, userFlagPatchDTO));
    }

    @Test
    void deleteUserFlag() {
        UUID id = UUID.randomUUID();
        UserFlag userFlag = UserFlag.builder().build();

        when(userFlagRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(userFlag));

        userFlagService.deleteUserFlag(id);

        verify(userFlagRepository).deleteById(id);
    }

    @Test
    void deleteUserFlagNotFound() {
        UUID id = UUID.randomUUID();
        when(userFlagRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userFlagService.deleteUserFlag(id));

    }
}