package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyReview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyReviewMapper {
    PropertyReviewDTO propertyReviewToPropertyReviewDTO(PropertyReview propertyReview);

    PropertyReviewResponseDTO propertyReviewToPropertyReviewResponseDTO(PropertyReview propertyReview);
}
