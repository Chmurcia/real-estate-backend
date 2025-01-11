package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileActivity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileActivityMapperTest {

    private final ProfileActivityMapper profileActivityMapper = Mappers
            .getMapper(ProfileActivityMapper.class);

    @Test
    void profileActivityToProfileActivityDTO() {
        UUID profileId = UUID.randomUUID();

        ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 6,
                11, 1, 0, 21, 0,
                ZoneId.of("Europe/Warsaw"));

        ProfileActivity profileActivity = ProfileActivity.builder()
                .id(UUID.randomUUID())
                .profile(Profile.builder().id(profileId).build())
                .activityTitle("Title")
                .activityDescription("Description")
                .activityDate(zonedDateTime)
                .build();

        ProfileActivityDTO profileActivityDTO = ProfileActivityDTO.builder()
                .activityTitle("Title")
                .activityDescription("Description")
                .activityDate(zonedDateTime)
                .build();

        ProfileActivityDTO mappedProfileActivity = profileActivityMapper
                .profileActivityToProfileActivityDTO(profileActivity);

        assertThat(mappedProfileActivity).isNotNull();

        assertEquals(profileActivityDTO.getActivityTitle(),
                mappedProfileActivity.getActivityTitle());

        assertEquals(profileActivityDTO.getActivityDescription(),
                mappedProfileActivity.getActivityDescription());

        assertEquals(profileActivityDTO.getActivityDate(),
                mappedProfileActivity.getActivityDate());
    }

    @Test
    void profileActivityToProfileActivityResponseDTO() {
        UUID id = UUID.randomUUID();

        ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 6,
                11, 1, 0, 21, 0,
                ZoneId.of("Europe/Warsaw"));

        ProfileActivity profileActivity = ProfileActivity.builder()
                .id(id)
                .profile(Profile.builder().build())
                .activityTitle("Title")
                .activityDescription("Description")
                .activityDate(zonedDateTime)
                .build();

        ProfileActivityResponseDTO profileActivityResponseDTO = ProfileActivityResponseDTO
                .builder()
                .id(id)
                .activityTitle("Title")
                .activityDescription("Description")
                .activityDate(zonedDateTime)
                .build();

        ProfileActivityResponseDTO mappedProfileActivityResponse = profileActivityMapper
                .profileActivityToProfileActivityResponseDTO(profileActivity);

        assertThat(mappedProfileActivityResponse).isNotNull();

        assertEquals(profileActivityResponseDTO.getId(),
                mappedProfileActivityResponse.getId());

        assertEquals(profileActivityResponseDTO.getActivityTitle(),
                mappedProfileActivityResponse.getActivityTitle());

        assertEquals(profileActivityResponseDTO.getActivityDescription(),
                mappedProfileActivityResponse.getActivityDescription());

        assertEquals(profileActivityResponseDTO.getActivityDate(),
                mappedProfileActivityResponse.getActivityDate());
    }

}