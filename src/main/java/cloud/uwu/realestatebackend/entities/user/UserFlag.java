package cloud.uwu.realestatebackend.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFlags {

    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    private Boolean isVerified;

    private Boolean isBanned;

    private Boolean isMuted;
}
