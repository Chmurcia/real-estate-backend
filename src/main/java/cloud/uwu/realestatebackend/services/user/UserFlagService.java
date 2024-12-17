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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFlagService {
    private final UserFlagRepository userFlagRepository;
    private final UserFlagMapper userFlagMapper;
    private final UserRepository userRepository;

    public UserFlagResponseDTO getUserFlagByUserId(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userFlagMapper.userFlagToUserFlagResponseDTO(user.getUserFlag());
    }

    public UserFlagResponseDTO getUserFlagById(UUID id) {
        UserFlag userFlag = getUserFlag(id);

        return userFlagMapper.userFlagToUserFlagResponseDTO(userFlag);
    }

    public void updateUserFlag(UUID id, UserFlagDTO userFlagDTO) {
        UserFlag userFlag = getUserFlag(id);

        if (userFlagDTO.getIsVerified() != null) {
            userFlag.setIsVerified(userFlagDTO.getIsVerified());
        } else {
            throw new NullException("isVerified is null");
        }

        if (userFlagDTO.getIsBanned() != null) {
            userFlag.setIsBanned(userFlagDTO.getIsBanned());
        } else {
            throw new NullException("isBanned is null");
        }

        if (userFlagDTO.getIsMuted() != null) {
            userFlag.setIsMuted(userFlagDTO.getIsMuted());
        } else {
            throw new NullException("isMuted is null");
        }

        userFlagRepository.save(userFlag);

    }
    public void patchUserFlag(UUID id, UserFlagPatchDTO userFlagPatchDTO) {
        UserFlag userFlag = getUserFlag(id);

        if (userFlagPatchDTO.getIsVerified() != null) {
            userFlag.setIsVerified(userFlagPatchDTO.getIsVerified());
        }

        if (userFlagPatchDTO.getIsBanned() != null) {
            userFlag.setIsBanned(userFlagPatchDTO.getIsBanned());
        }

        if (userFlagPatchDTO.getIsMuted() != null) {
            userFlag.setIsMuted(userFlagPatchDTO.getIsMuted());
        }

        userFlagRepository.save(userFlag);
    }

    public void deleteUserFlag(UUID id) {
        getUserFlag(id);
        userFlagRepository.deleteById(id);
    }


    private UserFlag getUserFlag(UUID id) {
        return userFlagRepository.findById(id).orElseThrow(
                () -> new NotFoundException("userFlag not found")
        );
    }
}
