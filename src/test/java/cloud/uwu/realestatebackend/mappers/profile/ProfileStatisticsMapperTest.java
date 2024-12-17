package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileStatistics;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileStatisticsMapperTest {
    
    ProfileStatisticsMapper profileStatisticsMapper = Mappers
            .getMapper(ProfileStatisticsMapper.class);
    
    @Test
    void profileStatisticsToProfileStatisticsDTO() {
        ProfileStatistics profileStatistics = ProfileStatistics.builder()
                .id(UUID.randomUUID())
                .profile(Profile.builder().id(UUID.randomUUID()).build())
                .totalRatings(192)
                .totalOffers(3)
                .totalViews(71623)
                .totalTrusts(17)
                .totalPosts(9)
                .totalActionPoints(4512)
                .build();

        ProfileStatisticsDTO profileStatisticsDTO = ProfileStatisticsDTO.builder()
                .totalRatings(192)
                .totalOffers(3)
                .totalViews(71623)
                .totalTrusts(17)
                .totalPosts(9)
                .totalActionPoints(4512)
                .build();

        ProfileStatisticsDTO mappedProfileStatistics = profileStatisticsMapper
                .profileStatisticsToProfileStatisticsDTO(profileStatistics);

        assertThat(mappedProfileStatistics).isNotNull();

        assertEquals(profileStatisticsDTO.getTotalRatings(),
                mappedProfileStatistics.getTotalRatings());

        assertEquals(profileStatisticsDTO.getTotalOffers(),
                mappedProfileStatistics.getTotalOffers());

        assertEquals(profileStatisticsDTO.getTotalViews(),
                mappedProfileStatistics.getTotalViews());

        assertEquals(profileStatisticsDTO.getTotalTrusts(),
                mappedProfileStatistics.getTotalTrusts());

        assertEquals(profileStatisticsDTO.getTotalPosts(),
                mappedProfileStatistics.getTotalPosts());

        assertEquals(profileStatisticsDTO.getTotalActionPoints(),
                mappedProfileStatistics.getTotalActionPoints());
    }

    @Test
    void profileStatisticsToProfileStatisticsResponseDTO() {

        UUID id = UUID.randomUUID();
        UUID profileId = UUID.randomUUID();

        ProfileStatistics profileStatistics = ProfileStatistics.builder()
                .id(id)
                .profile(Profile.builder().id(profileId).build())
                .totalRatings(192)
                .totalOffers(3)
                .totalViews(71623)
                .totalTrusts(17)
                .totalPosts(9)
                .totalActionPoints(4512)
                .build();

        ProfileStatisticsResponseDTO profileStatisticsResponseDTO = ProfileStatisticsResponseDTO
                .builder()
                .id(id)
                .profileId(profileId)
                .totalRatings(192)
                .totalOffers(3)
                .totalViews(71623)
                .totalTrusts(17)
                .totalPosts(9)
                .totalActionPoints(4512)
                .build();

        ProfileStatisticsResponseDTO mappedProfileStatisticsResponse = profileStatisticsMapper
                .profileStatisticsToProfileStatisticsResponseDTO(profileStatistics);

        assertThat(mappedProfileStatisticsResponse).isNotNull();

        assertEquals(profileStatisticsResponseDTO.getId(),
                mappedProfileStatisticsResponse.getId());

        assertEquals(profileStatisticsResponseDTO.getProfileId(),
                mappedProfileStatisticsResponse.getProfileId());

        assertEquals(profileStatisticsResponseDTO.getTotalRatings(),
                mappedProfileStatisticsResponse.getTotalRatings());

        assertEquals(profileStatisticsResponseDTO.getTotalOffers(),
                mappedProfileStatisticsResponse.getTotalOffers());

        assertEquals(profileStatisticsResponseDTO.getTotalViews(),
                mappedProfileStatisticsResponse.getTotalViews());

        assertEquals(profileStatisticsResponseDTO.getTotalTrusts(),
                mappedProfileStatisticsResponse.getTotalTrusts());

        assertEquals(profileStatisticsResponseDTO.getTotalPosts(),
                mappedProfileStatisticsResponse.getTotalPosts());

        assertEquals(profileStatisticsResponseDTO.getTotalActionPoints(),
                mappedProfileStatisticsResponse.getTotalActionPoints());
    }
}