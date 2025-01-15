package cloud.uwu.realestatebackend.controllers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsResponseDTO;
import cloud.uwu.realestatebackend.services.property.PropertyRoomsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyRoomsController.URL)
@RequiredArgsConstructor
public class PropertyRoomsController {
    public static final String URL = PropertyURL + "/rooms";

    private final PropertyRoomsService propertyRoomsService;

    @GetMapping("/{roomsId}")
    public ResponseEntity<PropertyRoomsResponseDTO> getPropertyRoomsById(
            @PathVariable("roomsId") @NotNull UUID id) {
        PropertyRoomsResponseDTO foundRooms = propertyRoomsService
                .getPropertyRoomsById(id);

        return ResponseEntity.ok(foundRooms);
    }

    @PutMapping("/{roomsId}")
    public ResponseEntity<Object> updatePropertyRooms(
            @PathVariable("roomsId") @NotNull UUID id,
            @RequestBody @Validated PropertyRoomsDTO propertyRoomsDTO) {
        propertyRoomsService.updatePropertyRooms(id, propertyRoomsDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{roomsId}")
    public ResponseEntity<Object> patchPropertyRooms(
            @PathVariable("roomsId") @NotNull UUID id,
            @RequestBody @Validated PropertyRoomsPatchDTO propertyRoomsPatchDTO) {
        propertyRoomsService.patchPropertyRooms(id, propertyRoomsPatchDTO);

        return ResponseEntity.noContent().build();
    }
}
