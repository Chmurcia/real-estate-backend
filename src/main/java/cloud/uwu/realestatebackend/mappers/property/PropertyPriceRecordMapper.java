package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyPriceRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyPriceRecordMapper {
    PropertyPriceRecordDTO propertyPriceRecordToPropertyPriceRecordDTO(PropertyPriceRecord propertyPriceRecord);

    PropertyPriceRecordResponseDTO propertyPriceRecordToPropertyPriceRecordResponseDTO(PropertyPriceRecord propertyPriceRecord);
}
