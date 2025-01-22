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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyPriceRecordService {
    private final PropertyPriceRecordRepository propertyPriceRecordRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyPriceRecordMapper propertyPriceRecordMapper;

    public Page<PropertyPriceRecordResponseDTO> getPropertyPriceRecordsByPropertyId(
            UUID id, Integer page, Integer size) {
        getProperty(id);

        page = (page != null) ? Math.max(page, 0) : 0;
        size = (size != null && size > 0) ? size : 50;

        PageRequest pageable = PageRequest.of(page, size);

        return propertyPriceRecordRepository
                .getPropertyPriceRecordsByPropertyIdOrderByCreatedAtAsc(id, pageable)
                .map(propertyPriceRecordMapper::propertyPriceRecordToPropertyPriceRecordResponseDTO);

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
