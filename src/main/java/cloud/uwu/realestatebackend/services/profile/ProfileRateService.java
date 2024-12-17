package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRatePatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileAsk;
import cloud.uwu.realestatebackend.entities.profile.ProfileRate;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileRateMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRateRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileRateService {
    private final ProfileRateRepository profileRateRepository;
    private final ProfileRepository profileRepository;
    private final ProfileRateMapper profileRateMapper;

    public Page<ProfileRateResponseDTO> getProfileRatesByProfileId(UUID id, int page, int size) {
        page = Math.max(page, 0);
        size = size > 0 ? size : 50;

        getProfile(id);

        PageRequest pageable = PageRequest.of(page, size);

        return profileRateRepository.getProfileRatesByProfileId(id, pageable)
                .map(profileRateMapper::profileRateToProfileRateResponseDTO);
    }

    public ProfileRateResponseDTO getProfileRateById(UUID id) {
        ProfileRate profileRate = getProfileRate(id);

        return profileRateMapper.profileRateToProfileRateResponseDTO(profileRate);
    }

    public ProfileRateResponseDTO createProfileRate(ProfileRateDTO profileRateDTO) {
        Profile profile;
        if (profileRateDTO.getProfileId() != null) {
            profile = getProfile(profileRateDTO.getProfileId());
        } else {
            throw new NullException("profileId is null");
        }

        ProfileRate profileRate = ProfileRate.builder()
                .profile(profile)
                .evaluatorId(profileRateDTO.getEvaluatorId())
                .title(profileRateDTO.getTitle())
                .description(profileRateDTO.getDescription())
                .rate(profileRateDTO.getRate())
                .build();

        ProfileRate savedProfileRate = profileRateRepository.save(profileRate);

        return profileRateMapper.profileRateToProfileRateResponseDTO(savedProfileRate);
    }

    public void updateProfileRate(UUID id, ProfileRateDTO profileRateDTO) {
        ProfileRate profileRate = getProfileRate(id);

        if (profileRateDTO.getTitle() != null &&
                StringUtils.hasText(profileRateDTO.getTitle())) {
            profileRate.setTitle(profileRateDTO.getTitle());
        } else {
            throw new NullException("title is null");
        }

        if (profileRateDTO.getDescription() != null &&
                StringUtils.hasText(profileRateDTO.getDescription())) {
            profileRate.setDescription(profileRateDTO.getDescription());
        } else {
            throw new NullException("description is null");
        }

        if (profileRateDTO.getRate() != null) {
            profileRate.setRate(profileRateDTO.getRate());
        } else {
            throw new NullException("rate is null");
        }

        profileRateRepository.save(profileRate);
    }

    public void patchProfileRate(UUID id, ProfileRatePatchDTO profileRatePatchDTO) {
        ProfileRate profileRate = getProfileRate(id);

        if (profileRatePatchDTO.getTitle() != null &&
                StringUtils.hasText(profileRatePatchDTO.getTitle())) {
            profileRate.setTitle(profileRatePatchDTO.getTitle());
        }

        if (profileRatePatchDTO.getDescription() != null &&
                StringUtils.hasText(profileRatePatchDTO.getDescription())) {
            profileRate.setDescription(profileRatePatchDTO.getDescription());
        }

        if (profileRatePatchDTO.getRate() != null) {
            profileRate.setRate(profileRatePatchDTO.getRate());
        }

        profileRateRepository.save(profileRate);
    }

    public void deleteProfileRate(UUID id) {
        getProfileRate(id);

        profileRateRepository.deleteById(id);
    }

    private Profile getProfile(UUID id) {
        return profileRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Profile not found"));
    }

    private ProfileRate getProfileRate(UUID id) {
        return profileRateRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ProfileRate not found"));
    }
}
