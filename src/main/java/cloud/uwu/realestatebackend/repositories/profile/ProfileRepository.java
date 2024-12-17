package cloud.uwu.realestatebackend.repositories.profile;

import cloud.uwu.realestatebackend.entities.profile.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID>{
    Page<Profile> getProfilesByCountry(String country, Pageable pageable);

    Page<Profile> getProfilesByState(String state, Pageable pageable);

    Page<Profile> getProfilesByCity(String city, Pageable pageable);
}
