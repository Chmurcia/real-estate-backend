package cloud.uwu.realestatebackend.mappers.property.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewResponseDTO;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhoodReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyNeighbourhoodReviewMapper {
    @Mapping(source = "neighbourhood.id", target = "neighbourhoodId")
    PropertyNeighbourhoodReviewDTO propertyNeighbourhoodReviewToPropertyNeighbourhoodReviewDTO(PropertyNeighbourhoodReview propertyNeighbourhoodReview);

    PropertyNeighbourhoodReviewResponseDTO propertyNeighbourhoodReviewToPropertyNeighbourhoodReviewResponseDTO(PropertyNeighbourhoodReview propertyNeighbourhoodReview);
}
