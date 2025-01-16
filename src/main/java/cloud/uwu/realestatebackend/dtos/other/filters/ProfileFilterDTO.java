package cloud.uwu.realestatebackend.dtos.other.filters;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileFilterDTO {
    private String nickName;

    private String country;

    private String state;

    private String city;
}
