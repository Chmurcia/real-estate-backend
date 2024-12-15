package cloud.uwu.realestatebackend.dtos.user.userRole;

import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.userEnum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleResponseDTO {

    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private UUID user;

    @Enumerated(EnumType.STRING)
    private Role role;

}
