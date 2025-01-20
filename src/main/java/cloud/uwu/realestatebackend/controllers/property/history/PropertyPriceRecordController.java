package cloud.uwu.realestatebackend.controllers.property.history;

import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordResponseDTO;
import cloud.uwu.realestatebackend.services.property.PropertyPriceRecordService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyPriceRecordController.URL)
@RequiredArgsConstructor
public class PropertyPriceRecordController {
    public static final String URL = PropertyURL + "/price";

    private final PropertyPriceRecordService propertyPriceRecordService;

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<Page<PropertyPriceRecordResponseDTO>> getPropertyPriceRecordsByPropertyId(
            @PathVariable("propertyId") @NotNull UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<PropertyPriceRecordResponseDTO> foundPriceRecords = propertyPriceRecordService
                .getPropertyPriceRecordsByPropertyId(id, page, size);

        return ResponseEntity.ok(foundPriceRecords);
    }

    @GetMapping("/{priceRecordId}")
    public ResponseEntity<PropertyPriceRecordResponseDTO> getPropertyPriceRecordById(
            @PathVariable("priceRecordId") @NotNull UUID id) {
        PropertyPriceRecordResponseDTO foundPriceRecord = propertyPriceRecordService
                .getPropertyPriceRecordById(id);

        return ResponseEntity.ok(foundPriceRecord);
    }

    @PostMapping
    public ResponseEntity<PropertyPriceRecordResponseDTO> createPropertyPriceRecord(
            @RequestParam UUID propertyId,
            @RequestBody @Validated PropertyPriceRecordDTO propertyPriceRecordDTO) {
        PropertyPriceRecordResponseDTO createdPriceRecord = propertyPriceRecordService
                .createPropertyPriceRecord(propertyId, propertyPriceRecordDTO);

        URI url = URI.create(URL + "/" +createdPriceRecord.getId());
        return ResponseEntity.created(url).body(createdPriceRecord);
    }

    @DeleteMapping("/{priceRecordId}")
    public ResponseEntity<Object> deletePropertyPriceRecord(
            @PathVariable("priceRecordId") @NotNull UUID id) {
        propertyPriceRecordService.deletePropertyPriceRecord(id);

        return ResponseEntity.noContent().build();
    }
}
