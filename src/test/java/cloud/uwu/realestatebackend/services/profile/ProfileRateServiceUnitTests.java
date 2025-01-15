package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRatePatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileRate;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileRateMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRateRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileRateServiceUnitTests {

    @Mock
    private ProfileRateRepository profileRateRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileRateMapper profileRateMapper;

    @InjectMocks
    private ProfileRateService profileRateService;

    @Test
    void getProfileRatesByProfileId() {
        UUID profileId = UUID.randomUUID();

        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        ProfileRate profileRate1 = ProfileRate.builder().build();
        ProfileRate profileRate2 = ProfileRate.builder().build();

        Page<ProfileRate> profileRates = new PageImpl<>(
                List.of(profileRate1, profileRate2),
                pageable, 2
        );

        when(profileRepository.findById(profileId))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(profileRateRepository.getProfileRatesByProfileId(profileId, pageable))
                .thenReturn(profileRates);

        when(profileRateMapper.profileRateToProfileRateResponseDTO(any(ProfileRate.class)))
                .thenReturn(ProfileRateResponseDTO.builder().build());

        Page<ProfileRateResponseDTO> profileRateResponseDTOS = profileRateService
                .getProfileRatesByProfileId(profileId, page, size);

        assertThat(profileRateResponseDTOS).isNotNull();
        assertEquals(profileRates.getContent().size(), 2);
    }

    @Test
    void getProfileRatesByProfileId_ShouldReturn1Element() {
        UUID profileId = UUID.randomUUID();

        int page = 0;
        int size = 1;

        PageRequest pageable = PageRequest.of(page, size);

        ProfileRate profileRate1 = ProfileRate.builder().build();

        Page<ProfileRate> profileRates = new PageImpl<>(
                List.of(profileRate1),
                pageable, 2
        );

        when(profileRepository.findById(profileId))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(profileRateRepository.getProfileRatesByProfileId(profileId, pageable))
                .thenReturn(profileRates);

        when(profileRateMapper.profileRateToProfileRateResponseDTO(any(ProfileRate.class)))
                .thenReturn(ProfileRateResponseDTO.builder().build());

        Page<ProfileRateResponseDTO> profileRateResponseDTOS = profileRateService
                .getProfileRatesByProfileId(profileId, page, size);

        assertThat(profileRateResponseDTOS).isNotNull();
        assertEquals(profileRates.getContent().size(), 1);
    }

    @Test
    void getProfileRatesByProfileId_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> profileRateService
                .getProfileRatesByProfileId(UUID.randomUUID(), 0, 50));
    }

    @Test
    void getProfileRateById() {
        ProfileRate profileRate = ProfileRate.builder().id(UUID.randomUUID()).build();
        ProfileRateResponseDTO profileRateResponseDTO = ProfileRateResponseDTO
                .builder().id(profileRate.getId()).build();

        when(profileRateRepository.findById(profileRate.getId()))
                .thenReturn(Optional.of(profileRate));

        when(profileRateMapper.profileRateToProfileRateResponseDTO(profileRate))
                .thenReturn(profileRateResponseDTO);

        ProfileRateResponseDTO foundProfileRate = profileRateService
                .getProfileRateById(profileRate.getId());

        assertThat(foundProfileRate).isNotNull();
        assertEquals(profileRate.getId(), profileRateResponseDTO.getId());
    }

    @Test
    void getProfileRateById_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> profileRateService
                .getProfileRateById(UUID.randomUUID()));
    }

    @Test
    void createProfileRate() {
        Profile profile = Profile.builder().id(UUID.randomUUID()).build();
        ProfileRateDTO profileRateDTO = ProfileRateDTO.builder()
                .evaluatorId(UUID.randomUUID())
                .title("Rand Title")
                .description("Rand Description")
                .rate(7)
                .build();
        ProfileRate profileRate = ProfileRate.builder()
                .id(UUID.randomUUID())
                .profile(profile)
                .evaluatorId(profileRateDTO.getEvaluatorId())
                .title(profileRateDTO.getTitle())
                .description(profileRateDTO.getDescription())
                .rate(profileRateDTO.getRate())
                .build();
        ProfileRateResponseDTO profileRateResponseDTO = ProfileRateResponseDTO
                .builder()
                .id(profileRate.getId())
                .evaluatorId(profileRate.getEvaluatorId())
                .title(profileRate.getTitle())
                .description(profileRate.getDescription())
                .rate(profileRate.getRate())
                .build();

        when(profileRepository.findById(profile.getId()))
                .thenReturn(Optional.of(profile));

        when(profileRepository.findById(profileRateDTO.getEvaluatorId()))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(profileRateRepository.saveAndFlush(any(ProfileRate.class)))
                .thenReturn(profileRate);

        when(profileRateMapper.profileRateToProfileRateResponseDTO(any(ProfileRate.class)))
                .thenReturn(profileRateResponseDTO);

        ProfileRateResponseDTO foundProfileRate = profileRateService
                .createProfileRate(profile.getId(), profileRateDTO);

        assertThat(foundProfileRate).isNotNull();
        assertEquals(profileRateDTO.getEvaluatorId(), foundProfileRate.getEvaluatorId());
        assertEquals(profileRateDTO.getTitle(), foundProfileRate.getTitle());
        assertEquals(profileRateDTO.getDescription(), foundProfileRate.getDescription());
        assertEquals(profileRateDTO.getRate(), foundProfileRate.getRate());
    }

    @Test
    void createProfileRateProfile_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> profileRateService
                        .createProfileRate(UUID.randomUUID(),
                                ProfileRateDTO.builder().build()));
    }

    @Test
    void createProfileRateProfileId_ShouldThrowNullException() {
        assertThrows(NotFoundException.class, () -> profileRateService
                .createProfileRate(UUID.randomUUID(),
                        ProfileRateDTO.builder().build()));
    }

    @Test
    void updateProfileRate() {
        ProfileRate profileRate = ProfileRate.builder()
                .id(UUID.randomUUID())
                .title("wrong")
                .description("wrong")
                .rate(0)
                .build();
        ProfileRateDTO profileRateDTO = ProfileRateDTO.builder()
                .title("Better title")
                .description("Better description")
                .rate(9)
                .build();

        when(profileRateRepository.findById(profileRate.getId()))
                .thenReturn(Optional.of(profileRate));

        profileRateService.updateProfileRate(profileRate.getId(), profileRateDTO);

        verify(profileRateRepository).save(profileRate);
    }

    @Test
    void updateProfileRate_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileRateService.updateProfileRate(UUID.randomUUID(),
                        ProfileRateDTO.builder().build()));
    }

    @Test
    void patchProfileRate() {
        ProfileRate profileRate = ProfileRate.builder()
                .id(UUID.randomUUID())
                .title("wrong")
                .description("wrong")
                .rate(0)
                .build();
        ProfileRatePatchDTO profileRatePatchDTO = ProfileRatePatchDTO.builder()
                .title("Better title")
                .description("Better description")
                .rate(9)
                .build();

        when(profileRateRepository.findById(profileRate.getId()))
                .thenReturn(Optional.of(profileRate));

        profileRateService.patchProfileRate(profileRate.getId(), profileRatePatchDTO);

        verify(profileRateRepository).save(profileRate);
    }

    @Test
    void patchProfileRate_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileRateService.patchProfileRate(UUID.randomUUID(),
                        ProfileRatePatchDTO.builder().build()));
    }

    @Test
    void deleteProfileRate() {
        UUID id = UUID.randomUUID();

        when(profileRateRepository.findById(id))
                .thenReturn(Optional.of(ProfileRate.builder().build()));

        profileRateService.deleteProfileRate(id);

        verify(profileRateRepository).deleteById(id);
    }

    @Test
    void deleteProfileRate_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileRateService.deleteProfileRate(UUID.randomUUID()));
    }
}