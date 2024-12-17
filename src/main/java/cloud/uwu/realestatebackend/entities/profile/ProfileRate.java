package cloud.uwu.realestatebackend.entities.profile;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRate {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false, referencedColumnName = "id")
    private Profile profile;

    private UUID evaluatorId;

    private String title;

    private String description;

    private Integer rate;
}
