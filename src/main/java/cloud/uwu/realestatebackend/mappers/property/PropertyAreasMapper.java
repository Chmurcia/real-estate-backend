package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyAreas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyAreasMapper {
    PropertyAreasDTO propertyAreasToPropertyAreasDTO(PropertyAreas propertyAreas);

    PropertyAreasResponseDTO propertyAreasToPropertyAreasResponseDTO(PropertyAreas propertyAreas);
}
