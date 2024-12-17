package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileAsk;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileAskMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileAskRepository;
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
public class ProfileAskService {
    private final ProfileAskRepository profileAskRepository;
    private final ProfileRepository profileRepository;
    private final ProfileAskMapper profileAskMapper;

    public Page<ProfileAskResponseDTO> getProfileAsksByProfileId(UUID id, int page, int size) {
        page = Math.max(page, 0);
        size = size > 0 ? size : 50;

        getProfile(id);

        PageRequest pageable = PageRequest.of(page, size);

        return profileAskRepository.getProfileAsksByProfileId(id, pageable)
                .map(profileAskMapper::profileAskToProfileAskResponseDTO);
    }

    public ProfileAskResponseDTO getProfileAskById(UUID id) {
        ProfileAsk profileAsk = getProfileAsk(id);
        return profileAskMapper.profileAskToProfileAskResponseDTO(profileAsk);
    }

    public ProfileAskResponseDTO createProfileAsk(ProfileAskDTO profileAskDTO) {
        Profile profile;
        if (profileAskDTO.getProfileId() != null) {
            profile = getProfile(profileAskDTO.getProfileId());
        } else {
            throw new NullException("profileId is null");
        }

        ProfileAsk profileAsk = ProfileAsk.builder()
                .profile(profile)
                .askTitle(profileAskDTO.getAskTitle())
                .askDescription(profileAskDTO.getAskDescription())
                .askAnswer(profileAskDTO.getAskAnswer())
                .build();

        ProfileAsk savedProfileAsk = profileAskRepository.save(profileAsk);

        return profileAskMapper.profileAskToProfileAskResponseDTO(savedProfileAsk);
    }

    public void updateProfileAsk(UUID id, ProfileAskDTO profileAskDTO) {
        ProfileAsk profileAsk = getProfileAsk(id);

        if (profileAskDTO.getAskTitle() != null &&
                StringUtils.hasText(profileAskDTO.getAskTitle())) {
            profileAsk.setAskTitle(profileAskDTO.getAskTitle());
        } else {
            throw new NullException("askTitle is null");
        }

        if (profileAskDTO.getAskDescription() != null &&
                StringUtils.hasText(profileAskDTO.getAskDescription())) {
            profileAsk.setAskDescription(profileAskDTO.getAskDescription());
        } else {
            throw new NullException("askDescription is null");
        }

        if (profileAskDTO.getAskAnswer() != null &&
                StringUtils.hasText(profileAskDTO.getAskAnswer())) {
            profileAsk.setAskAnswer(profileAskDTO.getAskAnswer());
        } else {
            throw new NullException("askAnswer is null");
        }

        profileAskRepository.save(profileAsk);
    }

    public void patchProfileAsk(UUID id, ProfileAskPatchDTO profileAskPatchDTO) {
        ProfileAsk profileAsk = getProfileAsk(id);

        if (profileAskPatchDTO.getAskTitle() != null &&
                StringUtils.hasText(profileAskPatchDTO.getAskTitle())) {
            profileAsk.setAskTitle(profileAskPatchDTO.getAskTitle());
        }

        if (profileAskPatchDTO.getAskDescription() != null &&
                StringUtils.hasText(profileAskPatchDTO.getAskDescription())) {
            profileAsk.setAskDescription(profileAskPatchDTO.getAskDescription());
        }

        if (profileAskPatchDTO.getAskAnswer() != null &&
                StringUtils.hasText(profileAskPatchDTO.getAskAnswer())) {
            profileAsk.setAskAnswer(profileAskPatchDTO.getAskAnswer());
        }

        profileAskRepository.save(profileAsk);
    }

    public void deleteProfileAsk(UUID id) {
        getProfileAsk(id);

        profileAskRepository.deleteById(id);
    }

    private Profile getProfile(UUID id) {
        return profileRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Profile not found"));
    }

    private ProfileAsk getProfileAsk(UUID id) {
        return profileAskRepository.findById(id).orElseThrow(() ->
                new NotFoundException("profileAsk not found"));
    }
}
