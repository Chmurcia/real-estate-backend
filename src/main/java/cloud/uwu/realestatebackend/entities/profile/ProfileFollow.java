package cloud.uwu.realestatebackend.entities.profile;

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
public class ProfileFollow {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false, referencedColumnName = "id")
    private Profile follower;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false, referencedColumnName = "id")
    private Profile following;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
