package cloud.uwu.realestatebackend.mappers.property.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodResponseDTO;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PropertyNeighbourhoodReviewMapper.class})
public interface PropertyNeighbourhoodMapper {
    PropertyNeighbourhoodDTO propertyNeighbourhoodToPropertyNeighbourhoodDTO(PropertyNeighbourhood propertyNeighbourhood);

    PropertyNeighbourhoodResponseDTO propertyNeighbourhoodToPropertyNeighbourhoodResponseDTO(PropertyNeighbourhood propertyNeighbourhood);
}
