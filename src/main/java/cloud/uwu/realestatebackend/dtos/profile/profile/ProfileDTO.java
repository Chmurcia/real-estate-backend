package cloud.uwu.realestatebackend.dtos.profile.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {
    @NotBlank(message = "first_name must be defined")
    @Size(min = 2, max = 100, message = "first_name must contain between 2 and 100 characters")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "last_name must be defined")
    @Size(min = 2, max = 100, message = "last_name must contain between 2 and 100 characters")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "nick_name must be defined")
    @Size(min=3, max = 50, message = "nick_name must contain between 3 and 50 characters")
    @JsonProperty("nick_name")
    private String nickName;

    @NotBlank(message = "phone_number must be defined")
    @Size(min = 9, max = 9, message = "phone_number must be 9 characters long")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank(message = "avatar_url must be defined")
    @JsonProperty("avatar_url")
    private String avatarURL;

    @NotBlank(message = "Bio must be defined")
    @Size(min = 1, max = 500, message = "Bio must contain between 1 and 255 characters")
    private String bio;

    @NotBlank(message = "Country must be defined")
    @Size(min = 2, max = 100, message = "Name of a country must contain between 2 and 100 characters")
    private String country;

    @NotBlank(message = "State must be defined")
    @Size(min = 2, max = 150, message = "Name of a state must contain between 2 and 150 characters")
    private String state;

    @NotBlank(message = "City must be defined")
    @Size(min = 2, max = 150, message = "Name of a city must contain between 2 and 150 characters")
    private String city;

    @NotNull(message = "birth_date must be defined")
    @Past(message = "birth_date must be in the past")
    @JsonProperty("birth_date")
    private LocalDateTime birthDate;
}
