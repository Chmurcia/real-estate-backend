package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileSettings;
import cloud.uwu.realestatebackend.entities.profile.ProfileStatistics;
import cloud.uwu.realestatebackend.entities.user.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileMapperTest {

    ProfileMapper profileMapper = Mappers
            .getMapper(ProfileMapper.class);

    @Test
    void profileToProfileDTO() {
        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .user(User.builder().id(UUID.randomUUID()).build())
                .profileStatistics(ProfileStatistics.builder().build())
                .profileSettings(ProfileSettings.builder().build())
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("818291082")
                .avatarURL("url://uwu.com")
                .bio("Blah blah blah blah")
                .country("Poland")
                .city("Warsaw")
                .birthDate(LocalDateTime.of(2001, 9, 4, 0, 0))
                .build();

        ProfileDTO profileDTO = ProfileDTO.builder()
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("818291082")
                .avatarURL("url://uwu.com")
                .bio("Blah blah blah blah")
                .country("Poland")
                .city("Warsaw")
                .birthDate(LocalDateTime.of(2001, 9, 4, 0, 0))
                .build();

        ProfileDTO mappedProfile = profileMapper
                .profileToProfileDTO(profile);

        assertThat(mappedProfile).isNotNull();

        assertEquals(profileDTO.getFirstName(), mappedProfile.getFirstName());
        assertEquals(profileDTO.getLastName(), mappedProfile.getLastName());
        assertEquals(profileDTO.getPhoneNumber(), mappedProfile.getPhoneNumber());
        assertEquals(profileDTO.getAvatarURL(), mappedProfile.getAvatarURL());
        assertEquals(profileDTO.getBio(), mappedProfile.getBio());
        assertEquals(profileDTO.getCountry(), mappedProfile.getCountry());
        assertEquals(profileDTO.getCity(), mappedProfile.getCity());
        assertEquals(profileDTO.getBirthDate(), mappedProfile.getBirthDate());
    }

    @Test
    void profileToProfileResponseDTO() {
        UUID id = UUID.randomUUID();

        Profile profile = Profile.builder()
                .id(id)
                .user(User.builder().id(UUID.randomUUID()).build())
                .profileStatistics(ProfileStatistics.builder().build())
                .profileSettings(ProfileSettings.builder().build())
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("818291082")
                .avatarURL("url://uwu.com")
                .bio("Blah blah blah blah")
                .country("Poland")
                .city("Warsaw")
                .birthDate(LocalDateTime.of(2001, 9, 4, 0, 0))
                .build();

        ProfileResponseDTO profileDTO = ProfileResponseDTO.builder()
                .id(id)
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("818291082")
                .avatarURL("url://uwu.com")
                .bio("Blah blah blah blah")
                .country("Poland")
                .city("Warsaw")
                .birthDate(LocalDateTime.of(2001, 9, 4, 0, 0))
                .build();

        ProfileResponseDTO mappedProfileResponse = profileMapper
                .profileToProfileResponseDTO(profile);

        assertThat(mappedProfileResponse).isNotNull();

        assertEquals(profileDTO.getId(), mappedProfileResponse.getId());
        assertEquals(profileDTO.getFirstName(), mappedProfileResponse.getFirstName());
        assertEquals(profileDTO.getLastName(), mappedProfileResponse.getLastName());
        assertEquals(profileDTO.getPhoneNumber(), mappedProfileResponse.getPhoneNumber());
        assertEquals(profileDTO.getAvatarURL(), mappedProfileResponse.getAvatarURL());
        assertEquals(profileDTO.getBio(), mappedProfileResponse.getBio());
        assertEquals(profileDTO.getCountry(), mappedProfileResponse.getCountry());
        assertEquals(profileDTO.getCity(), mappedProfileResponse.getCity());
        assertEquals(profileDTO.getBirthDate(), mappedProfileResponse.getBirthDate());
    }
}