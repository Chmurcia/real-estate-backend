package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileStatisticsMapper {
    ProfileStatisticsDTO profileStatisticsToProfileStatisticsDTO(ProfileStatistics profileStatistics);

    ProfileStatisticsResponseDTO profileStatisticsToProfileStatisticsResponseDTO(ProfileStatistics profileStatistics);
}
