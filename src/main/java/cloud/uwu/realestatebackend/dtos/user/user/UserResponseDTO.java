package cloud.uwu.realestatebackend.dtos.user;

import cloud.uwu.realestatebackend.entities.user.UserFlag;
import cloud.uwu.realestatebackend.entities.user.UserNotification;
import cloud.uwu.realestatebackend.entities.user.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private UUID id;

    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name = "user_flag_id", referencedColumnName = "id")
    private UserFlag userFlag;

    @OneToOne
    @JoinColumn(name = "user_notification_id", referencedColumnName = "id")
    private UserNotification userNotification;

    @OneToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "id")
    private UserRole userRole;
}
