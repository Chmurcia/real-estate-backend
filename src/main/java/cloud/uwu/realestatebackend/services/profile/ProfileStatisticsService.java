package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileStatisticsMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileStatisticsService {
    private final ProfileStatisticsRepository profileStatisticsRepository;
    private final ProfileStatisticsMapper profileStatisticsMapper;

    public ProfileStatisticsResponseDTO getProfileStatisticsById(UUID id) {
        ProfileStatistics profileStatistics = getProfileStatistics(id);

        return profileStatisticsMapper
                .profileStatisticsToProfileStatisticsResponseDTO(profileStatistics);
    }

    public void updateProfileStatistics(UUID id, ProfileStatisticsDTO profileStatisticsDTO) {
        ProfileStatistics profileStatistics = getProfileStatistics(id);

        if (profileStatisticsDTO.getTotalRatings() != null) {
            profileStatistics.setTotalRatings(profileStatisticsDTO
                    .getTotalRatings());
        } else {
            throw new NullException("totalRatings is null");
        }

        if (profileStatisticsDTO.getTotalOffers() != null) {
            profileStatistics.setTotalOffers(profileStatisticsDTO
                    .getTotalOffers());
        } else {
            throw new NullException("totalOffers is null");
        }

        if (profileStatisticsDTO.getTotalViews() != null) {
            profileStatistics.setTotalViews(profileStatisticsDTO
                    .getTotalViews());
        } else {
            throw new NullException("totalViews is null");
        }

        if (profileStatisticsDTO.getTotalTrusts() != null) {
            profileStatistics.setTotalTrusts(profileStatisticsDTO
                    .getTotalTrusts());
        } else {
            throw new NullException("totalTrusts is null");
        }

        if (profileStatisticsDTO.getTotalPosts() != null) {
            profileStatistics.setTotalPosts(profileStatisticsDTO
                    .getTotalPosts());
        } else {
            throw new NullException("totalPosts is null");
        }

        if (profileStatisticsDTO.getTotalActionPoints() != null) {
            profileStatistics.setTotalActionPoints(profileStatisticsDTO
                    .getTotalActionPoints());
        } else {
            throw new NullException("totalActionPoints is null");
        }

        profileStatisticsRepository.save(profileStatistics);
    }

    public void patchProfileStatistics(UUID id,
                                       ProfileStatisticsPatchDTO profileStatisticsPatchDTO) {
        ProfileStatistics profileStatistics = getProfileStatistics(id);

        if (profileStatisticsPatchDTO.getTotalRatings() != null) {
            profileStatistics.setTotalRatings(profileStatisticsPatchDTO
                    .getTotalRatings());
        }

        if (profileStatisticsPatchDTO.getTotalOffers() != null) {
            profileStatistics.setTotalOffers(profileStatisticsPatchDTO
                    .getTotalOffers());
        }

        if (profileStatisticsPatchDTO.getTotalViews() != null) {
            profileStatistics.setTotalViews(profileStatisticsPatchDTO
                    .getTotalViews());
        }

        if (profileStatisticsPatchDTO.getTotalTrusts() != null) {
            profileStatistics.setTotalTrusts(profileStatisticsPatchDTO
                    .getTotalTrusts());
        }

        if (profileStatisticsPatchDTO.getTotalPosts() != null) {
            profileStatistics.setTotalPosts(profileStatisticsPatchDTO
                    .getTotalPosts());
        }

        if (profileStatisticsPatchDTO.getTotalActionPoints() != null) {
            profileStatistics.setTotalActionPoints(profileStatisticsPatchDTO
                    .getTotalActionPoints());
        }

        profileStatisticsRepository.save(profileStatistics);
    }

    public void deleteProfileStatistics(UUID id) {
        getProfileStatistics(id);

        profileStatisticsRepository.deleteById(id);
    }

    private ProfileStatistics getProfileStatistics(UUID id) {
        return profileStatisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProfileStatistics not found"));
    }
}
