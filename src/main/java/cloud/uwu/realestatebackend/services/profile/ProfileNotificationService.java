package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileNotification.ProfileNotificationDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileNotification.ProfileNotificationResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileNotification;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileNotificationMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileNotificationRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileNotificationService {
    private final ProfileNotificationRepository profileNotificationRepository;
    private final ProfileNotificationMapper profileNotificationMapper;
    private final ProfileRepository profileRepository;

    public ProfileNotificationResponseDTO getProfileNotificationById(UUID id) {
        return profileNotificationMapper
                .profileNotificationToProfileNotificationResponseDTO(
                        getProfileNotification(id)
                );
    }

    public List<ProfileNotificationResponseDTO> getProfileNotificationsByProfileId(UUID id) {
        getProfile(id);

        return profileNotificationRepository
                .getProfileNotificationsByProfileId(id).stream()
                .map(profileNotificationMapper::profileNotificationToProfileNotificationResponseDTO)
                .toList();
    }

    public ProfileNotificationResponseDTO createProfileNotification(
            ProfileNotificationDTO profileNotificationDTO
    ) {
        Profile profile;

        if (profileNotificationDTO.getProfileId() != null) {
            profile = getProfile(profileNotificationDTO.getProfileId());
        } else {
            throw new NullException("profileId is null");
        }

        ProfileNotification profileNotification = ProfileNotification.builder()
                .profile(profile)
                .notificationName(profileNotificationDTO.getNotificationName())
                .notificationId(profileNotificationDTO.getNotificationId())
                .notificationDescription(profileNotificationDTO.getNotificationDescription())
                .build();

        ProfileNotification savedProfileNotification =
                profileNotificationRepository.save(profileNotification);

        return profileNotificationMapper
                .profileNotificationToProfileNotificationResponseDTO(savedProfileNotification);
    }

    public void deleteProfileNotification(UUID id) {
        getProfileNotification(id);

        profileNotificationRepository.deleteById(id);
    }

    //

    private Profile getProfile(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
    }

    private ProfileNotification getProfileNotification(UUID id) {
        return profileNotificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProfileNotification not found"));
    }
}