package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileAsk;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ProfileAskMapperTest {

    private final ProfileAskMapper profileAskMapper = Mappers
            .getMapper(ProfileAskMapper.class);

    @Test
    void profileAskToProfileAskDTO() {
        UUID profileId = UUID.randomUUID();

        ProfileAsk profileAsk = ProfileAsk.builder()
                .id(UUID.randomUUID())
                .profile(Profile.builder().id(profileId).build())
                .askTitle("AskTitle")
                .askDescription("AskDescription")
                .askAnswer("AskAnswer")
                .build();

        ProfileAskDTO profileAskDTO = ProfileAskDTO.builder()
                .askTitle("AskTitle")
                .askDescription("AskDescription")
                .askAnswer("AskAnswer")
                .build();

        ProfileAskDTO mappedProfileAsk = profileAskMapper
                .profileAskToProfileAskDTO(profileAsk);

        assertThat(mappedProfileAsk).isNotNull();

        assertEquals(profileAskDTO.getAskTitle(),
                mappedProfileAsk.getAskTitle());

        assertEquals(profileAskDTO.getAskDescription(),
                mappedProfileAsk.getAskDescription());

        assertEquals(profileAskDTO.getAskAnswer(),
                mappedProfileAsk.getAskAnswer());
    }

    @Test
    void profileAskToProfileAskResponseDTO() {
        UUID id = UUID.randomUUID();

        ProfileAsk profileAsk = ProfileAsk.builder()
                .id(id)
                .profile(Profile.builder().build())
                .askTitle("AskTitle")
                .askDescription("AskDescription")
                .askAnswer("AskAnswer")
                .build();

        ProfileAskResponseDTO profileAskResponseDTO = ProfileAskResponseDTO
                .builder()
                .id(id)
                .askTitle("AskTitle")
                .askDescription("AskDescription")
                .askAnswer("AskAnswer")
                .build();

        ProfileAskResponseDTO mappedProfileAskResponse = profileAskMapper
                .profileAskToProfileAskResponseDTO(profileAsk);

        assertThat(mappedProfileAskResponse).isNotNull();

        assertEquals(profileAskResponseDTO.getId(),
                mappedProfileAskResponse.getId());

        assertEquals(profileAskResponseDTO.getAskTitle(),
                mappedProfileAskResponse.getAskTitle());

        assertEquals(profileAskResponseDTO.getAskDescription(),
                mappedProfileAskResponse.getAskDescription());

        assertEquals(profileAskResponseDTO.getAskAnswer(),
                mappedProfileAskResponse.getAskAnswer());
    }
}