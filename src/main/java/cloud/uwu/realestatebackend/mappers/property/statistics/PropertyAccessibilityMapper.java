package cloud.uwu.realestatebackend.mappers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAccessibility;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyAccessibilityMapper {
    PropertyAccessibilityDTO propertyAccessibilityToPropertyAccessibilityDTO(PropertyAccessibility propertyAccessibility);

    PropertyAccessibilityResponseDTO propertyAccessibilityToPropertyAccessibilityResponseDTO(PropertyAccessibility propertyAccessibility);
}
