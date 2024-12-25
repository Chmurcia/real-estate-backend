package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyRooms;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyRoomsMapper {
    PropertyRoomsDTO propertyRoomsToPropertyRoomsDTO(PropertyRooms propertyRooms);

    PropertyReviewDTO propertyRoomsToPropertyReviewDTO(PropertyRooms propertyRooms);
}
