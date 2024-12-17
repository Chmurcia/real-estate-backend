package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileActivity;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileActivityMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileActivityRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileActivityService {
    private final ProfileActivityRepository profileActivityRepository;
    private final ProfileRepository profileRepository;
    private final ProfileActivityMapper profileActivityMapper;

    public Page<ProfileActivityResponseDTO> getProfileActivitiesByProfileId(UUID id, int page, int size) {
        page = Math.max(page, 0);
        size = size > 0 ? size : 50;

        getProfile(id);

        PageRequest pageable = PageRequest.of(page, size);

        return profileActivityRepository.getProfileActivitiesByProfileId(id, pageable)
                .map(profileActivityMapper::profileActivityToProfileActivityResponseDTO);
    }

    public ProfileActivityResponseDTO getProfileActivityById(UUID id) {
        ProfileActivity profileActivity = getProfileActivity(id);

        return profileActivityMapper
                .profileActivityToProfileActivityResponseDTO(profileActivity);
    }

    public ProfileActivityResponseDTO createProfileActivity(ProfileActivityDTO profileActivityDTO) {
        Profile profile;
        if (profileActivityDTO.getProfileId() != null) {
            profile = getProfile(profileActivityDTO.getProfileId());
        } else {
            throw new NullException("profileId is null");
        }

        ProfileActivity profileActivity = ProfileActivity.builder()
                .profile(profile)
                .activityTitle(profileActivityDTO
                        .getActivityTitle())
                .activityDescription(profileActivityDTO
                        .getActivityDescription())
                .activityDate(profileActivityDTO
                        .getActivityDate())
                .build();

        ProfileActivity savedProfileActivity = profileActivityRepository
                .save(profileActivity);

        return profileActivityMapper
                .profileActivityToProfileActivityResponseDTO(savedProfileActivity);
    }

    public void updateProfileActivity(UUID id, ProfileActivityDTO profileActivityDTO) {
        ProfileActivity profileActivity = getProfileActivity(id);

        if(profileActivityDTO.getActivityTitle() != null &&
                StringUtils.hasText(profileActivityDTO
                        .getActivityTitle())) {
            profileActivity.setActivityTitle(profileActivity
                    .getActivityTitle());
        } else {
            throw new NullException("activityTitle is null");
        }

        if(profileActivityDTO.getActivityDescription() != null &&
                StringUtils.hasText(profileActivityDTO
                        .getActivityDescription())) {
            profileActivity.setActivityDescription(profileActivity
                    .getActivityDescription());
        } else {
            throw new NullException("activityTitle is null");
        }

        if(profileActivityDTO.getActivityDate() != null) {
            profileActivity.setActivityDate(profileActivity
                    .getActivityDate());
        } else {
            throw new NullException("activityDate is null");
        }

        profileActivityRepository.save(profileActivity);
    }

    public void patchProfileActivity(UUID id, ProfileActivityPatchDTO profileActivityPatchDTO) {
        ProfileActivity profileActivity = getProfileActivity(id);

        if(profileActivityPatchDTO.getActivityTitle() != null &&
                StringUtils.hasText(profileActivityPatchDTO
                        .getActivityTitle())) {
            profileActivity.setActivityTitle(profileActivity
                    .getActivityTitle());
        }

        if(profileActivityPatchDTO.getActivityDescription() != null &&
                StringUtils.hasText(profileActivityPatchDTO
                        .getActivityDescription())) {
            profileActivity.setActivityDescription(profileActivity
                    .getActivityDescription());
        }

        if(profileActivityPatchDTO.getActivityDate() != null) {
            profileActivity.setActivityDate(profileActivity
                    .getActivityDate());
        }

        profileActivityRepository.save(profileActivity);
    }

    public void deleteProfileActivity(UUID id) {
        getProfileActivity(id);

        profileActivityRepository.deleteById(id);
    }

    private Profile getProfile(UUID id) {
        return profileRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Profile not found"));
    }

    private ProfileActivity getProfileActivity(UUID id) {
        return profileActivityRepository.findById(id).orElseThrow(() ->
                new NotFoundException("profileActivity not found"));
    }
}
