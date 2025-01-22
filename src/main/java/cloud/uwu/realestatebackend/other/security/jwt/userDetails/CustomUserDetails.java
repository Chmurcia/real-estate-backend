package cloud.uwu.realestatebackend.other.security.jwt.userDetails;

import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.services.user.UserRoleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;
    private final UserRoleService userRoleService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = userRoleService.getUserRoleByUserId(user.getId()) != null
                ? userRoleService.getUserRoleByUserId(user.getId()).getRole().toString()
                : "USER";

        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId() != null ? user.getId().toString() : null;
    }
}
