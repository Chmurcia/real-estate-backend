package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.other.filters.ProfileFilterDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfilePatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileSettings;
import cloud.uwu.realestatebackend.entities.profile.ProfileStatistics;
import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileSettingsRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileStatisticsRepository;
import cloud.uwu.realestatebackend.sorts.ProfileSort;
import cloud.uwu.realestatebackend.specifications.ProfileSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileSettingsRepository profileSettingsRepository;
    private final ProfileStatisticsRepository profileStatisticsRepository;
    private final ProfileMapper profileMapper;

    public Page<ProfileResponseDTO> getProfiles(
            String nickName, String country, String state, String city,
            Integer page, Integer size, String sortBy, String sortDirection) {
        page = (page != null) ? Math.max(page, 0) : 0;
        size = (size != null && size > 0)  ? size : 50;

        ProfileSort sortField = (sortBy != null && ProfileSort.isValid(sortBy))
                ? ProfileSort.valueOf(sortBy.toUpperCase())
                : ProfileSort.COUNTRY;

        String sortOrder = ("desc".equalsIgnoreCase(sortDirection) ? "desc" : "asc");

        Sort sort = ("desc".equalsIgnoreCase(sortOrder))
                ? Sort.by(Sort.Order.desc(sortField.getField()))
                : Sort.by(Sort.Order.asc(sortField.getField()));

        PageRequest pageable = PageRequest.of(page, size, sort);

        var specification = ProfileSpecification
                .createSpecification(ProfileFilterDTO.builder()
                        .nickName(nickName)
                        .country(country)
                        .state(state)
                        .city(city)
                        .build());

        return profileRepository.findAll(specification, pageable)
                .map(profileMapper::profileToProfileResponseDTO);
    }

    public ProfileResponseDTO getProfileById(UUID id) {
        Profile profile = getProfile(id);

        return profileMapper.profileToProfileResponseDTO(profile);
    }

    public ProfileResponseDTO createProfile(ProfileDTO profileDTO) {
        ProfileSettings profileSettings = ProfileSettings.builder()
                .profileVisibility(true)
                .theme(Theme.LIGHT)
                .build();

        ProfileStatistics profileStatistics = ProfileStatistics.builder()
                .totalRatings(0)
                .totalOffers(0)
                .totalViews(0)
                .totalTrusts(0)
                .totalPosts(0)
                .totalActionPoints(0)
                .build();

        ProfileSettings savedProfileSettings = profileSettingsRepository
                .saveAndFlush(profileSettings);

        ProfileStatistics savedProfileStatistics = profileStatisticsRepository
                .saveAndFlush(profileStatistics);

        Profile profile = Profile.builder()
                .firstName(profileDTO.getFirstName())
                .lastName(profileDTO.getLastName())
                .phoneNumber(profileDTO.getPhoneNumber())
                .avatarURL(profileDTO.getAvatarURL())
                .bio(profileDTO.getBio())
                .country(profileDTO.getCountry())
                .state(profileDTO.getState())
                .city(profileDTO.getCity())
                .birthDate(profileDTO.getBirthDate())
                .profileSettings(savedProfileSettings)
                .profileStatistics(savedProfileStatistics)
                .build();

        Profile savedProfile = profileRepository.saveAndFlush(profile);

        return profileMapper.profileToProfileResponseDTO(savedProfile);
    }

    public void updateProfile(UUID id, ProfileDTO profileDTO) {
        Profile profile = getProfile(id);

        if (profileDTO.getFirstName() != null &&
                StringUtils.hasText(profileDTO.getFirstName())) {
            profile.setFirstName(profileDTO.getFirstName());
        } else {
            throw new NullException("firstName is Null");
        }
        if (profileDTO.getLastName() != null &&
                StringUtils.hasText(profileDTO.getLastName())) {
            profile.setLastName(profileDTO.getLastName());
        } else {
            throw new NullException("lastName is Null");
        }
        if (profileDTO.getPhoneNumber() != null &&
                StringUtils.hasText(profileDTO.getPhoneNumber())) {
            profile.setPhoneNumber(profileDTO.getPhoneNumber());
        } else {
            throw new NullException("phoneNumber is Null");
        }
        if (profileDTO.getAvatarURL() != null &&
                StringUtils.hasText(profileDTO.getAvatarURL())) {
            profile.setAvatarURL(profileDTO.getAvatarURL());
        } else {
            throw new NullException("avatarURL is Null");
        }
        if (profileDTO.getBio() != null &&
                StringUtils.hasText(profileDTO.getBio())) {
            profile.setBio(profileDTO.getBio());
        } else {
            throw new NullException("Bio is Null");
        }
        if (profileDTO.getCountry() != null &&
                StringUtils.hasText(profileDTO.getCountry())) {
            profile.setCountry(profileDTO.getCountry());
        } else {
            throw new NullException("Country is Null");
        }
        if (profileDTO.getState() != null &&
                StringUtils.hasText(profileDTO.getState())) {
            profile.setState(profileDTO.getState());
        } else {
            throw new NullException("State is Null");
        }
        if (profileDTO.getCity() != null &&
                StringUtils.hasText(profileDTO.getCity())) {
            profile.setCity(profileDTO.getCity());
        } else {
            throw new NullException("City is Null");
        }
        if (profileDTO.getBirthDate() != null) {
            profile.setBirthDate(profileDTO.getBirthDate());
        } else {
            throw new NullException("BirthDate is Null");
        }

        profileRepository.save(profile);
    }

    public void patchProfile(UUID id, ProfilePatchDTO profilePatchDTO) {
        Profile profile = getProfile(id);

        if (profilePatchDTO.getFirstName() != null &&
                StringUtils.hasText(profilePatchDTO.getFirstName())) {
            profile.setFirstName(profilePatchDTO.getFirstName());
        }
        if (profilePatchDTO.getLastName() != null &&
                StringUtils.hasText(profilePatchDTO.getLastName())) {
            profile.setLastName(profilePatchDTO.getLastName());
        }
        if (profilePatchDTO.getPhoneNumber() != null &&
                StringUtils.hasText(profilePatchDTO.getPhoneNumber())) {
            profile.setPhoneNumber(profilePatchDTO.getPhoneNumber());
        }
        if (profilePatchDTO.getAvatarURL() != null &&
                StringUtils.hasText(profilePatchDTO.getAvatarURL())) {
            profile.setAvatarURL(profilePatchDTO.getAvatarURL());
        }
        if (profilePatchDTO.getBio() != null &&
                StringUtils.hasText(profilePatchDTO.getBio())) {
            profile.setBio(profilePatchDTO.getBio());
        }
        if (profilePatchDTO.getCountry() != null &&
                StringUtils.hasText(profilePatchDTO.getCountry())) {
            profile.setCountry(profilePatchDTO.getCountry());
        }
        if (profilePatchDTO.getState() != null &&
                StringUtils.hasText(profilePatchDTO.getState())) {
            profile.setState(profilePatchDTO.getState());
        }
        if (profilePatchDTO.getCity() != null &&
                StringUtils.hasText(profilePatchDTO.getCity())) {
            profile.setCity(profilePatchDTO.getCity());
        }
        if (profilePatchDTO.getBirthDate() != null) {
            profile.setBirthDate(profilePatchDTO.getBirthDate());
        }

        profileRepository.save(profile);
    }

    public void deleteProfile(UUID id) {
        getProfile(id);

        profileRepository.deleteById(id);

    }

    private Profile getProfile(UUID id) {
        return profileRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Profile not found"));
    }
}
