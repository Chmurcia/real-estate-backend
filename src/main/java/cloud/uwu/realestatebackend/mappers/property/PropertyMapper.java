package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.property.PropertyDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertySmallResponseDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.mappers.property.location.PropertyLocationMapper;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyStatisticsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PropertyRoomsMapper.class, PropertyDetailsMapper.class, PropertyDistanceToMapper.class, PropertyPriceRecordMapper.class, PropertyAreasMapper.class, PropertyStatisticsMapper.class, PropertyLocationMapper.class})
public interface PropertyMapper {
    PropertyDTO propertyToPropertyDTO(Property property);

    @Mapping(source = "details", target = "detailsResponseDTO")
    @Mapping(source = "neighbourhood", target = "neighbourhoodResponseDTO")
    @Mapping(source = "distances", target = "distanceToResponseDTOS")
    @Mapping(source = "reviews", target = "reviewResponseDTOS")
    @Mapping(source = "propertyLocation", target = "propertyLocationResponseDTO")
    @Mapping(source = "statistics", target = "propertyStatisticsResponseDTO")
    @Mapping(source = "propertyMultimedia", target = "propertyMultimediaResponseDTO")
    PropertyResponseDTO propertyToPropertyResponseDTO(Property property);

    @Mapping(source = "statistics.counts.visits", target = "visits")
    @Mapping(source = "details.areas.totalArea", target = "totalArea")
    @Mapping(source = "details.rooms.totalRooms", target = "totalRooms")
    @Mapping(source = "propertyLocation", target = "propertyLocationResponseDTO")
    @Mapping(source = "propertyMultimedia", target = "propertyMultimediaResponseDTO")
    PropertySmallResponseDTO propertyToPropertySmallResponseDTO(Property property);
}
