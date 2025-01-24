package cloud.uwu.realestatebackend.dtos.profile.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfilePatchDTO {
    @Size(min = 2, max = 100, message = "First name must contain between 2 and 100 characters")
    @JsonProperty("first_name")
    private String firstName;

    @Size(min = 2, max = 100, message = "Last name must contain between 2 and 100 characters")
    @JsonProperty("last_name")
    private String lastName;

    @Size(min=3, max = 50, message = "nick_name must contain between 3 and 50 characters")
    @JsonProperty("nick_name")
    private String nickName;

    @Size(min = 9, max = 9, message = "Phone number must be 9 characters long")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("avatar_url")
    private String avatarURL;

    @Size(min = 1, max = 255, message = "Bio must contain between 1 and 255 characters")
    private String bio;

    @Size(min = 2, max = 100, message = "Name of a country must contain between 2 and 100 characters")
    private String country;

    @Size(min = 2, max = 150, message = "Name of a state must contain between 2 and 150 characters")
    private String state;

    @Size(min = 2, max = 150, message = "Name of a city must contain between 2 and 150 characters")
    private String city;

    @Past(message = "birth_date must be in the past")
    @JsonProperty("birth_date")
    private LocalDateTime birthDate;

}
