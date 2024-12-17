package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileStatisticsMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileStatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileStatisticsServiceUnitTests {

    @Mock
    private ProfileStatisticsRepository profileStatisticsRepository;

    @Mock
    private ProfileStatisticsMapper profileStatisticsMapper;

    @InjectMocks
    private ProfileStatisticsService profileStatisticsService;

    @Test
    void getProfileStatisticsById() {
        ProfileStatistics profileStatistics = ProfileStatistics
                .builder().id(UUID.randomUUID()).build();
        ProfileStatisticsResponseDTO profileStatisticsResponseDTO = ProfileStatisticsResponseDTO
                .builder().id(profileStatistics.getId()).build();

        when(profileStatisticsRepository.findById(profileStatistics.getId()))
                .thenReturn(Optional.of(profileStatistics));
        when(profileStatisticsMapper
                .profileStatisticsToProfileStatisticsResponseDTO(profileStatistics))
                .thenReturn(profileStatisticsResponseDTO);

        ProfileStatisticsResponseDTO foundProfileStatistics = profileStatisticsService
                .getProfileStatisticsById(profileStatistics.getId());

        assertThat(foundProfileStatistics).isNotNull();
        assertEquals(profileStatistics.getId(), foundProfileStatistics.getId());
    }

    @Test
    void getProfileStatisticsByIdNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileStatisticsService
                        .getProfileStatisticsById(UUID.randomUUID()));
    }

    @Test
    void updateProfileStatistics() {
        ProfileStatistics profileStatistics = ProfileStatistics
                .builder()
                .id(UUID.randomUUID())
                .totalRatings(54911)
                .totalOffers(15)
                .totalViews(918010)
                .totalTrusts(6712)
                .totalPosts(76)
                .totalActionPoints(13493)
                .build();
        ProfileStatisticsDTO profileStatisticsDTO = ProfileStatisticsDTO
                .builder()
                .totalRatings(54911)
                .totalOffers(15)
                .totalViews(918010)
                .totalTrusts(6712)
                .totalPosts(76)
                .totalActionPoints(13493)
                .build();

        when(profileStatisticsRepository.findById(profileStatistics.getId()))
                .thenReturn(Optional.of(profileStatistics));

        profileStatisticsService.updateProfileStatistics(profileStatistics.getId(),
                profileStatisticsDTO);

        verify(profileStatisticsRepository).save(profileStatistics);
    }

    @Test
    void updateProfileStatisticsNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileStatisticsService
                        .updateProfileStatistics(UUID.randomUUID(),
                                ProfileStatisticsDTO.builder().build()));
    }

    @Test
    void patchProfileStatistics() {
        ProfileStatistics profileStatistics = ProfileStatistics
                .builder()
                .id(UUID.randomUUID())
                .totalRatings(54911)
                .totalOffers(15)
                .totalViews(918010)
                .totalTrusts(6712)
                .totalPosts(76)
                .totalActionPoints(13493)
                .build();
        ProfileStatisticsPatchDTO profileStatisticsPatchDTO = ProfileStatisticsPatchDTO
                .builder()
                .totalRatings(54911)
                .totalOffers(15)
                .totalViews(918010)
                .totalTrusts(6712)
                .totalPosts(76)
                .totalActionPoints(13493)
                .build();

        when(profileStatisticsRepository.findById(profileStatistics.getId()))
                .thenReturn(Optional.of(profileStatistics));

        profileStatisticsService.patchProfileStatistics(profileStatistics.getId(),
                profileStatisticsPatchDTO);

        verify(profileStatisticsRepository).save(profileStatistics);
    }

    @Test
    void patchProfileStatisticsNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileStatisticsService
                        .patchProfileStatistics(UUID.randomUUID(),
                                ProfileStatisticsPatchDTO.builder().build()));
    }

    @Test
    void deleteProfileStatistics() {
        UUID profileStatisticsId = UUID.randomUUID();

        when(profileStatisticsRepository.findById(profileStatisticsId))
                .thenReturn(Optional.of(ProfileStatistics.builder().build()));

        profileStatisticsService.deleteProfileStatistics(profileStatisticsId);

        verify(profileStatisticsRepository).deleteById(profileStatisticsId);
    }

    @Test
    void deleteProfileStatisticsNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileStatisticsService
                        .deleteProfileStatistics(UUID.randomUUID()));
    }
}