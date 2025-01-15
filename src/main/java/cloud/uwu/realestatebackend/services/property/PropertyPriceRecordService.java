package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordResponseDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyPriceRecord;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.PropertyPriceRecordMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyPriceRecordRepository;
import cloud.uwu.realestatebackend.repositories.property.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyPriceRecordService {
    private final PropertyPriceRecordRepository propertyPriceRecordRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyPriceRecordMapper propertyPriceRecordMapper;

    public List<PropertyPriceRecordResponseDTO> getPropertyPriceRecordsByPropertyId(UUID id) {
        getProperty(id);

        return propertyPriceRecordRepository
                .getPropertyPriceRecordsByPropertyId(id).stream()
                .map(propertyPriceRecordMapper::propertyPriceRecordToPropertyPriceRecordResponseDTO)
                .toList();
    }

    public PropertyPriceRecordResponseDTO getPropertyPriceRecordById(UUID id) {
        return propertyPriceRecordMapper
                .propertyPriceRecordToPropertyPriceRecordResponseDTO(getPropertyPriceRecord(id));
    }

    public PropertyPriceRecordResponseDTO createPropertyPriceRecord(
            UUID propertyId, PropertyPriceRecordDTO propertyPriceRecordDTO) {
        Property property = getProperty(propertyId);

        PropertyPriceRecord propertyPriceRecord = PropertyPriceRecord.builder()
                .property(property)
                .price(propertyPriceRecordDTO.getPrice())
                .build();

        PropertyPriceRecord savedPropertyPriceRecord = propertyPriceRecordRepository
                .saveAndFlush(propertyPriceRecord);

        return propertyPriceRecordMapper
                .propertyPriceRecordToPropertyPriceRecordResponseDTO(savedPropertyPriceRecord);
    }

    public void deletePropertyPriceRecord(UUID id) {
        getPropertyPriceRecord(id);

        propertyPriceRecordRepository.deleteById(id);
    }

    //

    private PropertyPriceRecord getPropertyPriceRecord(UUID id) {
        return propertyPriceRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyPriceRecord not found"));
    }

    private Property getProperty(UUID id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Property not found"));
    }
}
