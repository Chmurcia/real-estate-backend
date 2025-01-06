package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyPriceRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyPriceRecordMapper {
    @Mapping(source = "property.id", target = "propertyId")
    PropertyPriceRecordDTO propertyPriceRecordToPropertyPriceRecordDTO(PropertyPriceRecord propertyPriceRecord);

    PropertyPriceRecordResponseDTO propertyPriceRecordToPropertyPriceRecordResponseDTO(PropertyPriceRecord propertyPriceRecord);
}
