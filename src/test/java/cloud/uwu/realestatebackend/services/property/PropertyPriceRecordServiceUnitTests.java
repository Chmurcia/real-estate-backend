package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordResponseDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyPriceRecord;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.PropertyPriceRecordMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyPriceRecordRepository;
import cloud.uwu.realestatebackend.repositories.property.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyPriceRecordServiceUnitTests {

    @Mock
    private PropertyPriceRecordRepository propertyPriceRecordRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private PropertyPriceRecordMapper propertyPriceRecordMapper;

    @InjectMocks
    private PropertyPriceRecordService propertyPriceRecordService;

    @Test
    void getPropertyPriceRecordsByPropertyId() {
        UUID id = UUID.randomUUID();

        List<PropertyPriceRecord> propertyPriceRecords = Arrays.asList(
                PropertyPriceRecord.builder().build(),
                PropertyPriceRecord.builder().build(),
                PropertyPriceRecord.builder().build(),
                PropertyPriceRecord.builder().build()
        );

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(Property.builder().build()));

        when(propertyPriceRecordRepository.getPropertyPriceRecordsByPropertyId(id))
                .thenReturn(propertyPriceRecords);

        when(propertyPriceRecordMapper
                .propertyPriceRecordToPropertyPriceRecordResponseDTO(any(PropertyPriceRecord.class)))
                .thenReturn(PropertyPriceRecordResponseDTO.builder().build());

        List<PropertyPriceRecordResponseDTO> foundPropertyPriceRecords =
                propertyPriceRecordService.getPropertyPriceRecordsByPropertyId(id);

        verify(propertyRepository).findById(id);
        assertEquals(foundPropertyPriceRecords.size(),
                propertyPriceRecords.size());
    }

    @Test
    void getPropertyPriceRecordsByPropertyId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyPriceRecordService
                        .getPropertyPriceRecordsByPropertyId(id));
    }

    @Test
    void getPropertyPriceRecordById() {
        UUID id = UUID.randomUUID();

        PropertyPriceRecord propertyPriceRecord = PropertyPriceRecord
                .builder()
                .price(190000.0)
                .build();

        PropertyPriceRecordResponseDTO propertyPriceRecordResponseDTO =
                PropertyPriceRecordResponseDTO.builder()
                        .price(190000.0)
                        .build();

        when(propertyPriceRecordRepository.findById(id))
                .thenReturn(Optional.of(propertyPriceRecord));

        when(propertyPriceRecordMapper
                .propertyPriceRecordToPropertyPriceRecordResponseDTO(propertyPriceRecord))
                .thenReturn(propertyPriceRecordResponseDTO);

        PropertyPriceRecordResponseDTO foundPropertyPriceRecord = propertyPriceRecordService
                .getPropertyPriceRecordById(id);

        verify(propertyPriceRecordRepository).findById(id);

        assertNotNull(foundPropertyPriceRecord);
    }

    @Test
    void getPropertyPriceRecordById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyPriceRecordService
                        .getPropertyPriceRecordById(id));
    }

    @Test
    void createPropertyPriceRecord() {
        PropertyPriceRecordDTO propertyPriceRecordDTO = PropertyPriceRecordDTO
                .builder()
                .propertyId(UUID.randomUUID())
                .price(190000.0)
                .build();

        PropertyPriceRecord propertyPriceRecord = PropertyPriceRecord
                .builder()
                .price(190000.0)
                .build();

        PropertyPriceRecordResponseDTO propertyPriceRecordResponseDTO =
                PropertyPriceRecordResponseDTO.builder()
                .price(190000.0)
                .build();

        when(propertyRepository
                .findById(propertyPriceRecordDTO.getPropertyId()))
                .thenReturn(Optional.of(Property.builder().build()));

        when(propertyPriceRecordRepository.save(any(PropertyPriceRecord.class)))
                .thenReturn(propertyPriceRecord);

        when(propertyPriceRecordMapper
                .propertyPriceRecordToPropertyPriceRecordResponseDTO(propertyPriceRecord))
                .thenReturn(propertyPriceRecordResponseDTO);

        PropertyPriceRecordResponseDTO createdPropertyPriceRecord =
                propertyPriceRecordService
                        .createPropertyPriceRecord(propertyPriceRecordDTO);

        verify(propertyRepository).findById(propertyPriceRecordDTO.getPropertyId());

        assertEquals(createdPropertyPriceRecord.getPrice(),
                propertyPriceRecordResponseDTO.getPrice());
    }

    @Test
    void createPropertyPriceRecord_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                propertyPriceRecordService
                        .createPropertyPriceRecord(PropertyPriceRecordDTO
                                .builder()
                                .propertyId(UUID.randomUUID())
                                .build()));
    }

    @Test
    void deletePropertyPriceRecord() {
        UUID id = UUID.randomUUID();

        when(propertyPriceRecordRepository.findById(id))
                .thenReturn(Optional.of(PropertyPriceRecord.builder().build()));

        propertyPriceRecordService.deletePropertyPriceRecord(id);

        verify(propertyPriceRecordRepository).deleteById(id);
    }

    @Test
    void deletePropertyPriceRecord_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyPriceRecordService
                        .deletePropertyPriceRecord(id));
    }
}