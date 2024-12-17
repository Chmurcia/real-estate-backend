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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileAskServiceUnitTests {

    @Mock
    private ProfileAskRepository profileAskRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileAskMapper profileAskMapper;

    @InjectMocks
    private ProfileAskService profileAskService;

    @Test
    void getProfileAsksByProfileId() {
        UUID profileId = UUID.randomUUID();
        Profile profile = Profile.builder().build();

        int page = 0;
        int size = 50;
        PageRequest pageable = PageRequest.of(page, size);

        ProfileAsk profileAsk1 = ProfileAsk
                .builder().build();
        ProfileAsk profileAsk2 = ProfileAsk
                .builder().build();

        Page<ProfileAsk> profileAsks = new PageImpl<>(
                List.of(profileAsk1, profileAsk2),
                pageable, 2
        );

        when(profileRepository.findById(profileId))
                .thenReturn(Optional.of(profile));

        when(profileAskRepository.getProfileAsksByProfileId(profileId, pageable))
                .thenReturn(profileAsks);

        when(profileAskMapper.profileAskToProfileAskResponseDTO(any(ProfileAsk.class)))
                .thenReturn(ProfileAskResponseDTO.builder().build());

        Page<ProfileAskResponseDTO> profileAskResponseDTOS = profileAskService
                .getProfileAsksByProfileId(profileId, page, size);

        assertThat(profileAskResponseDTOS).isNotNull();
        assertEquals(profileAskResponseDTOS.getContent().size(), 2);
    }

    @Test
    void getProfileAsksByProfileId_ShouldReturn1Element() {
        UUID profileId = UUID.randomUUID();
        Profile profile = Profile.builder().build();

        int page = 0;
        int size = 1;
        PageRequest pageable = PageRequest.of(page, size);

        ProfileAsk profileAsk1 = ProfileAsk
                .builder().build();

        Page<ProfileAsk> profileAsks = new PageImpl<>(
                List.of(profileAsk1),
                pageable, 2
        );

        when(profileRepository.findById(profileId))
                .thenReturn(Optional.of(profile));

        when(profileAskRepository.getProfileAsksByProfileId(profileId, pageable))
                .thenReturn(profileAsks);

        when(profileAskMapper.profileAskToProfileAskResponseDTO(any(ProfileAsk.class)))
                .thenReturn(ProfileAskResponseDTO.builder().build());

        Page<ProfileAskResponseDTO> profileAskResponseDTOS = profileAskService
                .getProfileAsksByProfileId(profileId, page, size);

        assertThat(profileAskResponseDTOS).isNotNull();
        assertEquals(profileAskResponseDTOS.getContent().size(), 1);
    }

    @Test
    void getProfileAsksByProfileId_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileAskService.getProfileAsksByProfileId(any(UUID.class), 0, 50));
    }

    @Test
    void getProfileAskById() {
        UUID id = UUID.randomUUID();
        ProfileAsk profileAsk = ProfileAsk.builder().id(id).build();
        ProfileAskResponseDTO profileAskResponseDTO = ProfileAskResponseDTO
                .builder().id(id).build();

        when(profileAskRepository.findById(id))
                .thenReturn(Optional.of(profileAsk));

        when(profileAskMapper.profileAskToProfileAskResponseDTO(profileAsk))
                .thenReturn(profileAskResponseDTO);

        ProfileAskResponseDTO foundProfileAsk = profileAskService
                .getProfileAskById(id);

        assertThat(foundProfileAsk).isNotNull();
    }

    @Test
    void getProfileAskById_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileAskService.getProfileAskById(UUID.randomUUID()));
    }

    @Test
    void createProfileAsk() {
        UUID profileId = UUID.randomUUID();
        UUID profileAskId = UUID.randomUUID();

        Profile profile = Profile.builder().id(profileId).build();

        ProfileAskDTO profileAskDTO = ProfileAskDTO.builder()
                .profileId(profileId)
                .askTitle("Tile of an ask")
                .askDescription("Description of an ask")
                .askAnswer("Answer for an ask")
                .build();

        ProfileAsk profileAsk = ProfileAsk.builder()
                .id(profileAskId)
                .profile(profile)
                .askTitle("Tile of an ask")
                .askDescription("Description of an ask")
                .askAnswer("Answer for an ask")
                .build();

        ProfileAskResponseDTO profileAskResponseDTO = ProfileAskResponseDTO
                .builder()
                .id(profileAskId)
                .askTitle("Tile of an ask")
                .askDescription("Description of an ask")
                .askAnswer("Answer for an ask")
                .build();

        when(profileRepository.findById(profileId))
                .thenReturn(Optional.of(profile));

        when(profileAskRepository.save(any(ProfileAsk.class)))
                .thenReturn(profileAsk);

        when(profileAskMapper
                .profileAskToProfileAskResponseDTO(profileAsk))
                .thenReturn(profileAskResponseDTO);

        ProfileAskResponseDTO savedProfileAsk = profileAskService
                .createProfileAsk(profileAskDTO);

        assertThat(savedProfileAsk).isNotNull();
        assertEquals(profileAskDTO.getAskTitle(),
                profileAskResponseDTO.getAskTitle());
        assertEquals(profileAskDTO.getAskDescription(),
                profileAskResponseDTO.getAskDescription());
        assertEquals(profileAskDTO.getAskAnswer(),
                profileAskResponseDTO.getAskAnswer());
    }

    @Test
    void createProfileAsk_ShouldThrowNullException() {
        assertThrows(NullException.class, () ->
                profileAskService.createProfileAsk(ProfileAskDTO.builder().build()));
    }

    @Test
    void updateProfileAsk() {
        UUID profileId = UUID.randomUUID();

        ProfileAsk profileAsk = ProfileAsk.builder()
                .id(profileId)
                .askTitle("Title")
                .askDescription("Description")
                .askAnswer("Ask")
                .build();

        ProfileAskDTO profileAskDTO = ProfileAskDTO.builder()
                .askTitle("Title")
                .askDescription("Description")
                .askAnswer("Ask")
                .build();


        when(profileAskRepository.findById(profileAsk.getId()))
                .thenReturn(Optional.of(profileAsk));

        profileAskService.updateProfileAsk(profileAsk.getId(), profileAskDTO);

        verify(profileAskRepository).save(profileAsk);
    }

    @Test
    void updateProfileAsk_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileAskService.updateProfileAsk(UUID.randomUUID(), ProfileAskDTO
                        .builder().build()));
    }

    @Test
    void patchProfileAsk() {
        UUID profileId = UUID.randomUUID();

        ProfileAsk profileAsk = ProfileAsk.builder()
                .id(profileId)
                .askTitle("Title")
                .askDescription("Description")
                .askAnswer("Ask")
                .build();

        ProfileAskPatchDTO profileAskPatchDTO = ProfileAskPatchDTO.builder()
                .askTitle("Title")
                .askDescription("Description")
                .askAnswer("Ask")
                .build();

        when(profileAskRepository.findById(profileAsk.getId()))
                .thenReturn(Optional.of(profileAsk));

        profileAskService.patchProfileAsk(profileAsk.getId(), profileAskPatchDTO);

        verify(profileAskRepository).save(profileAsk);
    }

    @Test
    void patchProfileAsk_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileAskService.patchProfileAsk(UUID.randomUUID(),
                        ProfileAskPatchDTO.builder().build()));
    }

    @Test
    void deleteProfileAsk() {
        UUID id = UUID.randomUUID();

        when(profileAskRepository.findById(id))
                .thenReturn(Optional
                        .of(ProfileAsk.builder().build()));

        profileAskService.deleteProfileAsk(id);

        verify(profileAskRepository).deleteById(id);
    }

    @Test
    void deleteProfileAsk_ShouldReturnNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileAskService.deleteProfileAsk(UUID.randomUUID()));
    }

}