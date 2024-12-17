package cloud.uwu.realestatebackend.entities.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFlag {

    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @OneToOne(mappedBy = "userFlag")
    private User user;

    private Boolean isVerified;

    private Boolean isBanned;

    private Boolean isMuted;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @CreationTimestamp
    private ZonedDateTime updatedAt;
}
