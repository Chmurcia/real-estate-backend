package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskResponseDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileRate;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileRateMapperTest {

    private final ProfileRateMapper profileRateMapper = Mappers
            .getMapper(ProfileRateMapper.class);

    @Test
    void profileRateToProfileRateDTO() {
        UUID profileId = UUID.randomUUID();
        UUID evaluatorId = UUID.randomUUID();

        ProfileRate profileAsk = ProfileRate.builder()
                .id(UUID.randomUUID())
                .profile(Profile.builder().id(profileId).build())
                .evaluatorId(evaluatorId)
                .title("Title")
                .description("Description")
                .rate(3)
                .build();

        ProfileRateDTO profileRateDTO = ProfileRateDTO.builder()
                .profileId(profileId)
                .evaluatorId(evaluatorId)
                .title("Title")
                .description("Description")
                .rate(3)
                .build();

        ProfileRateDTO mappedProfileRate = profileRateMapper
                .profileRateToProfileRateDTO(profileAsk);

        assertThat(mappedProfileRate).isNotNull();

        assertEquals(profileRateDTO.getProfileId(),
                mappedProfileRate.getProfileId());

        assertEquals(profileRateDTO.getTitle(),
                mappedProfileRate.getTitle());

        assertEquals(profileRateDTO.getDescription(),
                mappedProfileRate.getDescription());

        assertEquals(profileRateDTO.getRate(),
                mappedProfileRate.getRate());
    }

    @Test
    void profileRateToProfileRateResponseDTO() {
        UUID id = UUID.randomUUID();
        UUID profileId = UUID.randomUUID();
        UUID evaluatorId = UUID.randomUUID();

        ProfileRate profileRate = ProfileRate.builder()
                .id(id)
                .profile(Profile.builder().id(profileId).build())
                .evaluatorId(evaluatorId)
                .title("Title")
                .description("Description")
                .rate(3)
                .build();

        ProfileRateResponseDTO profileRateResponseDTO = ProfileRateResponseDTO.builder()
                .id(id)
                .profileId(profileId)
                .evaluatorId(evaluatorId)
                .title("Title")
                .description("Description")
                .rate(3)
                .build();

        ProfileRateResponseDTO mappedProfileRate = profileRateMapper
                .profileRateToProfileRateResponseDTO(profileRate);

        assertThat(mappedProfileRate).isNotNull();

        assertEquals(profileRateResponseDTO.getId(),
                mappedProfileRate.getId());

        assertEquals(profileRateResponseDTO.getProfileId(),
                mappedProfileRate.getProfileId());

        assertEquals(profileRateResponseDTO.getTitle(),
                mappedProfileRate.getTitle());

        assertEquals(profileRateResponseDTO.getDescription(),
                mappedProfileRate.getDescription());

        assertEquals(profileRateResponseDTO.getRate(),
                mappedProfileRate.getRate());
    }
}