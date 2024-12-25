package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.property.PropertyDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyResponseDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyStatisticsMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PropertyRoomsMapper.class, PropertyDetailsMapper.class, PropertyDistanceToMapper.class, PropertyPriceRecordMapper.class, PropertyAreasMapper.class, PropertyStatisticsMapper.class})
public interface PropertyMapper {
    PropertyDTO propertyToPropertyDTO(Property property);

    PropertyResponseDTO propertyToPropertyResponseDTO(Property property);
}
